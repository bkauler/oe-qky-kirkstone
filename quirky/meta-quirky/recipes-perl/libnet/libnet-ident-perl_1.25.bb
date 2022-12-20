# Recipe created by recipetool
# recipetool create -o libnet-ident-perl_1.25.bb http://deb.debian.org/debian/pool/main/libn/libnet-ident-perl/libnet-ident-perl_1.25.orig.tar.gz

SUMMARY = "Lookup username on remote end of TCP/IP connection"
HOMEPAGE = "https://metacpan.org/dist/Net-Ident"
SECTION = "Development/Libraries"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://deb.debian.org/debian/pool/main/libn/libnet-ident-perl/libnet-ident-perl_${PV}.orig.tar.gz \
           file://makefile_mod-perl.patch \
           file://compat-export.patch"

SRC_URI[md5sum] = "1351eae1ade13936808cc829564abf97"
SRC_URI[sha1sum] = "0e849f1f0c9aaff675d78c7cbeee4cfd62534143"
SRC_URI[sha256sum] = "2e5bd58b01c2a66e8049a2f8d9c93e1b5f6dce53e0ee3a481ce6a6f411f2c8f8"

S = "${WORKDIR}/Net-Ident-${PV}"

inherit cpan

RDEPENDS:${PN} = "perl"

