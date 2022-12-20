
PACKAGECONFIG += " lzma pango rsvg webp jp2 "

#ref: https://packages.debian.org/bullseye/imagemagick-6.q16
DEPENDS += " ghostscript cups cups-filters netpbm curl ffmpeg xdg-utils "

do_install:append() {
 ln -s magick.im7 ${D}/usr/bin/magick
 ln -s magick.im7 ${D}/usr/bin/magick-script
 ln -s magick.im7 ${D}/usr/bin/animate
 ln -s magick.im7 ${D}/usr/bin/compare
 ln -s magick.im7 ${D}/usr/bin/composite
 ln -s magick.im7 ${D}/usr/bin/conjure
 ln -s magick.im7 ${D}/usr/bin/convert
 ln -s magick.im7 ${D}/usr/bin/display
 ln -s magick.im7 ${D}/usr/bin/identify
 ln -s magick.im7 ${D}/usr/bin/import
 ln -s magick.im7 ${D}/usr/bin/mogrify
 ln -s magick.im7 ${D}/usr/bin/montage
 ln -s magick.im7 ${D}/usr/bin/stream
}
