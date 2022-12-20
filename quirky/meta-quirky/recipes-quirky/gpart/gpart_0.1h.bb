# Recipe created by recipetool
# recipetool create -o gpart_0.1h.bb http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/gpart-0.1h.tar.bz2

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c56db0143f4f80c369ee3af7425af6e"

SRC_URI = "http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/gpart-${PV}.tar.bz2 \
           file://errno.patch \
           file://largefile.patch \
           file://ntfs.patch \
           file://vfat.patch"
SRC_URI[md5sum] = "8c62420f489c2b741f034c14812a7b7b"
SRC_URI[sha256sum] = "c91f298ef8d278ddf76bb820d8d97fdb7b92487eaf718a6d8ddc955896d635e3"

# BK 170611 Makefile only. does not create a separate $B (build) dir.

do_configure () {
    sed -e 's%/usr/local%/usr%' inst.defs
    echo 'MAKEDEP = $(CC) -M
INSTALL = install
RM      = rm -f
prg     = gpart
version = 0.1h' > make.defs
}

#20200921 gcc9 requires 3 parameters for open() whereas before only 2...
#error msg:
#| In function 'open',
#|     inlined from 'make_mbr_backup' at gpart.c:1224:12,
#|     inlined from 'main' at gpart.c:1829:5:
#| /mnt/sda1/nvme/oe/oe-quirky/build-amd64/tmp/work/core2-64-poky-linux/gpart/0.1h-r0/recipe-sysroot/usr/include/bits/fcntl2.h:50:4: error: call to '__open_missing_mode' declared with attribute error: open with O_CREAT or O_TMPFILE in second argument needs 3 arguments
do_compile:prepend() {
 sed -i -e 's%O_CREAT)%O_CREAT,0755)%' ${S}/src/gpart.c
}

do_compile () {
    oe_runmake
}

do_install () {
    install -d ${D}/usr/sbin
    install -m755 src/gpart ${D}/usr/sbin
}

HOMEPAGE = "http://home.pages.de/~michab/gpart/"
SUMMARY = "Partition table recovery tool"
