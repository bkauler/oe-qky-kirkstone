# Recipe created by recipetool
# recipetool create -o libtommath_1.1.0.bb https://github.com/libtom/libtommath/archive/v1.1.0.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=23e7e0a32e53a2b1d35f5fd9ef053402"

SRC_URI = "https://github.com/libtom/libtommath/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "1a59f8ce57b42ea133187e54c5f1070f"
SRC_URI[sha256sum] = "71b6f3f99341b7693393ab4b58f03b79b6afc2ee5288666cc4538b4b336355f4"

do_configure () {
	sed -i '/ CC = /d' makefile_include.mk
	sed -i '/^LD=/d' makefile_include.mk
	sed -i '/^AR=/d' makefile_include.mk
	sed -i '/^RANLIB=/d' makefile_include.mk
	sed -i -e 's%/usr/local%/usr%' makefile_include.mk
}

do_compile () {
	oe_runmake
}

do_install () {
	oe_runmake install DESTDIR=${D}
}

#20200919
#stupid do_rootfs reports error. need to put something into split pkg...
do_install:append() {
 install -d ${D}/usr/bin
 touch libtommath-do-nothing-file
 install -m644 libtommath-do-nothing-file ${D}/usr/bin
}

HOMEPAGE = "http://www.libtom.net/LibTomMath/"
SUMMARY = "Portable number theoretic multiple-precision integer library"
