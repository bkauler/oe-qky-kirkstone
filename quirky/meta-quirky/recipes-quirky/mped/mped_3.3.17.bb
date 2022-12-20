SUMMARY = "Lightweight CLI text editor for programmers"
HOMEPAGE = "http://triptico.com/software/mp.html"
SECTION = "console/utils"
PRIORITY = "optional"

# 180727 BK note, the menu does not work with version 3.2.13, though, in the
#        past we have had opposite situation. don't know the cause.

#20200919 refs:
#https://bkhome.org/news/202008/minimum-profit-3317-now-has-utf-8-support.html
#https://bkhome.org/news/202008/mp-text-editor-utf-8-menu-fix.html


LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"

SRC_URI = "http://distro.ibiblio.org/quirky/quirky6/sources/alphabetical/m/mp-${PV}.tar.gz \
	file://mp.desktop \
	file://mprc \
	file://mp.xpm \
	file://config.cflags \
	file://config.h \
	file://config.ldflags"
SRC_URI[md5sum] = "e6366f1351211f6dabc22bb3f5ec6f99"
SRC_URI[sha256sum] = "daaebaf7eacf7919c7383581cdd2f0450d359434866b6d18d686d0edb589c952"

S = "${WORKDIR}/mp-${PV}"

#PR = "r0"
# 20210121 PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"

DEPENDS = "ncurses libpcre gettext"

inherit base

do_configure () {
    ./config.sh --without-gtk --with-included-regex --prefix=/usr --with-gettext --with-i18n
    #config.sh will have created these, but overwrite with what i exactly want...
    #(especially the -lncursesw)...
    cp -f ../config.cflags ./
    cp -f ../config.h ./
    cp -f ../config.ldflags ./
    #this is so menus will display utf-8 characters...
    sed -i -e  's%iso-8859-1%utf-8%' Makefile
    #remove these...
    sed -i -e 's%^CC=%#CC=%' Makefile
    sed -i -e 's%^CFLAGS=%#CFLAGS=%' Makefile
}

do_compile() {
	oe_runmake mp
}

do_install() {
	install -d ${D}/usr/bin
	install -d ${D}/etc
	#oe_runmake install DESTDIR=${D} PREFIX=/usr
	install -d ${D}/usr/share/applications
	install -d ${D}/usr/share/pixmaps
	install -m755 mp ${D}/usr/bin
	install -m644 ../mp.desktop ${D}/usr/share/applications
	install -m644 ../mp.xpm ${D}/usr/share/pixmaps
	install -m644 ../mprc ${D}/etc
}

TARGET_CC_ARCH += "${LDFLAGS}"

FILES:${PN} += "/usr/share/mp*/mp_*"
