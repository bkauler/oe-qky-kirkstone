DESCRIPTION = "File manager at the core of the ROX desktop"
HOMEPAGE = "http://rox.sf.net"
SECTION = "x11/applications"

DEPENDS = "gtk+ shared-mime-info"

RDEPENDS_${PN} = "shared-mime-info"

# 20210121 PR_NUM is defined in local.conf...
# 20211119 bump r5 to r6... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://../../README;md5=02172a117c24c52b026358d2edfb3590"

#20221218 new patch to remove hyphens
#20231203 new 011-remove-customise-menu.patch
SRC_URI = "http://distro.ibiblio.org/easyos/source/oe/pyro/rox-filer-2011-10-22-has_configure.tar.gz \
        file://001-Add-explicit-dependency-on-libm-and-libdl.patch;striplevel=3 \
        file://002-Extend-commit-c2232d50-to-Send-to.patch;striplevel=3 \
        file://003-rox-filer-20111022-modify-columns.patch;striplevel=3 \
        file://004-rox-filer-default-settings.patch;striplevel=3 \
        file://006-jwm-menus-fix.patch;striplevel=3 \
        file://007-sendto-to-openwith.patch;striplevel=3 \
        file://008-remove-user-msg.patch;striplevel=3 \
        file://009-rox-filer-home-easy-quirky.patch;striplevel=3 \
        file://010-remove-gethostbyname.patch;striplevel=3 \
        file://rox-filer-2.11-gcc-10-fix.patch \
        file://remove-hyphens-pango1p44.patch \
        file://011-remove-customise-menu.patch;striplevel=3"

SRC_URI[md5sum] = "b5968a9d6bd72e63d3adb2ba04f5d988"
SRC_URI[sha256sum] = "6c96dec2ba8232c03fed36c03227a46265b5360a486bdf3db4647be5311fabae"

inherit autotools-brokensep mime pkgconfig

S = "${WORKDIR}/rox-filer-2011-10-22-has_configure/ROX-Filer/src"

# do not recreate configure...
do_configure() {
    #this replaces the 005-* patch...
    sed -i -e 's%Log viewer">%Log viewer">\n   <property name="width_request">640</property>\n   <property name="height_request">300</property>%' ../Templates.ui
    #has code to prevent compiling, remove...
    sed -i -e 's% \-f configure % -f configureXXX %' ${S}/configure
    #needs -lm -ldl
    sed -i -e 's%\-lX11 %-lX11 -lm -ldl %' ${S}/configure
    oe_runconf
}

#note, this not needed, will do this anyway...
do_compile() {
	oe_runmake
}

do_install() {
    install -d ${D}/usr/local/apps/ROX-Filer
    install -m755 ../ROX-Filer ${D}/usr/local/apps/ROX-Filer
    install -m644 ../AppInfo.xml ${D}/usr/local/apps/ROX-Filer
    install -m755 ../AppRun ${D}/usr/local/apps/ROX-Filer
    install -m644 ../Options.xml ${D}/usr/local/apps/ROX-Filer
    install -m644 ../style.css ${D}/usr/local/apps/ROX-Filer
    install -m644 ../subclasses ${D}/usr/local/apps/ROX-Filer
    install -m644 ../Templates.ui ${D}/usr/local/apps/ROX-Filer
    install -m644 ../.DirIcon ${D}/usr/local/apps/ROX-Filer/
    install -d ${D}/usr/local/apps/ROX-Filer/images
    install -D ../images/* ${D}/usr/local/apps/ROX-Filer/images
}

FILES:${PN} += "/usr/local/apps/ROX-Filer"

SUMMARY = "Drag-and-drop based filemanager"
