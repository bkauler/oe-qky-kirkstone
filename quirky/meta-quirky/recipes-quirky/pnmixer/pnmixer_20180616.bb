# Recipe created by recipetool
# recipetool create -o pnmixer_20180616.bb http://distro.ibiblio.org/easyos/source/alphabetical/p/pnmixer-20180616.tar.gz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/p/pnmixer-${PV}.tar.gz"
SRC_URI[md5sum] = "a14b78e71d62dcd1396f51d9ac999165"
SRC_URI[sha1sum] = "21e4ed61e83f9c4d4b1f8acd8c1f456ff540d675"
SRC_URI[sha256sum] = "ff885973dbad02e3cc3d3a9afc66ec43e278a9039d25745a1d11f7741db0d80f"
SRC_URI[sha384sum] = "166e137f2f1168ad05274063ad594678988e7d4168f1c57fa3745e0c578b7471d0d258971a623d7ea1577a2e334fd9eb"
SRC_URI[sha512sum] = "052339b59e95bf8d738ba2519da4f743fa988b47145aedbeba3cf496540b5037d8836e407d433aa7a8388e3d3299e466ee71f5d84a2d43dc5b304bc7d38a0616"

inherit cmake gettext pkgconfig

DEPENDS = "alsa-lib gtk+"

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = "-DWITH_LIBNOTIFY=NO -DWITH_GTK3=NO"

HOMEPAGE = "https://github.com/nicklan/pnmixer"
SUMMARY = "Audio volume tray applet"

