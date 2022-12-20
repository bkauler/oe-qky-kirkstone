# Recipe created by recipetool
# recipetool create -o xf86-video-rendition_4.2.7.bb https://www.x.org/releases/individual/driver/xf86-video-rendition-4.2.7.tar.gz

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=1f82ff47b53d054af9757517c438fabb"

SRC_URI = "https://www.x.org/releases/individual/driver/xf86-video-rendition-${PV}.tar.gz"
SRC_URI[md5sum] = "64223cd2f29188de81a3a7ddd2c6c3ee"
SRC_URI[sha256sum] = "16712fec045f5e499d32267d0cdbe89eea7ca33d8bdeed93b4874adb9b4e1bce"

DEPENDS = "xorgproto xserver-xorg libpciaccess xcb-proto"

inherit pkgconfig autotools

EXTRA_OECONF = ""

FILES:${PN} += "${datadir}/X11 ${libdir}/xorg"

# Architecture did not match (Unknown (15666), expected x86-64) on /work/nocona-64-oe-linux/xf86-video-rendition/2_4.2.7-r0/packages-split/xf86-video-rendition/usr/lib/xorg/modules/v10002d.uc
INSANE_SKIP:${PN} += "arch"

HOMEPAGE = "http://www.X.org"
SUMMARY = "The Xorg driver for RenditionMicron based video cards"
