# Recipe created by recipetool
# recipetool create -o gdmap_0.8.1.bb https://downloads.sourceforge.net/project/gdmap/gdmap/0.8.1/gdmap-0.8.1.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "https://downloads.sourceforge.net/project/gdmap/gdmap/${PV}/gdmap-${PV}.tar.gz \
           file://03-gtk-widget-macros.patch \
           file://10-math-underlink.patch"
SRC_URI[md5sum] = "54b43354dbe774810104607ad1eaaed3"
SRC_URI[sha256sum] = "a200c98004b349443f853bf611e49941403fce46f2335850913f85c710a2285b"

DEPENDS = "gtk+ intltool-native libxml2 libpng gdk-pixbuf glib-2.0 glib-2.0-native"

inherit gettext pkgconfig autotools

#20200921 override OE...
CFLAGS:append = " -Wno-error=format-security "

EXTRA_OECONF = ""

HOMEPAGE = "http://gdmap.sourceforge.net"
SUMMARY = "GdMap is a tool which allows to visualize disk space."
