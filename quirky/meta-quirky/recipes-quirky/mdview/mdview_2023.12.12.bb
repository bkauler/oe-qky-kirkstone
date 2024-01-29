# Recipe created by recipetool
# recipetool create -o mdview_2023.12.12.bb https://bkhome.org/news/202401/images/mdview-2023.12.12.tar.gz
# ...coz currently unable to ssh to ibiblio.org. ref: https://bkhome.org/news/202401/mdview-markdown-viewer-updated.html

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=79808397c3355f163c012616125c9e26"

SRC_URI = "https://bkhome.org/news/202401/images/mdview-${PV}.tar.gz"

SRC_URI[md5sum] = "c7dc26799dade6e3790ab11ebeed8af1"
SRC_URI[sha256sum] = "ecc7de78099ef28a2f052ee90f3c3e3131721a23e198e7d38e76dd2958b6ba98"

DEPENDS = "gtk+ gtk+3"
inherit pkgconfig gettext

CFLAGS:append = " -Wno-error=format-security"

do_configure () {
	true
}

do_compile () {
	${CC} *.c -o mdview ${CFLAGS} ${LDFLAGS} `pkg-config --cflags --libs gtk+-3.0`
}

do_install () {
	install -d ${D}/usr/bin
	install -m 755 mdview ${D}/usr/bin
}

HOMEPAGE = "http://www.lightofdawn.org/blog/?viewDetailed=00141"
SUMMARY = "Simple markdown viewer, using only gtk+"

