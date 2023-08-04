# Recipe created by recipetool
# recipetool create -o parcellite_1.2.2.bb https://github.com/rickyrockrat/parcellite/archive/refs/tags/1.2.2.tar.gz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0e2cd40e05189ec81232da84bd6e1a \
                    file://deb/copyright;md5=a4cddd6e1f0fc51ab5724b965a3aee9c"

SRC_URI = "https://github.com/rickyrockrat/parcellite/archive/refs/tags/${PV}.tar.gz \
           file://config.simple.h"

SRC_URI[md5sum] = "c06753ebcc1a926e7929a8599c299695"
SRC_URI[sha256sum] = "50c45015c82c75d7b90586d5f4c0fa02568b769806c39afa2369f5713d2f9f49"

DEPENDS = "intltool-native glib-2.0 libx11 gtk+ pango cairo gdk-pixbuf \
           harfbuzz fontconfig freetype glib-2.0-native"

inherit gettext pkgconfig autotools

EXTRA_OECONF = "--enable-appindicator=no"

do_configure:prepend() {
 cp -f ${WORKDIR}/config.simple.h ${S}/src/
}

HOMEPAGE = "https://github.com/rickyrockrat/parcellite"
SUMMARY = "Clipboard manager"
