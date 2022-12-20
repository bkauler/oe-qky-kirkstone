# Recipe created by recipetool
# recipetool create -o vym_2.8.8.bb https://github.com/insilmaril/vym/archive/refs/tags/v2.8.8.tar.gz

#20211220 bump r6 to r7  20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=eff77bd1d84089c0e34f240e4486c8a5"

SRC_URI = "https://github.com/insilmaril/vym/archive/refs/tags/v${PV}.tar.gz"
SRC_URI[md5sum] = "48115a347d1cab2c1b993666d3cc347d"
SRC_URI[sha256sum] = "0599915108c43ca7b5521f520748875bc6d7d3b0336930f1c2f6aa81d49f9628"

DEPENDS = "qtbase dbus qttools qtbase-native qttools-native qtscript qtsvg"

inherit cmake cmake_qt5 pkgconfig

EXTRA_OECMAKE = ""

HOMEPAGE = "http://www.insilmaril.de/vym/"
SUMMARY = "Mind mapping visualization"

#/usr/vym not so good, make it /usr/share/vym... no, doesn't work
XXXdo_configure:prepend() {
 sed -i -e 's%$${PREFIX}/vym%$${PREFIX}/share/vym%' ${S}/vym.pro
}

do_install:append() {
 mkdir -p ${D}/usr/share/applications
 echo '[Desktop Entry]
Encoding=UTF-8
Name=VYM mind mapper
Comment=VYM mind mapper
Type=Application
Exec=vym
Icon=vym.png
Terminal=false
Categories=X-Personal-organizer' > ${D}/usr/share/applications/vym.desktop

 mkdir -p ${D}/usr/share/pixmaps
 ln -s ../../vym/icons/vym.png ${D}/usr/share/pixmaps/vym.png
}
