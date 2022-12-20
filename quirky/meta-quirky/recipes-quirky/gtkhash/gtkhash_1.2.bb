# Recipe created by recipetool
# recipetool create -o gtkhash_1.2.bb http://deb.debian.org/debian/pool/main/g/gtkhash/gtkhash_1.2.orig.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://deb.debian.org/debian/pool/main/g/gtkhash/gtkhash_${PV}.orig.tar.gz"
SRC_URI[md5sum] = "6f3253944f8751279180de94870a5f13"
SRC_URI[sha256sum] = "d0b2b867dce165d568382d7d6cdda551c842c8e17f52e760f7d32dab29089f71"

DEPENDS = "openssl intltool-native zlib glib-2.0 nettle  glib-2.0-native gtk+ \
  libgcrypt libgcrypt-native"

inherit gettext pkgconfig autotools

EXTRA_OECONF = "--with-gtk=2.0 --disable-thunar --disable-peony --disable-nemo \
  --disable-nautilus --disable-caja --disable-appstream --disable-schemas-compile \
  --disable-blake2"

# just run existing configure, works...
do_configure() {
 #20200920 libgcrypt-config is broken...
 PTN1='s%\$LIBGCRYPT_CONFIG --version%pkg-config libgcrypt --version%'
 sed -i -e "${PTN1}" ${S}/configure
 PTN2='s%\$LIBGCRYPT_CONFIG --api-version%pkg-config libgcrypt --api-version%'
 sed -i -e "${PTN2}" ${S}/configure
 PTN3='s%\$LIBGCRYPT_CONFIG --cflags%pkg-config libgcrypt --cflags%'
 sed -i -e "${PTN3}" ${S}/configure
 PTN4='s%\$LIBGCRYPT_CONFIG --libs%pkg-config libgcrypt --libs%'
 sed -i -e "${PTN4}" ${S}/configure
 PTN4='s%\$LIBGCRYPT_CONFIG --host%pkg-config libgcrypt --host%'
 sed -i -e "${PTN4}" ${S}/configure
 #crap, --version check not working, do this hack...
 PTN5='s%"\$major" -gt "\$req_major"%2 -gt 1%'
 sed -i -e "${PTN5}" ${S}/configure
 
 oe_runconf
}

FILES:${PN} += "${datadir}/icons ${datadir}/glib-2.0"

HOMEPAGE = "https://github.com/tristanheaven/gtkhash/"
SUMMARY = "gui to create hashes"
