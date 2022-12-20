# Recipe created by recipetool
# recipetool create -o xf86-video-chips_1.4.0.bb https://www.x.org/releases/individual/driver/xf86-video-chips-1.4.0.tar.gz

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=d16ab8e6e1c8f1eaca1ef57449f284b2"

SRC_URI = "https://www.x.org/releases/individual/driver/xf86-video-chips-${PV}.tar.gz"
SRC_URI[md5sum] = "be372a549b3f030742a6db369d4e259b"
SRC_URI[sha256sum] = "9a5341e47a78cf0f5e82e705e27c551faa78c4b504d77c8f149ba447dcbcb3eb"

DEPENDS = "libpciaccess xorgproto xcb-proto xserver-xorg"

inherit pkgconfig autotools

EXTRA_OECONF = ""

FILES:${PN} += "${datadir}/X11 ${libdir}/xorg"

HOMEPAGE = "http://www.X.org"
SUMMARY = "The Xorg Chips and Technologies video driver"
