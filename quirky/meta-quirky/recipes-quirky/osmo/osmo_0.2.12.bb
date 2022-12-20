# Recipe created by recipetool
# recipetool create -o osmo_0.2.14.bb https://downloads.sourceforge.net/project/osmo-pim/osmo-pim/osmo-0.2.14/osmo-0.2.14.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "https://downloads.sourceforge.net/project/osmo-pim/osmo-pim/osmo-${PV}/osmo-${PV}.tar.gz \
           file://osmo-0p2p12-restore-libgtkhtml.patch \
           file://xml2-config"

SRC_URI[md5sum] = "567fb326603a693667d735942a64895d"
SRC_URI[sha256sum] = "93bda51614f92e4193840451612e792219f7d8c90d78bdc7ea6d6c14a2be6b78"

DEPENDS = "libical glib-2.0 sqlite3 libarchive libnotify pango \
           gtk+ libgtkhtml libxml2 cups glib-2.0-native"

inherit pkgconfig autotools gettext

EXTRA_OECONF = "--enable-backup=yes --enable-printing=yes --disable-xmltest --disable-gtktest"

do_configure:prepend() {
 #xml2-config in libxml2 is broken
 cp -f ${WORKDIR}/xml2-config ${WORKDIR}/recipe-sysroot/usr/bin/crossscripts/
 chmod 755 ${WORKDIR}/recipe-sysroot/usr/bin/crossscripts/xml2-config
}

do_install:prepend() {
 #failing to install the .mo files
 echo -e '\nall:\n\ninstall:\n\nall-am:\n\n' > ${B}/po/Makefile
}

do_install:append() {
 #just this is enough...
 install -d ${D}/usr/share/doc/nls/osmo
 install -m 644 ${S}/po/osmo.pot ${D}/usr/share/doc/nls/osmo
}

HOMEPAGE = "https://clayo.org/osmo/"
SUMMARY = "diary organiser planner"
