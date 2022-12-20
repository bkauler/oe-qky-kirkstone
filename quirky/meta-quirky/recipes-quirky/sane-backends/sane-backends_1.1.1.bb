# Recipe created by recipetool
# recipetool create -o sane-backends_1.1.1.bb https://gitlab.com/sane-project/backends/-/archive/1.1.1/backends-1.1.1.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://gitlab.com/sane-project/backends/-/archive/${PV}/backends-${PV}.tar.gz"

SRC_URI[md5sum] = "97eda1cd839533544372e57790132d83"
SRC_URI[sha1sum] = "977df1114fb876e535cd4727ec9ff167a2aa036e"
SRC_URI[sha256sum] = "4caa9155b797e8b83abc88bcbfc8212155d388311c3ba077bbcf9c98ed78ca0e"

S = "${WORKDIR}/backends-${PV}"

DEPENDS = "libusb1 libjpeg-turbo curl v4l-utils libxml2 poppler libpng12 tiff \
           cups gphoto2"

inherit gettext pkgconfig autotools

# po compile error, so have --disable-nls
EXTRA_OECONF = "--with-usb --without-systemd --with-gphoto2 --with-group=scanner \
                --without-avahi --with-v4l --disable-nls"

#configure.ac:63: error: required file './ABOUT-NLS' not found
do_configure:prepend() {
 touch ${S}/ABOUT-NLS
 
 #There is also a failure:
 # #define SANE_DLL_V_MAJOR UNKNOWN  error: 'UNKNOWN' undeclared
 #online search, this might fix it...
 # ref: https://gitlab.com/sane-project/backends/-/issues/440
 echo '1.1.1' > ${S}/.tarball-version
}

# --disable-nls still get po fail. do this:
do_compile:prepend() {
 echo -e '\n\nall:\n\ninstall:\n\nclean:\n\nuninstall:\n\n' > ${B}/po/Makefile
}

do_install:append() {
 rm -rf ${D}/run
 
 #ERROR: sane-backends-1.1.1-r0 do_populate_sysroot: QA Issue: sane-backends.pc failed sanity test (tmpdir)
 #well, i see sane-backends.pc has something weird in it...
 sed -i -e 's%^ldflags=.*%ldflags=-Wl,-O1 -Wl,--hash-style=gnu -Wl,--as-needed -Wl,-z,relro,-z,now%' ${D}/${libdir}/pkgconfig/sane-backends.pc
}

# datadir=/usr/share  libdir=/usr/lib
FILES:${PN} += "${datadir}/sane ${libdir}/sane"

INSANE_SKIP:${PN} += "dev-so"

HOMEPAGE = "http://www.sane-project.org"
SUMMARY = "The Scanner Access Now Easy backends"

