# Recipe created by recipetool
# recipetool create -o fscryptctl-static_1.0.0.bb https://github.com/google/fscryptctl/archive/refs/tags/v1.0.0.tar.gz

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "https://github.com/google/fscryptctl/archive/refs/tags/v${PV}.tar.gz"
SRC_URI[md5sum] = "1013d00ac166b233631100e5905004cc"
SRC_URI[sha1sum] = "92a614f33ce7e563d65d4ebe2453d217971a5216"
SRC_URI[sha256sum] = "3828d5ad9b93664b9fec0174fc5d8e96d7b021a7896da74efe18fabe5f01d638"
SRC_URI[sha384sum] = "a525eb11623d4aef14626ed914184b71f4c3bde669f53fc95da7678fe966cb820b2a8cfd2ee9e3fd915112f5d7afca83"
SRC_URI[sha512sum] = "078fc63c7b1602725f2227a8d6d755a09f3ad60e0a64cc1503d0eabb8360cff0e5b5d76f7e8ccbba1e517be92c8d035293be264caba151df9046e459660feeca"

S = "${WORKDIR}/fscryptctl-${PV}"

LDFLAGS += " -static"

do_configure () {
	true
}

do_compile () {
	oe_runmake
}

do_install () {
	#oe_runmake install
	install -d ${D}/bin
	install -m755 fscryptctl ${D}/bin
}

SUMMARY = "fscrypt management utility for ext4 and f2fs"
HOMEPAGE = "https://github.com/google/fscryptctl"

