# Recipe created by recipetool
# recipetool create -o galculator_2.1.4.bb http://galculator.mnim.org/downloads/galculator-2.1.4.tar.bz2

PROVIDES = "galculator"
DESCRIPTION = "GTK Advanced Calculator"

#PR = "r0"
# 20210121 PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://galculator.mnim.org/downloads/galculator-${PV}.tar.bz2"
SRC_URI[md5sum] = "1a115d6799dc453a29760e7bd4a56374"
SRC_URI[sha256sum] = "01cfafe6606e7ec45facb708ef85efd6c1e8bb41001a999d28212a825ef778ae"

DEPENDS = "flex-native intltool-native glib-2.0-native gtk+"
# error: "glib-gettextize: command not found"
# but it is there, installed by 'glib-2.0' ...fix set dep 'glib-2.0-native'

inherit pkgconfig gettext autotools

EXTRA_OECONF = "--disable-quadmath --disable-gtk3"

#20221210 multiple defs
# ref: https://gcc.gnu.org/gcc-10/porting_to.html
CFLAGS:append = " -fcommon"

HOMEPAGE = "http://galculator.sourceforge.net/"
SUMMARY = "A GTK2based scientific calculator with algebraic RPN and formula entry modes"
