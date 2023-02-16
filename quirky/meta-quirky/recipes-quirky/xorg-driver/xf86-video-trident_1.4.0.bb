# Recipe created by recipetool
# recipetool create -o xf86-video-trident_1.4.0.bb https://www.x.org/releases/individual/driver/xf86-video-trident-1.4.0.tar.gz

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=2e9eb6db89324a99415a93a059157da7"

SRC_URI = "https://www.x.org/releases/individual/driver/xf86-video-trident-${PV}.tar.gz"

SRC_URI[md5sum] = "7ec4459f4a8242726156a0038c3de626"
SRC_URI[sha256sum] = "9afcb42e1753295af9b84cae8285c9db49e11a9e26015f886a011deaa48a5e7d"

DEPENDS += "xorgproto xcb-proto xserver-xorg libpciaccess libdrm mesa"

inherit pkgconfig autotools

EXTRA_OECONF = ""


HOMEPAGE = "https://github.com/freedesktop/xorg-xf86-video-trident"
SUMMARY = "The Xorg Trident video driver"
