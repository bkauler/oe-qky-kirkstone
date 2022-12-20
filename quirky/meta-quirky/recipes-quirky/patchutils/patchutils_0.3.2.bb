# Recipe created by recipetool
# recipetool create -o patchutils_0.3.2.bb http://cyberelk.net/tim/data/patchutils/stable/patchutils-0.3.2.tar.bz2

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = "http://cyberelk.net/tim/data/patchutils/stable/patchutils-${PV}.tar.bz2"
SRC_URI[md5sum] = "74607b4a28c9009c6aeeed0e91098917"
SRC_URI[sha256sum] = "fdeb1a571b42faeafbc6bcd999717efca256601a1aa2b4b2f34ec93b6977ae21"

DEPENDS = "perl patch"

inherit perlnative autotools pkgconfig

#20200921 override OE...
CFLAGS:append = " -Wno-error=format-security "

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = ""

# shebang lines wrong...
do_install:append() {
 sed -i -e 's%^#!/mnt/.*%#!/usr/bin/perl%' ${D}/usr/bin/splitdiff
 sed -i -e 's%^#!/mnt/.*%#!/usr/bin/perl%' ${D}/usr/bin/recountdiff
 sed -i -e 's%^#!/mnt/.*%#!/usr/bin/perl%' ${D}/usr/bin/unwrapdiff
 sed -i -e 's%^#!/mnt/.*%#!/usr/bin/perl%' ${D}/usr/bin/fixcvsdiff
}

RDEPENDS:patchutils += "bash perl"

HOMEPAGE = "http://cyberelk.net/tim/patchutils/index.html"
SUMMARY = "A collection of tools that operate on patch files"
