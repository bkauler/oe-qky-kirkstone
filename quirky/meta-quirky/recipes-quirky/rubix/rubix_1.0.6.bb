# Recipe created by recipetool
# recipetool create -o rubix_1.0.6.bb http://sed.free.fr/rubix/rubix-1.0.6.tar.bz2

LICENSE = "UCB"
LIC_FILES_CHKSUM = "file://LICENCE;md5=ba4d0847af1fc9755496301b7e68c188"

SRC_URI = "http://sed.free.fr/rubix/rubix-${PV}.tar.bz2"
SRC_URI[md5sum] = "76346b49b67dd7ade0f69b4ae77e3f3a"
SRC_URI[sha256sum] = "ccff4c98373f76c6bab751f5f5a60df7ad8691c79b56157e4aba2dd76f501a87"

DEPENDS = "libx11"

# NOTE: this is a Makefile-only piece of software.

CFLAGS += "-fomit-frame-pointer -ffast-math -DGAMESDIR=\"/usr/games\" -DENGLISH"
SROOT = "${WORKDIR}/recipe-sysroot"

do_configure () {
    true
}

#20200921 hmmm, had to move -lX11 to the end, and add -lm
do_compile () {
    for aCFILE in cube event fillpoly line main screen
    do
      ${CC} -c ${aCFILE}.c ${CFLAGS} -I${SROOT}/usr/include -lX11
    done
    ${CC} -o rubix ${LDFLAGS} cube.o event.o fillpoly.o line.o main.o screen.o -lX11 -lm
}

do_install () {
    install -d ${D}/usr/bin
    install -m755 rubix ${D}/usr/bin
}

HOMEPAGE = "http://sed.free.fr/rubix/"
SUMMARY = "rubix is a Rubiks cube system for the LinuxX11 operating system."
