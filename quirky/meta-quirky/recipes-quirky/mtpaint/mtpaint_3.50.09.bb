# Recipe created by recipetool
# recipetool create -o mtpaint_3.50.09.bb https://distro.ibiblio.org/easyos/source/alphabetical/m/mtpaint-3.50.09.tar.gz

# note, Mark Tyler is the original author: http://mtpaint.sourceforge.net/
# now maintained by 'wjaguar'.

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/m/mtpaint-${PV}.tar.gz"
SRC_URI[md5sum] = "c669c6bf4355bc814f3953c064ea3976"
SRC_URI[sha1sum] = "cdd8e3d367a06d13e78a1cb3071812f166bcac13"
SRC_URI[sha256sum] = "9cfd2d8e5a41e39bd47fc615c2e47cff336d304bee33cd88e105c3f3fb11bfb4"

DEPENDS = "libx11 gtk+ gifsicle giflib libxpm jpeg tiff jasper lcms fontconfig \
           libpng zlib freetype freetype-native fontconfig-native"

inherit pkgconfig gettext

do_configure () {
    #note, most of these options not really needed, as will autodetect...
    ./configure --cpu=${TARGET_ARCH} intl man jpeg tiff jasper lcms2 gtk2 cflags
}

do_compile () {
	oe_runmake
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

SUMMARY = "A simple GTK+1/2 painting program"
HOMEPAGE = "https://github.com/wjaguar/mtPaint"
