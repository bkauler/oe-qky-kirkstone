# Recipe created by recipetool
# recipetool create -o fpm2_0.90.bb https://als.regnet.cz/fpm2/download/fpm2-0.90.tar.xz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

SRC_URI = "https://als.regnet.cz/fpm2/download/fpm2-${PV}.tar.xz"

SRC_URI[md5sum] = "221669fbd81bc6b9c5615bca9a6e57b8"
SRC_URI[sha256sum] = "1f3ab9c41c86267da97c48dda2f2333e163a3179fb21be1d34d4b4bf8792dfd1"

DEPENDS = "nettle libx11 gtk+3 libxml2 intltool-native glib-2.0 glib-2.0-native \
           gdk-pixbuf pango harfbuzz cairo libxcb fontconfig freetype"

inherit pkgconfig gettext autotools

EXTRA_OECONF = "--disable-debug"

#20221210 multiple defs
# ref: https://gcc.gnu.org/gcc-10/porting_to.html
CFLAGS:append = " -fcommon"

HOMEPAGE = "http://als.regnet.cz/fpm2/"
SUMMARY = "An application that allows you to securely store your passwords."
