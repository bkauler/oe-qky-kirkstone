#180417 ***broken for aarch64***

#PR = "r1"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

#20180417 as not doing an autoreconf, existing config.sub and config.guess do not
# recognize aarch64, hence the 07-* patch.

SRC_URI = "http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/inkscapelite-0.36.3.tar.bz2 \
           file://01-inkscapelite-glib.h.patch \
           file://02-inkscapelite-export-dynamic.patch \
           file://03-inkscapelite-fix-hruler.patch \
           file://04-inkscapelite-fix-vruler.patch \
           file://05-inkscapelite-rulerfontfix-jamesbond.patch \
           file://07-inkscapelite-autotools-fix-aarch64.patch"
SRC_URI[md5sum] = "816615d99d1d9673a14c1465c2467924"
SRC_URI[sha256sum] = "e83476d9c8a3af0c47d7d0934991587060fede4ab3cb23e2a92ca4b6f2404088"

# BK 170624 this patch is for 64-bit target only. SITEINFO_BIT will have value of 32 or 64, for target cpu...
# ref: http://lists.openembedded.org/pipermail/openembedded-devel/2017-January/110874.html
SRC_URI:append = "${@['',' file://06-inkscapelite-64bit-texttoolcrash-jamesbond.patch'][d.getVar('SITEINFO_BITS') != '32']}"

S = "${WORKDIR}/${BPN}-0.36.3"

DEPENDS = "libxft gtk+ libx11 intltool-native fontconfig flex-native libice libxml2 libart-lgpl glib-2.0 expat freetype glib-2.0-native popt libpng12"

inherit pkgconfig gettext autotools-brokensep mime-xdg

EXTRA_OECONF = "--with-popt --with-xft --without-gnome-print --disable-mmx"

# 170624 do_compile fails:
# libtool: Version mismatch error.  This is libtool 2.4.6, but the
# libtool: definition of this LT_INIT comes from an older release.
# libtool: You should recreate aclocal.m4 with macros from libtool 2.4.6
# libtool: and run autoconf again.
# ref: https://stackoverflow.com/questions/3096989/libtool-version-mismatch-error
# BK stuffed around...
#do_configure_prepend() {
#    #rm -f ${S}/aclocal.m4
#    #rm -f ${S}/acinclude.m4
#    oe_runconf
#    oe_runmake maintainer-clean
#    #aclocal; libtoolize --force; autoheader
#    #glib-gettextize --force --copy
#    #aclocal
#    #intltoolize --copy --force --automake
#    #autoheader
#    ##automake --foreign
#    rm -f ${S}/aclocal.m4
#    #rm -f ${S}/acinclude.m4
#    rm -f ${S}/config.h.in
#    rm -f ${S}/configure
#    rm -f ${S}/Makefile.in
#    rm -f ${S}/ltmain.sh
#}

#BK 2020-09-19
# configure complains cannot find freetype-config, give it one...
# even though trying not to use it...
do_configure:prepend() {
    echo '#!/bin/sh' > ${STAGING_BINDIR_CROSS}/freetype-config
    chmod 755 ${STAGING_BINDIR_CROSS}/freetype-config
    #sed -i -e 's%$FREETYPECONFIG --cflags%pkg-config --cflags freetype2%' ${S}/configure.ac
    #sed -i -e 's%$FREETYPECONFIG --libs $config_static%pkg-config --libs freetype2%' ${S}/configure.ac
}

#inkscapelite-0.36.3-p06-r1 do_package_qa: QA Issue: package contains desktop file with key 'MimeType' but does not inhert mime-xdg: inkscapelite path '/work/core2-64-poky-linux/inkscapelite/0.36.3-p06-r1/packages-split/inkscapelite/usr/share/applications/inkscape.desktop' [mime-xdg]
ERROR_QA:remove:inkscapelite = "mime-xdg"
#inkscapelite-0.36.3-p06-r1 do_package_qa: QA Issue: inkscapelite: The compile log indicates that host include and/or library paths were used.
ERROR_QA:remove:inkscapelite = "compile-host-path"

# BK just run existing configure, works...
do_configure() {
    oe_runconf
}

# 171102 ***WARNING*** this fix works for 64-bit build.
# BK 170624 do_compile: src/libnr/gen_nr_config binary executable wants to run,
# and generate file 'nr_config.h'
do_compile:prepend() {
    touch ${S}/src/libnr/gen_nr_config
    echo '#ifndef __NR_CONFIG_H__
#define __NR_CONFIG_H__

#define NR_SIZEOF_CHAR 1
#define NR_SIZEOF_SHORT 2
#define NR_SIZEOF_INT 4
#define NR_SIZEOF_LONG 8

typedef signed char NRByte;
typedef unsigned char NRUByte;
typedef signed short NRShort;
typedef unsigned short NRUShort;
typedef signed int NRLong;
typedef unsigned int NRULong;

#endif' > ${S}/src/libnr/nr_config.h
    #remove this line;
    sed -i -e 's%\./gen_nr_config > nr_config\.h%%' ${S}/src/libnr/Makefile

    #
    for aFILE in Makefile extensions/Makefile glade/Makefile po/Makefile src/Makefile src/libarikkei/Makefile src/svg/Makefile src/libnr/Makefile src/display/Makefile src/helper/Makefile src/xml/Makefile src/libnrtype/Makefile src/widgets/Makefile src/dialogs/Makefile src/utest/Makefile src/modules/Makefile
    do
     sed -i -e "s%^includedir=.*%includedir=${SROOT}/usr/include%" ${S}/${aFILE}
     sed -i -e "s%^oldincludedir=.*%oldincludedir=${SROOT}/usr/include%" ${S}/${aFILE}
    done
    
    # 171102 final link: error: unrecognized command line option '--export-dynamic'
    # from t2, this fix, also needs "-lm"
    sed -i -e 's%\-\-export\-dynamic %-lm %' ${S}/src/Makefile

    ##180417 hmmm, might have to rethink all of this. aarch64 build wants gnu/stubs-32.h
    #if [ ! -f ${WORKDIR}/recipe-sysroot/usr/include/gnu/stubs-32.h ];then
    # ln -s stubs-64.h ${WORKDIR}/recipe-sysroot/usr/include/gnu/stubs-32.h
    #fi
}

# BK 170624 do_compile now complains using freetype-config
# also there is an absolute path /usr/include
SROOT = "${WORKDIR}/recipe-sysroot"
do_configure:prepend() {
    sed -i -e 's%$FREETYPE_CONFIG --cflags%pkg-config --cflags freetype2%' ${S}/configure
    sed -i -e 's%$FREETYPE_CONFIG --libs%pkg-config --libs freetype2%' ${S}/configure
    #sed -i -e "s%^includedir=.*%includedir=${SROOT}/usr/include%" ${S}/configure
    #sed -i -e "s%^oldincludedir=.*%oldincludedir=${SROOT}/usr/include%" ${S}/configure
    
    #171102 tries to use libpng16
    rm -f ${WORKDIR}/recipe-sysroot/usr/lib/pkgconfig/libpng.pc
    ln -s libpng12.pc ${WORKDIR}/recipe-sysroot/usr/lib/pkgconfig/libpng.pc
}

#20200921
ERROR_QA:remove = "compile-host-path"

HOMEPAGE = "http://puppylinux.org/wikka/InkLite"
SUMMARY = "A Gtk SVG Scaleable Vector Graphic application"
