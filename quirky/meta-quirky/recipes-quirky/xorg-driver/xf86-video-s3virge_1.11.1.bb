# Recipe created by recipetool
# recipetool create -o xf86-video-s3virge_1.11.1.bb https://www.x.org/releases/individual/driver/xf86-video-s3virge-1.11.1.tar.gz

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=09743e0f5c076a765cd16697b5b1effb"

SRC_URI = "https://www.x.org/releases/individual/driver/xf86-video-s3virge-${PV}.tar.gz"

SRC_URI[md5sum] = "06b53f6145216e190723756104c4f9f8"
SRC_URI[sha256sum] = "847ae8d41ea07a3bd96b12ac4d60f57cd9008b865d5ed2908eb2b58888164241"

DEPENDS = "xorgproto libpciaccess xserver-xorg xcb-proto libdrm mesa"

inherit pkgconfig autotools

EXTRA_OECONF = ""

HOMEPAGE = "https://www.x.org"
SUMMARY = "Xorg S3 Virge video driver"
