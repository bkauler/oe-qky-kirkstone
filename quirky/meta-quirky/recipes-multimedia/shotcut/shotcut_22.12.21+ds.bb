# Recipe created by recipetool
# recipetool create -o shotcut_22.12.21+ds.bb http://deb.debian.org/debian/pool/main/s/shotcut/shotcut_22.12.21+ds.orig.tar.xz

LICENSE = "GPL-3.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://CuteLogger/LICENSE.LGPL;md5=fbc093901857fcd118f065f900982c24 \
                    file://drmingw/doc/LICENSE-libdwarf.txt;md5=f6cfadeff118dbf73dcc5212d5c23772"

SRC_URI = "http://deb.debian.org/debian/pool/main/s/shotcut/shotcut_22.12.21%2Bds.orig.tar.xz"

SRC_URI[md5sum] = "f5e634ea42378775762b81fb9b607a38"
SRC_URI[sha256sum] = "bd17764cfaa0243f1f635d3697979c5771e519ba3de6ec541cbdcacf1905f521"

inherit cmake_qt5 pkgconfig gettext python3-dir python3native mime mime-xdg

DEPENDS = "mlt frei0r ffmpeg qtbase qtdeclarative qtscript qtsvg \
           qttools qtx11extras qtxmlpatterns qtmultimedia qtwebsockets \
           qtquickcontrols2 libsdl2 libsdl2-image libsdl2-mixer libsdl2-net \
           libsdl2-ttf lame x264 x265 pulseaudio alsa-lib \
           qt5-styleplugins qtimageformats desktop-file-utils \
           qtbase-native qtdeclarative-native qttools-native qtxmlpatterns-native"

EXTRA_QMAKEVARS_PRE += "DEFINES+=SHOTCUT_NOUPGRADE DEFINES+=NDEBUG"

EXTRA_OECMAKE = ""

#ERROR: shotcut-22.12.21+ds-r3 do_package_qa: QA Issue: -dev package shotcut-dev contains non-symlink .so '/usr/lib/libCuteLogger.so' [dev-elf]
ERROR_QA:remove = "dev-elf"
#ERROR: shotcut-22.12.21+ds-r3 do_package_qa: QA Issue: shotcut rdepends on shotcut-dev [dev-deps]
ERROR_QA:remove = "dev-deps"

HOMEPAGE = "https://shotcut.org/"
SUMMARY = "Video editor"
