# Recipe created by recipetool
# recipetool create -o notification-daemon_3.20.0.bb https://gitlab.gnome.org/GNOME/notification-daemon/-/archive/3.20.0/notification-daemon-3.20.0.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "https://gitlab.gnome.org/GNOME/notification-daemon/-/archive/${PV}/notification-daemon-${PV}.tar.gz \
 file://config.rpath"

SRC_URI[md5sum] = "55bc8735aaf8a0fd0f9df26b13928680"
SRC_URI[sha1sum] = "7008ddffad55920e6f6fcfe8857f4a93231d1400"
SRC_URI[sha256sum] = "4c711f6d6448c63cafe73d967af453df9a2e6da1320210820b14488cc61d3da2"
SRC_URI[sha384sum] = "39a471d1fc8ccc4f94f215172861bb2c70d9b394666c372f17f105fb972f1021318fc797466c73b099dfceb82b59cfa7"
SRC_URI[sha512sum] = "afc8720f950d721afa5aeabbc02813484f238c96e1d993e180725cdd4a64db0bed702a3088bb43b703533d70f1c84ebbc299aa49a3bcdb797f94ddbee9d36c91"

DEPENDS = "libx11 glib-2.0 gtk+3 libnotify dbus gconf glib-2.0-native"

inherit pkgconfig gettext autotools-brokensep

EXTRA_OECONF = "--disable-binreloc"

#configure.ac:70: error: required file 'build-aux/config.rpath' not found
# configure.ac:70: error: required file './ABOUT-NLS' not found
do_configure:prepend() {
 touch ${S}/ABOUT-NLS
 #is this a bad fix?...
 mkdir -p ${S}/build-aux
 cp -a -f ${WORKDIR}/config.rpath ${S}/build-aux/
 chmod 755 ${S}/build-aux/config.rpath
}
# ...hmm, do we also need autotools-brokensep?

#another error:
# /bin/bash: line 1: gdbus-codegen: command not found
# ...perhaps dep glib-2.0-native will fix? --yes

HOMEPAGE = "https://gitlab.gnome.org/GNOME/notification-daemon/"
SUMMARY = "Daemon for displaying popup notifications"
