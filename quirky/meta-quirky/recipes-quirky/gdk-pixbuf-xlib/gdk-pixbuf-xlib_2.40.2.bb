# Recipe created by recipetool
# recipetool create -o gdk-pixbuf-xlib_2.40.2.bb http://deb.debian.org/debian/pool/main/g/gdk-pixbuf-xlib/gdk-pixbuf-xlib_2.40.2.orig.tar.xz

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "http://deb.debian.org/debian/pool/main/g/gdk-pixbuf-xlib/gdk-pixbuf-xlib_${PV}.orig.tar.xz"

SRC_URI[md5sum] = "fbd57e867e039a8cf9164d145c0f0434"
SRC_URI[sha1sum] = "46c7261de823a1fa40cebfc90a741383b3a75025"
SRC_URI[sha256sum] = "8b8e1c270ec16a06f665ea841f8e4e167eaa0118d0cbfeeade43745f09198ff7"

DEPENDS = "gtk+ gdk-pixbuf libx11 gdk-pixbuf-native"

inherit meson pkgconfig gettext

EXTRA_OEMESON += " \
  --buildtype=release \
  -Dgtk_doc=false \
"

HOMEPAGE = "https://packages.debian.org/bookworm/libgdk-pixbuf-xlib-2.0-0"
SUMMARY = "This package contains a deprecated library to render GdkPixbuf structures to X drawables using Xlib"

#20221208 allow install into same areas at gdk-pixbuf...
SSTATE_ALLOW_OVERLAP_FILES = "/"
