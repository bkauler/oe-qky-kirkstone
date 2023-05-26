#20230526 BK
#Recipe created by recipetool
# recipetool create -o xset_1.2.5.bb https://www.x.org/releases/individual/app/xset-1.2.5.tar.gz

#got this out of meta/recipes-graphics/xorg-app/xorg-app-commin.inc:
SUMMARY = "X application"
HOMEPAGE = "http://www.x.org/"
BUGTRACKER = "https://bugs.freedesktop.org"
SECTION = "x11/apps"
# depends on virtual/libx11
REQUIRED_DISTRO_FEATURES = "x11"
DEPENDS = "util-macros-native virtual/libx11"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=bea81cc9827cdf1af0e12c2b8228cf8d"

SRC_URI = "https://www.x.org/releases/individual/app/xset-${PV}.tar.gz"

SRC_URI[md5sum] = "efbca421bec205cfbb1ba8d9f0345a75"
SRC_URI[sha256sum] = "2068d1356d80c29ce283f0fff5895667b38f24ea95df363d3dde7b8c8a92fffe"

DEPENDS += "libxext libxmu libx11 xorgproto \
            libxext libxcb libxau libxdmcp"

inherit pkgconfig autotools features_check

#libxxf86misc is very deprecated, no longer in OE...
EXTRA_OECONF = "--without-fontcache --without-xf86misc"

FILES:${PN} += " ${libdir}/X11/${BPN} ${datadir}/X11/app-defaults/"
