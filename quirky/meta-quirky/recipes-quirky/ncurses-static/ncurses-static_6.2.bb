# Recipe created by recipetool
# recipetool create -o ncurses-static_6.2.bb http://deb.debian.org/debian/pool/main/n/ncurses/ncurses_6.2+20201114.orig.tar.gz

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=910e05334f7e0b7631da6b4ebb1e1aab"

SRC_URI = "http://deb.debian.org/debian/pool/main/n/ncurses/ncurses_${PV}%2B20201114.orig.tar.gz"
SRC_URI[md5sum] = "c522f09793230ef1ed3b6dff5bbc0740"
SRC_URI[sha1sum] = "fe4bfc4c178c5211802ba28c47bdcc326e251d29"
SRC_URI[sha256sum] = "aa3f8cfaff2a2b78f184274ec43d9da910c864e4b4d80fc47b5b48cba9154cd2"
SRC_URI[sha384sum] = "27f0853eb5c967f4927d4c800099a35fbe3531b96c2e6887f051468358d7bffa6bd6c370fefd9cb077452dc45eadf759"
SRC_URI[sha512sum] = "d163bc8f08f6b2406f8f562fecd9035e0e6f2db8b539cbcaeb4a80b15027b518026526eac1b2681da82b8d03dd1c924a85de1294e6ace2a5dbc03126512a3e2c"

S = "${WORKDIR}/ncurses-${PV}-20201114"

DEPENDS = ""

inherit autotools pkgconfig

EXTRA_OECONF = "--enable-widec --with-normal --without-shared --enable-sigwinch \
    --enable-hard-tabs --enable-colorfgbg --with-termlib"

#do_configure: QA Issue: This autoconf log indicates errors, it looked at host include and/or library paths while determining system capabilities.
ERROR_QA:remove = "configure-unsafe"
#QA Issue: /usr/bin/ncursesw6-config contained in package ncurses-static requires /bin/bash, but no providers found in RDEPENDS_ncurses-static?
ERROR_QA:remove = "file-rdeps"

do_configure() {
 oe_runconf
}

do_install:prepend() {
 #20210828 install fails, cripple this script...
 echo '#!/bin/sh' > ${B}/misc/run_tic.sh
 
 #20210828 aarch64 build:
 #strip: Unable to recognise the format of the input file /mnt/sda1/nvme/oe-builds/oe-quirky/build-aarch64-musl/tmp/work/aarch64-poky-linux-musl/ncurses-static/6.2-r2/image/usr/bin/aarch64-poky-linux-musl-tic
 #/mnt/sda1/nvme/oe-builds/oe-quirky/build-aarch64-musl/tmp/hosttools/install: strip process terminated abnormally
 #sed -i -e 's%${INSTALL} -s%${INSTALL}%' ${B}/progs/Makefile
 sed -i -e 's% -s$%%' ${B}/progs/Makefile
}

XXXdo_install:append() {
 #do not want the binaries...
 rm -rf ${D}/usr/bin
}

SUMMARY = "the new curses library"
HOMEPAGE = "http://www.gnu.org/software/ncurses/ncurses.html"
