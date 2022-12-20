# Recipe created by recipetool
# recipetool create -o help2man_1.47.10.bb https://ftp.gnu.org/gnu/help2man/help2man-1.47.10.tar.xz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://ftp.gnu.org/gnu/help2man/help2man-${PV}.tar.xz"
SRC_URI[md5sum] = "0d70833650a552e0af742882ba84f2ee"
SRC_URI[sha256sum] = "f371cbfd63f879065422b58fa6b81e21870cd791ef6e11d4528608204aa4dcfb"

inherit autotools

EXTRA_OECONF = "--disable-nls"

BBCLASSEXTEND = "native"

# We don't want to reconfigure things
do_configure() {
	oe_runconf
}

do_install:append () {
	# Make sure we use /usr/bin/env perl
	sed -i -e "1s:#!.*:#! /usr/bin/env perl:" ${D}${bindir}/help2man
}

FILES:${PN} += "${libdir}/help2man"

HOMEPAGE = "https://www.gnu.org/software/help2man/"
SUMMARY = "produces simple man pages"
