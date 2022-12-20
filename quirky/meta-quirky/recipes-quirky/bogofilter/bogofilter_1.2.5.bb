# Recipe created by recipetool
# recipetool create -o bogofilter_1.2.5.bb https://downloads.sourceforge.net/project/bogofilter/bogofilter-stable/bogofilter-1.2.5.tar.xz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4eedb06443f9256fd583c69b43864b6e"

SRC_URI = "https://downloads.sourceforge.net/project/bogofilter/bogofilter-stable/bogofilter-${PV}.tar.xz"

SRC_URI[md5sum] = "8763f87adfff7b802ced177d8c654539"
SRC_URI[sha256sum] = "3248a1373bff552c500834adbea4b6caee04224516ae581fb25a4c6a6dee89ea"

DEPENDS = "flex-native gsl sqlite3 sqlite3-native xmlto-native"

inherit pkgconfig perlnative autotools

EXTRA_OECONF = " --without-included-gsl --with-database=sqlite3 \
                --with-charset=iso-8859-1 --enable-unicode"

#/usr/bin/pogoupgrade perl script has corrupted shebang, delete it:
do_install:append() {
 rm -f ${D}/usr/bin/bogoupgrade
}

HOMEPAGE = "https://sourceforge.net/projects/bogofilter/"
SUMMARY = "A fast Bayesian spam filter"
