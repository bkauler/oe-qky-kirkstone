# Recipe created by recipetool
# recipetool create -o vcdimager_2.0.1.bb https://ftp.gnu.org/gnu/vcdimager/vcdimager-2.0.1.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "https://ftp.gnu.org/gnu/vcdimager/vcdimager-${PV}.tar.gz \
           file://01-vcdimager-hack-bitwise2.patch"
SRC_URI[md5sum] = "3890d73da62d0607c87962c41cd33a29"
SRC_URI[sha256sum] = "67515fefb9829d054beae40f3e840309be60cda7d68753cafdd526727758f67a"

DEPENDS = "libcdio libcdio-paranoia popt libxml2 zlib bzip2"

inherit pkgconfig autotools

EXTRA_OECONF = ""

HOMEPAGE = "http://www.gnu.org/software/vcdimager/"
SUMMARY = "Fullfeatured mastering suite for Super Video CDs"
