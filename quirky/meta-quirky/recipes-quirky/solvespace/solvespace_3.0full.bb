# Recipe created by recipetool
# recipetool create -o solvespace_3.0full.bb http://distro.ibiblio.org/easyos/source/alphabetical/s/solvespace-3.0full.tar.gz

LICENSE = "GPL-3.0-only & MIT"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=8006d9c814277c1bfc4ca22af94b59ee \
                    file://extlib/mimalloc/LICENSE;md5=a88b9521ac8b519ef8f0577b1d08541d"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/s/solvespace-${PV}.tar.gz"
SRC_URI[md5sum] = "671c530fd275ebaa620b6c6500fa1fef"
SRC_URI[sha1sum] = "507173a80f6a52c009c5945d5793a65fa9bd3c44"
SRC_URI[sha256sum] = "c6d50a203412f0be61d6a240391f2eb65fa8ba17ed19722bd20a24ca73500188"
SRC_URI[sha384sum] = "63d71b73d5e6613a67ab337fb11fc192afaf72f26df9d4e7c3b8fe40b226faf65afc70f8615bc4581b303c6fdf4ec7a3"
SRC_URI[sha512sum] = "c128b3691c942e34d68ce32c2f14747c41093be4f2669924c16913d808659739ef9b84f28144725ac711b86a2017604dcfb1ced091545b83edb27e58d9df8bad"

DEPENDS = "cairo json-c zlib fontconfig libpng virtual/libgl freetype gtkmm3 atkmm \
           cairomm glibmm pangomm libglu mesa"

inherit cmake pkgconfig gettext mime-xdg

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release -DENABLE_OPENMP=ON -DCMAKE_INSTALL_PREFIX=/usr"

HOMEPAGE = "https://github.com/solvespace/solvespace"
SUMMARY = "Parametric 2d/3d CAD"
