# Recipe created by recipetool
# recipetool create -o setcolortemperature_20210313.bb http://distro.ibiblio.org/easyos/source/alphabetical/s/setcolortemperature-20210313.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4d92310652ef3543d1976ddc874e6ce3"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/s/setcolortemperature-${PV}.tar.gz"
SRC_URI[md5sum] = "fbd17d77b55de061460c15f829008449"
SRC_URI[sha1sum] = "134cfe3b0d8c3e2337f3a955c447bd71ccdbf857"
SRC_URI[sha256sum] = "f7e45617923dc5457fa041ab24b3359d5305d2482e43b397066b39d53d41037a"
SRC_URI[sha384sum] = "ec212fd67c2fe4cddbc7ae0678bd488ba0bc9cff2196bdd9a0be5cf54ce263f145fb9cf8de17d7d5a44581590ba45550"
SRC_URI[sha512sum] = "213ec32512192822a22d1a8b1d8a00acfba322079e94b54d956fc43136155ecf80a698a36c5fb01fb42ef9da0d6e26d09c8fdab651cc98ada595d724f6a09b69"

DEPENDS = "libx11 libxrandr libxcb libxext libxrender libxau libxdmcp"

do_configure () {
 true
}

do_compile () {
	oe_runmake
}

do_install () {
	oe_runmake install DESTDIR=${D}
}

HOMEPAGE = "https://github.com/Tookmund/setcolortemperature"
SUMMARY = "Screen temperature utility"
