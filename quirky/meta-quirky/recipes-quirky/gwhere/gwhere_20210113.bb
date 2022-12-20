# Recipe created by recipetool
# recipetool create -o gwhere_20210113.bb http://distro.ibiblio.org/easyos/source/alphabetical/g/gwhere-20210113.tar.gz

# source from: 
# https://github.com/wdlkmpx/gwhere

# 20210121 PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/g/gwhere-${PV}.tar.gz"
SRC_URI[md5sum] = "4259db4fcd85eba700b1f7328cac2faa"
SRC_URI[sha1sum] = "1120da5d66d7a726d41c114a05f6c0ccb49c1446"
SRC_URI[sha256sum] = "15a0fdb2662c432b6d4fb89d3eaadcd1ea71b19fc26858d20a8003014a67f500"
SRC_URI[sha384sum] = "d73aba661ca0a994aab68914a46cfb6e4382df295c6583b10c2fec5b8c12baea1fb2109adc80d3cd89fa1d5c56d41ec6"
SRC_URI[sha512sum] = "af45eebbebf09e5d8845df609f3b37ee85ab4c0a99c02d72e82a1901f17abe4d6cae3409433928cdc803d6fc4c0832ef494170969ba10dd8e07d573f896b5fda"

DEPENDS = "intltool-native zlib gtk+ glib-2.0 glib-2.0-native"

inherit gettext pkgconfig autotools-brokensep

EXTRA_OECONF = "--disable-debug"

#do not know if need these..
# OE imposes -Wdeprecated-declarations, this overrides, reduces verbosity in do_compile file...
CFLAGS:append = " -Wno-error=format-security -Wno-deprecated-declarations "

#20221210 multiple defs
# ref: https://gcc.gnu.org/gcc-10/porting_to.html
CFLAGS:append = " -fcommon"

do_configure:prepend() {
 touch ${S}/config.rpath
}

# install, get QA errors, files in wrong place...
INSANE_SKIP:${PN} += "dev-so libdir"

HOMEPAGE = "https://github.com/wdlkmpx/gwhere"
SUMMARY = "Removable media catalog manager"
