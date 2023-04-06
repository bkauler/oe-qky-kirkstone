# Recipe created by recipetool
# recipetool create -o flatpak_1.10.8.bb https://github.com/flatpak/flatpak/releases/download/1.10.8/flatpak-1.10.8.tar.xz

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://libglnx/COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "https://github.com/flatpak/flatpak/releases/download/${PV}/flatpak-${PV}.tar.xz"

SRC_URI[md5sum] = "25ee921580f591e87b1a8a476026e67f"
SRC_URI[sha1sum] = "89420d434afa1d3bb9c43450935fd13e37ddc439"
SRC_URI[sha256sum] = "65569dbf31344581a1e7782d09e702bb41e7011ae21cd021c414a2925f84b82c"
SRC_URI[sha384sum] = "21c185ee2160256ba4265e3d95a9a88c5c773f690e6f6ce6bee67fcdb66eba3a0f043a55a9835e7f6d7309ec527b4a49"
SRC_URI[sha512sum] = "0823aa522d5f5b0a6cb967609ef8db18390a1992578c7c15921494973759d83467f31112d81226797c741a4ed3732087ce6b290bd8d3cc103415094e32d0365a"

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
EXTRA_OECONF = "--disable-gtk-doc-html \
    --with-privileged-group=wheel --with-system-install-dir=/var/lib/flatpak \
    --disable-docbook-docs --disable-installed-tests --disable-documentation \
    --disable-static \
    --with-run-media-dir=/mnt --disable-gtk-doc-check --disable-seccomp \
    --disable-selinux-module --enable-xauth --disable-system-helper"

SUMMARY = "Linux application sandboxing and distribution framework"
HOMEPAGE = "https://github.com/flatpak/flatpak"

