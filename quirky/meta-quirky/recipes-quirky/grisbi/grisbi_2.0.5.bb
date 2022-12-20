# Recipe created by recipetool
# recipetool create -o grisbi_2.0.5.bb https://sourceforge.net/projects/grisbi/files/grisbi%20stable/2.0.x/2.0.5/grisbi-2.0.5.tar.bz2

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = "https://sourceforge.net/projects/grisbi/files/grisbi%20stable/2.0.x/${PV}/grisbi-${PV}.tar.bz2"
SRC_URI[md5sum] = "260fba3bba82fd48e6fad5a940f68143"
SRC_URI[sha256sum] = "bd3adbabfc4b4dfc05eff62d2b36458a24b0f00d07cf35a29f6af2f203c77a3f"

DEPENDS = "openssl gtk+3 intltool-native glib-2.0 libgsf libxml2 zlib libofx \
           libical libxml++ glib-2.0-native"

inherit pkgconfig gettext autotools mime-xdg

EXTRA_OECONF = "--without-goffice --with-ofx --with-openssl --with-libxml2 --disable-frenchdoc --disable-schemas-compile"

FILES:${PN} += "${datadir}/mime ${datadir}/icons ${datadir}/mime-info ${datadir}/glib-2.0"

HOMEPAGE = "http://www.grisbi.org/"
SUMMARY = "A personal account management package"
