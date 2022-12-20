# Recipe created by recipetool
# recipetool create -o picscale_0.1e.bb https://distro.ibiblio.org/easyos/source/alphabetical/p/picscale-0.1e.tar.gz
# 20220824 bacon-native rolled back to version 3.9.3

# 20220906 PR_NUM is defined in local.conf, currently r9
#PR = "r${@int(PR_NUM) + 1}"

HOMEPAGE = "https://basic-converter.proboards.com/thread/1014/compile-picscale-openembedded"
SUMMARY = "Scale png images from the command line."

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/p/picscale-${PV}.tar.gz"

SRC_URI[md5sum] = "f657ecfcd6cdbe7fa57af6da2ced2683"
SRC_URI[sha256sum] = "6e5acaa9c745b782b2c583b92b92768e0a9376443e19b1e29268ab8be6290fc6"

DEPENDS = "gtk+ gdk-pixbuf bacon-native gdk-pixbuf-native"

do_configure () {
 true
}

do_compile () {
 mkdir -p build
 # reqd for bacon 3.x: -a rebuild libbacon.a
 bacon -a -d build -c "${CC}" -o "${CFLAGS}" -l "${LDFLAGS}" picscale.bac
}

do_install () {
 install -d ${D}/usr/bin
 install -m755 build/picscale ${D}/usr/bin
}


