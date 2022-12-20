# Recipe created by recipetool
# recipetool create -o pa-applet_20211106-p2.bb https://distro.ibiblio.org/easyos/source/alphabetical/p/pa-applet-20211106-p2.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fa91beaec60b2e43e243bf14f031d0b"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/p/pa-applet-${PV}.tar.gz"
SRC_URI[md5sum] = "ede469119185d49dd8e7debca70d9025"
SRC_URI[sha256sum] = "2c83cca5334cc9dfabf3319d383268b451ea1459b91abfd4f0ec1af5dd97360a"

DEPENDS = "libx11 libnotify glib-2.0 pulseaudio alsa-lib gtk+"

inherit pkgconfig autotools

EXTRA_OECONF = " --enable-gtk2 --with-mixer=pavucontrol"

