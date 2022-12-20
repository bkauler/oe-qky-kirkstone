# Recipe created by recipetool
# recipetool create -o xf86-video-mach64_20220206.bb https://distro.ibiblio.org/easyos/source/alphabetical/x/xf86-video-mach64-20220206.tar.gz

require recipes-graphics/xorg-driver/xorg-driver-video.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=be79d1b174a1e5b7e9303201e18d45f4"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/x/xf86-video-mach64-${PV}.tar.gz"

SRC_URI[md5sum] = "ea9be37b4878982c7fe9e01a824a4053"
SRC_URI[sha256sum] = "aeb5a4c8e7ca9bf7c2ae62d347c71fd49c7fb2fdcd04fa587fdddceb52e842ec"

DEPENDS = "xserver-xorg xorgproto libdrm libpciaccess xcb-proto"

inherit pkgconfig autotools

# only because configure is broken for cross-compile...
#EXTRA_OECONF = "--disable-dri"
# no, do it this way:
# ref: http://lists.openembedded.org/pipermail/openembedded-commits/2009-July/104205.html
EXTRA_OECONF = "ac_cv_file__usr_include_xorg_dri_h=yes \
                ac_cv_file__usr_include_xorg_sarea_h=yes \
                ac_cv_file__usr_include_xorg_dristruct_h=yes \
                ac_cv_file__usr_include_xorg_damage_h=yes"


FILES:${PN} += "${datadir}/X11 ${libdir}/xorg"

HOMEPAGE = "http://www.X.org"
SUMMARY = "The Xorg driver for ATi Mach64 video chips"

