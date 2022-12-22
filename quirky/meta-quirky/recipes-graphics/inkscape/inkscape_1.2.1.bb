SUMMARY = "Inkscape is a Free and open source vector graphics editor"
HOMEPAGE = "https://inkscape.org/"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=46f815712c095f667139ef42f2270d57"

PV = "1.2.1"
SRC_URI = " \
    https://inkscape.org/gallery/item/34673/${BP}.tar.xz \
    file://0001-Fix-build-with-poppler-22.09.patch \
"
SRC_URI[sha256sum] = "46ce7da0eba7ca4badc1db70e9cbb67e0adf9bb342687dc6e08b5ca21b8d4c1b"
S = "${WORKDIR}/${BPN}-${PV}_2022-07-14_9c6d41e410"

DEPENDS = " \
    glib-2.0-native \
    pango \
    gtkmm3 \
    libsoup-2.4 \
    harfbuzz \
    poppler \
    gsl \
    bdwgc \
    lcms \
    gspell \
    libxslt \
    \
    double-conversion \
    libwpg \
    librevenge \
    libcdr \
    libvisio \
    potrace \
"

inherit cmake pkgconfig gettext gtk-icon-cache bash-completion mime-xdg

FILES:${PN} += "${datadir}/metainfo"

INSANE_SKIP:${PN} = "useless-rpaths"
