#BK 20230916 original recipe modified to build statically-linked 'openssl'.

SUMMARY = "Secure Socket Layer"
DESCRIPTION = "Secure Socket Layer (SSL) binary and related cryptographic tools."
HOMEPAGE = "http://www.openssl.org/"
BUGTRACKER = "http://www.openssl.org/news/vulnerabilities.html"
SECTION = "libs/network"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=c75985e733726beaba57bc5253e96d04"

SRC_URI = "http://www.openssl.org/source/openssl-${PV}.tar.gz \
           file://run-ptest \
           file://0001-buildinfo-strip-sysroot-and-debug-prefix-map-from-co.patch \
           file://afalg.patch \
           file://0001-Configure-do-not-tweak-mips-cflags.patch \
           "

SRC_URI:append:class-nativesdk = " \
           file://environment.d-openssl.sh \
           "

SRC_URI[sha256sum] = "1761d4f5b13a1028b9b6f3d4b8e17feb0cedc9370f6afe61d7193d2cdce83323"

inherit lib_package multilib_header multilib_script ptest perlnative
MULTILIB_SCRIPTS = "${PN}-bin:${bindir}/c_rehash"

PACKAGECONFIG ?= ""
PACKAGECONFIG:class-native = ""
PACKAGECONFIG:class-nativesdk = ""

PACKAGECONFIG[cryptodev-linux] = "enable-devcryptoeng,disable-devcryptoeng,cryptodev-linux,,cryptodev-module"
PACKAGECONFIG[no-tls1] = "no-tls1"
PACKAGECONFIG[no-tls1_1] = "no-tls1_1"

S = "${WORKDIR}/openssl-${PV}"
B = "${WORKDIR}/build"
do_configure[cleandirs] = "${B}"

#| ./libcrypto.so: undefined reference to `getcontext'
#| ./libcrypto.so: undefined reference to `setcontext'
#| ./libcrypto.so: undefined reference to `makecontext'
EXTRA_OECONF:append:libc-musl = " no-async"
EXTRA_OECONF:append:libc-musl:powerpc64 = " no-asm"

#BK
EXTRA_OECONF:append = " no-pic no-shared no-threads"

LDFLAGS += "-static"

# adding devrandom prevents openssl from using getrandom() which is not available on older glibc versions
# (native versions can be built with newer glibc, but then relocated onto a system with older glibc)
EXTRA_OECONF:class-native = "--with-rand-seed=os,devrandom"
EXTRA_OECONF:class-nativesdk = "--with-rand-seed=os,devrandom"

# Relying on hardcoded built-in paths causes openssl-native to not be relocateable from sstate.
CFLAGS:append:class-native = " -DOPENSSLDIR=/not/builtin -DENGINESDIR=/not/builtin"
CFLAGS:append:class-nativesdk = " -DOPENSSLDIR=/not/builtin -DENGINESDIR=/not/builtin"

# This allows disabling deprecated or undesirable crypto algorithms.
# The default is to trust upstream choices.
DEPRECATED_CRYPTO_FLAGS ?= ""

do_configure () {
	# When we upgrade glibc but not uninative we see obtuse failures in openssl. Make
	# the issue really clear that perl isn't functional due to symbol mismatch issues.
	cat <<- EOF > ${WORKDIR}/perltest
	#!/usr/bin/env perl
	use POSIX;
	EOF
	chmod a+x ${WORKDIR}/perltest
	${WORKDIR}/perltest

	os=${HOST_OS}
	case $os in
	linux-gnueabi |\
	linux-gnuspe |\
	linux-musleabi |\
	linux-muslspe |\
	linux-musl )
		os=linux
		;;
	*)
		;;
	esac
	target="$os-${HOST_ARCH}"
	case $target in
	linux-arc | linux-microblaze*)
		target=linux-latomic
		;;
	linux-arm*)
		target=linux-armv4
		;;
	linux-aarch64*)
		target=linux-aarch64
		;;
	linux-i?86 | linux-viac3)
		target=linux-x86
		;;
	linux-gnux32-x86_64 | linux-muslx32-x86_64 )
		target=linux-x32
		;;
	linux-gnu64-x86_64)
		target=linux-x86_64
		;;
	linux-mips | linux-mipsel)
		# specifying TARGET_CC_ARCH prevents openssl from (incorrectly) adding target architecture flags
		target="linux-mips32 ${TARGET_CC_ARCH}"
		;;
	linux-gnun32-mips*)
		target=linux-mips64
		;;
	linux-*-mips64 | linux-mips64 | linux-*-mips64el | linux-mips64el)
		target=linux64-mips64
		;;
	linux-nios2* | linux-sh3 | linux-sh4 | linux-arc*)
		target=linux-generic32
		;;
	linux-powerpc)
		target=linux-ppc
		;;
	linux-powerpc64)
		target=linux-ppc64
		;;
	linux-powerpc64le)
		target=linux-ppc64le
		;;
	linux-riscv32)
		target=linux-generic32
		;;
	linux-riscv64)
		target=linux-generic64
		;;
	linux-sparc | linux-supersparc)
		target=linux-sparcv9
		;;
	mingw32-x86_64)
		target=mingw64
		;;
	esac

	useprefix=${prefix}
	if [ "x$useprefix" = "x" ]; then
		useprefix=/
	fi
	# WARNING: do not set compiler/linker flags (-I/-D etc.) in EXTRA_OECONF, as they will fully replace the
	# environment variables set by bitbake. Adjust the environment variables instead.
	PERLEXTERNAL="$(realpath ${S}/external/perl/Text-Template-*/lib)"
	test -d "$PERLEXTERNAL" || bberror "PERLEXTERNAL '$PERLEXTERNAL' not found!"
	HASHBANGPERL="/usr/bin/env perl" PERL=perl PERL5LIB="$PERLEXTERNAL" \
	perl ${S}/Configure ${EXTRA_OECONF} ${PACKAGECONFIG_CONFARGS} ${DEPRECATED_CRYPTO_FLAGS} --prefix=$useprefix --openssldir=${libdir}/ssl-3 --libdir=${libdir} $target
	perl ${B}/configdata.pm --dump
}

do_compile:prepend() {
 sed -i -e 's%"--disable-static", %%' ${B}/Makefile
 sed -i -e 's%--disable-static%%' ${B}/Makefile
}

do_install () {

 install -d ${D}${bindir}
 install -m755 ${B}/apps/openssl ${D}${bindir}
 install -d ${D}${sysconfdir}/ssl/certs
 install -d ${D}${sysconfdir}/ssl/private
 install -m644 ${S}/apps/openssl.cnf ${D}${sysconfdir}/ssl
 install -d ${D}${libdir}/ssl-3

	# Although absolute symlinks would be OK for the target, they become
	# invalid if native or nativesdk are relocated from sstate.
	ln -sf ${@oe.path.relative('${libdir}/ssl-3', '${sysconfdir}/ssl/certs')} ${D}${libdir}/ssl-3/certs
	ln -sf ${@oe.path.relative('${libdir}/ssl-3', '${sysconfdir}/ssl/private')} ${D}${libdir}/ssl-3/private
	ln -sf ${@oe.path.relative('${libdir}/ssl-3', '${sysconfdir}/ssl/openssl.cnf')} ${D}${libdir}/ssl-3/openssl.cnf
}


PTEST_BUILD_HOST_FILES += "configdata.pm"
PTEST_BUILD_HOST_PATTERN = "perl_version ="
do_install_ptest () {
 true
}

CVE_PRODUCT = "openssl:openssl"

CVE_VERSION_SUFFIX = "alphabetical"

# Only affects OpenSSL >= 1.1.1 in combination with Apache < 2.4.37
# Apache in meta-webserver is already recent enough
CVE_CHECK_IGNORE += "CVE-2019-0190"
