# Recipe created by recipetool
# recipetool create -o hwclock-static_2.37.4.bb https://mirrors.edge.kernel.org/pub/linux/utils/util-linux/v2.37/util-linux-2.37.4.tar.xz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://mirrors.edge.kernel.org/pub/linux/utils/util-linux/v2.37/util-linux-${PV}.tar.xz"

SRC_URI[md5sum] = "755919e658c349cad9e1c7c771742d48"
SRC_URI[sha256sum] = "634e6916ad913366c3536b6468e7844769549b99a7b2bf80314de78ab5655b83"

S = "${WORKDIR}/util-linux-${PV}"

DEPENDS = "bison-native"

inherit pkgconfig gettext autotools

EXTRA_OECONF = "--disable-shared --enable-static --disable-all-programs --disable-nls \
     --disable-tls --disable-widechar --disable-libuuid --disable-libblkid \
     --disable-libmount --disable-libsmartcols --disable-libfdisk --without-util \
     --enable-hwclock --without-btrfs --without-python --without-systemd \
     --without-cap-ng --without-readline --without-slang --without-ncurses \
     --without-ncursesw --without-udev --disable-colors-default"

SUMMARY = "A suite of basic system administration utilities"
HOMEPAGE = "https://en.wikipedia.org/wiki/Util-linux"

LDFLAGS:append = " -static"

do_compile:prepend() {
 sed -i -e 's%^LDADD = .*%LDADD = %' ${B}/Makefile
 #sed -i -e 's%^hwclock_LDADD = %hwclock_LDADD = -static %' ${B}/Makefile
}

do_compile:append() {
 ${CC} -static -o hwclock-static sys-utils/hwclock-hwclock.o sys-utils/hwclock-hwclock-parse-date.o sys-utils/hwclock-hwclock-cmos.o sys-utils/hwclock-hwclock-rtc.o lib/hwclock-monotonic.o  ./.libs/libcommon.a -lm ${CFLAGS} ${LDFLAGS}
}

do_install() {
 install -d ${D}/${bindir}
 install ${B}/hwclock-static ${D}/${bindir}
}
