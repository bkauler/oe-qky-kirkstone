# Recipe created by recipetool
# recipetool create -o gst-editing-services_1.18.2.bb https://gstreamer.freedesktop.org/src/gstreamer-editing-services/gst-editing-services-1.18.2.tar.xz

LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d"

SRC_URI = "https://gstreamer.freedesktop.org/src/gstreamer-editing-services/gst-editing-services-${PV}.tar.xz"
SRC_URI[md5sum] = "1404b6bf39514a557cf7c0f6154b61af"
SRC_URI[sha256sum] = "25ac7cd252a9bcb9ae179eca0b52fc9cf5384c1df16adab4d87bd84b3f9a625f"

DEPENDS = "glib-2.0 glib-2.0-native libxml2 bison-native flex-native \
  python3-numpy-native gstreamer1.0 gstreamer1.0-plugins-bad \
  gstreamer1.0-plugins-base gstreamer1.0-plugins-good \
  gstreamer1.0-plugins-ugly gstreamer1.0-python python3-pygobject"

inherit meson pkgconfig gettext upstream-version-is-even gobject-introspection

GIR_MESON_ENABLE_FLAG = "enabled"
GIR_MESON_DISABLE_FLAG = "disabled"

#20230317 removed:
#    -Ddbghelp=disabled 
#    -Dgtk_doc=disabled 

EXTRA_OEMESON += " \
    -Dexamples=disabled \
    -Dtests=disabled \
    -Dbash-completion=disabled \
    "



HOMEPAGE = "https://gitlab.freedesktop.org/gstreamer/gst-editing-services"
SUMMARY = "High-level library for facilitating the creation of audio/video non-linear editors"

