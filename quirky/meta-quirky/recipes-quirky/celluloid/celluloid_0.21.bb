# Recipe created by recipetool
# recipetool create -o celluloid_0.18.bb https://github.com/celluloid-player/celluloid/archive/v0.18.tar.gz

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=8006d9c814277c1bfc4ca22af94b59ee"

SRC_URI = "https://github.com/celluloid-player/celluloid/releases/download/v${PV}/celluloid-${PV}.tar.xz"
SRC_URI[md5sum] = "ffc1670474dc414183eaef4cf4ccf3a6"
SRC_URI[sha1sum] = "c185e1fdfc1764b964af00529f184d503a54bc83"
SRC_URI[sha256sum] = "2299d8cc1a5a620cc3686495da22ac4ccd6dd020827d9dd27e19a61d83707eb7"

DEPENDS = "libepoxy mpv gtk+3 glib-2.0 appstream-glib glib-2.0-native"

inherit gettext pkgconfig autotools-brokensep

EXTRA_OECONF = ""

# 20200929 why does build sometimes fail?
# today, error "unterminated comment" line 680 in src/mpris/celluloid-mpris-gdbus.c
# and i see 680 is end of the file -- however, file is supposed to have 8355 lines!
# try this:
PARALLEL_MAKE = "-j 1"

HOMEPAGE = "https://celluloid-player.github.io/"
SUMMARY = "GUI frontend for MPV media player"
