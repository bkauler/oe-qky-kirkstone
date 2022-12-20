SUMMARY = "Scribus: Open source desktop publishing"
HOMEPAGE = "https://www.scribus.net/"
LICENSE = "GPL-2.0-or-later & LGPL-2.0-or-later & BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=300e791cb9c6b02206963fb25dfa9aa5"

inherit cmake_qt5 cmake_extra_sanity pkgconfig python3native gtk-icon-cache mime mime-xdg

# 20221201 BK
# 20221205 remove:  libwpd-native libpagemaker-native libfreehand-native
#      librevenge-native libvisio-native libmspub-native
DEPENDS = "icu cairo cairo-native freetype freetype-native tiff boost \
           libpagemaker virtual/libgl jpeg libcdr zlib libxml2 \
           poppler librsvg sqlite3 hunspell libdrm expat libjpeg-turbo \
           lcms pixman libpng harfbuzz ghostscript libxcb libxrender \
           libx11 libxext openssl mesa gnutls glib-2.0 graphite2 xz util-linux \
           libxau libxdmcp libxshmfence libgcrypt libxdamage libxfixes libxxf86vm \
           libidn libunistring nettle gmp libxau glib-2.0-native \
           qtbase qtbase-native qttools qttools-native \
           python3 python3-native libwpd libmspub \
           libvisio libfreehand \
           librevenge libpagemaker cups \
           cups-filters fontconfig fontconfig-native"

# remove once we don't have patches any more
inherit dos2unix

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}-devel/${PV}/${BPN}-${PV}.tar.xz \
    file://0001-Build-break-with-poppler-22.2.0.patch \
    file://0002-Build-break-with-poppler-22.03.0.patch \
    file://0003-Build-break-with-poppler-22.04.0.patch \
    file://0004-Build-break-with-poppler-22.09.0.patch \
"
SRC_URI[sha256sum] = "47816e8fcf6d05788ff16aa4499f97ff22431c777a7789149b0a88b451e16b74"

# necessary since poppler 21.01.0
EXTRA_OECMAKE = "-DWANT_CPP17=ON"

FILES:${PN} += " \
    ${datadir}/icons \
    ${datadir}/mime \
    ${datadir}/metainfo \
"
