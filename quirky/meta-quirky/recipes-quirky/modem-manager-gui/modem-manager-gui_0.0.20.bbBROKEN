# Recipe created by recipetool
# recipetool create -o modem-manager-gui_0.0.20.bb http://archive.ubuntu.com/ubuntu/pool/universe/m/modem-manager-gui/modem-manager-gui_0.0.20.orig.tar.gz

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "http://archive.ubuntu.com/ubuntu/pool/universe/m/modem-manager-gui/modem-manager-gui_${PV}.orig.tar.gz \
           file://fix-tray-icon.patch \
           file://Makefile_h \
           file://resources.h"

SRC_URI[md5sum] = "e942fa59f8101d09ddbb5f1aa4cbb8c7"
SRC_URI[sha1sum] = "66b602a45a3ca754ebe6e40c7f54d4f0034bc213"
SRC_URI[sha256sum] = "00c7054293e5e7832a7eeb7d9ba0d35745e95d2f7df27ab8f912302e2bb56fc5"
SRC_URI[sha384sum] = "55732866b82eee02fab80ae896ff51abd344d8e898755472062ac57ccda0c8f43f9663d30e957a684cc373075a571314"
SRC_URI[sha512sum] = "6802415e14b758642e8827c70ad92cf6ce5ffe0ed80c55c4765ef66b15a8047ca8a510eefcc7f040fcd59eb7890ce7ed94731d6f8abd110280c8a997dde977d1"

S = "${WORKDIR}/${BPN}"

DEPENDS = "gdbm po4a po4a-native gtk+3 mobile-broadband-provider-info modemmanager \
           ofono networkmanager ppp cairo gdk-pixbuf glib-2.0 glib-2.0-native libnotify"

# NOTE: this is a Makefile-only piece of software, so we cannot generate much of the
# recipe automatically - you will need to examine the Makefile yourself and ensure
# that the appropriate arguments are passed in.

do_configure () {
 cp -f ${WORKDIR}/Makefile_h ${S}/
 cp -f ${WORKDIR}/resources.h ${S}/
}

do_compile () {
	oe_runmake
}

do_install () {
 oe_runmake install
}

HOMEPAGE = "https://sourceforge.net/projects/modem-manager-gui/"
SUMMARY = "Modem Manager GUI"

