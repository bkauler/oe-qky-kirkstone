# Recipe created by recipetool

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://lib/uuid/COPYING;md5=58dcd8452651fc8b07d1f65ce07ca8af"

SRC_URI = "https://downloads.sourceforge.net/project/e2fsprogs/e2fsprogs/v${PV}/e2fsprogs-${PV}.tar.gz \
           file://remove.ldconfig.call.patch \
           file://ptest.patch \
           file://mkdir_p.patch \
           file://0001-misc-create_inode.c-set-dir-s-mode-correctly.patch \
           file://0001-configure.ac-correct-AM_GNU_GETTEXT.patch \
           file://0001-intl-do-not-try-to-use-gettext-defines-that-no-longe.patch \
           file://CVE-2019-5188.patch \
           file://0001-e2fsck-don-t-try-to-rehash-a-deleted-directory.patch \
           file://e2fsck-fix-use-after-free-in-calculate_tree.patch \
"

SRC_URI[md5sum] = "2c2f9d4bcd0be54b3b3b8d5feec7b0ff"
SRC_URI[sha256sum] = "e69c69839cf80cb55afa18b9a99ed8f2e559db0313e3d15ac5497ed7e1a34c4b"

DEPENDS = "file util-linux attr"

# remove:  gettext
inherit perlnative texinfo autotools pkgconfig

S = "${WORKDIR}/e2fsprogs-${PV}"
CFLAGS += " -static"
LDFLAGS += " -static"

EXTRA_OECONF = "--disable-fuse2fs --disable-nls --disable-tdb --disable-mmp --disable-uuidd \
   --disable-defrag --disable-imager --enable-libblkid --enable-libuuid --disable-bmap-stats \
   --disable-debugfs --disable-backtrace --with-crond-dir=no"

# warning: -s (strip) does not work for aarch64 target...
do_install () {
 install -d ${D}/usr/bin
 install -d ${D}/usr/sbin
 install -m 755 ${B}/e2fsck/e2fsck ${D}/usr/sbin
 install -m 755 ${B}/misc/badblocks ${D}/usr/sbin
 install -m 755 ${B}/misc/blkid ${D}/usr/sbin
 install -m 755 ${B}/misc/chattr ${D}/usr/bin
 install -m 755 ${B}/misc/dumpe2fs ${D}/usr/sbin
 install -m 755 ${B}/misc/e2freefrag ${D}/usr/sbin
 install -m 755 ${B}/misc/e4crypt ${D}/usr/sbin
 install -m 755 ${B}/misc/lsattr ${D}/usr/bin
 install -m 755 ${B}/misc/mke2fs ${D}/usr/sbin
 install -m 755 ${B}/misc/tune2fs ${D}/usr/sbin
 install -m 755 ${B}/resize/resize2fs ${D}/usr/bin
}

HOMEPAGE = "http://e2fsprogs.sourceforge.net/"
SUMMARY = "ext2/3/4 filesystem utilities"
