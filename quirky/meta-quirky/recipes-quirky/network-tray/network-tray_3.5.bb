# Recipe created by recipetool
# 181119 v3.4: fix for NetworkManager, has all interfaces up.

#PR = "r1"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/n/network_tray-${PV}.tar.gz"
SRC_URI[md5sum] = "12470374c61123a70744c90fd6e7e28b"
SRC_URI[sha256sum] = "b8c7dfe370ae81ae454f73950ddb5be0d559abc2abedd7754dd9b58314fcd3ac"

S = "${WORKDIR}/network_tray-${PV}"

# NOTE: no Makefile found.
DEPENDS = "gtk+"
inherit pkgconfig gettext

do_configure () {
    if [ -f network_tray ];then
     rm -f network_tray
    fi
    if [ -f network_tray.pot ];then
     rm -f network_tray.pot
    fi
}

do_compile () {
    ${CC} -Wl,--no-as-needed `pkg-config --cflags --libs gtk+-2.0` network_tray.c -o network_tray ${CFLAGS} ${LDFLAGS}
    xgettext --keyword="_" network_tray.c  -o network_tray.pot
}

do_install () {
    install -d ${D}/root/Startup
    install -m755 network_tray ${D}/root/Startup
    install -d ${D}/usr/share/doc/nls/network_tray
    install -m644 network_tray.pot ${D}/usr/share/doc/nls/network_tray
    install -d ${D}/usr/local/lib/X11/mini-icons
    install -m644 networkblank.xpm ${D}/usr/local/lib/X11/mini-icons
    install -m644 networkboth.xpm ${D}/usr/local/lib/X11/mini-icons
    install -m644 networkdead.xpm ${D}/usr/local/lib/X11/mini-icons
    install -m644 networkin.xpm ${D}/usr/local/lib/X11/mini-icons
    install -m644 networkout.xpm ${D}/usr/local/lib/X11/mini-icons
}

FILES:${PN} += '/root/Startup /usr/local/lib/X11/mini-icons'

HOMEPAGE = "https://bkhome.org/news"
SUMMARY = "Tray applet for Puppy and derivatives, show network activity"

