# Recipe created by recipetool
# recipetool create -o libxaw3dxft_1.6.2h.bb http://sourceforge.net/projects/sf-xpaint/files/libxaw3dxft/libXaw3dXft-1.6.2h.tar.bz2

HOMEPAGE = "http://sourceforge.net/projects/sf-xpaint/files/libxaw3dxft/"
SUMMARY = "based on X Athena Widget Set, lbXaw3d with freetype"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=8d5e9a1707c9f4300b992b34e21ac54c"

SRC_URI = "http://sourceforge.net/projects/sf-xpaint/files/libxaw3dxft/libXaw3dXft-${PV}.tar.bz2"

SRC_URI[md5sum] = "c32029b710abeed8e32d48536880d515"
SRC_URI[sha256sum] = "5d749e3d682d2fb60959ebc3bce3228ca5c949a46aafc349e89a77f6c6ed830b"

S = "${WORKDIR}/libXaw3dXft-${PV}"

#no:  xmlto-native
#do not know if all those -native really needed...
DEPENDS = "bison-native libxmu libxt flex-native libxext libxft libxpm \
           util-macros xorgproto virtual/libx11 libxau freetype freetype-native \
           libxft-native libxau-native util-macros-native libxft-native \
           libxext-native"

inherit pkgconfig autotools features_check

#--enable-internationalization provides utf8 support.
#--enable-multiplane-bitmaps for xpm support.
EXTRA_OECONF = "--disable-specs --without-groff --without-ps2pdf --without-fop \
     --without-xmlto --enable-internationalization --enable-multiplane-bitmaps \
    --enable-gray-stipples --enable-arrow-scrollbars"

REQUIRED_DISTRO_FEATURES ?= "x11"

