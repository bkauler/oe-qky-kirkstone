# Recipe created by recipetool
# recipetool create -o guvcview_2.0.7-2.bb https://sourceforge.net/projects/guvcview/files/source/guvcview-src-2.0.7-2.tar.bz2

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

#SRC_URI = "https://sourceforge.net/projects/guvcview/files/source/guvcview-src-${PV}.tar.bz2"
#SRC_URI[md5sum] = "100d8b27b9e5c7e3cf45802952d1cb46"
#SRC_URI[sha256sum] = "f0d1d7814597d42fddf1655f3654f74efd9f27e35bbe39ee4f0d42683de85c98"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/g/guvcview-2.0.7-2.tar.gz"
SRC_URI[md5sum] = "c920e683cca4f292248d83c0e3bfc0ac"
SRC_URI[sha256sum] = "2eeb581b3005f21dc1d018185106941a5901d4dc531805d51e701fea9fd5db21"

DEPENDS = "v4l-utils glib-2.0 portaudio-v19 pulseaudio ffmpeg libsdl2 libusb1 \
   gtk+3 libpng intltool-native gsl eudev alsa-lib jack libjpeg-turbo libxcb \
   libx11 libice libsm libxtst libsndfile1 libcap libcap-ng libvpx libwebp \
   opencore-amr lame speex libtheora libvorbis x264 x265 xvidcore libxrandr \
   libxcursor libxext libxi libxcomposite libxdamage libxfixes libepoxy \
   harfbuzz pango fontconfig freetype libxrender libffi libxau libxdmcp \
   libogg libdrm graphite2 expat glib-2.0-native"

inherit pkgconfig gettext autotools

EXTRA_OECONF = "--with-gnu-ld"

HOMEPAGE = "https://sourceforge.net/projects/guvcview/"
SUMMARY = "Capture and view video from V4L2 devices"
