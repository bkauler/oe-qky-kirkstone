# pup-tools recipe created by recipetool
# recipetool create -o pup-tools_20201010.bb http://distro.ibiblio.org/easyos/source/alphabetical/p/pup-tools-20201010.tar.gz
# modified to compile c apps only, statically with musl.

#PR = "r0"
# 20210121 PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"

DEPENDS = "libx11 gtk+"

inherit gettext pkgconfig

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://readme.txt;md5=9add27eb01e81e0a682a99520030f7ce"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/p/pup-tools-${PV}.tar.gz"
SRC_URI[md5sum] = "32e04a3eaf3a28524e68106eff480625"
SRC_URI[sha256sum] = "fb3078c4a71864162c6b9197e5d13883110d671800f5e11b87d41f67a31db019"

S = "${WORKDIR}/pup-tools-${PV}"

do_configure() {
 true
}

do_compile () {
    
	cd gcc
	#this one fails:  pup_event_frontend_d
	for aFILE in bitflip printcols truncate vercmp getlocalip
	do
	 ${CC} -o ${aFILE} ${aFILE}.c ${CFLAGS} ${LDFLAGS} -static
	done
	# this also fails...
	#${CC} -lX11 getcurpos.c -o getcurpos ${CFLAGS} ${LDFLAGS} -static
	cd ..
}

do_install () {
    install -d ${D}${bindir}
    install -m755 gcc/vercmp ${D}${bindir}
    install -m755 gcc/truncate ${D}${bindir}
    install -d ${D}${sbindir}
    install -m755 gcc/bitflip ${D}${sbindir}
    #install -m755 gcc/getcurpos ${D}${sbindir}
    install -m755 gcc/printcols ${D}${sbindir}
    install -m755 gcc/getlocalip ${D}${sbindir}
    install -d ${D}/usr/local/pup_event
    #install -m755 gcc/pup_event_frontend_d ${D}/usr/local/pup_event
}

FILES:${PN} += " /usr/local/petget /usr/local/pup_event"

HOMEPAGE = "http://distro.ibiblio.org/easyos/project/woofq"
SUMMARY = "Core utilities used in Puppy and derivatives."
