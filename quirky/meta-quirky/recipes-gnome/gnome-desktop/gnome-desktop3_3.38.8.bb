SUMMARY = "GNOME library for reading .desktop files"
SECTION = "x11/gnome"
LICENSE = "GPL-2.0-only & LGPL-2.0-only"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://COPYING.LIB;md5=5f30f0716dfdd0d91eb439ebec522ec2 \
"

BPN = "gnome-desktop"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase itstool pkgconfig upstream-version-is-even gobject-introspection features_check

# gobject-introspection is mandatory and cannot be configured
REQUIRED_DISTRO_FEATURES = "gobject-introspection-data"
#20221203 UNKNOWN_CONFIGURE_WHITELIST has been renamed to UNKNOWN_CONFIGURE_OPT_IGNORE
# but another error: meson.build:1:0: ERROR: Unknown options: "introspection"
UNKNOWN_CONFIGURE_OPT_IGNORE:append = " introspection"
GIR_MESON_OPTION = ''

SRC_URI[archive.sha256sum] = "04dc3b37c2b7d98df972670ac2a5a33ec7dc278e6814c3d23d099ae6789dcae8"

SRC_URI += " \
    file://gnome-desktop-thumbnail-don-t-assume-time_t-is-long.patch \
    file://0001-meson.build-Disable-libseccomp-for-all-archs.patch \
"

DEPENDS += "gsettings-desktop-schemas virtual/libx11 gtk+3 startup-notification xkeyboard-config iso-codes udev"

inherit features_check gtk-doc
REQUIRED_DISTRO_FEATURES += "x11"

GTKDOC_MESON_OPTION = "gtk_doc"
#20221203 add: -Dsystemd=disabled
EXTRA_OEMESON = "-Ddesktop_docs=false -Dsystemd=disabled"

PACKAGES =+ "libgnome-desktop3"
FILES:libgnome-desktop3 = "${libdir}/lib*${SOLIBS} ${datadir}/libgnome-desktop*/pnp.ids ${datadir}/gnome/*xml"

RRECOMMENDS:libgnome-desktop3 += "gsettings-desktop-schemas"
