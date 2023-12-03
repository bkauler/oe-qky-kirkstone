# Recipe created by recipetool
# recipetool create -o solvespace_3.1-e7c0c16-20231117.bb https://distro.ibiblio.org/easyos/source/alphabetical/s/solvespace-3.1-e7c0c16-20231117.tar.gz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=8006d9c814277c1bfc4ca22af94b59ee"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/s/solvespace-${PV}.tar.gz"

SRC_URI[md5sum] = "bd8da9a5bda902922e943a1c86836e1e"
SRC_URI[sha256sum] = "4bf760df33734b736226ca6363abf1e7a2d1c5d3ecb3929672e406483f952035"

DEPENDS = "cairo json-c zlib fontconfig libpng virtual/libgl freetype gtkmm3 atkmm \
           cairomm glibmm pangomm libglu mesa"

inherit cmake pkgconfig gettext mime-xdg

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release -DENABLE_OPENMP=ON \
     -DCMAKE_INSTALL_PREFIX=/usr -DENABLE_TESTS=OFF"

HOMEPAGE = "https://github.com/solvespace/solvespace"
SUMMARY = "Parametric 2d/3d CAD"
