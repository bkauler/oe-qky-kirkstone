# Recipe created by recipetool
# recipetool create -o xconsole_1.0.7.bb https://www.x.org/releases/individual/app/xconsole-1.0.7.tar.gz

require xorg-app-common.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=3ae977a68975db2fb36e2e77081085bb"

SRC_URI = "https://www.x.org/releases/individual/app/xconsole-${PV}.tar.gz"
SRC_URI[md5sum] = "83c007fb2324451c639c7f05e3d1add5"
SRC_URI[sha1sum] = "c20b3acc238e8f54bf988c3c57347b99d9fc545c"
SRC_URI[sha256sum] = "91bc7327643b1ca57800a37575930af16fbea485d426a96d8f465de570aa6eb3"
SRC_URI[sha384sum] = "3aaa66a86329f8bd2dbc8c2aafd9390182ecfd06a8d4a94d11ac6bba8f89729a87465064e441922da35d7c403aa4dfd8"
SRC_URI[sha512sum] = "9f185551035c235878e43f63b6c138a9640f74783a60356006e150a984fa3c70f6f7e36d112e5b736e1994df24a288cb43c549eaf5c1e9eec1a1988b0cc49c38"

DEPENDS += "libx11 libxmu libxaw libxt xorgproto gettext-native"

#inherit pkgconfig autotools gettext

EXTRA_OECONF = ""

