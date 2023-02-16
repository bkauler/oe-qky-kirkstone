# Recipe created by recipetool
# recipetool create -o xf86-video-trident_1.3.8.bb https://www.x.org/archive/individual/driver/xf86-video-trident-1.3.8.tar.gz

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=2e9eb6db89324a99415a93a059157da7"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/x/xf86-video-trident-${PV}.tar.gz"

SRC_URI[md5sum] = "3d393ba3a74b54a933e7445717c425b5"
SRC_URI[sha1sum] = "443de5e3100431ca1d9f19cad26572a9e0fe2756"
SRC_URI[sha256sum] = "99b34598c1840a9d77cbd0800da2c2387d057b0e6b295697ef167d9e02677442"

DEPENDS += "xorgproto xcb-proto xserver-xorg libpciaccess libdrm mesa"

inherit pkgconfig autotools

EXTRA_OECONF = ""


HOMEPAGE = "https://github.com/freedesktop/xorg-xf86-video-trident"
SUMMARY = "The Xorg Trident video driver"
