# Recipe created by recipetool
# recipetool create -o brlaser_7-pre.bb http://distro.ibiblio.org/easyos/source/alphabetical/b/brlaser-7-pre.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/b/brlaser-${PV}.tar.gz"
SRC_URI[md5sum] = "cdb4640d07b027916ae7a5588de2ef64"
SRC_URI[sha1sum] = "900b24c5c0637aef33e0207f44b4f8f3d0db342c"
SRC_URI[sha256sum] = "718eb1a77a5f381f7d9ed65f621d8170e100c8c0d412c2efec81f86f1671b58f"
SRC_URI[sha384sum] = "eab2fc1b54a7b583cf2f6b74f82dd1175a3f17a388d187f3bfb5d0709d7eb38a957f4d6d56631caba424fd718933be07"
SRC_URI[sha512sum] = "770c0059657c5f9a958d7d457aedb9bea30d871d7cc4de58e30f5b3ed8a557920394d5d14be804ddca447178067963c7017238c0bf70b32817ea9cb9ddff729c"

DEPENDS = "cups ghostscript cups-filters"

inherit cmake

EXTRA_OECMAKE = ""

HOMEPAGE = "https://github.com/pdewacht/brlaser"
SUMMARY = "cups driver for many brother printers"

