# Recipe created by recipetool
# recipetool create -o grisbi_1.2.2.bb https://downloads.sourceforge.net/project/grisbi/grisbi%20stable/1.2.x/1.2.2/grisbi-1.2.2.tar.bz2

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = "https://downloads.sourceforge.net/project/grisbi/grisbi%20stable/1.2.x/${PV}/grisbi-${PV}.tar.bz2"
SRC_URI[md5sum] = "4397b442cd893e07da11fef9bfd8fa49"
SRC_URI[sha1sum] = "d298ac3879ede5694153fc00eecc1647bd357666"
SRC_URI[sha256sum] = "7c6dcb9d123076df33f7225085db44f0b95afbb773378fd94b7c492dbbf731de"
SRC_URI[sha384sum] = "df7613c80e6ed2fa22bdb0e3c3ab1546ad35a9d9ff14bb9f81934ac4bfecb9bd00585f425e06ceae3bb607a21c908b28"
SRC_URI[sha512sum] = "7d627f9ae588c0d5a8235bb86f3ed160a572114ccfec1dce9c7137296e5525411600033ca4e42b242004edddc42f2160e95c18c0c0cd13516cf62486e081acdd"

DEPENDS = "libgsf intltool-native gtk+3 glib-2.0 zlib openssl libofx libxml2 glib-2.0-native"

# NOTE: if this software is not capable of being built in a separate build directory
# from the source, you should replace autotools with autotools-brokensep in the
# inherit line
inherit pkgconfig gettext autotools

EXTRA_OECONF = "--without-goffice --with-ofx --with-openssl --with-libxml2 --disable-frenchdoc --disable-schemas-compile"

FILES_${PN} += "${datadir}/mime ${datadir}/icons ${datadir}/mime-info ${datadir}/glib-2.0"

HOMEPAGE = "http://www.grisbi.org/"
SUMMARY = "A personal account management package"
