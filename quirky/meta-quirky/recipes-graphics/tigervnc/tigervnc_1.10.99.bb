# Recipe created by recipetool
# recipetool create -o tigervnc_1.10.99.bb https://distro.ibiblio.org/easyos/source/alphabetical/t/tigervnc-1.10.99.tar.gz
#this is the last commit before pam became mandatory:
# ref: https://github.com/TigerVNC/tigervnc/tree/a92aec4fbb287c2ebb79f3d3406668086057ce20
#most of the recipe below is based on tigervnc 1.11.0 recipe in kirkstone meta-oe/recipes-graphics

DESCRIPTION = "TigerVNC remote display system"
HOMEPAGE = "https://www.tigervnc.com/"
SECTION = "x11/utils"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENCE.TXT;md5=75b02c2872421380bbd47781d2bd75d3"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/t/tigervnc-${PV}.tar.gz \
           file://0002b-do-not-build-tests.patch \
           file://0003-add-missing-dynamic-library-to-FLTK_LIBRARIES.patch \
           file://0004-tigervnc-add-fPIC-option-to-COMPILE_FLAGS.patch"

SRC_URI[md5sum] = "6d5acea4dcedbd249304679ba3210bb6"
SRC_URI[sha1sum] = "167fa097ee180fdb8eae381025fa22af5ca97218"
SRC_URI[sha256sum] = "4d16c98e8d06ccd0979d9be466cbcf6204e42a753bfe032c54dd1a9861c8c7d5"

DEPENDS = "fontconfig xserver-xorg gnutls libjpeg-turbo libx11 libxtst \
           gettext-native fltk zlib"

RDEPENDS:${PN} = "bash xkbcomp"

inherit autotools cmake features_check pkgconfig gettext

REQUIRED_DISTRO_FEATURES = "x11"

B = "${S}"

# Keep sync with xorg-server in oe-core
XORG_PN ?= "xorg-server"
XORG_PV ?= "1.20.6"
SRC_URI += "${XORG_MIRROR}/individual/xserver/${XORG_PN}-${XORG_PV}.tar.bz2;name=xorg"
XORG_S = "${WORKDIR}/${XORG_PN}-${XORG_PV}"
SRC_URI[xorg.md5sum] = "a98170084f2c8fed480d2ff601f8a14b"
SRC_URI[xorg.sha256sum] = "6316146304e6e8a36d5904987ae2917b5d5b195dc9fc63d67f7aca137e5a51d1"

# It is the directory containing the Xorg source for the
# machine on which you are building TigerVNC.
XSERVER_SOURCE_DIR="${S}/unix/xserver"

do_patch[postfuncs] += "do_patch_xserver"
do_patch_xserver () {
    for subdir in Xext xkb GL hw/xquartz/bundle hw/xfree86/common; do
        install -d ${XSERVER_SOURCE_DIR}/$subdir
    done

    for subdir in hw/dmx/doc man doc hw/dmx/doxygen; do
        install -d ${XSERVER_SOURCE_DIR}/$subdir
    done

    sources="hw/xquartz/bundle/cpprules.in man/Xserver.man doc/smartsched \
             hw/dmx/doxygen/doxygen.conf.in xserver.ent.in xkb/README.compiled \
             hw/xfree86/xorgconf.cpp hw/xfree86/Xorg.sh.in"
    for i in ${sources}; do
        install -m 0644 ${XORG_S}/$i ${XSERVER_SOURCE_DIR}/$i;
    done

    cd ${XORG_S}
    find . -type f | egrep '.*\.(c|h|am|ac|inc|m4|h.in|pc.in|man.pre|pl|txt)$' | \
    xargs tar cf - | (cd ${XSERVER_SOURCE_DIR} && tar xf -)

    cd ${XSERVER_SOURCE_DIR}
    xserverpatch="${S}/unix/xserver120.patch"
    echo "Apply $xserverpatch"
    patch -p1 -b --suffix .vnc < $xserverpatch
}

EXTRA_OECONF = "--disable-xorg --disable-xnest --disable-xvfb --disable-dmx \
        --disable-xwin --disable-xephyr --disable-kdrive --with-pic \
        --disable-static --disable-xinerama \
        --with-xkb-output=${localstatedir}/lib/xkb \
        --disable-glx --disable-dri --disable-dri2 \
        --disable-config-hal \
        --disable-config-udev \
        --without-dtrace \
        --disable-unit-tests \
        --disable-devel-docs \
        --disable-selective-werror \
        --disable-xshmfence \
        --disable-config-udev \
        --disable-dri3 \
        --disable-libunwind \
        --without-xmlto \
        --enable-systemd-logind=no \
        --disable-xinerama \
        --disable-xwayland \
"

EXTRA_OECMAKE += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '-DCMAKE_INSTALL_UNITDIR=${systemd_unitdir}', '-DINSTALL_SYSTEMD_UNITS=OFF', d)}"

do_configure:append () {
    olddir=`pwd`
    cd ${XSERVER_SOURCE_DIR}

    rm -rf aclocal-copy/
    rm -f aclocal.m4

    export ACLOCALDIR="${XSERVER_SOURCE_DIR}/aclocal-copy"
    mkdir -p ${ACLOCALDIR}/
    if [ -d ${STAGING_DATADIR_NATIVE}/aclocal ]; then
        cp-noerror ${STAGING_DATADIR_NATIVE}/aclocal/ ${ACLOCALDIR}/
    fi
    if [ -d ${STAGING_DATADIR}/aclocal -a "${STAGING_DATADIR_NATIVE}/aclocal" != "${STAGING_DATADIR}/aclocal" ]; then
        cp-noerror ${STAGING_DATADIR}/aclocal/ ${ACLOCALDIR}/
    fi
    ACLOCAL="aclocal --system-acdir=${ACLOCALDIR}/" autoreconf -Wcross --verbose --install --force ${EXTRA_AUTORECONF} $acpaths || bbfatal "autoreconf execution failed."
    chmod +x ./configure
    ${CACHED_CONFIGUREVARS} ./configure ${CONFIGUREOPTS} ${EXTRA_OECONF}
    cd $olddir
}

do_compile:append () {
    olddir=`pwd`
    cd ${XSERVER_SOURCE_DIR}

    oe_runmake

    cd $olddir
}

do_install:append() {
    olddir=`pwd`
    cd ${XSERVER_SOURCE_DIR}/hw/vnc

    oe_runmake 'DESTDIR=${D}' install

    cd $olddir
}

FILES:${PN} += " \
    ${libdir}/xorg/modules/extensions \
    ${datadir}/icons \
    ${systemd_unitdir} \
"

FILES:${PN}-dbg += "${libdir}/xorg/modules/extensions/.debug"
