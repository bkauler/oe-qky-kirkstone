# Recipe created by recipetool
# recipetool create -o xvidtune_1.0.4.bb https://www.x.org/releases/individual/app/xvidtune-1.0.4.tar.gz

require recipes-graphics/xorg-app/xorg-app-common.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=fa0b9c462d8f2f13eba26492d42ea63d"

SRC_URI = "https://www.x.org/releases/individual/app/xvidtune-${PV}.tar.gz"

SRC_URI[md5sum] = "e73ede189f78df416b711113cafc82df"
SRC_URI[sha256sum] = "e5982c9e6c5009f0061c187a9cc82368215bd004cfa464a3d738c90e1d258668"

# libx11 xorgproto
DEPENDS += "libxaw libxxf86vm libxt libxmu libxpm"

#inherit pkgconfig autotools

#EXTRA_OECONF = ""

HOMEPAGE = "http://www.X.org"
SUMMARY = "Video mode tuner for Xorg"
