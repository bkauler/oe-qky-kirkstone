# Recipe created by recipetool
# recipetool create -o xf86-video-s3_0.6.5.bb https://www.x.org/archive/individual/driver/xf86-video-s3-0.6.5.tar.gz
# 20220306 bumped to 0.7.0. FAILED.

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=0eae1e9f9b6904bf113c02c911019b1a"

SRC_URI = "https://www.x.org/archive/individual/driver/xf86-video-s3-${PV}.tar.gz"

#           file://build_fix-s3.patch"

SRC_URI[md5sum] = "4823c8b0739b2368de90caba5d61671b"
SRC_URI[sha256sum] = "4b0fb8008a64b3b5da9e36750361e9032d69439b47145b9f2efd3e3cd9178074"

DEPENDS += "xorgproto xcb-proto libpciaccess xserver-xorg libdrm mesa"

inherit pkgconfig autotools

EXTRA_OECONF = ""


HOMEPAGE = "http://www.X.org"
SUMMARY = "The Xorg video driver for classic non ViRGE S3 chips"
