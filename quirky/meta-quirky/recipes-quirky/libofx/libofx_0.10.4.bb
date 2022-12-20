# Recipe created by recipetool
# recipetool create -o libofx_0.10.4.bb https://sourceforge.net/projects/libofx/files/libofx/libofx-0.10.4.tar.gz

HOMEPAGE = "http://libofx.sourceforge.net/"
SUMMARY = "OFX banking protocol abstraction library"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4641e94ec96f98fabc56ff9cc48be14b"

SRC_URI = "https://sourceforge.net/projects/libofx/files/libofx/libofx-${PV}.tar.gz"

SRC_URI[md5sum] = "dda6963e4acbb1dee99f6242ad362ead"
SRC_URI[sha1sum] = "49ba965aea0a091c4ffb3e20fc04480c5b408432"
SRC_URI[sha256sum] = "416f30969a9978708efb1d166bf83e26fe97dfc160736028d9f34ad98fa78ed1"

DEPENDS = "opensp curl libxml2 libxml++"

inherit autotools pkgconfig

SROOT = "${WORKDIR}/recipe-sysroot"

EXTRA_OECONF = "--disable-html-docs --disable-doxygen --with-opensp-includes=${SROOT}/usr/include/OpenSP --with-opensp-libs=${SROOT}/usr/lib"

#not required to do this here, but interesting to know...
# originally is "-Wformat -Wformat-security -Werror=format-security"
SECURITY_STRINGFORMAT = "-Wformat"
