# Recipe created by recipetool
# recipetool create -o flatpak_1.12.8.bb https://github.com/flatpak/flatpak/releases/download/1.12.8/flatpak-1.12.8.tar.xz

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
  file://subprojects/libglnx/COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "https://github.com/flatpak/flatpak/releases/download/${PV}/flatpak-${PV}.tar.xz"

SRC_URI[md5sum] = "57ca323be9de526203e5995207238670"
SRC_URI[sha1sum] = "aadb61d0d67fa6bc4a3cbe54b0acfb78403a5cd1"
SRC_URI[sha256sum] = "e6db731e7a746372e8f8461e6225c0c9b26623c08a3a9914dbfd8e7c91944931"

# libseccomp gobject-introspection gobject-introspection-native
# polkit105  lcov gnupg
DEPENDS = "zstd gpgme appstream-glib libarchive glib-2.0 \
   bison-native dconf libcap libsoup-2.4 libxau json-glib \
   gdk-pixbuf fuse libxml2 \
   xmlto libxslt ostree libyaml gcab glib-networking \
   openssl gnutls bzip2 xz zip zlib glib-2.0-native libxml2-native \
   python3-pyparsing-native libassuan"

inherit gettext pkgconfig autotools gobject-introspection

# --enable-introspection=no 
# change --with-system-install-dir=/var/lib/flatpak to /mnt/wkg/flatpak
EXTRA_OECONF = "--disable-gtk-doc-html \
    --with-privileged-group=wheel --with-system-install-dir=/mnt/wkg/flatpak \
    --disable-docbook-docs --disable-installed-tests --disable-documentation \
    --disable-static \
    --with-run-media-dir=/mnt --disable-gtk-doc-check --disable-seccomp \
    --disable-selinux-module --enable-xauth --disable-system-helper"

SUMMARY = "Linux application sandboxing and distribution framework"
HOMEPAGE = "https://flatpak.org/"

