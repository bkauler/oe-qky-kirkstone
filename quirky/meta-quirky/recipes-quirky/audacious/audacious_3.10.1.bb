# Recipe created by recipetool
# recipetool create -o audacious_3.10.1.bb https://distfiles.audacious-media-player.org/audacious-3.10.1.tar.bz2
# 20210407 added jack, pulseaudio --but think only need in audacious-plugins

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=39e1d7b39523036a31fe48b095b8912a"

SRC_URI = "https://distfiles.audacious-media-player.org/audacious-${PV}.tar.bz2"
SRC_URI[md5sum] = "9dfcf0290f766d91b0e195a232d4e4bf"
SRC_URI[sha1sum] = "297000119e0d7b94e355bd78d484c85baeab3795"
SRC_URI[sha256sum] = "8366e840bb3c9448c2cf0cf9a0800155b0bd7cc212a28ba44990c3d2289c6b93"
SRC_URI[sha384sum] = "01ef51592c036629194ed6f0f428d5a899355f0527286a30f4434d69393307fb44692d8b60c0a388b79879378a5ee967"
SRC_URI[sha512sum] = "e88891caaa3897f9b4abf39136e20834aedf1287d0d5eefea392fda89050db8db00c6f363976a68fe250ddbae4e27590f7615916a76370a44ca9235f1fa60b43"

#20210407 added: alsa-lib alsa-plugins jack  20211027 added pulseaudio
DEPENDS = "gtk+ glib-2.0 dbus dbus-glib dbus-native dbus-glib-native alsa-lib \
           alsa-plugins jack pulseaudio"

inherit gettext pkgconfig autotools-brokensep mime-xdg

#20211027 no, jack and pulseaudio are handled in audacious-plugins
#EXTRA_OECONF = " --enable-jack"

HOMEPAGE = "https://audacious-media-player.org/"
SUMMARY = "audio player"
