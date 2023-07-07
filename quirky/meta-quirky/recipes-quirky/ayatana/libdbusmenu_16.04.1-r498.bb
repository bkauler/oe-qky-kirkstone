# Recipe created by recipetool
# recipetool create -o libdbusmenu_16.04.1-r498.bb https://distro.ibiblio.org/easyos/source/alphabetical/l/libdbusmenu-16.04.1-r498.tar.gz

LICENSE = "GPL-3.0-only & LGPL-2.1-only & LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6 \
                    file://COPYING-GPL3;md5=f27defe1e96c2e1ecd4e0c9be8967949 \
                    file://COPYING.2.1;md5=2d5025d4aa3495befef8f17206a5b0a1"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/l/libdbusmenu-${PV}.tar.gz \
  file://0005-configure-Fix-HAVE_VALGRIND-not-being-defined.patch \
  file://0006-dbusmenu-bench-to-py3.patch"

SRC_URI[md5sum] = "53abc19417fab91a4c1d51789ffff53c"
SRC_URI[sha256sum] = "2913f3d1c02ecaa16077ea085d07f762a7443393eb9e836abcffc32434403f4d"

# NOTE: the following prog dependencies are unknown, ignoring: vapigen glib-mkenums xsltproc
#removed:  gobject-introspection gobject-introspection-native
DEPENDS = "glib-2.0 libx11 valgrind intltool-native \
           atk gtk+ gtk+3 json-glib \
           libxslt dbus dbus-glib dbus-native dbus-glib-native \
           gtk-doc-native valgrind libxslt-native vala-native \
          "

inherit gettext pkgconfig autotools

#compile fail for gobject-introspection dep: No module named 'giscanner._giscanner'
#vala needs gobject-introspection, so disable...
EXTRA_OECONF = "--disable-vala --disable-tests --disable-gtk-doc --enable-introspection=no"

do_configure:prepend() {
 touch ${S}/gtk-doc.make
 sed -i -e 's%^EXTRA_DIST .*%EXTRA_DIST = version.xml.in%' ${S}/docs/libdbusmenu-glib/reference/Makefile.am
 sed -i -e 's%^EXTRA_DIST .*%EXTRA_DIST = version.xml.in%' ${S}/docs/libdbusmenu-gtk/reference/Makefile.am
}

#QA Issue: /usr/libexec/dbusmenu-bench contained in package libdbusmenu requires /usr/bin/python3, but no providers found in RDEPENDS:libdbusmenu? [file-rdeps]
ERROR_QA:remove = "file-rdeps"

HOMEPAGE = "https://github.com/AyatanaIndicators/libdbusmenu"
SUMMARY = "Create menu via dbus"

