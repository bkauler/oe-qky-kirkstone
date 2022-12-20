# Recipe created by recipetool
# recipetool create -o awf_1.4.0.bb https://github.com/valr/awf/archive/refs/tags/v1.4.0.tar.gz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=9eef91148a9b14ec7f9df333daebc746"

SRC_URI = "https://github.com/valr/awf/archive/refs/tags/v${PV}.tar.gz"
SRC_URI[md5sum] = "c1d5d53630517fe34fe93f122fa0d713"
SRC_URI[sha256sum] = "bb14517ea3eed050b3fec37783b79c515a0f03268a55dfd0b96a594b5b655c78"

DEPENDS = "gtk+3 gtk+"

inherit pkgconfig autotools

EXTRA_OECONF = ""

do_configure:prepend() {
 touch ${S}/README
}

HOMEPAGE = "https://github.com/valr/awf"
SUMMARY = "A widget factory for gtk2 and gtk3"
