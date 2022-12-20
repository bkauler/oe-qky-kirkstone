# Recipe created by recipetool
# recipetool create -o helpsurfer_0.6.bb http://distro.ibiblio.org/easyos/source/alphabetical/h/helpsurfer-0.6.tar.gz

#refs:
#https://bkhome.org/news/202002/helpsurfer-internal-help-viewer-crashes.html
#https://bkhome.org/news/201708/helpsurfer-03.html
#https://bkhome.org/news/201708/improving-surfer-tiny-web-browser.html
#https://bkhome.org/news/202002/helpsurfer-crash-fixed.html

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/h/helpsurfer-${PV}.tar.gz"
SRC_URI[md5sum] = "92aae580c400c0994b0241f41e55acc2"
SRC_URI[sha1sum] = "db46640d1dcb33c775b6170605a966207268a19e"
SRC_URI[sha256sum] = "2a608b9fa48b97bdcb34820f84c33d7433a037747efb953d44494a1e3a037979"
SRC_URI[sha384sum] = "034619cc4fd79dfd495f9759c295992a34cb0d0a346d2a17173f0551a464f3d441965f2efc3593c100cad37322173218"
SRC_URI[sha512sum] = "875793eaef7315de7d1b04030fee7210a474f25ca87e7271d207fe500f9a83d54b84c2c9ef5597b56491dc8927d68816f4bc3bf9e5135211773dd0183c8f043e"

S = "${WORKDIR}/helpsurfer-0.6"

DEPENDS = "gtk+ libgtkhtml gnet libsystem gdk-pixbuf openssl libxml2"
inherit pkgconfig

SUSR = "${WORKDIR}/recipe-sysroot/usr"

do_configure () {
    if [ -f ${S}/download ];then
     rm -f ${S}/download
    fi
    if [ -f ${S}/surfer ];then
     rm -f ${S}/surfer
    fi
    sed -i -e 's%^CC	=%# CC	=%' ${S}/src/Makefile
    sed -i -e 's%^CFLAGS	=%CFLAGS	+=%' ${S}/src/Makefile
    sed -i -e 's%^LDFLAGS	=%# LDFLAGS	=%' ${S}/src/Makefile
    sed -i -e 's%^DESTDIR	=%# DESTDIR	=%' ${S}/src/Makefile
    sed -i -e 's%^surfer_LDFLAGS.*%surfer_LDFLAGS = $(LDFLAGSF) $(LDFLAGS) -L $(SUSR)/lib -Wl,-rpath,$(SUSR)/lib -l System `pkg-config --libs gtk+-2.0  libgtkhtml-2.0 gnet-2.0`%' ${S}/src/Makefile
    sed -i -e 's%^DESTDIR	=%# DESTDIR	=%' ${S}/data/Makefile
}

do_compile () {
    oe_runmake
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

HOMEPAGE = "http://murga-linux.com/puppy/viewtopic.php?t=111186"
SUMMARY = "A tiny web browser for viewing local html files."

#i can't see where it is accessing the host system, so this hack for now...
#ERROR: helpsurfer-0.6-r0 do_package_qa: QA Issue: helpsurfer: The compile log indicates that host include and/or library paths were used.
#         Please check the log '/mnt/sda1/nvme/oe/oe-quirky/build-amd64/tmp/work/core2-64-poky-linux/helpsurfer/0.6-r0/temp/log.do_compile' for more information. [compile-host-path]
#ERROR: helpsurfer-0.6-r0 do_package_qa: QA Issue: helpsurfer: /work/core2-64-poky-linux/helpsurfer/0.6-r0/packages-split/helpsurfer/usr/bin/surfer contains probably-redundant RPATH /lib [useless-rpaths]
ERROR_QA:remove = "compile-host-path"
ERROR_QA:remove = "useless-rpaths"
