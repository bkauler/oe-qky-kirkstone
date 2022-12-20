# Recipe created by recipetool
# recipetool create -o ccrypt_1.11.bb http://ccrypt.sourceforge.net/download/1.11/ccrypt-1.11.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=1ba78d21e3f0c5d658ce9955a95c6061"

SRC_URI = "http://ccrypt.sourceforge.net/download/${PV}/ccrypt-${PV}.tar.gz"
SRC_URI[md5sum] = "262573b04416b3b947f0d38807ec5246"
SRC_URI[sha1sum] = "6d20a4db9ef7caeea6ce432f3cffadf10172e420"
SRC_URI[sha256sum] = "b19c47500a96ee5fbd820f704c912f6efcc42b638c0a6aa7a4e3dc0a6b51a44f"
SRC_URI[sha384sum] = "96dd22b4dc14b07fcf8a7947f3024f38e77a7dd83ce02fbf3a2e1a3714a00c710e2bf81966d56754252c14eb1abd31ca"
SRC_URI[sha512sum] = "75c2b93e855d36e717d3b7cabee7ce43ce372a21c8291beb43f24fd69e11114bb0e19e6dd03ec5d901e7e60eac9351afab65c346b7304b16054b91d392050313"

#DEPENDS = "libxcrypt gettext gettext-native"
DEPENDS = "libxcrypt"

#inherit gettext autotools pkgconfig
inherit autotools pkgconfig

EXTRA_OECONF = "--disable-nls"

do_compile:prepend() {
 echo -e '\n\nall:\n\nall-no:\n\nall-yes:\n\nall-no-yes:\n\nall-nono:\n\ncheck:\n\ninstall:\n\ninstall-exec:\n\ninstall-data:\n\ninstall-strip:\n\ninstall-dvi install-html install-info install-ps install-pdf:\n\ninstalldirs:\n\ninstallcheck:\n\nuninstall:\n\ninfo dvi ps pdf html:\n\nclean:\n\ndistclean:\n\nMakefile:\n\n' > ${B}/intl/Makefile
}

HOMEPAGE = "http://ccrypt.sourceforge.net"
SUMMARY = "A utility to encrypt files."

