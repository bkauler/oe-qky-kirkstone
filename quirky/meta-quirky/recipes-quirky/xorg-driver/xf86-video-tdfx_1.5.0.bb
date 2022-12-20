# Recipe created by recipetool
# recipetool create -o xf86-video-tdfx_1.5.0.bb https://www.x.org/releases/individual/driver/xf86-video-tdfx-1.5.0.tar.gz

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=41f74bf6ac6803f497df136f0896153a"

SRC_URI = "https://www.x.org/releases/individual/driver/xf86-video-tdfx-${PV}.tar.gz"
SRC_URI[md5sum] = "e52b9ca2e2c019ed9383fc55ce2e76b2"
SRC_URI[sha256sum] = "3128bb885d9df7b388537ec16fc565ffed51ba0d893eac936019e8ad6a1e3c41"

DEPENDS += "xorgproto libpciaccess libdrm xserver-xorg xcb-proto mesa"

inherit pkgconfig autotools

#EXTRA_OECONF = ""
# ref: http://lists.openembedded.org/pipermail/openembedded-commits/2009-July/104205.html
# ref: tmp-glibc/work/core2-64-oe-linux/xf86-video-*/*/recipe-sysroot/usr/include/xorg
EXTRA_OECONF = "ac_cv_file__usr_include_xorg_dri_h=yes \
                ac_cv_file__usr_include_xorg_sarea_h=yes \
                ac_cv_file__usr_include_xorg_dristruct_h=yes \
                ac_cv_file__usr_include_xorg_damage_h=yes \
                ac_cv_file__usr_include_xorg_exa_h=yes"


FILES:${PN} += "${datadir}/X11 ${libdir}/xorg"

HOMEPAGE = "http://www.X.org"
SUMMARY = "The Xorg tdfx video driver"

