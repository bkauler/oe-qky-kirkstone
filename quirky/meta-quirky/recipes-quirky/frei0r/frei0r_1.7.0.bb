# Recipe created by recipetool
# recipetool create -o frei0r_1.7.0.bb https://github.com/dyne/frei0r/archive/refs/tags/v1.7.0.tar.gz
# note: package has both cmake and autotools.

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

SRC_URI = "https://github.com/dyne/frei0r/archive/refs/tags/v${PV}.tar.gz \
           file://1001_fix_metainformation.patch \
           file://1002_spelling.patch"

SRC_URI[md5sum] = "d46006b92c3382a2f3260804249dcc11"
SRC_URI[sha256sum] = "6f7cf95ea2257687cc31db0ed9c9bc0eec152e953d515f346eabec048ed2b29d"

DEPENDS = "cairo zlib libxxf86vm libxrender libxfixes libxext libxdmcp libxdamage \
           libxcb libxau libx11 util-linux libpng pixman mesa freetype fontconfig \
           expat libdrm libjpeg-turbo"

inherit pkgconfig autotools

EXTRA_OECONF = "--disable-cpuflags --without-opencv"

#| ../../frei0r-1.7.0/src/filter/ndvi/ndvi.cpp:21:10: fatal error: frei0r.hpp: No such file or directory
#|    21 | #include "frei0r.hpp"
# ...i got it from debian. ***NO*** hey, it is already in the source...
do_compile:prepend() {
 cp -f ${S}/include/frei0r.hpp ${S}/src/filter/ndvi/
 cp -f ${S}/include/frei0r.h ${S}/src/filter/ndvi/
 cp -f ${S}/include/frei0r_cairo.h ${S}/src/filter/ndvi/
 cp -f ${S}/include/frei0r_cfc.h ${S}/src/filter/ndvi/
 cp -f ${S}/include/frei0r_colorspace.h ${S}/src/filter/ndvi/
 cp -f ${S}/include/frei0r_math.h ${S}/src/filter/ndvi/
 cp -f ${S}/include/blur.h ${S}/src/filter/ndvi/
 
 #see error msg below. fix...
 sed -i -e 's%^docs_DATA =.*%docs_DATA = %' ${B}/Makefile
}

#| make[1]: Entering directory '/mnt/sda1/nvme/oe-builds/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/frei0r/1.7.0-r6/build'
#| make[1]: *** No rule to make target 'README.txt', needed by 'all-am'.  Stop.
#| make[1]: Leaving directory '/mnt/sda1/nvme/oe-builds/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/frei0r/1.7.0-r6/build'

#install this as well, like debian does...
do_install:append() {
 install ${S}/include/frei0r.hpp ${D}/usr/include
}

HOMEPAGE = "https://frei0r.dyne.org/"
SUMMARY = "A minimalistic plugin API for video effects"

