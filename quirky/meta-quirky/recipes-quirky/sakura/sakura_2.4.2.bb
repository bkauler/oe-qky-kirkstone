# 20221029 changed vte to vte9 which is the old gtk2 version.

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://GPL;md5=40b505c793557ad69a6307d6b00940a5"

#PR = "r1"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

# patch to compile manually...
SRC_URI = "http://distro.ibiblio.org/easyos/source/oe/pyro/sakura-${PV}.tar.bz2 \
           file://sakura-2.4.2-compile-hack.patch"

SRC_URI[md5sum] = "46669519c77f7402b2de24cdefe251bb"
SRC_URI[sha1sum] = "24b98c42c057da39f57c281a4726901952774641"
SRC_URI[sha256sum] = "c5e6242ca4afba3ad43731939f7d2a728bd40056a93de50f34e5bf76a696fad6"
SRC_URI[sha384sum] = "d589bb360922c7890918df5f00799fb2bd3efd7cddba54c463770c7e35e26077ce75936940cbfac015012b2338a0efbd"
SRC_URI[sha512sum] = "97a4721771d9512a9b78ddcde3425cf5dd08540ec15ef4236e032319db7db35149ba0223a64849aca70b9e5b091c9bdf237a7be852cb54b79f6d79ab17945d3b"

DEPENDS = "gtk+ glib-2.0 vte9"

# REMOVE THIS:
#inherit cmake pkgconfig
## do_compile fail: "src/sakura.c:24:20: fatal error: stdlib.h: No such file or directory"
## tried this, no go:  -DCMAKE_NO_SYSTEM_FROM_IMPORTED=1
#EXTRA_OECMAKE = "-DCMAKE_INSTALL_PREFIX=/usr -DCMAKE_NO_SYSTEM_FROM_IMPORTED=1"
## re above error, try this
#SINC = "${WORKDIR}/recipe-sysroot/usr/include"
#CFLAGS += "-I${SINC}"

inherit pkgconfig

do_configure () {
    true
}

do_compile () {
    ${CC} -o sakura src/sakura.c ${CFLAGS} ${LDFLAGS} `pkg-config --cflags --libs gtk+-2.0 vte` -lX11
}

do_install () {
    install -d ${D}/usr/bin
    install -m755 sakura ${D}/usr/bin
}

HOMEPAGE = "http://www.pleyades.net/david/projects/sakura"
SUMMARY = "sakura is a terminal emulator based on GTK and libvte"
