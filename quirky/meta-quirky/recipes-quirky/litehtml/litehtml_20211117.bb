# Recipe created by recipetool
# recipetool create -o litehtml_20211117.bb https://distro.ibiblio.org/easyos/source/alphabetical/l/litehtml-20211117.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=55d411204c54bf2524f471635a7d306a"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/l/litehtml-${PV}.tar.gz"
SRC_URI[md5sum] = "7fe2b555e7a494f60694c67c475b2d13"
SRC_URI[sha256sum] = "c92c9c5fafccfe172700b178f447a596a749cfb373773ed8f7a2ad258c81ed75"

# vim-native has 'xxd' utility...
DEPENDS = "vim-native ncurses gettext-native"

inherit cmake

# 20220610 aarch64: add -DBUILD_TESTING=false
EXTRA_OECMAKE = "-DBUILD_SHARED_LIBS=true -DBUILD_TESTING=false"

HOMEPAGE = "https://github.com/litehtml/litehtml"
SUMMARY = "Lightweight HTML/CSS rendering engine"
