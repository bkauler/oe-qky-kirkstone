SUMMARY = "Simple DirectMedia Layer"
DESCRIPTION = "Simple DirectMedia Layer is a cross-platform multimedia \
library designed to provide low level access to audio, keyboard, mouse, \
joystick, 3D hardware via OpenGL, and 2D video framebuffer."
HOMEPAGE = "http://www.libsdl.org"
BUGTRACKER = "http://bugzilla.libsdl.org/"

SECTION = "libs"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=27818cd7fd83877a8e3ef82b82798ef4"

#PROVIDES = "virtual/libsdl"

SRC_URI = "http://www.libsdl.org/release/SDL-${PV}.tar.gz \
           file://libsdl-1.2.15-xdata32.patch \
           file://pkgconfig.patch \
           file://0001-build-Pass-tag-CC-explictly-when-using-libtool.patch \
           file://CVE-2019-7577.patch \
           file://CVE-2019-7574.patch \
           file://CVE-2019-7572.patch \
           file://CVE-2019-7578.patch \
           file://CVE-2019-7575.patch \
           file://CVE-2019-7635.patch \
           file://CVE-2019-7637.patch \
           file://CVE-2019-7638.patch \
           file://CVE-2019-7576.patch \
           file://CVE-2019-13616.patch \
          "

UPSTREAM_CHECK_REGEX = "SDL-(?P<pver>\d+(\.\d+)+)\.tar"

S = "${WORKDIR}/SDL-${PV}"

SRC_URI[md5sum] = "9d96df8417572a2afb781a7c4c811a85"
SRC_URI[sha256sum] = "d6d316a793e5e348155f0dd93b979798933fb98aa1edebcc108829d6474aad00"

BINCONFIG = "${bindir}/sdl-config"

inherit autotools lib_package binconfig-disabled pkgconfig

CVE_PRODUCT = "simple_directmedia_layer sdl"

#20230114 cutdown, framebuffer only...
#   took this out, don't know what it does:  --disable-loadso
EXTRA_OECONF = " \
    --disable-alsa --disable-arts --disable-audio --disable-cdrom \
    --disable-dga --disable-diskaudio --disable-dummyaudio --disable-esd \
    --disable-input-tslib --disable-joystick \
    --disable-mintaudio --disable-nas --disable-osmesa-shared \
    --disable-oss --disable-pulseaudio --disable-screensaver \
    --disable-sndio --disable-video-aalib --disable-video-carbon \
    --disable-video-cocoa --disable-video-dga --disable-video-dummy \
    --disable-video-ggi --disable-video-opengl --disable-video-photon \
    --disable-video-ps2gs --disable-video-ps3 --disable-video-svga \
    --disable-video-vgl --disable-video-wscons --disable-video-x11 \
    --disable-video-x11-dgamouse --disable-video-x11-vm \
    --disable-video-x11-xinerama --disable-video-x11-xme \
    --disable-video-x11-xrandr --disable-video-x11-xv \
    --disable-x11-shared --enable-events --enable-input-events \
    --enable-video --enable-video-fbcon --without-x \
    \
    --disable-directx --disable-stdio-redirect --disable-video-directfb \
    --disable-rpath --enable-sdl-dlopen --disable-video-qtopia \
    --disable-video-picogui --enable-pthreads --disable-xbios --disable-gem \
    --enable-file --enable-static --enable-timers \
    --disable-shared"

EXTRA_AUTORECONF += "--include=acinclude --exclude=autoheader"

#20230114 -static. um no...
#LDFLAGS += "-static"

do_configure:prepend() {
        # Remove old libtool macros.
        MACROS="libtool.m4 lt~obsolete.m4 ltoptions.m4 ltsugar.m4 ltversion.m4"
        for i in ${MACROS}; do
               rm -f ${S}/acinclude/$i
        done
        export SYSROOT=$PKG_CONFIG_SYSROOT_DIR
}

#BBCLASSEXTEND = "native nativesdk"

#CVE-2019-14906 is a RHEL specific vulnerability.
CVE_CHECK_IGNORE += "CVE-2019-14906"
