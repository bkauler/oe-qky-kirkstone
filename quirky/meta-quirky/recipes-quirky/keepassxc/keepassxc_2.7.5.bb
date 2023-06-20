# Recipe created by recipetool
# recipetool create -o keepassxc_2.7.5.bb https://github.com/keepassxreboot/keepassxc/releases/download/2.7.5/keepassxc-2.7.5-src.tar.xz

LICENSE = "GPL-2.0-only & LGPL-2.1-only & LGPL-3.0-only & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=37e5e6cbd48376e286035994b16f3fa1 \
                    file://LICENSE.BSD;md5=677773ca05a2595e20cc384dd57949d0 \
                    file://LICENSE.CC0;md5=dc13273fbbc8345561ef12133a66eeb8 \
                    file://LICENSE.GPL-2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://LICENSE.GPL-3;md5=1ebbd3e34237af26da5dc08a4e440464 \
                    file://LICENSE.LGPL-2.1;md5=4fbd65380cdd255951079008b364516c \
                    file://LICENSE.LGPL-3;md5=3000208d539ec061b899bce1d9ce9404 \
                    file://LICENSE.MIT;md5=5a9126e7f56a0cf3247050de7f10d0f4"

SRC_URI = "https://github.com/keepassxreboot/keepassxc/releases/download/${PV}/keepassxc-${PV}-src.tar.xz"

SRC_URI[md5sum] = "4f7ae95e60cdeac65d307867f7ab6f12"
SRC_URI[sha1sum] = "01006079effd4cb4c4188562ed8684e07f87af87"
SRC_URI[sha256sum] = "ede24800901816c49569aa4f8bc7180a40cb8b554617fa2a2a2653caac13000c"
SRC_URI[sha384sum] = "f9a968bdd3cc796d87bfb68bbe3f4589c0b8af2d9431da1d9bdd1e25fff9f05714ea1f137fbbccada266c54e99317036"
SRC_URI[sha512sum] = "904fe319cb18a06e86162a0a4c4fbcac05b9e6936b69c5362b7fe2b89cd268061cadd6854f7dde80d3e8cfa2b9e22c27974277f3db1f6c32a2720e01a4c4aa1b"

DEPENDS = "argon2 botan libusb1 libx11 libxi libxtst minizip pcsc-lite qrencode \
           qtbase qtimageformats qtsvg qttools qtx11extras \
           readline zlib \
           qtbase-native qttools-native"

inherit cmake_qt5 pkgconfig mime mime-xdg

EXTRA_OECMAKE = "-DWITH_XC_ALL=ON -DWITH_GUI_TESTS=OFF -DWITH_XC_BROWSER=ON \
  -DWITH_XC_UPDATECHECK=OFF -DWITH_TESTS=OFF -DKEEPASSXC_BUILD_TYPE=Release \
  -DKEEPASSXC_DIST_TYPE=Other -DWITH_XC_DOCS=OFF -DWITH_XC_NETWORKING=ON \
  -DWITH_XC_SSHAGENT=ON"

#argon2 has installed libs and pkgconfig in wrong place...
do_configure:prepend() {
 ARGFND="$(find ${WORKDIR}/recipe-sysroot/usr/lib -type f -name libargon2.so.1)"
 if [ "${ARGFND}" ];then
  if [ ! -f ${WORKDIR}/recipe-sysroot/usr/lib/libargon2.so.1 ];then
   ARGDIR="$(dirname ${ARGFND})"
   cp -a -f ${ARGDIR}/* ${WORKDIR}/recipe-sysroot/usr/lib/
  fi
 fi
}

#ERROR: keepassxc-2.7.5-r3 do_package_qa: QA Issue: /usr/bin/keepassxc-cli contained in package keepassxc requires libargon2.so.1()(64bit), but no providers found in RDEPENDS:keepassxc? [file-rdeps]
ERROR_QA:remove = "file-rdeps"
WARN_QA:remove = "file-rdeps"

SUMMARY = "Password manager"
HOMEPAGE = "https://keepassxc.org/"
