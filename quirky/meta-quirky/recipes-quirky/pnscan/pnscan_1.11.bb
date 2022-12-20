LICENSE = "GPL-1.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=76d2667df44a60aa2ece84148097b345"

#20200919 new d/l:
SRC_URI = "http://distro.ibiblio.org/easyos/source/oe/pyro/pnscan-${PV}.tar.gz"
SRC_URI[md5sum] = "2fcb161470929d8c35bdb2c67f5030e4"
SRC_URI[sha1sum] = "981f892febd329e511e44a1eab24ae1d19844901"
SRC_URI[sha256sum] = "d39d01d0b2ea061958a6b7a786cc959a206b8c8d74752e9168956dd981808e94"
SRC_URI[sha384sum] = "9dc4e770030b6d7b6e8b5309673d0a973bc4114c6a96b8bbd0a7bd6d6a6ee25f3774fcf416e68e54d62e91c601cd96d9"
SRC_URI[sha512sum] = "b3ca7b18846a7756d3626676dc8c9198896dccfa0a94bab859335e721786eebb48745248c7edc5d3c8365dd6894e7bf923816deda3bcbb54a90b91c388b36756"

# NOTE: this is a Makefile-only piece of software.
# note: there is EXTRA_OEMAKE_append = " ", but think can just append directly onto oe_runmake
# note: that "-lnsl" in Makefile lead me "up the garden path" -- it isn't actually used!!!

do_configure () {
	#sed -i -e 's%/usr/local%/usr%' ${B}/Makefile
	#sed -i '/^DESTDIR=/d' ${B}/Makefile
	true
}

do_compile () {
	#oe_runmake lnx
	#...um, the Makefile is too messy. do it directly:
    ${CC} -c pnscan.c ${CFLAGS} -lpthread
    ${CC} -c bm.c ${CFLAGS} -lpthread
    ${CC} -c version.c ${CFLAGS} -lpthread
    ${CC} -o pnscan pnscan.o bm.o version.o ${LDFLAGS} ${CFLAGS} -lpthread
}

do_install () {
	#oe_runmake install-all DESTDIR=${D}/usr
	#...not working properly, just do this:
	install -d ${D}/usr/bin
	install -m 755 pnscan ${D}/usr/bin
}

HOMEPAGE = "https://github.com/ptrrkssn/pnscan"
SUMMARY = "pnscan is a tool that can be used to survey TCP network service"
