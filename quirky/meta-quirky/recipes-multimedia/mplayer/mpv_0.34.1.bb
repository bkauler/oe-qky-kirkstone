SUMMARY = "Open Source multimedia player"
DESCRIPTION = "mpv is a fork of mplayer2 and MPlayer. It shares some features with the former projects while introducing many more."
SECTION = "multimedia"
HOMEPAGE = "http://www.mpv.io/"
# BK 20200920 bumped to 0.32.0, from 0.27.2
# BK 20201013 remove pulseaudio dep.
# BK 20210407 added jack dep. bring back pulseaudio dep.
# BK 20220104 bumped to 0.34.1
# BK 20220719 i686 build: luajit failed.

# BK 20180623 added: mpg123 xvidcore  20200920 added x265
# added deps as used in xine-lib + more... removed xsp
# 20211027 added pulseaudio
# 20220104 added libass, lua luajit
DEPENDS = "zlib ffmpeg jpeg virtual/libx11 libxv \
           libxscrnsaver libv4l libxinerama \
           libxext fontconfig freetype libx11 librsvg libpng alsa-lib \
           libdvdnav faac faad2 flac gdk-pixbuf mesa libglu liba52 libmad libmng \
           libtheora libva libvdpau libvorbis libogg libvpx libsdl libsdl-image \
           libsdl-mixer libsdl-ttf speex libmodplug vcdimager wavpack x264 libxcb \
           libxvmc lame lcms libcdio libraw1394 libavc1394 libdc1394 mpeg2dec \
           schroedinger taglib openssl libsamplerate0 libbluray mesa libdvdread \
           libdvdcss libcddb libcdio-paranoia libarchive libdrm libwebp \
           mpg123 xvidcore x265 jack pulseaudio libass"

# 20220719
DEPENDS:append:x86-64 = " lua luajit lua-native"
DEPENDS:append:aarch64 = " lua luajit lua-native"

REQUIRED_DISTRO_FEATURES = "x11"

LICENSE = "LGPL-2.1-only & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=4fbd65380cdd255951079008b364516c \
                    file://LICENSE.GPL;md5=b234ee4d69f5fce4486a80fdaf4a4263"

# While this item does not require it, it depends on ffmpeg which does
LICENSE_FLAGS = "commercial"

SRC_URI = "https://github.com/mpv-player/mpv/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "b5c76f9a7ce3a19a445869ffd9871d12"
SRC_URI[sha256sum] = "32ded8c13b6398310fa27767378193dc1db6d78b006b70dbcbd3123a1445e746"

#inherit waf pkgconfig pythonnative distro_features_check
inherit waf pkgconfig features_check mime-xdg python3native

# removed...
## Note: both lua and libass are required to get on-screen-display (controls)
#PACKAGECONFIG ??= " \
#    lua \
#    libass \
#    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
#"
#PACKAGECONFIG[drm] = "--enable-drm,--disable-drm,libdrm"
#PACKAGECONFIG[gbm] = "--enable-gbm,--disable-gbm,virtual/mesa"
#PACKAGECONFIG[lua] = "--enable-lua,--disable-lua,lua luajit"
#PACKAGECONFIG[libass] = "--enable-libass,--disable-libass,libass"
#PACKAGECONFIG[libarchive] = "--enable-libarchive,--disable-libarchive,libarchive"
#PACKAGECONFIG[jack] = "--enable-jack, --disable-jack, jack"
#PACKAGECONFIG[vaapi] = "--enable-vaapi, --disable-vaapi,libva"
#PACKAGECONFIG[vdpau] = "--enable-vdpau, --disable-vdpau,libvdpau"
#PACKAGECONFIG[wayland] = "--enable-wayland, --disable-wayland,wayland libxkbcommon"

SIMPLE_TARGET_SYS = "${@'${TARGET_SYS}'.replace('${TARGET_VENDOR}', '')}"

# removed: --disable-encoding  20200920 changed --enable-pulse
#20200920 removed, no longer avail: --enable-dvdread --disable-vapoursynth-lazy
#20201013 --disable-pulse  20210407 --enable-jack 20211027 --enable-pulse
#20220104 added --enable-libass --enable-vaapi --enable-vdpau --enable-lua
#20220104 | waf: error: no such option: --disable-libsmbclient
#20220104 | waf: error: no such option: --enable-libass
EXTRA_OECONF = " \
    --prefix=${prefix} \
    --target=${SIMPLE_TARGET_SYS} \
    --confdir=${sysconfdir}/mpv \
    --datadir=${datadir} \
    --disable-manpage-build \
    --enable-gl \
    --enable-libbluray \
    --enable-dvdnav \
    --enable-cdda \
    --disable-uchardet \
    --disable-rubberband \
    --enable-lcms2 \
    --disable-vapoursynth \
    --disable-debug-build \
    --enable-pulse \
    --enable-libmpv-shared \
    --enable-drm \
    --enable-alsa \
    --enable-libarchive \
    --disable-lua \
    --enable-jack \
    --enable-vaapi \
    --enable-vdpau \
    ${PACKAGECONFIG_CONFARGS} \
"

# 20220719
EXTRA_OECONF:append:i686 = " --disable-lua"
EXTRA_OECONF:append:x86-64 = " --enable-lua"
EXTRA_OECONF:append:aarch64 = " --enable-lua"

get_waf() {
    cd ${S}
    ./bootstrap.py
    sed -i -e 's|/usr/bin/env python|/usr/bin/env python3|g' ${S}/waf
    chmod +x ${S}/waf
    cd -
}

do_patch[postfuncs] += "get_waf"

FILES:${PN} += "${datadir}/icons"
