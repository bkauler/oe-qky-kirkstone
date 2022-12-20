# Recipe created by recipetool
# recipetool create -o libetpan_1.9.4.bb https://github.com/dinhvh/libetpan/archive/refs/tags/1.9.4.tar.gz

LICENSE = "BSD-1-Clause"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=f18ebe7e452708c26f83954f81062ba7"

SRC_URI = "https://github.com/dinhvh/libetpan/archive/refs/tags/${PV}.tar.gz"
SRC_URI[md5sum] = "66bc8ccb241123aa61d405a576763a44"
SRC_URI[sha256sum] = "82ec8ea11d239c9967dbd1717cac09c8330a558e025b3e4dc6a7594e80d13bb1"

#just threw in all those -native in case needed...
DEPENDS = "gnutls gnutls-native openssl openssl-native zlib curl curl-native \
           db db-native expat expat-native nettle nettle-native libunistring gmp"

inherit autotools pkgconfig gettext

EXTRA_OECONF = " --with-zlib --enable-ipv6 --disable-lockfile --enable-shared --disable-static"

do_configure:prepend() {
 touch ${S}/README
}

HOMEPAGE = "http://www.etpan.org"
SECTION = "libs"
SUMMARY = "A library for communicating with mail and news servers"

#QA Issue: libetpan.pc failed sanity test 
do_install:append() {
 sed -i -e 's%^Libs: .*%Libs: -L${libdir} -letpan -lz -lssl -lcrypto -ldb-5%' ${D}/usr/lib/pkgconfig/libetpan.pc
}
