# Recipe created by recipetool
# recipetool create -o qlipper_20230121.bb https://distro.ibiblio.org/easyos/source/alphabetical/q/qlipper-20230121.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6c1929ba725ff7dce420aad4b737eda1"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/q/qlipper-${PV}.tar.gz"

SRC_URI[md5sum] = "73b71f8f8f0aed03a9f5995f7fa1a091"
SRC_URI[sha256sum] = "dc36b945aecd635049ef79df2acbef02d408f267d4bf857fe1d549e6431a5267"

DEPENDS = "libx11 qtbase qttools qtdeclarative qtxmlpatterns \
           qtbase-native qtdeclarative-native qttools-native qtxmlpatterns-native"

inherit cmake_qt5 gettext pkgconfig

EXTRA_OECMAKE = ""

HOMEPAGE = "https://github.com/pvanek/qlipper"
SUMMARY = "Clipboard manager"
