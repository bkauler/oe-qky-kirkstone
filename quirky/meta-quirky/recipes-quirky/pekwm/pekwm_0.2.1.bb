# Recipe created by recipetool
# recipetool create -o pekwm_0.2.1.bb https://github.com/pekwm/pekwm/releases/download/release-0.2.1/pekwm-0.2.1.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://github.com/pekwm/pekwm/releases/download/release-${PV}/pekwm-${PV}.tar.gz"

SRC_URI[md5sum] = "8b5021bea8f3d5c7d6223d9d16a1aea3"
SRC_URI[sha1sum] = "9ad3cf8a0428132a6fc5b439da8a58d557347d12"
SRC_URI[sha256sum] = "015e6eb9dffc816fa2d4a9b8cb345a52894f4dca5a01d0612f30242db87e0e50"

DEPENDS = "libpng freetype libx11 gawk-native \
   libxinerama libxext libjpeg-turbo libxpm libxft libxrandr \
   fontconfig libxcb libxrender libxau libxdmcp expat libice libsm"

inherit cmake pkgconfig gettext

EXTRA_OECMAKE = ""

SUMMARY = "Small and light window manager for X"
HOMEPAGE = "https://github.com/pekwm/pekwm"
