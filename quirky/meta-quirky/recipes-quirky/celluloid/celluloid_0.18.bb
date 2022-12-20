# Recipe created by recipetool
# recipetool create -o celluloid_0.18.bb https://github.com/celluloid-player/celluloid/archive/v0.18.tar.gz

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=8006d9c814277c1bfc4ca22af94b59ee"

SRC_URI = "https://github.com/celluloid-player/celluloid/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "3fde6852840798ad61b3e39f1513f8d4"
SRC_URI[sha1sum] = "17c7bcc5f34f003140cad407b6a3ecd4d87ce769"
SRC_URI[sha256sum] = "3ce6158097d94786a62de5f26ab2cb71301e9fd0841ede8381e1535489cf0de8"
SRC_URI[sha384sum] = "1f0eafd8b6542178abee06ce68c978ac3a6a9ebd2b479e85136e8db77e2614a4e70aca622ae4a44a4ff383762ec508a2"
SRC_URI[sha512sum] = "acb76bd216100afbe3b083919e122308def1431845955dd418e7b2d57f37fb1353209d1f77f41790d3e01603ee1137fe2c5a57c7bb0ca9b0b13388278cc291f1"

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
