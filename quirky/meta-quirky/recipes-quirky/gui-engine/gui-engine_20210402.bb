# Recipe created by recipetool
# recipetool create -o gui-engine_20210402.bb http://distro.ibiblio.org/easyos/source/alphabetical/g/gui-engine-20210402.tar.gz

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7abc10426c1b5831606b008fa3b712be"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/g/gui-engine-${PV}.tar.gz"

SRC_URI[md5sum] = "2eff6a0c239e536db3ad7cfe1305a30c"
SRC_URI[sha256sum] = "8928322216ff3c677635287c0e36de334b4e8d1e53241fadd0ec83cda065b731"

DEPENDS = "libsdl-fb-static libsdl-gfx-fb-static"

CFLAGS += "-I${WORKDIR}/recipe-sysroot/usr/include/SDL -D_GNU_SOURCE=1 -D_REENTRANT"
LDFLAGS += "-static -lSDL -lpthread -lSDL_gfx"

do_configure () {
	sed -i '/^CFLAGS/d' Makefile
	sed -i '/^LDFLAGS/d' Makefile
	true
}

do_compile () {
	oe_runmake
}

do_install () {
 install -d ${D}/usr/bin
 install -m755 example ${D}/usr/bin
}

