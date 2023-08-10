# Recipe created by recipetool
# recipetool create -o pw-tray_1.0.bb https://distro.ibiblio.org/easyos/source/alphabetical/p/pw_tray-1.0.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/p/pw_tray-${PV}.tar.gz"

SRC_URI[md5sum] = "69559e32b9776fa46936638d23af0421"
SRC_URI[sha256sum] = "8412b28d4ce755415e729ad864b0604fec80006124f66d4d3660131995d4236a"

S = "${WORKDIR}/pw_tray-${PV}"

DEPENDS = "gtk+"
inherit pkgconfig gettext

do_configure () {
 if [ -f pw_tray ];then
  rm -f pw_tray
 fi
 if [ -f pw_tray.pot ];then
  rm -f pw_tray.pot
 fi
}

do_compile () {
 ${CC} -Wl,--no-as-needed `pkg-config --cflags --libs gtk+-2.0` pw_tray.c -o pw_tray ${CFLAGS} ${LDFLAGS}
 xgettext --keyword="_" pw_tray.c  -o pw_tray.pot
}

do_install () {
 install -d ${D}/root/Startup
 install -m755 pw_tray ${D}/root/Startup
 install -d ${D}/usr/share/doc/nls/pw_tray
 install -m644 pw_tray.pot ${D}/usr/share/doc/nls/pw_tray
 install -d ${D}/usr/local/lib/X11/mini-icons
 install -m644 vault22.xpm ${D}/usr/local/lib/X11/mini-icons
}

FILES:${PN} += "/root/Startup /usr/local/lib/X11/mini-icons"

HOMEPAGE = "https://bkhome.org/news"
SUMMARY = "Tray wrapper for a password manager"
