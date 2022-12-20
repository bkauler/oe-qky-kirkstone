SUMMARY = "A collection of multi-dimensional data structures and indexing algorithms"
HOMEPAGE = "https://gitlab.com/mdds/mdds"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=60a6093677ded88b5e28677e52a0c011"

inherit autotools-brokensep pkgconfig

#20221207 broken link: http://kohei.us/files/mdds/src/mdds-${PV}.tar.bz2
SRC_URI = " \
    https://distro.ibiblio.org/easyos/source/oe/dunfell/mdds-1.5.0.tar.bz2 \
    file://0001-configure.ac-remove-fixed-paths-causing-trouble-when.patch \
"
SRC_URI[md5sum] = "52cb08e92fec8842a3724bd89051f9d3"
SRC_URI[sha256sum] = "144d6debd7be32726f332eac14ef9f17e2d3cf89cb3250eb31a7127e0789680d"

S = "${WORKDIR}/mdds-${PV}"

DEPENDS = "boost"

BBCLASSEXTEND = "native"
