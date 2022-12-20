# Recipe created by recipetool
# recipetool create -o flsynclient_0.6-patched3.bb http://distro.ibiblio.org/easyos/source/alphabetical/f/flsynclient-0.6-patched3.tar.bz2
#note, we are using a patched 0.6, as 0.7 does not work properly. Ours is also gettext-ized.

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/f/flsynclient-${PV}.tar.bz2"
SRC_URI[md5sum] = "9625bed4023939de98cc1aa3afa7108c"
SRC_URI[sha1sum] = "0657819e511c5a51b4f5dd85df3ce8402c5549c5"
SRC_URI[sha256sum] = "e494b3558f7092ac7646168c05415e5fcf9796fd9b2b98dbb6c3c4caa45cd034"
SRC_URI[sha384sum] = "590205a7dd197cf000c708bf810d7ee84a4eea0e0e48a81bad31b25f451a85b039e94902d38db76130bf21a978488ea6"
SRC_URI[sha512sum] = "14df077afeaf984bfb2c974ccfcbb0231d61c846bcc871d44fb6637390c1ce384c1b358e3783793ab5eba8221ffc60595594c560d43796235354fccf2f6b2316"

DEPENDS = "fltk libpng freetype fontconfig libbsd expat libxcb zlib libxdmcp \
           libxau libxinerama libxfixes libxcursor libxrender libxft libpthread-stubs \
           libx11 libxext"

inherit gettext

do_configure () {
 #the Makefile runs 'g++ -MM UImain.c main.c >.depend' do it manually:
 echo 'UImain.o: UImain.c UImain.h
main.o: main.c UImain.h' > .depend
 sed -i -e 's%^all: depend %all: %' Makefile
 
 #20210502 works when host and target both x86_64, but not when different. try...
 sed -i -e 's%^CC = .*%CC = $(CXX)%' Makefile
}

do_compile () {
 xgettext --keyword="_" main.c UImain.c -o flsynclient.pot
 sed -i -e 's%charset=CHARSET%charset=UTF-8%' flsynclient.pot
 oe_runmake
}

do_install () {
	install -d ${D}/usr/bin
	install -d ${D}/usr/share/pixmaps
	install -d ${D}/usr/share/doc/nls/flsynclient
	install -m755 flsynclient ${D}/usr/bin/
	install -m644 flsynclient.png ${D}/usr/share/pixmaps/
	install -m644 flsynclient.xpm ${D}/usr/share/pixmaps/
	install -m644 flsynclient.pot ${D}/usr/share/doc/nls/flsynclient/
}

HOMEPAGE = "http://matteolucarelli.altervista.org/flsynclient/index_en.htm"
SUMMARY = "GUI to manage Synaptics touchpad"

