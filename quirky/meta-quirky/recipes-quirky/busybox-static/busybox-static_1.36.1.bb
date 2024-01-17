# Recipe created by recipetool
# recipetool create -o busybox-static_1.34.1.bb https://busybox.net/downloads/busybox-1.34.1.tar.bz2
# recipetool create -o busybox-static_1.36.1.bb http://deb.debian.org/debian/pool/main/b/busybox/busybox_1.36.1.orig.tar.bz2

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=de10de48642ab74318e893a61105afbb \
                    file://archival/libarchive/bz/LICENSE;md5=28e3301eae987e8cfe19988e98383dae"

#SRC_URI = "http://busybox.net/downloads/busybox-${PV}.tar.bz2 
#           file://BBconfig-1.34.1-20221027"

SRC_URI = "http://deb.debian.org/debian/pool/main/b/busybox/busybox_${PV}.orig.tar.bz2 \
           file://BBconfig-1.36.1-20240111"

SRC_URI[sha256sum] = "b8cc24c9574d809e7279c3be349795c5d5ceb6fdf19ca709f80cde50e47de314"

S = "${WORKDIR}/busybox-${PV}"

DEPENDS = "kern-tools-native flex-native bison-native"

export EXTRA_CFLAGS = "${CFLAGS}"
export EXTRA_LDFLAGS = "${LDFLAGS}"

EXTRA_OEMAKE = "CC='${CC}' LD='${CCLD}' V=1 ARCH=${TARGET_ARCH} CROSS_COMPILE=${TARGET_PREFIX} SKIP_STRIP=y HOSTCC='${BUILD_CC}' HOSTCPP='${BUILD_CPP}'"

do_configure () {
 cp -a -f ${WORKDIR}/BBconfig-1.36.1-20240111 ${S}/.config
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
