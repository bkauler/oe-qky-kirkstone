# Recipe created by recipetool
# recipetool create -o sudo-sh_1.0.bb https://distro.ibiblio.org/easyos/source/alphabetical/s/sudo-sh-1.0.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.GPL-2.0-only;md5=4ee23c52855c222cba72583d301d2338"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/s/sudo-sh-${PV}.tar.gz"

SRC_URI[md5sum] = "3fd850d348605638ff734f0c0491a193"
SRC_URI[sha256sum] = "8b8253a857f879bd3fe415b5eb6275aa5f0330a3dcd69525adc97e1c0a48b24c"

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
