# Recipe created by recipetool
# recipetool create -o pflask_git.bb https://github.com/ghedo/pflask.git
# ref: https://github.com/OverC/meta-overc/blob/master-oci/meta-cube/recipes-containers/pflask/pflask_git.bb

# BK note, source pkg has 'waf' in it. 2020-09-19 the one in pyro repo doesn't.
# anyway, that waf is a python2 script. waf-2.0.20 handles python3.

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=c2cd5f772e6f9b401d92014b0d1ebccd"

#2020-09-19 don't have that repo anymore...
#SRC_URI = "http://distro.ibiblio.org/easyos/source/oe/thud/pflask-20180925.tar.gz"
#SRC_URI[md5sum] = "b42fced1a7c6023c1e772d9b0fb0368f"
#SRC_URI[sha256sum] = "0fae0da5c6db8a9726f5186b416ee50321b374db48ccc4d3693fe6456f06ae8d"
#S = "${WORKDIR}/pflask-20180925"

SRC_URI = "http://distro.ibiblio.org/easyos/source/oe/pyro/pflask-${PV}master.tar.gz \
  file://waf-2.0.20"

SRC_URI[md5sum] = "3dec4d7b15f1fb746a9d0df0aa571081"
SRC_URI[sha1sum] = "78c0ff51540ed8f94d743ea738cbef1dfbbbf1e7"
SRC_URI[sha256sum] = "d1334b277ffe4e0e05e535a055851402dddc961ea3eea9a850da4699c24bc824"
SRC_URI[sha384sum] = "f2aca865cf0f1d2f5890efa1cacfa500a8fdf3c6f8089820620f2d17d3256db57d264535eb75b7886da3ce02ce30b740"
SRC_URI[sha512sum] = "13221d474a539ffbfe0006369dc85b34f681eb3f26fe04a5e87f5db0e9e251638329e28a4189b6ade0db91acfebc018bbdc754e71cd4d3de3dc8b1b33a389388"

S = "${WORKDIR}/${BPN}-${PV}master"

# 2020-09-18 removed 'pythonnative', try 'python3native'
#inherit pkgconfig waf python3native
inherit pkgconfig python3native

# dbus and libcap-ng are optional deps, but i don't think need dbus...
DEPENDS = "libcap-ng"

do_configure:prepend () {
	cp -f ${WORKDIR}/waf-2.0.20 ./waf
	chmod 755 waf
}

do_configure () {
	./waf configure --prefix=${prefix}
}

#20221210
do_compile:prepend() {
 #fix multiple definitions of use_syslog
 #sed -i -e 's%^int use_syslog%use_syslog%' ${S}/src/printf.c
 sed -i -e 's%^int use_syslog%extern int use_syslog%' ${S}/src/printf.h
}

do_compile () {
	./waf build
}

do_install () {
	install -d ${D}/usr/bin
	install -m 755 build/pflask ${D}/usr/bin
}

SECTION = "devel"
SUMMARY = "Light weight container runtime"
HOMEPAGE = "https://ghedo.github.io/pflask/"
