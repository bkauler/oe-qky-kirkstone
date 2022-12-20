# Recipe created by recipetool
# recipetool create -o libhandy_1.8.0.bb https://gitlab.gnome.org/GNOME/libhandy/-/archive/1.8.0/libhandy-1.8.0.tar.gz

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "https://gitlab.gnome.org/GNOME/libhandy/-/archive/${PV}/libhandy-${PV}.tar.gz"

SRC_URI[md5sum] = "66234b9ba140d57b415d983a94f6362b"
SRC_URI[sha1sum] = "4c767ce0d8724f2e7f18e9144a93649c4edae5e9"
SRC_URI[sha256sum] = "34bafc4ed57401bf0c18be85b06d38fc274fe5858db5eeee9c28b67a07d762da"

GIR_MESON_ENABLE_FLAG = 'enabled'
GIR_MESON_DISABLE_FLAG = 'disabled'
GTKDOC_MESON_OPTION = 'gtk_doc'

inherit meson gobject-introspection vala gettext features_check pkgconfig gtk-doc

ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"

DEPENDS += "gtk+3"

PACKAGES =+ "${PN}-examples"
FILES:${PN}-examples = "${bindir}"

HOMEPAGE = "https://gitlab.gnome.org/GNOME/libhandy"
SUMMARY = "gtk3 library for mobile devices"
