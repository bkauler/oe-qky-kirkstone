# Recipe created by recipetool
# recipetool create -o gview_0.9.1a.bb https://downloads.sourceforge.net/project/gview/gview/gview-0.9a/gview-0.9.1a.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "https://downloads.sourceforge.net/project/gview/gview/gview-0.9a/gview-${PV}.tar.gz"
SRC_URI[md5sum] = "94d74effd7d37fc141a76ad861ad66d0"
SRC_URI[sha256sum] = "8111793dfdde7f678e040cef3e6b5b2275bf403a3596b7bec436ea2248a452dd"

# NOTE: no Makefile found.
DEPENDS = "gtk+ gdk-pixbuf"
inherit pkgconfig

do_configure () {
    true
}

#20221210 multiple def of progname
# ref: https://gcc.gnu.org/gcc-10/porting_to.html
CFLAGS:append = " -fcommon"
XXXdo_compile:prepend() {
 #sed -i -e 's%^char \*progname%extern char *progname%' ${S}/utils.h
 true
}

do_compile () {
    ${CC} -c *.c ${CFLAGS} `pkg-config --cflags gtk+-2.0`
    ${CC} -o gview *.o ${LDFLAGS} `pkg-config --libs gtk+-2.0`
}

do_install () {
    install -d ${D}/usr/bin
    install -m755 gview ${D}/usr/bin
}

HOMEPAGE = "http://gview.sourceforge.net/"
SUMMARY = "A tiny simple gtk image viewer"
