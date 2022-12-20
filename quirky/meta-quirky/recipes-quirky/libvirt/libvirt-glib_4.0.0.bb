# Recipe created by recipetool
# recipetool create -o libvirt-glib_4.0.0.bb http://deb.debian.org/debian/pool/main/libv/libvirt-glib/libvirt-glib_4.0.0.orig.tar.xz

SUMMARY = "libvirt glib integration for events"
HOMEPAGE = "https://libvirt.org"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "http://deb.debian.org/debian/pool/main/libv/libvirt-glib/libvirt-glib_${PV}.orig.tar.xz \
           file://Disable-syntax-checks.patch"

SRC_URI[md5sum] = "0d0932949cde8a8933f6fb6aaf66dfe0"
SRC_URI[sha1sum] = "8c5209b6e6f40a4c6c99ed9c64e01a6411a89880"
SRC_URI[sha256sum] = "8423f7069daa476307321d1c11e2ecc285340cd32ca9fc05207762843edeacbd"

GIR_MESON_ENABLE_FLAG = 'enabled'
GIR_MESON_DISABLE_FLAG = 'disabled'
#GTKDOC_MESON_OPTION = 'gtk_doc'

inherit meson gobject-introspection gettext features_check pkgconfig gtk-doc vala

ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"

DEPENDS += "gobject-introspection gobject-introspection-native \
            libxml2 libxml2-native libxslt libxslt-native \
            glib-2.0 glib-2.0-native libvirt"

#20221208 removed -Dsystem=true as not a valid option.
EXTRA_OEMESON += "-Dtests=disabled -Ddocs=disabled"

