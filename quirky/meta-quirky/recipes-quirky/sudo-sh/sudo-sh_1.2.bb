# Recipe created by recipetool
# recipetool create -o sudo-sh_1.0.bb https://distro.ibiblio.org/easyos/source/alphabetical/s/sudo-sh-1.0.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.GPL-2.0-only;md5=4ee23c52855c222cba72583d301d2338"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/s/sudo-sh-${PV}.tar.gz"

SRC_URI[md5sum] = "0d830f9ed0bbf1b1282925c23883fa21"
SRC_URI[sha256sum] = "23ec0aa029e8d551f5734fca64473d769ccd7dbce321669240033e50c9c1779b"

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
HOMEPAGE = "https://bkhome.org/news/202306/light-weight-replacement-for-sudo.html"
