# Recipe created by recipetool
# recipetool create -o btrfs-progs-fscrypt-static_6.5.1-231201.bb https://distro.ibiblio.org/easyos/source/alphabetical/b/btrfs-progs-fscrypt-6.5.1-231201.tar.gz

LICENSE = "GPL-2.0-only & LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=fcb02dc552a041dee27e4b85c7396067 \
                    file://libbtrfsutil/COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/b/btrfs-progs-fscrypt-${PV}.tar.gz \
           file://0001-Add-a-possibility-to-specify-where-python-modules-ar.patch"

SRC_URI[md5sum] = "e08f4be41e988dd32af6c7ef443eff91"
SRC_URI[sha256sum] = "f84f651aad6ff313523f23a204d021b76a8b3fd93c62b0dc10a4f1541c91539b"

S = "${WORKDIR}/btrfs-progs-fscrypt-${PV}"

DEPENDS = "zstd zlib util-linux-libuuid util-linux"

SUMMARY = "Checksumming Copy on Write Filesystem utilities"
HOMEPAGE = "https://btrfs.wiki.kernel.org"
SECTION = "base"

PACKAGECONFIG ??= " programs crypto-builtin zstd "

PACKAGECONFIG[manpages] = "--enable-documentation, --disable-documentation, python3-sphinx-native python3-sphinx-rtd-theme-native"
PACKAGECONFIG[programs] = "--enable-programs,--disable-programs"
PACKAGECONFIG[convert] = "--enable-convert --with-convert=ext2,--disable-convert --without-convert,e2fsprogs"
PACKAGECONFIG[zoned] = "--enable-zoned,--disable-zoned"
PACKAGECONFIG[python] = "--enable-python,--disable-python,python3-setuptools-native"
PACKAGECONFIG[lzo] = "--enable-lzo,--disable-lzo,lzo"
PACKAGECONFIG[zstd] = "--enable-zstd,--disable-zstd,zstd"
PACKAGECONFIG[udev] = "--enable-libudev,--disable-libudev,udev"

# Pick only one crypto provider
PACKAGECONFIG[crypto-builtin] = "--with-crypto=builtin"
PACKAGECONFIG[crypto-libgcrypt] = "--with-crypto=libgcrypt,,libgcrypt"
PACKAGECONFIG[crypto-libsodium] = "--with-crypto=libsodium,,libsodium"
PACKAGECONFIG[crypto-libkcapi] = "--with-crypto=libkcapi,,libkcapi"

inherit autotools-brokensep pkgconfig manpages

CLEANBROKEN = "1"

#need --enable-experimental for fscrypt support
EXTRA_OECONF = "--enable-largefile --enable-static --enable-experimental"

EXTRA_OECONF:append:libc-musl = " --disable-backtrace "
EXTRA_PYTHON_CFLAGS = "${DEBUG_PREFIX_MAP}"
EXTRA_PYTHON_CFLAGS:class-native = ""
EXTRA_PYTHON_LDFLAGS = "${LDFLAGS}"
EXTRA_OEMAKE = "V=1 'EXTRA_PYTHON_CFLAGS=${EXTRA_PYTHON_CFLAGS}' 'EXTRA_PYTHON_LDFLAGS=${EXTRA_PYTHON_LDFLAGS}'"

do_configure:prepend() {
	# Upstream doesn't ship this and autoreconf won't install it as automake isn't used.
	mkdir -p ${S}/config
	cp -f $(automake --print-libdir)/install-sh ${S}/config/
}

do_compile() {
 oe_runmake static
 oe_runmake btrfs.static
}

do_install() {
    install -d ${D}${bindir}
    install ${B}/btrfs-corrupt-block.static ${D}${bindir}/
    install ${B}/btrfs-find-root.static ${D}${bindir}/
    install ${B}/btrfs-image.static ${D}${bindir}/
    install ${B}/btrfs-map-logical.static ${D}${bindir}/
    install ${B}/btrfs-select-super.static ${D}${bindir}/
    install ${B}/btrfs.static ${D}${bindir}/
    install ${B}/btrfstune.static ${D}${bindir}/
    install ${B}/mkfs.btrfs.static ${D}${bindir}/
}

RDEPENDS:${PN} = "libgcc"
