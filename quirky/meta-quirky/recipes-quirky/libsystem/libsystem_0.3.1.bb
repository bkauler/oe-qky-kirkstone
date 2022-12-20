# Recipe created by recipetool
# recipetool create -o libsystem_0.1.6-p1.bb http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/libSystem-0.1.6-patched1.tar.bz2

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6"

SRC_URI = "https://www.defora.org/os/download/download/4446/libSystem-0.3.1.tar.gz"
SRC_URI[md5sum] = "259b715bb6a329eb1939760007ade8d8"
SRC_URI[sha256sum] = "6f82f6fbee516d33e14311bcdc86dae0509c86c8f1e594d4573398b12a2a5b89"

S = "${WORKDIR}/libSystem-0.3.1"

DEPENDS = "openssl gnet"

# BK note, Makefile only.

do_configure () {
    sed -i -e 's%^CC	=%# CC	=%' ${S}/src/Makefile
    sed -i -e 's%^CFLAGS	=%CFLAGS	+=%' ${S}/src/Makefile
    sed -i -e 's%^CC	=%# CC	=%' ${S}/tools/Makefile
    sed -i -e 's%^CFLAGS	=%CFLAGS	+=%' ${S}/tools/Makefile
    sed -i -e 's%^LDFLAGS	=%LDFLAGS	+=%' ${S}/tools/Makefile
    
    #disable tests...
    echo -e 'all: \n\ninstall: \n\nclean: \n\nuninstall: \n' > ${S}/tests/Makefile
    #tools failed also...
    echo -e 'all: \n\ninstall: \n\nclean: \n\nuninstall: \n' > ${S}/tools/Makefile
    #and...
    echo -e 'all: \n\ninstall: \n\nclean: \n\nuninstall: \n' > ${S}/doc/Makefile
    
    #fix installing to /usr/local....
    sed -i -e 's%/usr/local%/usr%' ${S}/config.h
    sed -i -e 's%/usr/local%/usr%' ${S}/Makefile
    sed -i -e 's%/usr/local%/usr%' ${S}/data/Makefile
    sed -i -e 's%/usr/local%/usr%' ${S}/src/Makefile
    sed -i -e 's%/usr/local%/usr%' ${S}/include/Makefile
    sed -i -e 's%/usr/local%/usr%' ${S}/include/System/Makefile
    sed -i -e 's%/usr/local%/usr%' ${S}/src/python/Makefile
}

do_compile () {
	oe_runmake
}

do_install () {
	oe_runmake install DESTDIR=${D}
}

HOMEPAGE = "https://www.defora.org/os/download/189/libSystem"
SUMMARY = "A system library needed by surfer web browser."
