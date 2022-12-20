# Recipe created by recipetool
# recipetool create -o pngoverlay-cairo_20190623.bb http://distro.ibiblio.org/easyos/source/alphabetical/p/pngoverlay-cairo-20190623.tar.gz
# 20210409 replaces 'pngoverlay'.

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=00badb5496bb9f7fbc773ca0adb4c11a"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/p/pngoverlay-cairo-${PV}.tar.gz"
SRC_URI[md5sum] = "5f8ee94221917e4b027ef2631c8342dd"
SRC_URI[sha1sum] = "c945da412d9fe4d1f998c507f1edc4f6676aef41"
SRC_URI[sha256sum] = "eb198669d92e33accfb6f94e740dc516c4d44edc3da06df9927260da0530b447"
SRC_URI[sha384sum] = "66a8a72f63302243ff13c0f481b0f5d1c28a14db232c7e9284538280917cd072a5b7a09cb51cc0d253153181211103b2"
SRC_URI[sha512sum] = "be7ca712229d53e39023497476ca3e76227b9d2ba21a059f973a55358c68ccb1a92dbdfa20b13ef5abf34c4c67ac5fada076c1a79c43ed278b05ed0815c3e2a5"

inherit pkgconfig

DEPENDS = "gtk+ cairo glib-2.0 cairo-native glib-2.0-native cairo-native"

do_configure () {
	true
}

do_compile () {
	oe_runmake
}

do_install () {
	#oe_runmake install DESTDIR=${D}
    install -d ${D}/usr/sbin
    install -m755 pngoverlay-cairo ${D}/usr/sbin
}

HOMEPAGE = "https://github.com/puppylinux-woof-CE/woof-CE/issues/1470"
SUMMARY = "Overlay two 48x48 PNG images"
