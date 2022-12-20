# Recipe created by recipetool
# recipetool create -o yad0_0.42.39.bb https://github.com/step-/yad/archive/refs/tags/0.42.39.tar.gz
# this is the yad gtk2 maintenance site.

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/step-/yad/archive/refs/tags/${PV}.tar.gz"
SRC_URI[md5sum] = "f629e1783a62156a1efd0a24947bb87f"
SRC_URI[sha1sum] = "666ed5482b1741e33ac6b8835dd3ae337b694e0a"
SRC_URI[sha256sum] = "caa6efaa8c2ec705db21ddaa85f2d6b9614cb5f4081a535f0cc318e1f67242ef"
SRC_URI[sha384sum] = "089bf9d92d56dbb94c81defaa57fa84cb33586754aa7b0835a58237b3065484eefaed78206a0b75211843c9bbf920e4e"
SRC_URI[sha512sum] = "e9fb47534a4c41e10213a33514f028ea5874b0b02d8ee86da2ce9b478dd92dcbb3280f11b825149ad21bb7cd429b82b851cc94f6cbba5de578a9147b0607a426"

S = "${WORKDIR}/yad-${PV}"

REQUIRED_DISTRO_FEATURES = "x11"

DEPENDS = "intltool-native glib-2.0 glib-2.0-native pango gtk+ gtksourceview2"

inherit gettext pkgconfig autotools

EXTRA_OECONF = "--with-gtk=gtk2 --enable-sourceview"

SUMMARY = "GUIs for shell scripts"
HOMEPAGE = "https://github.com/step-/yad"
