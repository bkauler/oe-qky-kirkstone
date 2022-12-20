# Recipe created by recipetool
# recipetool create -o gpptp_2.1-jafadmin-i18n.bb https://distro.ibiblio.org/easyos/source/alphabetical/g/gpptp-2.1-jafadmin-i18n.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/g/gpptp-${PV}.tar.gz"

SRC_URI[md5sum] = "8b2387c7a7a63dc1ec315f6016aa7e3c"
SRC_URI[sha256sum] = "91625f4b53e3506ecf81033ac744f5f42e2b6c92ba640cca2f8a49b78095b93a"

DEPENDS = "gtk+"
inherit pkgconfig

do_configure () {
    true
}

do_compile () {
    #path prefix gets appended when run pkg-config (see test above)
    ${CC} -o gpptp gpptp.c ${CFLAGS} ${LDFLAGS} `pkg-config --libs gtk+-2.0` `pkg-config --cflags gtk+-2.0`
    ${CC} -o mk-vpn-key mk-vpn-key.c ${CFLAGS} ${LDFLAGS}
}

do_install () {
    install -d ${D}/usr/sbin
    install -m755 gpptp ${D}/usr/sbin
    install -m755 mk-vpn-key ${D}/usr/sbin
    
    install -d ${D}/usr/share/doc/nls/gpptp
    install -m 644 ${S}/gpptp.pot ${D}/usr/share/doc/nls/gpptp/
}

FILES:${PN} += "/usr/share/doc/nls/gpptp/gpptp.pot"

HOMEPAGE = "https://bkhome.org/news"
SUMMARY = "Linux PPTP Client"
