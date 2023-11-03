
#20231014
#ref: https://github.com/OldManYellsAtCloud/meta-browser/tree/fix-nspr-for-kirkstone

PACKAGECONFIG:append = " gpu openmax forbid-multiple-compositors disable-sandboxed-libraries x11-only"

#20231101 do_configure:
# ERROR: Nasm is required to build with AV1, FFVPX, JPEG and VPX, but it was not found.
DEPENDS:append = " nasm-native"
#try this instead of nasm...
DEPENDS:append = " gnutls zlib libpcre libvpx libjpeg-turbo libffi \
    libffi-native icu icu-native pixman pixman-native libevent \
    libwebp ffmpeg"
EXTRA_OECONF:append = " --enable-av1 --with-system-ffi --with-system-icu \
    --enable-system-pixman --with-system-libevent --with-system-jpeg \
    --with-system-libvpx --enable-system-webp"
#ERROR: Requested 'icu-i18n >= 73.1' but version of icu-i18n is 70.1
#...see meta-quirky/recipes-support/icu have bumped from openembedded-core master.
#cannot get rid of nasm...
#ERROR: Nasm is required to build with FFVPX, but it was not found.
#....so have brought back nasm-native dep.
#USE_LIBS contains "dav1d", which does not match any LIBRARY_NAME in the tree
#...seems need to bring back av1


