# Recipe created by recipetool
# recipetool create -o sudo-sh_1.0.bb https://distro.ibiblio.org/easyos/source/alphabetical/s/sudo-sh-1.0.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.GPL-2.0-only;md5=4ee23c52855c222cba72583d301d2338"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/s/sudo-sh-${PV}.tar.gz"

SRC_URI[md5sum] = "8a34db6c92564d74eaeae9bc204d239c"
SRC_URI[sha256sum] = "c4642eedc7a68d0a0708ebe7c586149ca1e8d2b248e2aa1d56a5ee9d5e81d5c7"

do_configure () {
 true
}

do_compile () {
 ${CC} -o sudo-sh sudo-sh.c ${CFLAGS} ${LDFLAGS}
}

do_install () {
 install -d ${D}/usr/bin
 #need setuid...
 install -m4711 sudo-sh ${D}/usr/bin
}

SUMMARY = "mini-sudo"
HOMEPAGE = "https://bkhome.org/news"
