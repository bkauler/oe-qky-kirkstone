# Recipe created by recipetool
# recipetool create -o jwm_2.4.2.bb https://github.com/joewing/jwm/releases/download/v2.4.2/jwm-2.4.2.tar.xz

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c2938cf0f6887d0bca0cea7cac3e097f"

SRC_URI = "https://github.com/joewing/jwm/releases/download/v${PV}/jwm-${PV}.tar.xz"

SRC_URI[md5sum] = "6ee49e3d525d6408aa51096fa8949413"
SRC_URI[sha256sum] = "4e07a21774e9defcdddb3ffaa3e18dfe113dfe168fe0428c5e0da987c95e26e5"

DEPENDS = "libjpeg-turbo libxinerama libxrender libx11 cairo libxft librsvg \
           libpng12 libxmu libxpm libxext intltool-native"

inherit gettext autotools-brokensep pkgconfig

EXTRA_OECONF = "--disable-confirm"

do_configure:prepend() {
 #this commit has broken autoreconf...
 # autoreconf: running: automake --add-missing --copy --force-missing
 # automake: error: no 'Makefile.am' found for any configure output
 # ref: https://github.com/joewing/jwm/commit/607fc3d4e3127b79f79cfd602e24523dfcf2a29d
 sed -i '/^AM_INIT_AUTOMAKE/d' ${S}/configure.ac
}

HOMEPAGE = "http://www.joewing.net/programs/jwm"
SUMMARY = "Joes Window Manager"
