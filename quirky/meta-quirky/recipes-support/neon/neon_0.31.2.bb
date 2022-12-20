# Recipe created by recipetool
# recipetool create -o neon_0.31.2.bb https://github.com/notroj/neon/archive/0.31.2.tar.gz
# 20210120 BK: OE only has 0.30.2, but libreoffice needs 0.31.x
#              note, no_external_man_entity.patch is from debian sid.

SUMMARY = "An HTTP and WebDAV client library with a C interface"
HOMEPAGE = "http://www.webdav.org/neon/"
SECTION = "libs"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://src/COPYING.LIB;md5=f30a9716ef3762e3467a2f62bf790f0a"

#BK:           file://no_external_man_entity.patch 

SRC_URI = "https://github.com/notroj/neon/archive/${PV}.tar.gz \
           file://pkgconfig.patch \
           "

SRC_URI[md5sum] = "97abd4398d33be76f16033c7b107fecb"
SRC_URI[sha1sum] = "2b82f184f573e82a7ec8a26fe6d6d608d0887426"
SRC_URI[sha256sum] = "c6513d20c0affca6f4b45e2414a86cce951709cf4448b6b64ccdf3579fda0ce5"
SRC_URI[sha384sum] = "838d0d691577f222653c051444c5554200e04c2bc7711de2d1b36a6b731868befb78e1d6a40df34573e95b32814109c6"
SRC_URI[sha512sum] = "cf3d42e14cec0cc716f7985d350ff7095b795047c4b14d15c33e3fda60d1779b2c4f7d87c83d74f8be4346df15d02513eec1b2435d5258b6ab957b36d6115148"

inherit autotools-brokensep binconfig-disabled lib_package pkgconfig

# Enable gnutls or openssl, not both
PACKAGECONFIG ?= "expat gnutls libproxy webdav zlib"
PACKAGECONFIG:class-native = "expat gnutls webdav zlib"

PACKAGECONFIG[expat] = "--with-expat,--without-expat,expat"
PACKAGECONFIG[gnutls] = "--with-ssl=gnutls,,gnutls"
PACKAGECONFIG[gssapi] = "--with-gssapi,--without-gssapi,krb5"
PACKAGECONFIG[libproxy] = "--with-libproxy,--without-libproxy,libproxy"
PACKAGECONFIG[libxml2] = "--with-libxml2,--without-libxml2,libxml2"
PACKAGECONFIG[openssl] = "--with-ssl=openssl,,openssl"
PACKAGECONFIG[webdav] = "--enable-webdav,--disable-webdav,"
PACKAGECONFIG[zlib] = "--with-zlib,--without-zlib,zlib"

# BK get install errors, missing po/*.gmo, so disable nls...
EXTRA_OECONF += "--enable-shared --disable-nls"

do_configure:prepend() {
 #BK autogen is supposed to create this file, but doesn't...
 echo '0.31.2' > ${S}/.version
 touch -d '+1 hour' ${S}/.version
 #BK hmm, now complains install-sh missing, try this to force create it...
 libtoolize --copy --force --install
}

do_compile:append() {
    oe_runmake -C test
}

do_install:prepend() {
 #BK more weirdness, install wants this, but it is missing...
 mkdir doc/man
 mkdir doc/html
 #quick hack, create empty files...
 touch doc/man/neon.3
 touch doc/man/neon.1
 touch doc/html/neon.html
}

BINCONFIG = "${bindir}/neon-config"

BBCLASSEXTEND = "native"
