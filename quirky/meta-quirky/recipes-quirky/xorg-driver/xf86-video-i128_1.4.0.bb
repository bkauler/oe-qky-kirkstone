# Recipe created by recipetool
# recipetool create -o xf86-video-i128_1.4.0.bb https://www.x.org/releases/individual/driver/xf86-video-i128-1.4.0.tar.gz

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=47dae2fb2926bd08adffd5128f45190c"

SRC_URI = "https://www.x.org/releases/individual/driver/xf86-video-i128-${PV}.tar.gz"
SRC_URI[md5sum] = "6e9ac9edaa337e0184b6fd1d26315d93"
SRC_URI[sha256sum] = "ed95981ed0f77e1c5bdece5f4bf8e1ff3012953bde87170ce3aefaec2cce7453"

DEPENDS = "xserver-xorg libpciaccess xorgproto xcb-proto"

inherit pkgconfig autotools

EXTRA_OECONF = ""

FILES:${PN} += "${datadir}/X11 ${libdir}/xorg"

HOMEPAGE = "http://www.X.org"
SUMMARY = "The Xorg Number Nine i128 video driver"
