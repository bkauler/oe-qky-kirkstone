# Recipe created by recipetool
# recipetool create -o xf86-video-i740_1.4.0.bb https://www.x.org/releases/individual/driver/xf86-video-i740-1.4.0.tar.gz

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=c85da4d100605ac6d8d47d47eb2bf191"

SRC_URI = "https://www.x.org/releases/individual/driver/xf86-video-i740-${PV}.tar.gz"
SRC_URI[md5sum] = "2d0fa9b90b64642f245b4827a8a0decb"
SRC_URI[sha256sum] = "956e3718b181602d7cf44a9fb00e9d07dca0ded78ce5db1c3115ce8ee57fc367"

DEPENDS = "xserver-xorg xorgproto libpciaccess xcb-proto"

inherit pkgconfig autotools

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = ""

FILES:${PN} += "${datadir}/X11 ${libdir}/xorg"

HOMEPAGE = "http://www.X.org"
SUMMARY = "The Xorg Intel i740 video driver"

