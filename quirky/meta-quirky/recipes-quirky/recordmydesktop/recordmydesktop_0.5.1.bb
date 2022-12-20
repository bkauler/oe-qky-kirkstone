# Recipe created by recipetool
# recipetool create -o recordmydesktop_0.5.1.bb https://github.com/recordmydesktop/recordmydesktop/archive/refs/tags/0.5.1.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "https://github.com/recordmydesktop/recordmydesktop/archive/refs/tags/${PV}.tar.gz"

SRC_URI[md5sum] = "ac79f12d2686038058070d0577f6f52e"
SRC_URI[sha256sum] = "66bf1b6accca8a48d665ebf17e76b0fc50d36170d38b6d254f3a6931ebc5ebca"

#do not need this, src pkg is different from the other github repo...
#S = "${WORKDIR}/recordmydesktop-${PV}/recordmydesktop"

#leave out: jack
DEPENDS = "libx11 libxfixes popt libvorbis libxext libsm zlib libxdamage libice libtheora alsa-lib libogg"

inherit autotools

EXTRA_OECONF = "--enable-jack=no --enable-oss=no"

HOMEPAGE = "https://github.com/recordmydesktop/recordmydesktop"
SUMMARY = "X Window desktop recording"
