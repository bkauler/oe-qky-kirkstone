# Recipe created by recipetool
# recipetool create -o libmaa_1.3.2.bb https://downloads.sourceforge.net/project/dict/libmaa/libmaa-1.3.2/libmaa-1.3.2.tar.gz

# note, libmaa needed by dictd.

LICENSE = "LGPL-2.0-only & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=55ca817ccb7d5b5b66355690e9abc605 \
                    file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

#SRC_URI = "https://downloads.sourceforge.net/project/dict/libmaa/libmaa-${PV}/libmaa-${PV}.tar.gz"
SRC_URI = "http://distro.ibiblio.org/easyos/source/oe/pyro/libmaa-1.3.2.tar.gz"
SRC_URI[md5sum] = "01dab2cde2e0a322653e45bfa63537ee"
SRC_URI[sha256sum] = "59a5a01e3a9036bd32160ec535d25b72e579824e391fea7079e9c40b0623b1c5"

# NOTE: the following prog dependencies are unknown, ignoring: fig2dev dvips refbibtex latex latex2e bibtex perl4
inherit perlnative autotools-brokensep pkgconfig

EXTRA_OECONF = ""

SROOT = "${WORKDIR}/recipe-sysroot"

do_compile:prepend() {
    #creates a broken LIBTOOL variable in Makefile...
    LTNAME="$(find ${SROOT}/usr/bin/crossscripts -maxdepth 1 -name '*libtool')"
    #ex: x86_64-oe-linux-libtool
    sed -i -e "s%^LIBTOOL=.*%LIBTOOL= ${LTNAME}%" ${S}/Makefile
}

HOMEPAGE = "http://sourceforge.net/projects/dict/"
SUMMARY = "A library providing many lowlevel data structures"
