# Recipe created by recipetool
# recipetool create -o double-conversion_3.1.5.bb https://github.com/google/double-conversion/archive/refs/tags/v3.1.5.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ea35644f0ec0d9767897115667e901f"

SRC_URI = "https://github.com/google/double-conversion/archive/refs/tags/v${PV}.tar.gz"
SRC_URI[md5sum] = "e94d3a33a417e692e5600e75019f0272"
SRC_URI[sha1sum] = "ea899f825b61f374b310654927b41244d80b538e"
SRC_URI[sha256sum] = "a63ecb93182134ba4293fd5f22d6e08ca417caafa244afaa751cbfddf6415b13"
SRC_URI[sha384sum] = "c658427995e38f1442faee1ed8472f9c5d4c17c3eeebad4ebd3e255f40e8eef5479f99404cdc20e5ee57a953a2ca9a37"
SRC_URI[sha512sum] = "0aeabdbfa06c3c4802905ac4bf8c2180840577677b47d45e1c91034fe07746428c9db79260ce6bdbdf8b584746066cea9247ba43a9c38155caf1ef44e214180a"

inherit cmake

# ref: http://www.linuxfromscratch.org/blfs/view/svn/general/double-conversion.html
EXTRA_OECMAKE = "-DBUILD_SHARED_LIBS=ON"

HOMEPAGE = "https://github.com/google/double-conversion"
SUMMARY = "Efficient binary-decimal and decimal-binary conversion routines for IEEE doubles"


