
#BK 20230704 only build libdevmapper, not entire lvm2.
#require lvm2.inc
HOMEPAGE = "https://www.sourceware.org/lvm2/"
SECTION = "utils"
SUMMARY = "Manage logical volumes in Linux."
LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=12713b4d9386533feeb07d6e4831765a \
                    file://COPYING.LIB;md5=fbc093901857fcd118f065f900982c24"
DEPENDS += "libaio"
SRC_URI = "git://sourceware.org/git/lvm2.git;branch=main \
           file://lvm.conf \
           file://0001-implement-libc-specific-reopen_stream.patch \
           file://0002-Guard-use-of-mallinfo-with-__GLIBC__.patch \
           file://0004-tweak-MODPROBE_CMD-for-cross-compile.patch \
           file://0001-Avoid-bashisms-in-init-scripts.patch \
           file://0005-do-not-build-manual.patch \
           file://0006-start-lvm2-monitor.service-after-tmp.mount.patch \
           file://reproducible-build.patch \
           "
SRCREV = "3e8bd8d1bd70691f09a170785836aeb4f83154e6"
S = "${WORKDIR}/git"
UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\d+(\_\d+)+)"
inherit autotools-brokensep pkgconfig
# odirect is always enabled because there currently is a bug in
# lib/device/dev-io.c which prevents compiling without it. It is
# better to stick to configurations that were actually tested by
# upstream...
PACKAGECONFIG ??= "odirect"
PACKAGECONFIG[odirect] = "--enable-o_direct,--disable-o_direct"
# Unset user/group to unbreak install.
EXTRA_OECONF = "--with-user= \
                --with-group= \
                --enable-realtime \
                --enable-cmdlib \
                --enable-pkgconfig \
                --with-usrlibdir=${libdir} \
                --with-systemdsystemunitdir=${systemd_system_unitdir} \
                --disable-thin_check_needs_check \
                --with-thin-check=${sbindir}/thin_check \
                --with-thin-dump=${sbindir}/thin_dump \
                --with-thin-repair=${sbindir}/thin_repair \
                --with-thin-restore=${sbindir}/thin_restore \
"

DEPENDS += "autoconf-archive-native"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
    oe_runmake 'DESTDIR=${D}' -C libdm install
}

#BK 20230704 do want to package...
## Do not generate package libdevmapper
#PACKAGES = ""

#BBCLASSEXTEND = "native nativesdk"
