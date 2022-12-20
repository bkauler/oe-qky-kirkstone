# Recipe created by recipetool
# recipetool create -o xf86-video-apm_1.3.0.bb https://www.x.org/releases/individual/driver/xf86-video-apm-1.3.0.tar.gz

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=0b302c1eb730ff7a191f2cbdc952f689"

SRC_URI = "https://www.x.org/releases/individual/driver/xf86-video-apm-${PV}.tar.gz"
SRC_URI[md5sum] = "5649d6d8f64fd1cd365039189747f152"
SRC_URI[sha256sum] = "d3eee0423c544de40809ab714164cc2a1ac45dc7fae42bac745755afa475d4c0"

DEPENDS += "xorgproto xcb-proto libpciaccess xserver-xorg"

inherit pkgconfig autotools

EXTRA_OECONF = ""

FILES:${PN} += "${datadir}/X11 ${libdir}/xorg"

HOMEPAGE = "http://www.X.org"
SUMMARY = "The Xorg Alliance ProMotion video driver"
