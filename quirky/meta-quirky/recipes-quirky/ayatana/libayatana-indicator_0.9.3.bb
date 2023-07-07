# Recipe created by recipetool
# recipetool create -o libayatana-indicator_0.9.3.bb https://github.com/AyatanaIndicators/libayatana-indicator/archive/refs/tags/0.9.3.tar.gz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/AyatanaIndicators/libayatana-indicator/archive/refs/tags/${PV}.tar.gz"

SRC_URI[md5sum] = "ca55c642e9fb8192c5f80908b1a74b2f"
SRC_URI[sha256sum] = "09c5456fcb430b6ee0626fafdf99a32eb8746b267d56ab2bd4c8a8dd6ca731da"

inherit cmake pkgconfig

DEPENDS = "gtk+3 glib-2.0 libx11 glib-2.0-native"

EXTRA_OECMAKE = "-DENABLE_TESTS=OFF -DENABLE_COVERAGE=OFF -DENABLE_IDO=OFF"

HOMEPAGE = "https://github.com/AyatanaIndicators/libayatana-indicator/"
SUMMARY = "Ayatana indicators shared library"
