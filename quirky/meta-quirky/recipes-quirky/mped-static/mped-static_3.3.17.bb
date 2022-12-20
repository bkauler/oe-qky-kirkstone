DESCRIPTION = "Lightweight text editor for programmers."
HOMEPAGE = "http://triptico.com/software/mp.html"
SECTION = "console/utils"
PRIORITY = "optional"
LICENSE = "GPL-2.0-only"

LIC_FILES_CHKSUM = "file://COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"

#20210828 ncurses only has shared libs, so have built 'ncurses-static'
DEPENDS = "ncurses-static"

SRC_URI = "http://distro.ibiblio.org/easyos/source/oe/pyro/mp-${PV}.tar.gz \
	file://mp.desktop \
	file://mprc \
	file://mp.xpm \
	file://config.ldflags \
	file://config.h \
	file://config.cflags \
	file://Makefile"

SRC_URI[md5sum] = "e6366f1351211f6dabc22bb3f5ec6f99"
SRC_URI[sha256sum] = "daaebaf7eacf7919c7383581cdd2f0450d359434866b6d18d686d0edb589c952"

S = "${WORKDIR}/mp-${PV}"

#20200819 bump to r1...
#PR = "r1"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

inherit base

do_configure () {
    cp -f ../config.ldflags ${S}/
    cp -f ../config.h ${S}/
    cp -f ../config.cflags ${S}/
    cp -f ../Makefile ${S}/
    #20200819 L16L: fix for utf-8 menus... NO, have edited Makefile
    #sed -i -e 's%iso\-8859\-1%utf-8%' ${S}/Makefile
}

do_compile() {
	oe_runmake
}

do_install() {
	install -d ${D}/usr/bin
	install -d ${D}/etc
	#oe_runmake install DESTDIR=${D} PREFIX=/usr
	install -d ${D}/usr/share/applications
	install -d ${D}/usr/share/pixmaps
	install -m755 mp ${D}/usr/bin
	#install -m755 mp_doccer/mp_doccer ${D}/usr/bin
	install -m644 ../mp.desktop ${D}/usr/share/applications
	install -m644 ../mp.xpm ${D}/usr/share/pixmaps
	install -m644 ../mprc ${D}/etc
}

TARGET_CC_ARCH += "${LDFLAGS}"

#FILES:${PN} += "/usr/share/mp*/mp_*"

SUMMARY = ""
