# Recipe created by recipetool
# recipetool create -o xfdiff-cut_4.5.0.bb http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/xfdiff-cut-4.5.0.tar.bz2

#PR = "r1"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://distro.ibiblio.org/quirky/quirky6/sources/t2/april/xfdiff-cut-${PV}.tar.bz2 \
           file://xfdiff-fix-aarch64.patch"
SRC_URI[md5sum] = "989d1520ad6b4c89733827df06f47793"
SRC_URI[sha256sum] = "f9b037ac5222058a5f7204eb803612fc6078190f17d1eb6ed1cb8971f641a5d1"

DEPENDS = "intltool-native gtk+ glib-2.0 libtubo diffutils patch libpng"

inherit autotools-brokensep pkgconfig gettext

#EXTRA_OECONF = ""

#do_configure_prepend() {
#    oe_runmake distclean
#}

#do_configure() {
#    oe_runconf
#}

#20200919
#ERROR: xfdiff-cut-4.5.0-r1 do_package_qa: QA Issue: xfdiff-cut: /work/core2-64-poky-linux/xfdiff-cut/4.5.0-r1/packages-split/xfdiff-cut/usr/bin/xfdiff4 contains probably-redundant RPATH /usr/lib [useless-rpaths]
ERROR_QA:remove = "useless-rpaths"

HOMEPAGE = ""
SUMMARY = "Graphical diff"
