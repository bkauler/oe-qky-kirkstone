# Recipe created by recipetool
# recipetool create -o dictd-client_1.12.1.bb https://downloads.sourceforge.net/project/dict/dictd/dictd-1.12.1/dictd-1.12.1.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

#20221221 patches from debian
SRC_URI = "https://downloads.sourceforge.net/project/dict/dictd/dictd-${PV}/dictd-${PV}.tar.gz \
    file://01-dictl-konwert.patch \
    file://02-man-hyphens.patch \
    file://03-dictdplugin_popen-g-4.3compile.patch \
    file://04-dictl-translit.patch \
    file://05-colorit-nopp-fix.patch \
    file://06-colorit-manpage-nopager.patch \
    file://07-dictfmt.1-man.patch \
    file://08-dictd-close-fds.patch \
    file://09-spelling-typos.patch \
    file://10-md5-64bit.patch \
    file://11-dict_lookup.patch \
    file://12-no-name-flag.patch \
    file://13-Fix-compiler-warnings.patch \
    file://14-fix-mime-option.patch \
    file://15-mime-with-strategy.patch \
    file://16-combining-greek-ypogegrammeni.patch \
    file://17-fix-some-gcc-8-warnings.patch \
    file://18-colorit-missing-packages.patch \
"

SRC_URI[md5sum] = "62696491174c22079f664830d07c0623"
SRC_URI[sha256sum] = "a237f6ecdc854ab10de5145ed42eaa2d9b6d51ffdc495f7daee59b05cc363656"

S = "${WORKDIR}/dictd-${PV}"

DEPENDS = "bison-native flex-native libmaa zlib"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF = "--disable-plugin"

SROOT = "${WORKDIR}/recipe-sysroot"

do_compile:prepend() {
    #creates a broken LIBTOOL variable in Makefile...
    LTNAME="$(find ${SROOT}/usr/bin/crossscripts -maxdepth 1 -name '*libtool')"
    sed -i -e "s%^LIBTOOL=.*%LIBTOOL= ${LTNAME}%" ${B}/Makefile
    
    #20221221 cannot find obj files...
    ln -s ./ .libs
}

do_compile() {
    #only compile dict client...
    oe_runmake dict
}

do_install() {
    #only install client...
    oe_runmake install.dict DESTDIR=${D}
}

HOMEPAGE = "https://sourceforge.net/projects/dict"
SUMMARY = "Client/server software, human language dictionary databases, and tools supporting the DICT protocol"
