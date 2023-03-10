# Recipe created by recipetool
# recipetool create -o gtkdialog_0.8.4pre-svn514.bb http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/gtkdialog-0.8.4pre-svn514.tar.bz2
# 20201025 vte 
# 20221029 now vte9 which is the old gtk2 version.

#PR = "r2"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 2}"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/gtkdialog-${PV}.tar.bz2 \
           file://gtkdialog-fix-aarch64-widget-order.patch"
SRC_URI[md5sum] = "2d4f4d783f867513887252b3b418026f"
SRC_URI[sha256sum] = "ef64020da838a44841dcad2c4e9dbcb128aca87bdcc35a194e6844cc36b44425"

DEPENDS = "bison-native flex-native gtk+ libglade vte9"

inherit pkgconfig autotools

EXTRA_OECONF = ""

#20221210 multiple defs
# ref: https://gcc.gnu.org/gcc-10/porting_to.html
CFLAGS:append = " -fcommon"

do_configure:prepend() {
 if [ ! -f ${S}/doc/version.texi ];then
  echo '@set UPDATED 24 September 2015
@set UPDATED-MONTH September 2015
@set EDITION 0.8.4
@set VERSION 0.8.4' > ${S}/doc/version.texi
 fi
}

# install cannot find 'gtk-update-icon-cache', do manual install...
do_install() {
    install -d ${D}/usr/bin
    install -m755 ${B}/src/gtkdialog ${D}/usr/bin
    install -d ${D}/usr/share/icons/hicolor/32x32/apps
    install -m644 ${S}/data/icons/hicolor/32x32/apps/gtkdialog.png ${D}/usr/share/icons/hicolor/32x32/apps
    install -d ${D}/usr/share/doc/gtkdialog/reference
    install -D ${S}/doc/reference/* ${D}/usr/share/doc/gtkdialog/reference
    #this is complicated coz has subdirs...
    #install -d ${D}/usr/share/doc/gtkdialog/examples
    #install -D ${S}/examples/* ${D}/usr/share/doc/gtkdialog/examples
    mkdir -p ${D}/usr/share/doc/gtkdialog/examples
    cp -a -f ${S}/examples/* ${D}/usr/share/doc/gtkdialog/examples/
}

# don't know why i am doing this, maybe needed for packaging...
FILES:${PN} += "/usr/share/doc/gtkdialog/reference /usr/share/doc/gtkdialog/examples"

HOMEPAGE = "https://github.com/01micko/gtkdialog"
SUMMARY = "A utility for creating X11 dialog boxes"
