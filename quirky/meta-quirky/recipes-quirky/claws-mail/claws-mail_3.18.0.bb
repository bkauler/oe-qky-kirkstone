# Recipe created by recipetool
# recipetool create -o claws-mail_3.18.0.bb https://distro.ibiblio.org/easyos/source/alphabetical/c/claws-mail-3.18.0.tar.gz

#20211209 fixed usr/bin/gpgme-config in gpgme pkg, have to recompile claws-mail...
# currently r5, bump to r6...
#PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=e059bde2972c1790af786f3e86bac22e"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/c/claws-mail-${PV}.tar.gz \
           file://gumbo.pc"
SRC_URI[md5sum] = "466a1e9ba0796cca21f4828c085106ef"
SRC_URI[sha256sum] = "f107deec1f0debfae77f7f0022aef713f85a5b1f7d0b1a0e98f6eebe1e3556f2"

#20211209 removed: valgrind
DEPENDS = "libsm gdk-pixbuf cairo libarchive flex-native gnutls bison-native \
           libcanberra libsoup-2.4 startup-notification libnotify libetpan perl \
           networkmanager dbus curl librsvg nettle libical expat zlib gtk+ glib-2.0 \
           poppler fontconfig glib-2.0-native litehtml libunistring libxcb openssl \
           gpgme gnupg openldap enchant2 aspell libgcrypt libpng harfbuzz libidn2 \
           libnsl2 pango fontconfig gmp libpcre fribidi libassuan freetype libffi \
           gdb"

inherit gettext perlnative pkgconfig autotools mime-xdg

EXTRA_OECONF = " --disable-python-plugin --disable-perl-plugin --disable-fancy-plugin \
                 --disable-dillo-plugin --disable-clamd-plugin --enable-libetpan \
                 --enable-litehtml_viewer-plugin --disable-manual --disable-libsm"

#litehtml has libgumbo in it, but missing gumbo.pc
# should fix this in the litehtml recipe, but oh well...
do_configure:prepend() {
 if [ ! -e ${WORKDIR}/recipe-sysroot/usr/lib/pkgconfig/gumbo.pc ];then
  cp -f ${WORKDIR}/gumbo.pc ${WORKDIR}/recipe-sysroot/usr/lib/pkgconfig/
  #hmmm...
  cp -f ${WORKDIR}/recipe-sysroot/usr/include/gumbo/gumbo.h ${S}/src/plugins/litehtml_viewer/litehtml/
 fi
}

HOMEPAGE = "https://www.claws-mail.org/"
SUMMARY = "Email client"
