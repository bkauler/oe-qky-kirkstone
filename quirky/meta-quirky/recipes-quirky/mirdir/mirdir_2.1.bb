# Recipe created by recipetool
# recipetool create -o mirdir_2.1.bb https://downloads.sourceforge.net/project/mirdir/mirdir/2.1/mirdir-2.1-Unix.tar.gz

#PR = "r1"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=90329875672f5743cdde9fcf86b19218"

SRC_URI = "https://downloads.sourceforge.net/project/mirdir/mirdir/${PV}/mirdir-${PV}-Unix.tar.gz \
           file://mirdir-fix-aarch64.patch"
SRC_URI[md5sum] = "56afe2aae7983176fd804c264740d6a2"
SRC_URI[sha256sum] = "8f0d1e6dc67698eee1fc6e0ef813913be8f8321aa60545d86d87f4c86abd9284"

S = "${WORKDIR}/${BPN}-${PV}-UNIX"

inherit autotools-brokensep

EXTRA_OECONF = ""

do_install() {
    install -d ${D}/usr/bin
    install ${B}/bin/mirdir ${D}/usr/bin
}

# 170627 pi2 build, strip fails...
do_compile:prepend() {
    sed -i -e 's%strip $(EXE)%%' ${B}/Makefile
}

HOMEPAGE = "https://sourceforge.net/p/mirdir/wiki/Home/"
SUMMARY = "Compare two directory trees copy differences."
