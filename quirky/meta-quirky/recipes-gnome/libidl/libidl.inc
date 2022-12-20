DESCRIPTION = "Library for parsing CORBA IDL files"
SECTION = "x11/gnome/libs"
LICENSE = "LGPL-2.0-or-later"

inherit autotools pkgconfig

#20221207 patches from debian
SRC_URI = "https://download.gnome.org/sources/libIDL/0.8/libIDL-${PV}.tar.bz2 \
   file://02-fix-Makefile.am \
   file://01-fix-configure.in"

S = "${WORKDIR}/libIDL-${PV}"

FILES:${PN} = "${libdir}/*.so.*"
FILES:${PN}-dev += " ${bindir}"
