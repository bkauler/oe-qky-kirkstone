# Recipe created by recipetool
# recipetool create -o aqemu_0.9.2.bb https://github.com/tobimensch/aqemu/archive/refs/tags/v0.9.2.tar.gz
# note, later versions of aqemu crash at startup.

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=d461aa93e2ce1d0aa97a257c16a6dc95"

#patches from debian
SRC_URI = "https://github.com/tobimensch/aqemu/archive/refs/tags/v${PV}.tar.gz \
           file://01_qemu_parallel_typo.patch \
           file://0002-Remove-VLAN-stuff-QEMU-doesn-t-support-it-anymore.patch \
           file://0003-Fix-build-with-GCC-10.patch \
           file://0004-manpage-fix-groff-message-aqemu.1-Empty-input-line-1.patch \
           file://0005-fix-spelling-of-some-words.patch \
           file://0006-Update-desktop-file-and-move-icons-to-share-icons.patch \
           file://0007-Fix-crash-because-of-duplicated-widget-name.patch \
"

SRC_URI[md5sum] = "cd7f6077a5a49a25450dc007532b0f95"
SRC_URI[sha256sum] = "e3d54de00ebdce3754f97f7e0e7cce8cebb588e8ce6bc249401cc909281b08de"

inherit cmake_qt5 gettext pkgconfig mime mime-xdg

DEPENDS = "qtbase mesa libdrm libxcb qemu libvncserver qtbase-native"

EXTRA_OECMAKE = ""

HOMEPAGE = "https://github.com/tobimensch/aqemu"
SUMMARY = "GUI for QEMU"
