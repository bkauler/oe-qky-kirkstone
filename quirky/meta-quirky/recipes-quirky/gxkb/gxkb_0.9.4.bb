# Recipe created by recipetool
# recipetool create -o gxkb_0.9.4.bb https://github.com/zen-tools/gxkb/archive/refs/tags/v0.9.4.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://doc/COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

SRC_URI = "https://github.com/zen-tools/gxkb/archive/refs/tags/v${PV}.tar.gz"

SRC_URI[md5sum] = "0a551fa179ba579bcdff078ffae6bb03"
SRC_URI[sha256sum] = "93016bca96926db2244d7462f8b6310de5bbbae20e8498a2b5fa923afbe0466f"

DEPENDS = "libxklavier libwnck3 glib-2.0 gtk+3 glib-2.0-native"

inherit pkgconfig autotools gettext

EXTRA_OECONF = "--enable-appindicator=no"

HOMEPAGE = "https://zen-tools.github.io/gxkb/"
SUMMARY = "X11 keyboard switcher"
