# Recipe created by recipetool
# recipetool create -o obexfs_0.12.bb https://sourceforge.net/projects/openobex/files/obexfs/0.12/obexfs-0.12.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "https://sourceforge.net/projects/openobex/files/obexfs/${PV}/obexfs-${PV}.tar.gz"
SRC_URI[md5sum] = "0f505672b025cdb505e215ee707a2e2f"
SRC_URI[sha1sum] = "dbe6d006db7122142fcc14dc473f9bf1b1c8a99a"
SRC_URI[sha256sum] = "72a62aeb4d4decff962f22239513651843857492e9d6b50f515c9bf9fccd73bc"
SRC_URI[sha384sum] = "bbbf44cb302a045506873f967f81c59b3d1dde6b54e1074cdb0a3e1db469a5569144cee96ec6350a29af9f76bdedf778"
SRC_URI[sha512sum] = "f9b9c7c220271b7bf538d748beb3cb7fabd0377ef74855a959f7595755904be739ed5995909f423a40218d05a71de6b48a5ef471e2dc0934164aba2805c7e2e0"

DEPENDS = "fuse openobex obexftp"

inherit pkgconfig autotools

EXTRA_OECONF = ""

HOMEPAGE = "http://dev.zuckschwerdt.org/openobex/wiki/ObexFs/"
SUMMARY = "FUSE based filesystem using ObexFTP and OpenOBEX"
