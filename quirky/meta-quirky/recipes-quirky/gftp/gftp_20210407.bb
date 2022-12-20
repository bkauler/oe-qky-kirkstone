# Recipe created by recipetool
# recipetool create -o gftp_20210407.bb http://distro.ibiblio.org/easyos/source/alphabetical/g/gftp-20210407.tar.gz

#2021-04-16 rebuilding this pkg, so bump the "-r<n>" in pkg name...  20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=3305ea8f1515c82aff287ba72bc88dd8"

#20210416 gftp-20210407-auth-dlg.patch added. 
# ref: https://github.com/masneyb/gftp/issues/107

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/g/gftp-${PV}.tar.gz \
           file://gftp-20210407-auth-dlg.patch"
SRC_URI[md5sum] = "feb4d20a804788c0696fe4ebdb7e69af"
SRC_URI[sha1sum] = "7381caa654ce252a161335feda1baea1c9660017"
SRC_URI[sha256sum] = "19c51945ed04fd042789e6b807b13a55b83592f99d1478ea5322bc37ca4e71be"
SRC_URI[sha384sum] = "8a2bd493546e0c2c3c3cdfe8029887834aa2059bcf98c433a20f3313be17f275b1b3ce6a7b855a0295d17a07a4b22a47"
SRC_URI[sha512sum] = "a337a041185a62fa27fbf1edee0a61487c651b10e3a9db7984832d008499fe1461ab350ddb6416c7c6d29639f74efa0477cce665f222243141551222c8fe07d7"

DEPENDS = "openssl glib-2.0 gtk+ intltool-native glib-2.0-native zlib flex-native"

inherit pkgconfig gettext autotools-brokensep

EXTRA_OECONF = "--disable-textport --disable-gtk-warnings"

#autogen.sh fail, try these...
do_configure:prepend() {
 mkdir -p ${S}/autoconf-m4
 mkdir -p ${S}/autoconf
 touch ${S}/autoconf/config.rpath
}

HOMEPAGE = "https://github.com/masneyb/gftp"
SUMMARY = "FTP client"
