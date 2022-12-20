SUMMARY = "A collection of multi-dimensional data structures and indexing algorithms"
HOMEPAGE = "https://gitlab.com/mdds/mdds"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=60a6093677ded88b5e28677e52a0c011"

inherit autotools-brokensep pkgconfig

#20221207 broken link: http://kohei.us/files/mdds/src/mdds-${PV}.tar.bz2
SRC_URI = " \
    https://distro.ibiblio.org/easyos/source/oe/dunfell/mdds-1.3.1.tar.bz2 \
    file://0001-configure.ac-remove-fixed-paths-causing-trouble-when.patch \
"
SRC_URI[md5sum] = "913ef8b1680ad8b8407c3a4bb0c82634"
SRC_URI[sha256sum] = "dcb8cd2425567a5a5ec164afea475bce57784bca3e352ad4cbdd3d1a7e08e5a1"

S = "${WORKDIR}/mdds-${PV}"

DEPENDS = "boost"

BBCLASSEXTEND = "native"
