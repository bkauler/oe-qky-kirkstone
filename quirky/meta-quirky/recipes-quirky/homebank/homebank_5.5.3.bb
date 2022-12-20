# Recipe created by recipetool
# recipetool create -o homebank_5.5.3.bb http://homebank.free.fr/public/homebank-5.5.3.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "http://homebank.free.fr/public/homebank-${PV}.tar.gz"
SRC_URI[md5sum] = "8930329b71e5dee78c89db319bf64bef"
SRC_URI[sha256sum] = "073607918a9610087791f36f59e70d1261fee8e4e1146a5cfd5871a1d2d91093"

#2020-09-19 /mnt/sda1/nvme/oe/oe-quirky/build-amd64/tmp/work/core2-64-poky-linux/homebank/5.4.3-r0/temp/run.do_configure.528: line 169: glib-gettextize: command not found. fix: glib-2.0-native
DEPENDS = "libsoup-2.4 gtk+3 libofx glib-2.0 glib-2.0-native intltool-native"

inherit pkgconfig gettext autotools mime-xdg

EXTRA_OECONF = "--with-ofx"

FILES:${PN} += "${datadir}/mime ${datadir}/application-registry ${datadir}/icons ${datadir}/mime-info ${datadir}/appdata"

HOMEPAGE = "http://homebank.free.fr/"
SUMMARY = "Personal finance management"
