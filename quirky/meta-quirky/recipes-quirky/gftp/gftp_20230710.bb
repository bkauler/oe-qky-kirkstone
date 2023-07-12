# Recipe created by recipetool
# recipetool create -o gftp_20230710.bb https://distro.ibiblio.org/easyos/source/alphabetical/g/gftp-20230710.tar.gz

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=96218099ba2d4714da5e61f39acded79"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/g/gftp-${PV}.tar.gz"

SRC_URI[md5sum] = "93412d14fcdab1f1a2323904c4858717"
SRC_URI[sha256sum] = "40c3d496a1b72822ef1a3c4d0a2653633406c9c6a189a6615f0d4001c8fdae70"

DEPENDS = "gtk+ openssl glib-2.0 glib-2.0-native openssl-native \
           intltool-native zlib flex-native"

inherit gettext pkgconfig autotools-brokensep

EXTRA_OECONF = "--disable-textport --disable-gtk-warnings --enable-ssl \
                --enable-gtk2 --disable-gtk3"

#autogen.sh fail, try these...
do_configure:prepend() {
 mkdir -p ${S}/autoconf-m4
 mkdir -p ${S}/autoconf
 touch ${S}/autoconf/config.rpath
}

HOMEPAGE = "https://github.com/masneyb/gftp"
SUMMARY = "FTP client"
