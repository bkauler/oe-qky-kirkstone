# Recipe created by recipetool
# recipetool create -o xf86-video-neomagic_1.3.0.bb https://www.x.org/releases/individual/driver/xf86-video-neomagic-1.3.0.tar.gz

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=3a6358ddf387f4be24801a5337a021a8"

SRC_URI = "https://www.x.org/releases/individual/driver/xf86-video-neomagic-${PV}.tar.gz"
SRC_URI[md5sum] = "bf875a5ec62eed477b361abd599b68d6"
SRC_URI[sha256sum] = "eb7213829ae27fe055b55b975a464c2212f1ab7082bed806e8c79bfd38dd4217"

DEPENDS += "libpciaccess xorgproto xserver-xorg xcb-proto libdrm mesa"

inherit pkgconfig autotools

EXTRA_OECONF = ""

FILES_${PN} += "${datadir}/X11 ${libdir}/xorg"

HOMEPAGE = "http://www.X.org"
SUMMARY = "The Xorg Neomagic video driver"
