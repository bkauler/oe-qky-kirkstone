# Recipe created by recipetool
# recipetool create -o limine_4.20231006.0.bb https://github.com/limine-bootloader/limine/releases/download/v4.20231006.0/limine-4.20231006.0.tar.gz

SUMMARY = "x86/x86_64 BIOS/UEFI bootloader"
HOMEPAGE = "https://limine-bootloader.org/"

LICENSE = "0BSD & BSD-2-Clause & GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5048071341a34d6ee7cfd620c7b8c5ad \
                    file://common/flanterm/LICENSE;md5=09632083f0cc1cd8cba3da105d7b2164 \
                    file://freestanding-headers/LICENSE;md5=816588618cfe863e6139e3242192f9ab \
                    file://libgcc-binaries/COPYING.RUNTIME;md5=fe60d87048567d4fe8c8a0ed2448bcc8 \
                    file://libgcc-binaries/COPYING3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://limine-efi/COPYING;md5=c27a4b4a954b36c8afddf7587fd749be"

SRC_URI = "https://github.com/limine-bootloader/limine/releases/download/v${PV}/limine-${PV}.tar.gz \
           file://remove-ext4-encrypt-warning.patch"

SRC_URI[md5sum] = "397a4d69635dd559bc040bd5ed25d9e1"
SRC_URI[sha256sum] = "77af00e354f0a9a6a1e4d13281186854ab403c05b2a3650be4f9d5ede381f8d2"

inherit autotools

DEPENDS = "mtools-native nasm-native coreutils-native"

# ref: https://github.com/limine-bootloader/limine/issues/193
export CROSS_TOOLCHAIN="${TARGET_SYS}"

#20220819 make this work for i686 host...
#EXTRA_OECONF = "--enable-all"
EXTRA_OECONF = "--enable-bios --enable-limine-deploy --enable-bios-cd --enable-bios-pxe"
EXTRA_OECONF:append:x86-64 = " --enable-uefi-ia32 --enable-uefi-x86_64 --enable-cd-efi"
