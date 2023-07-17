# Recipe created by recipetool
# recipetool create -o qtemu_20230101.bb https://distro.ibiblio.org/easyos/source/alphabetical/q/qtemu-20230101.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/q/qtemu-${PV}.tar.gz"

SRC_URI[md5sum] = "3f13b994838cfcefea4b35277a7ca6d0"
SRC_URI[sha256sum] = "b900b7ca6aeaf19b603dd3c5249f09fdce527ad5100f10f54113239d49bb40cc"

DEPENDS = "qtbase mesa libdrm libxcb qemu"

inherit qmake5 gettext pkgconfig

#have to install manually...
do_install() {
 install -d ${D}/usr/bin
 install -m755 ${B}/qtemu ${D}/usr/bin
 install -d ${D}/usr/share/applications
 echo '[Desktop Entry]
Type=Application
Name=QtEmu GUI for QEMU
GenericName=QtEmu
Comment=A front-end written in Qt for the QEMU emulator
Icon=qtemu.png
Exec=qtemu
Terminal=false
StartupNotify=false
Categories=Qt;System;
Keywords=QEMU;emulator;' > ${D}/usr/share/applications/qtemu.desktop
 install -d ${D}/usr/share/pixmaps
 install -m644 ${S}/images/QEMU.png ${D}/usr/share/pixmaps/qtemu.png
 install -d ${D}/usr/share/qtemu/translations
 install -m644 ${S}/translations/template_qtemu.ts ${D}/usr/share/qtemu/translations
}

HOMEPAGE = "https://gitlab.com/qtemu/gui"
SUMMARY = "GUI for QEMU"
