#BK 20230704 have removed polkit dep.

SUMMARY = "udisks provides dbus interfaces for disks and storage devices"
LICENSE = "GPL-2.0-or-later & LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=dd79f6dbbffdbc8e86b086a8f0c0ef43"

DEPENDS = " \
    glib-2.0-native \
    libxslt-native \
    acl \
    libatasmart \
    libgudev \
    glib-2.0 \
    dbus-glib \
    libblockdev \
"
DEPENDS += "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"

RDEPENDS:${PN} = "acl"

SRC_URI = "git://github.com/storaged-project/udisks.git;branch=2.9.x-branch;protocol=https \
           file://udisks2-remove-polkit-dep.patch"

SRCREV = "001c486e6d099ed33e2de4f5c73c03e3ee180f81"
S = "${WORKDIR}/git"

CVE_PRODUCT = "udisks"

inherit autotools-brokensep systemd gtk-doc gobject-introspection gettext

#error: removed "--enable-zram" needs systemd
#error: removed "--enable-btrfs" cannot locate ITS rules for org.freedesktop.UDisks2.btrfs.policy.in
EXTRA_OECONF = "--disable-man --disable-gtk-doc \
                --disable-polkit"

do_configure:prepend() {
    # | configure.ac:656: error: required file 'build-aux/config.rpath' not found
    mkdir -p ${S}/build-aux
    touch ${S}/build-aux/config.rpath
}

FILES:${PN} += " \
    ${datadir}/dbus-1/ \
    ${datadir}/bash-completion \
    ${nonarch_base_libdir}/udev/* \
    ${exec_prefix}${nonarch_base_libdir}/udisks2/* \
    ${systemd_system_unitdir} \
"

PACKAGES =+ "${PN}-libs"
FILES:${PN}-libs = "${libdir}/lib*${SOLIBS}"
FILES:${PN} += "${nonarch_libdir}/tmpfiles.d"

SYSTEMD_SERVICE:${PN} = "${BPN}.service"
SYSTEMD_AUTO_ENABLE = "disable"
