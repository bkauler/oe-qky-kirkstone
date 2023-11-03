# Recipe created by recipetool
# recipetool create -o iotop-py_20220307.bb https://repo.or.cz/iotop.git/snapshot/a14256a3ff74eeee59493ac088561f1bafab85a7.tar.gz

SUMMARY = "Per process I/O bandwidth monitor"
HOMEPAGE = "http://guichaz.free.fr/iotop/"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

SRC_URI = "https://repo.or.cz/iotop.git/snapshot/a14256a3ff74eeee59493ac088561f1bafab85a7.tar.gz"
SRC_URI[md5sum] = "ae3be5af07f04ae8850997478095426e"
SRC_URI[sha256sum] = "e2a9740e61405547dd7479fd59a5cd7dcca25ffec0bb25f6d369e24a23f57f68"

S = "${WORKDIR}/iotop-a14256a"

DEPENDS += "python3-wheel-native"

#20221203
#distutils-common-base.bbclass is deprecated, please use setuptools3-base.bbclass instead
#distutils3.bbclass is deprecated, please use setuptools3.bbclass instead
#inherit distutils3
#inherit setuptools3
#20231013 setuptools3 fails...
inherit distutils3 python3-dir python3native

do_install:append() {
 install -d ${D}/usr/bin
 mv -f ${D}/usr/sbin/iotop ${D}/usr/bin/iotop-py
 rmdir ${D}/usr/sbin
}

RDEPENDS:${PN} += "python3-core python3-ctypes python3-curses python3-io \
    python3-pprint python3-profile python3-monotonic"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    _netlink
#    hotshot
#    hotshot.stats
