# Recipe created by recipetool
# recipetool create -o yaf-splash_1.02.bb http://distro.ibiblio.org/easyos/source/t2/april/yaf-splash-1.02.tar.bz2

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=8b45778259f8db1a33b5856ebbf25f8f"

SRC_URI = "http://distro.ibiblio.org/easyos/source/t2/april/yaf-splash-${PV}.tar.bz2"
SRC_URI[md5sum] = "5e894c1304fbe2cc6e07b10a208808e7"
SRC_URI[sha1sum] = "d653232f310e5c711ad3ff627f8dc72ca8b7b45f"
SRC_URI[sha256sum] = "802155d408d9a7dec4a00d2350c61a3e54923408640d3cc209deaa58801e3900"
SRC_URI[sha384sum] = "84070fc19482350b7c3aad99e99296f5d2550f4ceaeb8a2e944afac3e93732bccf29530c367b5cbe694d63192539cf84"
SRC_URI[sha512sum] = "534f21d975d7f0d975e08535c369278cc5154d6aecc3a6a16b4e4c904e84e80920554e4a783021cbf462b38868f1dca931fb407b2490f93aaf6082bdb7e8b4d1"

DEPENDS = "libx11 libsm libice libxt libxmu libxext"

inherit autotools-brokensep

EXTRA_OECONF = ""

#do not recreate configure script...
do_configure() {
 #oe_runconf
 true
}

do_compile() {
 #gcc -Wall -Wstrict-prototypes -Wnested-externs -Wno-format -std=c89 -U__STRICT_ANSI__ -c -I. -I. -I./..  -I/usr/include -DHAVE_CONFIG_H  -g -O2  yaf-splash.c
 ${CC} ${CFLAGS} -c yaf-splash.c
 
 #gcc -Wall -Wstrict-prototypes -Wnested-externs -Wno-format -std=c89 -U__STRICT_ANSI__ -c -I. -I. -I./..  -I/usr/include -DHAVE_CONFIG_H  -g -O2  resources.c
 ${CC} ${CFLAGS} -c resources.c
 
 #gcc -Wall -Wstrict-prototypes -Wnested-externs -Wno-format -std=c89 -U__STRICT_ANSI__ -c -I. -I. -I./..  -I/usr/include -DHAVE_CONFIG_H  -g -O2  visual.c
 ${CC} ${CFLAGS} -c visual.c
 
 #gcc -Wall -Wstrict-prototypes -Wnested-externs -Wno-format -std=c89 -U__STRICT_ANSI__ -c -I. -I. -I./..  -I/usr/include -DHAVE_CONFIG_H  -g -O2  overlay.c
 ${CC} ${CFLAGS} -c overlay.c
 
 #gcc -Wall -Wstrict-prototypes -Wnested-externs -Wno-format -std=c89 -U__STRICT_ANSI__ -c -I. -I. -I./..  -I/usr/include -DHAVE_CONFIG_H  -g -O2  usleep.c
 ${CC} ${CFLAGS} -c usleep.c
 
 #gcc -Wall -Wstrict-prototypes -Wnested-externs -Wno-format -std=c89 -U__STRICT_ANSI__ -L/usr/lib -o yaf-splash yaf-splash.o resources.o visual.o overlay.o usleep.o   -lSM -lICE -lXt -lXmu -lX11 -lXext 
${CC} ${CFLAGS} ${LDFLAGS} -o yaf-splash yaf-splash.o resources.o visual.o overlay.o usleep.o -lSM -lICE -lXt -lXmu -lX11 -lXext
}

do_install() {
 install -d ${D}/usr/bin
 install -m755 yaf-splash ${D}/usr/bin
}

HOMEPAGE = "http://www.nongnu.org/yaf-splash/"
SUMMARY = "popup window for shell scripts"
