SUMMARY = "Library for getting/setting POSIX.1e capabilities"
HOMEPAGE = "http://sites.google.com/site/fullycapable/"

# no specific GPL version required
LICENSE = "BSD | GPL-2.0-only"
LIC_FILES_CHKSUM = "file://License;md5=3f84fd6f29d453a56514cb7e4ead25f1"

DEPENDS = "hostperl-runtime-native gperf-native"

SRC_URI = "https://git.kernel.org/pub/scm/libs/libcap/libcap.git/snapshot/libcap-${PV}.tar.gz \
           file://0001-ensure-the-XATTR_NAME_CAPS-is-defined-when-it-is-use.patch \
           file://0002-tests-do-not-run-target-executables.patch \
           file://0001-tests-do-not-statically-link-a-test.patch \
           file://cap_sys_mount.patch \
"

SRC_URI[md5sum] = "706e8d5b2c81bb503e6aba6f87113b88"
SRC_URI[sha256sum] = "bf0496f7af816f20ccbad6a3e5e9714aa5da658fcb0804209137e4920417e33f"

# BK
S = "${WORKDIR}/libcap-${PV}"

inherit lib_package

# do NOT pass target cflags to host compilations
#
do_configure() {
	# libcap uses := for compilers, fortunately, it gives us a hint
	# on what should be replaced with ?=
	sed -e 's,:=,?=,g' -i Make.Rules
	sed -e 's,^BUILD_CFLAGS ?= ,BUILD_CFLAGS := $(BUILD_CFLAGS) ,' -i Make.Rules
}

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'pam', d)}"
PACKAGECONFIG_class-native ??= ""

PACKAGECONFIG[pam] = "PAM_CAP=yes,PAM_CAP=no,libpam"

# BK
EXTRA_OEMAKE = " \
  INDENT=  \
  lib='${baselib}' \
  RAISE_SETFCAP=no \
  DYNAMIC=no \
  BUILD_GPERF=yes \
"

EXTRA_OEMAKE:append:class-target = " SYSTEM_HEADERS=${STAGING_INCDIR}"

# these are present in the libcap defaults, so include in our CFLAGS too
CFLAGS += "-D_LARGEFILE64_SOURCE -D_FILE_OFFSET_BITS=64"

do_compile() {
	oe_runmake ${PACKAGECONFIG_CONFARGS}
}

do_install() {
	oe_runmake install \
		${PACKAGECONFIG_CONFARGS} \
		DESTDIR="${D}" \
		prefix="${prefix}" \
		SBINDIR="${sbindir}"
}

do_install:append() {
	# Move the library to base_libdir
	install -d ${D}${base_libdir}
	if [ ! ${D}${libdir} -ef ${D}${base_libdir} ]; then
		mv ${D}${libdir}/libcap* ${D}${base_libdir}
                if [ -d ${D}${libdir}/security ]; then
			mv ${D}${libdir}/security ${D}${base_libdir}
		fi
	fi
}

FILES:${PN}-dev += "${base_libdir}/*.so"

# pam files
FILES:${PN} += "${base_libdir}/security/*.so"

BBCLASSEXTEND = "native nativesdk"
