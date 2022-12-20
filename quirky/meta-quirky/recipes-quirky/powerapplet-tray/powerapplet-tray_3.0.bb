# Recipe created by recipetool
# recipetool create -o powerapplet-tray_2.6.bb http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/powerapplet_tray-2.6.tar.bz2

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://powerapplet_tray.c;md5=39148cdb2995acc1ce350c81e545a612"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/p/powerapplet_tray-3.0.tar.gz"
SRC_URI[md5sum] = "765323ff5a6fc9d01b60fa9da0af6637"
SRC_URI[sha256sum] = "1dfc5fc400ae8197105b063b0cff68af8467a4bf2861ed55095ca9495ad0882d"

S = "${WORKDIR}/powerapplet_tray-${PV}"

# NOTE: no Makefile found.
DEPENDS = "gtk+"
inherit pkgconfig gettext

do_configure () {
    if [ -f powerapplet_tray ];then
     rm -f powerapplet_tray
    fi
    if [ -f powerapplet_tray.pot ];then
     rm -f powerapplet_tray.pot
    fi
}

do_compile () {
    ${CC} `pkg-config --cflags --libs gtk+-2.0` powerapplet_tray.c -o powerapplet_tray ${CFLAGS} ${LDFLAGS}
    xgettext --keyword="_" powerapplet_tray.c  -o powerapplet_tray.pot
}

do_install () {
    install -d ${D}/root/Startup
    install -m755 powerapplet_tray ${D}/root/Startup
    install -d ${D}/usr/share/doc/nls/powerapplet_tray
    install -m644 powerapplet_tray.pot ${D}/usr/share/doc/nls/powerapplet_tray
}

FILES:${PN} += '/root/Startup'

HOMEPAGE = "https://bkhome.org/news/201910/powerapplettray-updated-to-version-27.html"
SUMMARY = "Battery status monitor for the system tray"
