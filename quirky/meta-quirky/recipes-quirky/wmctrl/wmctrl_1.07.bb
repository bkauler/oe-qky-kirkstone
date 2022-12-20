# Recipe created by recipetool
# recipetool create -o wmctrl_1.07.bb http://tripie.sweb.cz/utils/wmctrl/dist/wmctrl-1.07.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://tripie.sweb.cz/utils/wmctrl/dist/wmctrl-${PV}.tar.gz \
  file://01_64-bit-data.patch \
  file://02_manpage-fixes.patch"

SRC_URI[md5sum] = "1fe3c7a2caa6071e071ba34f587e1555"
SRC_URI[sha1sum] = "a123019a7fd5adc3e393fc1108cb706268a34e4d"
SRC_URI[sha256sum] = "d78a1efdb62f18674298ad039c5cbdb1edb6e8e149bb3a8e3a01a4750aa3cca9"
SRC_URI[sha384sum] = "dff9cdf12fa96ae24cb4e34fa940dc655e5e71284cb489f91a8b6710d7093b0eb41c0e08b06ee3fd5d0cf23b4aa2be6f"
SRC_URI[sha512sum] = "4c77ad1e204e8d444f682ad1d05c0993bcab9097ac6d4b6a944556ab85acbe713f549dbaf443cd4d1226a162ce7d46fbd209c92652e87fc8e609feee74907daa"

DEPENDS = "libx11 libice libsm libxmu glib-2.0 glib-2.0-native"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-x --disable-glibtest"

#do not try to recreate configure...
do_configure() {
 oe_runconf
}

HOMEPAGE = "http://tripie.sweb.cz/utils/wmctrl/"
SUMMARY = "A UNIX/Linux command line tool to interact with an EWMH/NetWM compatible X Window Manager"
