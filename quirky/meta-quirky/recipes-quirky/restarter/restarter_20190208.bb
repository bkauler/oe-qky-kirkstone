# Recipe created by recipetool
# recipetool create -o restarter_20190208.bb http://distro.ibiblio.org/easyos/source/alphabetical/r/restarter-20190208.tar.gz

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/PD;md5=b3597d12946881e13cb3b548d1173851"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/r/restarter-${PV}.tar.gz"
SRC_URI[md5sum] = "e8d69fcdcfe2aef87a080f7c4dcb54de"
SRC_URI[sha1sum] = "84427abe359546582e968ab6553f690f737a6351"
SRC_URI[sha256sum] = "af9df24d4fa4ae2758625349f444bf5b20d71925e8bede31d38a02e00e5a13ea"
SRC_URI[sha384sum] = "569dbecf7c993f6b2a8586557fb1e9bc09ccd1681b599a3a05341732863e7d564395f0af275ba844d16b5c7736b111e0"
SRC_URI[sha512sum] = "625345eac799a6a9747c65edbba9981e049dc93dd097828efe44e73e7acf5ce304b6c436360e56657f12b48dc34bb75b2c59d3b3d7c894f2baab9b279889814c"

#20210105
CFLAGS += "-I."

do_configure () {
 #true
 #20210105 fix Makefile...
 sed -i -e 's%^CC=%#CC=%' ${S}/Makefile
 sed -i -e 's%^CFLAGS=%#CFLAGS=%' ${S}/Makefile
}

do_compile () {
 oe_runmake
}

do_install () {
 install -m 755 -d ${D}${sbindir}
 install -m 755 ${S}/restarter ${D}${sbindir}
}

HOMEPAGE = "https://bitbucket.org/sivann/restarter/src/master/"
SUMMARY = "Command supervisor and restarter. Restarts a command on crash or exit."
