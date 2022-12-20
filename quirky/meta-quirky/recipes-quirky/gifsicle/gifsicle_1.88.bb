# Recipe created by recipetool
# recipetool create -o gifsicle_1.88.bb http://www.lcdf.org/gifsicle/gifsicle-1.88.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=046818e14ae66bb3819343c0d2780d5f"

SRC_URI = "http://www.lcdf.org/gifsicle/gifsicle-${PV}.tar.gz"
SRC_URI[md5sum] = "6b1cfb10c35b01ad877f2ae18cca4221"
SRC_URI[sha256sum] = "4585d2e683d7f68eb8fcb15504732d71d7ede48ab5963e61915201f9e68305be"

DEPENDS = "libx11 giflib"

inherit autotools

EXTRA_OECONF = "--enable-gifview --enable-gifdiff"

HOMEPAGE = "http://www.lcdf.org/gifsicle/"
SUMMARY = "A tool for creating gif animations."
