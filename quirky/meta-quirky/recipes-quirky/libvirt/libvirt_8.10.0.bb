# Recipe created by recipetool
# recipetool create -o libvirt_8.10.0.bb https://libvirt.org/sources/libvirt-8.10.0.tar.xz

# 20221105 PR_NUM is defined in local.conf, currently r10
#PR = "r${@int(PR_NUM) + 1}"

SUMMARY = "Library providing a simple virtualization API"
HOMEPAGE = "https://libvirt.org"

LICENSE = "LGPL-2.1-only & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LESSER;md5=4b54a1fd55a448865a0b32d41598759d"

SRC_URI = "https://libvirt.org/sources/libvirt-${PV}.tar.xz"

SRC_URI[md5sum] = "47feb4bed510cb7ed8fdc5be6b9d6d04"
SRC_URI[sha1sum] = "19f62513d981e1eba826d1a63d2bd9ff305b4104"
SRC_URI[sha256sum] = "bb07b7b00f08f826dd4f623f8b233e4e8b221b8859bb5937ff45355f0ae29952"

GIR_MESON_ENABLE_FLAG = 'enabled'
GIR_MESON_DISABLE_FLAG = 'disabled'
#GTKDOC_MESON_OPTION = 'gtk_doc'

inherit meson gobject-introspection gettext features_check pkgconfig gtk-doc

ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"

DEPENDS += "gobject-introspection gobject-introspection-native \
            libxml2 libxml2-native libxslt libxslt-native \
            glib-2.0 glib-2.0-native \
            attr util-linux libcap-ng curl fuse fuse3 libpcap \
            libssh2 libpciaccess readline eudev qemu yajl"


EXTRA_OEMESON += " -Dsystem=true -Ddriver_qemu=enabled -Ddocs=disabled"

#error: meson.build:1:0: ERROR: Unknown options: "introspection"
UNKNOWN_CONFIGURE_OPT_IGNORE:append = " introspection"
GIR_MESON_OPTION = ''

#installs to /var/run and /var/volatile but expected to be empty
ERROR_QA:remove = "empty-dirs"
#libvirt requires /usr/bin/python3, but no providers found in RDEPENDS:libvirt
ERROR_QA:remove = "file-rdeps"

do_install:append() {
 cp -a -f ${D}/usr/lib64/* ${D}/usr/lib/
 rm -rf ${D}/usr/lib64
}
