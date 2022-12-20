# Recipe created by recipetool
# recipetool create -o obs_26.1.2.bb https://github.com/obsproject/obs-studio/archive/refs/tags/26.1.2.tar.gz

LICENSE = "GPL-2.0-only & LGPL-2.1-only & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=333b307b6253b0198b6ca3afbd60c9f2 \
                    file://plugins/obs-outputs/librtmp/COPYING;md5=e344c8fa836c3a41c4cbd79d7bd3a379 \
                    file://plugins/mac-syphon/data/syphon_license.txt;md5=d4ac777b0a4d5609c55ef554894d298e \
                    file://plugins/obs-filters/rnnoise/COPYING;md5=1890bf89a18f8339491894a0b45428bf \
                    file://deps/json11/LICENSE.txt;md5=67c566d2a95f89606ed2c9fa60b68467 \
                    file://deps/jansson/LICENSE;md5=8b70213ec164c7bd876ec2120ba52f61 \
                    file://deps/libcaption/LICENSE.txt;md5=da1082841ec483429abb0e8c8779d6dc \
                    file://deps/w32-pthreads/COPYING;md5=51b90c76bb95fd648da6d973aac3ac04 \
                    file://deps/w32-pthreads/COPYING.LIB;md5=1f9afd9ce31d5df8ee588082031db79e"

SRC_URI = "https://github.com/obsproject/obs-studio/archive/refs/tags/${PV}.tar.gz"
SRC_URI[md5sum] = "179a87c9f45e1330dcbd0d4ad48dadae"
SRC_URI[sha256sum] = "bc8b4c61ba4aae87b70c589a6a5b8538e4f03121b31e9b98a2071d9f6006b897"

S = "${WORKDIR}/${BPN}-studio-${PV}"

#export THREADS_PTHREAD_ARG = "0"

DEPENDS = "fontconfig pulseaudio qtx11extras zlib libx11 qtsvg qtbase libxcb \
           virtual/libgl swig-native freetype dbus alsa-lib \
           imagemagick jack libsndfile1 swig eudev jansson x264 x265 mbedtls \
           luajit v4l-utils speex speexdsp curl ffmpeg libxcb sysfsutils \
           mesa glib-2.0 glib-2.0-native libxcomposite libxinerama pciutils \
           libdrm libxdamage libpciaccess libice libsm libxtst libcap libxxf86vm \
           expat libxshmfence graphite2 icu libpcre util-linux libxi flac \
           libpng jpeg libogg libtheora libxml2 libxau libgcrypt openssl \
           libxdmcp libbluray bzip2 libvdpau libvdpau-va-gl schroedinger \
           xvidcore wavpack libvorbis libvpx opencore-amr lame lcms xz libwebp \
           libxfixes libxext zlib"

inherit cmake cmake_qt5 python3native python3-dir pkgconfig

#CMake Error: TRY_RUN() invoked in cross-compiling mode, please set the following cache variables appropriately:
#   THREADS_PTHREAD_ARG (advanced)

EXTRA_OECMAKE = "-DUNIX_STRUCTURE=1 -DBUILD_BROWSER=OFF -DENABLE_PIPEWIRE=OFF \
                 -DTHREADS_PTHREAD_ARG=-pthread"

HOMEPAGE = "https://obsproject.com/"
SUMMARY = "Video recording and live streaming"

#ERROR: obs-26.1.2-r6 do_package_qa: QA Issue: -dev package obs-dev contains non-symlink .so '/usr/lib/libobs-scripting.so' [dev-elf]
ERROR_QA:remove = "dev-elf"
