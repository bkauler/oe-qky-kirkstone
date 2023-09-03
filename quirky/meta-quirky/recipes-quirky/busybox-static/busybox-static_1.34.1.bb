# Recipe created by recipetool
# recipetool create -o busybox-static_1.34.1.bb https://busybox.net/downloads/busybox-1.34.1.tar.bz2

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=de10de48642ab74318e893a61105afbb \
                    file://archival/libarchive/bz/LICENSE;md5=28e3301eae987e8cfe19988e98383dae"

#           file://0007-busybox-1.22-20130821-guess_fstype-applet.patch 

SRC_URI = "http://busybox.net/downloads/busybox-${PV}.tar.bz2 \
           file://BBconfig-1.34.1-20221027"

SRC_URI[md5sum] = "5ad7368a73d12b8c4f8881bf7afb3d82"
SRC_URI[sha256sum] = "415fbd89e5344c96acf449d94a6f956dbed62e18e835fc83e064db33a34bd549"

S = "${WORKDIR}/busybox-${PV}"

DEPENDS = "kern-tools-native flex-native bison-native"

export EXTRA_CFLAGS = "${CFLAGS}"
export EXTRA_LDFLAGS = "${LDFLAGS}"

EXTRA_OEMAKE = "CC='${CC}' LD='${CCLD}' V=1 ARCH=${TARGET_ARCH} CROSS_COMPILE=${TARGET_PREFIX} SKIP_STRIP=y HOSTCC='${BUILD_CC}' HOSTCPP='${BUILD_CPP}'"

do_configure () {
 cp -a -f ${WORKDIR}/BBconfig-1.34.1-20221027 ${S}/.config
}

do_compile () {
 oe_runmake
}

do_install () {
 #oe_runmake install
 install -d ${D}${bindir}
 install -m 755 busybox ${D}${bindir}
}

SUMMARY = "swiss-army-knife utilities"
HOMEPAGE = "https://busybox.net/"
