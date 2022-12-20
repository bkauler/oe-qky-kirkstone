# Recipe created by recipetool
# recipetool create -o qt5-styleplugins_20170311.bb https://github.com/qt/qtstyleplugins/archive/335dbece103e2cbf6c7cf819ab6672c2956b17b3.tar.gz
# ref: https://aur.archlinux.org/packages/qt5-styleplugins/
#      ...also got patches from here.

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "https://github.com/qt/qtstyleplugins/archive/335dbece103e2cbf6c7cf819ab6672c2956b17b3.tar.gz \
           file://0001-fix-build-against-Qt-5.15.patch \
           file://0002-fix-gtk2-background.patch"
SRC_URI[md5sum] = "88fb46f8ccdaf8655340b266577f166b"
SRC_URI[sha256sum] = "29ec24fa8df64be161ad06d0e5af3ba1a20bfe265004f5fe4ab9f5f3abf9a5ba"

S = "${WORKDIR}/qtstyleplugins-335dbece103e2cbf6c7cf819ab6672c2956b17b3"

DEPENDS = "qtbase qtbase-native"

inherit qmake5

HOMEPAGE = "https://github.com/qt/qtstyleplugins"
SUMMARY = "qt5 themes including gtk2"
