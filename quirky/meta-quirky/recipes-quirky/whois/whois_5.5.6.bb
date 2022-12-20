# Recipe created by recipetool
# recipetool create -o whois_5.5.6.bb https://github.com/rfc1036/whois/archive/v5.5.6.tar.gz

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://github.com/rfc1036/whois/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "3432d8106f0c79ee8c233851060ea9f1"
SRC_URI[sha1sum] = "58cdec70f9f85db967a5b602567de4d6140ace3e"
SRC_URI[sha256sum] = "fa86a9da4b6e79b6a04b0110f7f4f46214d038a051fef3d0767a09b44e49e8c8"
SRC_URI[sha384sum] = "4c43428670edf41b6955eb31ce23a6e853b6e42fd3e2b753d5bfe26acbada9eb6e054b67e56cc1967504a480e326d2c5"
SRC_URI[sha512sum] = "97a139a8e24b9a92c5597bc31e1b8d5d5bcab1ec4cc53f80cbdb7f2e5b7e54f36b3a8a8d90fd7b86452aad36adc0ba2015aaf936643b290c8a777f5f6cb27ef3"

DEPENDS = "libidn perl-native libxcrypt intltool-native"

do_configure () {
 true
}

do_compile () {
	oe_runmake
}

do_install () {
	#oe_runmake install 'DESTDIR=${D}'
    install -d ${D}/usr/bin
    install -m755 whois ${D}/usr/bin
}

HOMEPAGE = "http://www.linux.it/~md/software/"
SUMMARY = "URL whois lookup"
