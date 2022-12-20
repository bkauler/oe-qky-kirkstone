# Recipe created by recipetool
# recipetool create -o gftp_2.6.0.bb https://github.com/masneyb/gftp/archive/2.6.0b.tar.gz

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3305ea8f1515c82aff287ba72bc88dd8"

SRC_URI = "https://github.com/masneyb/gftp/archive/${PV}b.tar.gz"
SRC_URI[md5sum] = "af60c1c3ddcc266940cd34e76b53dd76"
SRC_URI[sha1sum] = "534ce4481d56871dd24af4a0547e24f4b5faabfc"
SRC_URI[sha256sum] = "1f4912c385b32099b41aeec169f45875fed09628a0866e68801ae818d575e607"
SRC_URI[sha384sum] = "cf084c23155699a103b4d29e09f8d56c582cda91f600a8d5f996d5a0664b1843e728ff085ff685ad66f8cf321b767557"
SRC_URI[sha512sum] = "959881b579d9126de3b9e5ad3503c841611faed534c5a0edf1de0014589669beccec13047dd3b9fd46db5a9d2d508af98b2dc21c9cb7e294c640aa21a904ea50"

S = "${WORKDIR}/${BPN}-${PV}b"

DEPENDS = "gtk+3 gtk+ glib-2.0 intltool-native openssl glib-2.0-native"

inherit gettext pkgconfig autotools

EXTRA_OECONF = "--disable-textport"

#20200921 autogen.sh reports this file missing. hack...
do_configure_prepend() {
 touch ${S}/config.rpath
}

SUMMARY = "A multithreaded ftp client for X"
HOMEPAGE = "https://github.com/masneyb/gftp"
