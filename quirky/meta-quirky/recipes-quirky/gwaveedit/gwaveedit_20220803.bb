# Recipe created by recipetool
# recipetool create -o gwaveedit_20220803.bb https://distro.ibiblio.org/easyos/source/alphabetical/g/gwaveedit-20220803.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/g/gwaveedit-${PV}.tar.gz \
 file://compile \
 file://config.guess \
 file://config.rpath \
 file://config.sub \
 file://depcomp \
 file://install-sh \
 file://missing"

SRC_URI[md5sum] = "f41c7b225345a38f1f1dc74b9a10255e"
SRC_URI[sha1sum] = "31a6d01e45cc3c2808d655a2069d1c7e557eb650"
SRC_URI[sha256sum] = "4a0e647a247f9e22bbc05a4cb1bcebf7647c169aba557b0ba83d6b69db47d949"

DEPENDS = "gtk+ glib-2.0 libpng alsa-lib libsamplerate0 pulseaudio jack \
           libsndfile1 pavucontrol"

inherit gettext pkgconfig autotools-brokensep

EXTRA_OECONF = "--with-default-mixerapp=pavucontrol --with-default-driver=pulse \
  --disable-ladspa --without-oss --without-sun --without-sdl --without-portaudio"

HOMEPAGE = "https://github.com/wdlkmpx/gWaveEdit"
SUMMARY = "A graphical program for editing playing and recording sound files."

do_configure:prepend() {
 mkdir ${S}/autoconf-m4
 mkdir ${S}/autoconf
 cp -a ${WORKDIR}/compile ${S}/autoconf/
 cp -a ${WORKDIR}/config.guess ${S}/autoconf/
 cp -a ${WORKDIR}/config.rpath ${S}/autoconf/
 cp -a ${WORKDIR}/config.sub ${S}/autoconf/
 cp -a ${WORKDIR}/depcomp ${S}/autoconf/
 cp -a ${WORKDIR}/install-sh ${S}/autoconf/
 cp -a ${WORKDIR}/missing ${S}/autoconf/
 chmod 755 ${S}/autoconf/compile
 chmod 755 ${S}/autoconf/config.guess
 chmod 755 ${S}/autoconf/config.sub
 chmod 755 ${S}/autoconf/depcomp
 chmod 755 ${S}/autoconf/install-sh
 chmod 755 ${S}/autoconf/missing
}
