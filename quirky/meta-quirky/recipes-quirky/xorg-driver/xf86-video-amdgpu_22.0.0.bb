# Recipe created by recipetool
# recipetool create -o xf86-video-amdgpu_19.0.1.bb https://www.x.org/releases/individual/driver/xf86-video-amdgpu-19.0.1.tar.gz
# 20220306 bumped to 22.0.0.

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=aabff1606551f9461ccf567739af63dc"

SRC_URI = "https://www.x.org/releases/individual/driver/xf86-video-amdgpu-${PV}.tar.gz"

SRC_URI[md5sum] = "73efb437f5eb29b2c52a9d82c7e15d72"
SRC_URI[sha256sum] = "bc47a1a8854e790270fa5de2fb9dfe8558139b03d8f68ac1057dcd235d572dcc"

DEPENDS += "xorgproto xcb-proto xserver-xorg libdrm mesa eudev"

inherit pkgconfig autotools

EXTRA_OECONF = ""

FILES:${PN} += "${datadir}/X11 ${libdir}/xorg"

HOMEPAGE = "http://www.X.org"
SUMMARY = "The Xorg amdgpu AMD Radeon video driver"
