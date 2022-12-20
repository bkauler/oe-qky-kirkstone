# Recipe created by recipetool
# recipetool create -o unicap_0.9.12.bb http://deb.debian.org/debian/pool/main/u/unicap/unicap_0.9.12+repack20150328.0.git2c600ae.orig.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

# patches from here: https://packages.debian.org/sid/libunicap2
SRC_URI = "http://deb.debian.org/debian/pool/main/u/unicap/unicap_${PV}%2Brepack20150328.0.git2c600ae.orig.tar.gz \
           file://1001_printf_warning.patch \
           file://1003_vid21394_pointer.patch \
           file://1005_tidy_gettext.patch \
           file://1009_v4l1.patch \
           file://1010_libv4l2.h_includes.patch \
           file://1011_euvccam_implicit_decls.patch \
           file://1012_gtkdoc_sourcedir.patch \
           file://1013_unicap_queue_t.patch"

SRC_URI[md5sum] = "d7905ce279098df5beb641321c770b95"
SRC_URI[sha256sum] = "cd9f077e71c0beda8c0b40ff62cdbce319d0cf7ff0ae4cfb289593bb46c787c4"

S = "${WORKDIR}/lib${BPN}"

#20221208 add: docbook-xml-dtd4-native docbook-xsl-stylesheets-native gtk-doc-native
DEPENDS = "v4l-utils intltool-native eudev libraw1394 pango glib-2.0 \
           glib-2.0-native gtk+ libxv docbook-xml-dtd4-native \
           docbook-xsl-stylesheets-native gtk-doc-native"

inherit pkgconfig gettext autotools-brokensep

EXTRA_OECONF = "--disable-static"

SECTION = "graphics"
HOMEPAGE = "http://unicap-imaging.org/"
SUMMARY = "Uniform interface to video capture devices"
