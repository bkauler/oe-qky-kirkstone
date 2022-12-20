# Recipe created by recipetool
# recipetool create -o gtk-vnc_0.9.0.bb https://download.gnome.org/sources/gtk-vnc/0.9/gtk-vnc-0.9.0.tar.xz
# 20211027 added pulseaudio

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=4339efb5fd592e45b9e2641de9fe734f"

SRC_URI = "https://download.gnome.org/sources/gtk-vnc/0.9/gtk-vnc-${PV}.tar.xz"
SRC_URI[md5sum] = "1eed0aa5d71f849eae9fa00ecf28e247"
SRC_URI[sha256sum] = "3a9a88426809a5df2c14353cd9839b8c8163438cb708b31d8048c79d180fcab7"

DEPENDS = "glib-2.0 glib-2.0-native gnutls libx11 cairo gdk-pixbuf intltool-native zlib gtk+ libgcrypt pulseaudio"

inherit pkgconfig gettext perlnative autotools

EXTRA_OECONF = "--without-sasl --with-pulseaudio --with-examples --without-python --with-gtk=2.0 --disable-vala --enable-introspection=no"

HOMEPAGE = "https://wiki.gnome.org/Projects/gtk-vnc"
SUMMARY = "VNC viewer for GTK"

