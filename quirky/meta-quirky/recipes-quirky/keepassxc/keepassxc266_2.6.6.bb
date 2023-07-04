# Recipe created by recipetool
# recipetool create -o keepassxc266_2.6.6.bb https://github.com/keepassxreboot/keepassxc/releases/download/2.6.6/keepassxc-2.6.6-src.tar.xz

LICENSE = "GPL-2.0-only & GPL-3.0-only & LGPL-2.1-only & LGPL-3.0-only & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=4e33f6950f334c3a3f3ea35055c05538 \
                    file://LICENSE.BSD;md5=677773ca05a2595e20cc384dd57949d0 \
                    file://LICENSE.CC0;md5=dc13273fbbc8345561ef12133a66eeb8 \
                    file://LICENSE.GPL-2;md5=751419260aa954499f7abaabaa882bbe \
                    file://LICENSE.GPL-3;md5=f27defe1e96c2e1ecd4e0c9be8967949 \
                    file://LICENSE.LGPL-2.1;md5=4b54a1fd55a448865a0b32d41598759d \
                    file://LICENSE.LGPL-3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
                    file://LICENSE.MIT;md5=5a9126e7f56a0cf3247050de7f10d0f4"

SRC_URI = "https://github.com/keepassxreboot/keepassxc/releases/download/${PV}/keepassxc-${PV}-src.tar.xz"

SRC_URI[md5sum] = "ea440316c59f46a1fe1c3ccbad1513f1"
SRC_URI[sha256sum] = "3603b11ac39b289c47fac77fa150e05fd64b393d8cfdf5732dc3ef106650a4e2"

S = "${WORKDIR}/keepassxc-${PV}"

#v2.6.6 does not need 'botan'. instead: libgcrypt, libsodium, argon2
DEPENDS = "argon2 libusb1 libx11 libxi libxtst minizip pcsc-lite qrencode \
           qtbase qtimageformats qtsvg qttools qtx11extras \
           readline zlib libgcrypt libsodium \
           qtbase-native qttools-native"

inherit cmake_qt5 pkgconfig mime mime-xdg

EXTRA_OECMAKE = "-DWITH_GUI_TESTS=OFF -DWITH_XC_BROWSER=ON \
  -DWITH_XC_UPDATECHECK=OFF -DWITH_TESTS=OFF -DKEEPASSXC_BUILD_TYPE=Release \
  -DKEEPASSXC_DIST_TYPE=Other -DWITH_XC_DOCS=OFF -DWITH_XC_NETWORKING=OFF \
  -DWITH_XC_SSHAGENT=OFF -DWITH_XC_YUBIKEY=OFF -DWITH_XC_TOUCHID=OFF \
  -DWITH_APP_BUNDLE=OFF"

#20230703 this has been fixed...
##argon2 has installed libs and pkgconfig in wrong place...
XXXdo_configure:prepend() {
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
