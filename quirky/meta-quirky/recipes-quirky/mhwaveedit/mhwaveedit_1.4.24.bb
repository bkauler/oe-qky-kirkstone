# Recipe created by recipetool
# recipetool create -o mhwaveedit_1.4.23.bb http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/mhwaveedit-1.4.23.tar.bz2
# 20210407 added jack  20211027 added pulseaudio
# 20220306 bumped to 1.4.24

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

#20220306 patches from debian
SRC_URI = "https://github.com/magnush/mhwaveedit/archive/refs/tags/v${PV}.tar.gz \
  file://01-kfreebsd_ftbfs.patch \
  file://03-big_endians.patch"

SRC_URI[md5sum] = "6b305c03888612083df9f8b189c0ce27"
SRC_URI[sha256sum] = "a4115b3d18f3f038b08b2bf4ff599703b7ba69bc7ac510d5f7279b3f47ea57dd"

# 20210407 added jack
DEPENDS = "gtk+ libsamplerate0 glib-2.0 libsndfile1 alsa-lib libpng jack pulseaudio"

inherit pkgconfig gettext autotools

# 20210407 change --without-jack to --with-jack
EXTRA_OECONF = "--with-default-driver=alsa --without-arts --with-default-mixerapp=defaultaudiomixer \
 --with-pulse --without-esound --with-jack --disable-ladspa --without-sdl"

do_configure:prepend() {
    chmod 755 ${S}/config.guess
    chmod 755 ${S}/config.rpath
    chmod 755 ${S}/config.sub
    #chmod 755 ${S}/cvscompile
    chmod 755 ${S}/depcomp
}

do_compile:prepend() {
    sed -i -e 's%^LDFLAGS =%LDFLAGS +=%' ${B}/Makefile
}

HOMEPAGE = "http://gna.org/projects/mhwaveedit/"
SUMMARY = "A graphical program for editing playing and recording sound files."
