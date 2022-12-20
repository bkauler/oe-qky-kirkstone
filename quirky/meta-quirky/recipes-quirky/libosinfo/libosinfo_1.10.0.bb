# Recipe created by recipetool
# recipetool create -o libosinfo_1.10.0.bb https://gitlab.com/libosinfo/libosinfo/-/archive/v1.10.0/libosinfo-v1.10.0.tar.gz

LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
                    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "https://gitlab.com/libosinfo/libosinfo/-/archive/v${PV}/libosinfo-v${PV}.tar.gz"

SRC_URI[md5sum] = "55ee01c523b905351c2aa6a5ff29a98e"
SRC_URI[sha1sum] = "f2e155d68bcaf229b0e34f233f010c963778bff0"
SRC_URI[sha256sum] = "e709c5f7d006527caea9b128cdae0ee3404217f2edb9a673ad9574b5eec257fc"

S = "${WORKDIR}/${BPN}-v${PV}"

GIR_MESON_ENABLE_FLAG = 'enabled'
GIR_MESON_DISABLE_FLAG = 'disabled'
#GTKDOC_MESON_OPTION = 'gtk_doc'

#20221208 meson has become a headache, as OE is applying options that
# are invalid. it used to be that meson would just give a warning.
# apparently, this one gets rid of OE applying -Dintrospection=enabled/disabled
GIR_MESON_OPTION = ""
# which leaves: ERROR: Unknown options: "docs"
EXTRA_OEMESON:remove = "-Ddocs=false"

inherit meson gobject-introspection vala gettext features_check pkgconfig gtk-doc

ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"

DEPENDS += "gobject-introspection gobject-introspection-native \
            libxml2 libxml2-native libxslt libxslt-native \
            glib-2.0 glib-2.0-native \
            libsoup-2.4 perl-native"

# these are auto detected, seems cannot force them...
#  -Denable-introspection=enabled 
#  -Denable-vala=enabled 

EXTRA_OEMESON += " \
  -Denable-gtk-doc=false \
  -Denable-tests=false \
  -Dwith-pci-ids-path="/usr/share/pci.ids" \
  -Dwith-usb-ids-path="/usr/share/usb.ids" \
"

HOMEPAGE = "https://libosinfo.org/"
SUMMARY = "Provision and manage an OS in a virtual environment"
