# Recipe created by recipetool
# recipetool create -o mlt_7.4.0.bb https://github.com/mltframework/mlt/archive/refs/tags/v7.4.0.tar.gz

#20220115 bump r6 to r7...  20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "LGPL-2.1-only & GPL-2.0-only & GPL-3.0-only & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=4b54a1fd55a448865a0b32d41598759d \
                    file://GPL;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://src/modules/plus/ebur128/COPYING;md5=b7edcc6cb01ace25ebd2555cf15473dc"

SRC_URI = "https://github.com/mltframework/mlt/archive/refs/tags/v${PV}.tar.gz"

SRC_URI[md5sum] = "6ae86f19eb91a726f203f3d32272925d"
SRC_URI[sha256sum] = "41af600a5b223e067dff529d4ebe83bf8dee4efa807c8f928141e1174f13c45c"

#removed:  tcl-native ruby-native
# 20220115 add: frei0r  removed:  python3-native
DEPENDS = "libx11 swig-native qtbase virtual/libgl \
     libsdl libsdl2 ffmpeg zlib libxxf86vm libxv xvidcore libxtst libxshmfence \
     libxrender libxrandr libxml2 libxi libxfixes libxext libxdmcp libxdamage \
     libxcb libxau x265 x264 libx11 libwebp wavpack libvpx libvorbis libvdpau \
     libva v4l-utils util-linux libusb1 eudev libtheora openssl speex speexdsp \
     sox libsndfile1 libsndfile1-native libsm libsamplerate0 libraw1394 \
     qtdeclarative qtscript qtsvg qttools qtx11extras qtxmlpatterns \
     pulseaudio libpthread-stubs libpthread-stubs-native libpng libpcre libpcre2 \
     pango opencore-amr libogg lame libmad xz libjpeg-turbo libid3tag icu \
     libice harfbuzz graphite2 mesa glib-2.0 glib-2.0-native gdk-pixbuf fribidi \
     freetype fontconfig flac fftw libffi expat libexif libdrm libdc1394 \
     libcdio-paranoia libcdio libcap bzip2 libbluray ffmpeg libao alsa-lib \
     swig lua-native perl-native frei0r"

# 20220115 just guessing, add python*...
inherit cmake cmake_qt5 pkgconfig python3-dir python3native

#removed:  -DCPU_SSE2=OFF
# 20220115 frei0r now on...
EXTRA_OECMAKE = "-DMOD_DECKLINK=OFF -DMOD_JACKRACK=OFF -DMOD_XINE=OFF \
     -DMOD_FREI0R=ON -DMOD_KDENLIVE=OFF -DMOD_RTAUDIO=OFF -DMOD_RUBBERBAND=OFF \
     -DSWIG_PYTHON=ON -DSWIG_LUA=ON -DSWIG_PERL=OFF"

#| ninja: error: build.ninja:4240: multiple rules generate out/lib/mlt.so [-w dupbuild=err]
#fix, ref: https://www.mail-archive.com/openembedded-devel@lists.openembedded.org/msg63379.html
EXTRA_OECMAKE_BUILD += "-w dupbuild=warn"

#/mlt/7.4.0-r6/image/usr/lib/perl5/vendor_perl/5.30.1/x86_64-linux/auto/mlt/mlt.so
#| CMake Error at src/swig/perl/cmake_install.cmake:50 (file):
#|   file RPATH_CHANGE could not write new RPATH:
# have changed -DSWIG_PERL to OFF.

SUMMARY = "MLT Multimedia Framework"
HOMEPAGE = "https://www.mltframework.org"
