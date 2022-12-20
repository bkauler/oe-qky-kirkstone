# Recipe created by recipetool
# recipetool create -o arp-scan_1.9.8-p1.bb https://distro.ibiblio.org/easyos/source/alphabetical/a/arp-scan-1.9.8-p1.tar.gz
# 1.9.8 ref: https://salsa.debian.org/pkg-security-team/arp-scan

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/a/arp-scan-${PV}.tar.gz"

SRC_URI[md5sum] = "4798cfdfd81a0ce0317ffdef597f67d5"
SRC_URI[sha1sum] = "a236a62b6207bc578609ec4e159405e2cfee8a11"
SRC_URI[sha256sum] = "40e9fbe7fcfdc80d863ed48034d6971cb7265b3ebb1edb8ebbb93bf801f69ed7"

DEPENDS = "libpcap"

RDEPENDS_arp-scan = "perl"

inherit autotools pkgconfig

EXTRA_OECONF = ""

ERROR_QA:remove = "file-rdeps"

HOMEPAGE = "https://github.com/royhills/arp-scan"
SUMMARY = "Utility to scan network of a certain interface for alive hosts"
