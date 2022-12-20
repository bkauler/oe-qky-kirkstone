# Recipe created by recipetool
# recipetool create -o lives_20220103.bb https://distro.ibiblio.org/easyos/source/alphabetical/l/lives-20220103.tar.gz
# 20220108 bumped to version 20220108. ref: https://github.com/salsaman/LiVES
# 20220109 bump.

LICENSE = "GPL-3.0-only & LGPL-3.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949 \
                    file://COPYING.LGPL;md5=59cf55ceb3219acc195418308b48f82e \
                    file://src/giw/COPYING;md5=22c72e3134a91cc1a6afc9e296b50069 \
                    file://lives-plugins/plugins/encoders/COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949 \
                    file://lives-plugins/plugins/playback/video/COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949 \
                    file://lives-plugins/weed-plugins/COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/l/lives-${PV}.tar.gz \
           file://cross-compile-fix.patch"

SRC_URI[md5sum] = "cbd94383fec0dd6209c0b8dbf8702746"
SRC_URI[sha256sum] = "cdd3f79856d2b0a3a9bf4a2ced021754c9afb7d6fbd440f1aa7b97d79ab6b02d"

# NOTE: unable to map the following pkg-config dependencies:
#  libweed-compat libvisual libweed-plugin liboil-0.3 libweed-utils libvisual-0.4 
#  gdk-wayland-3.0 libprojectM libfreenect opencv4 libweed libexplain opencv
# 20220108 removed: jack
DEPENDS = "glib-2.0 gtk+3 libtheora fftw unicap libsdl libxrender libpng \
           libogg ffmpeg mesa mjpegtools orc libdv pulseaudio zlib libx11 \
           alsa-lib schroedinger libtirpc ghostscript unicap \
           libebml glib-2.0-native libdv sox mpv libmatroska v4l-utils \
           libsdl-mixer libsdl-ttf libsdl-gfx bzip2 jpeg cups cups-filters \
           imagemagick"

#error at final link step with separate 'build' folder, so use 'autotools-brokensep'
#| make[1]: *** No rule to make target '/mnt/sda1/nvme/oe-builds/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/lives/20211222-r6/build/../lives-20211222/libweed/libweed.la', needed by 'lives-exe'.  Stop.

inherit gettext perlnative pkgconfig autotools-brokensep

EXTRA_OECONF = "--disable-doxygen --enable-threads=posix --disable-jack"

#attempting to fix:
# error: ../../libOSC/client/.libs/libOSC_client.a: No such file or directory
#this fixes it:
# ref: https://www.yoctoproject.org/docs/current/ref-manual/ref-manual.html
DISABLE_STATIC = ""

do_configure:prepend() {
 #| configure.ac:130: error: required file './ABOUT-NLS' not found
 touch ${S}/ABOUT-NLS
}

do_compile:prepend() {
 #20220104 only have this bug if enabled jack:
 #| /mnt/sda1/nvme/oe-builds/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/lives/20211222-r6/recipe-sysroot-native/usr/bin/x86_64-poky-linux/../../libexec/x86_64-poky-linux/gcc/x86_64-poky-linux/9.3.0/ld: /usr/lib/libjack.so: undefined reference to `PW_LOG_TOPIC_DEFAULT'
 #| collect2: error: ld returned 1 exit status
 sed -i -e 's%-L/usr/lib%%' ${S}/Makefile
 sed -i -e 's%-L/usr/lib%%' ${S}/src/Makefile
}

do_install:append() {
 rm -f ${D}/usr/bin/lives
 mv -f ${D}/usr/bin/lives-exe ${D}/usr/bin/lives
 ln -s lives ${D}/usr/bin/lives-exe
 rm -f ${D}/usr/share/applications/LiVES.desktop
 echo '[Desktop Entry]
Name=LiVES video editor and VJ tool
GenericName=LiVES
Comment=A video editor and VJ program
Categories=AudioVideoEditing
Exec=lives
Icon=lives.png
Type=Application
Terminal=false' > ${D}/usr/share/applications/lives.desktop
}

#ERROR: lives-20211222-r6 do_package_qa: QA Issue: /usr/lib/lives/plugins/effects/rendered/wave contained in package lives requires /usr/bin/perl, but no providers found in RDEPENDS_lives? [file-rdeps]
ERROR_QA:remove = "file-rdeps"

HOMEPAGE = "http://lives-video.com/"
SUMMARY = "Video editing"
