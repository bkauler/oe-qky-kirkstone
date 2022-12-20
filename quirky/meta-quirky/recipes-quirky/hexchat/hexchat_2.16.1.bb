# Recipe created by recipetool
# recipetool create -o hexchat_2.16.1.bb https://dl.hexchat.net/hexchat/hexchat-2.16.1.tar.xz

LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=3570313aad780956aafa367c8aa1f0b9 \
                    file://plugins/fishlim/LICENSE;md5=a1ad6bba27fe09eb8434c82a0e5f118c"

SRC_URI = "https://dl.hexchat.net/hexchat/hexchat-${PV}.tar.xz"
SRC_URI[md5sum] = "0af269d719c2c047310d44804bb31fdb"
SRC_URI[sha1sum] = "16c407c580e0f86762f928c4893d43df186f1df5"
SRC_URI[sha256sum] = "a7e497da71df419daf9855582d51c723a2611de25d5e97b5efc33fe78da5c4c7"
SRC_URI[sha384sum] = "798e8d573ba84023453cf12aa8709ae22b83ef6aeee02d8a62ceb6861f2f29af37a9a852895069e6fcfe5b78b38c34ee"
SRC_URI[sha512sum] = "9e6baa30ec8c3ce4fb56e2c82625da63295040eaf93a542aec8a4a18a6a3c95d69d1d4ffabc952bcdcd7b754afef234b348f296143991fafd0550342db95ce0e"

DEPENDS = "python3 libcanberra openssl gtk+ glib-2.0 glib-2.0-native dbus \
           dbus-glib dbus-glib-native sysfsutils libxml2 gdk-pixbuf \
           gdk-pixbuf-native python3-cffi python3-cffi-native python3-pycparser \
           python3-pycparser-native"

inherit meson pkgconfig gettext mime-xdg

EXTRA_OEMESON += " \
 -Dinstall-appdata=false \
 -Dwith-lua=false \
 -Dwith-perl=false \
 -Dwith-sysinfo=true \
"

HOMEPAGE = "https://hexchat.github.io/"
SUMMARY = "IRC client"
