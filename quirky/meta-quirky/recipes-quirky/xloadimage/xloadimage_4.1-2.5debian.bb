# Recipe created by recipetool
# recipetool create -o xloadimage_4.1-2.5debian.bb http://deb.debian.org/debian/pool/main/x/xloadimage/xloadimage_4.1.orig.tar.gz

LICENSE = "GPL-2.0-only"
#LIC_FILES_CHKSUM = "file://copyright.h;md5=da3b929bbccee81ce371237dde14be28"
#LIC_FILES_CHKSUM = "file://copyright.h;md5=8c3ca7f602611550c78874de352c421d"
# ...some patches modify this.
LIC_FILES_CHKSUM = "file://${COREBASE}/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

# patches from debian
SRC_URI = "http://deb.debian.org/debian/pool/main/x/xloadimage/xloadimage_4.1.orig.tar.gz \
 file://01_libjpeg-support.patch \
 file://02_png-support.patch \
 file://03_security-strfoo.patch \
 file://04_previous-image.patch \
 file://05_idelay-manpage.patch \
 file://06_-Wall-cleanup.patch \
 file://07_SYSPATHFILE.patch \
 file://08_manpage-config-path.patch \
 file://09_xloadimagerc-path.patch \
 file://10_config.c-HOME-fix.patch \
 file://11_fork-implies-quiet.patch \
 file://12_fix-tile.patch \
 file://13_varargs-is-obsolete.patch \
 file://14_errno-not-extern.patch \
 file://15_CAN-2005-0638.patch \
 file://16_CAN-2005-0639.patch \
 file://17_security-sprintf.patch \
 file://18_manpage_fixes.patch \
 file://19_fix_root_c_resource_leak.patch \
 file://20_patch.patch \
 file://21_libpng.patch \
 file://22-include.patch \
 file://23_jpeg.c-build-fix.patch \
 file://24_libtiff5.patch \
 file://25_hardening-flags.patch \
 file://26_uufilter-implicit-declaration.patch \
 file://27_shrink-should-not-zoom-upwards.patch \
 file://28_correct-scaling-fullscreen.patch \
 file://29_fix-manpage-hyphens.patch \
 file://30_libtiff5.patch \
 file://31_reproducible-build.patch \
 file://32_fix-spelling.patch \
 file://build.c \
"

SRC_URI[md5sum] = "7331850fc04056ab8ae6b5725d1fb3d2"
SRC_URI[sha256sum] = "400bc7d84dcfb3265a7a1ce51819679dc3adaeda231514bd89b0f932b78ff5c4"

S = "${WORKDIR}/${BPN}.4.1"

DEPENDS = "libx11 libpng zlib"

do_configure () {
 true
 cp -f ${WORKDIR}/build.c ./
}

do_compile () {
	#oe_runmake
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" new.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" niff.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" value.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" zio.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" cmuwmraster.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" faces.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" fbm.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" gif.c
${CC} -c -O -DHAVE_LIBPNG -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" imagetypes.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" img.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" mac.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" mcidas.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" mc_tables.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" pbm.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" pcx.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" pdsuncomp.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" rle.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" rlelib.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" sunraster.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" vff.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" vicar.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" xbitmap.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" xpixmap.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" xwd.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" bright.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" clip.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" compress.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" dither.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" fill.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" halftone.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" merge.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" reduce.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" rotate.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" smooth.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" undither.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" zoom.c
${CC} -c -O -DHAVE_LIBPNG -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" png.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" config.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" misc.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" options.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" root.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" send.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" window.c
${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" xloadimage.c

${CC} -c -O -DSYSV -DHAS_SELECT_INCLUDE   -DSYSPATHFILE=\"/etc/X11/Xloadimage\" build.c

${CC} -o xloadimage -O -DSYSV -DHAS_SELECT_INCLUDE -DHAVE_LIBPNG -DSYSPATHFILE=\"/etc/X11/Xloadimage\" build.o new.o niff.o value.o zio.o cmuwmraster.o faces.o fbm.o gif.o imagetypes.o img.o mac.o mcidas.o mc_tables.o pbm.o pcx.o pdsuncomp.o rle.o rlelib.o sunraster.o vff.o vicar.o xbitmap.o xpixmap.o xwd.o bright.o clip.o compress.o dither.o fill.o halftone.o merge.o reduce.o rotate.o smooth.o undither.o zoom.o png.o config.o misc.o options.o root.o send.o window.o xloadimage.o -lpng16 -lz -lX11 -lm ${LDFLAGS}

}

do_install () {
 #oe_runmake install
 install -d ${D}/usr/bin
 install -m755 xloadimage ${D}/usr/bin
 echo 'path = /usr/share/backgrounds

extension = .niff             # NIFF image
            .jpg .jpeg        # JPEG image
            .gif              # CompuServe GIF image
            .sun .csun .msun  # sun rasterfile
            .face             # faces project
            .xwd              # X window dump
            .xpm .pm .xbm .bm # X bitmap
            .fax              # G3 FAX
            .fbm              # fuzzy bitmap
            .mac              # macpaint
            .pbm .pgm .ppm    # PBMPLUS
            .img              # GEM IMG
            .pcx              # PCX image
            .png              # PNG image

filter = ' > Xloadimage
 install -d ${D}/etc/X11
 install -m644 Xloadimage ${D}/etc/X11
}

