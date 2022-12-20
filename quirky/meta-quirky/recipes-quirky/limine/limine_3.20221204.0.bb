# Recipe created by recipetool
# recipetool create -o limine_3.20221014.1.bb https://github.com/limine-bootloader/limine/releases/download/v3.20221014.1/limine-3.20221014.1.tar.gz

SUMMARY = "x86/x86_64 BIOS/UEFI bootloader"
HOMEPAGE = "https://limine-bootloader.org/"

LICENSE = "UCB & GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=566da3e4be60cd868e42cb88c9c0ddd3 \
                    file://stivale/LICENSE.md;md5=21e2e2557201f26b907e5694c9e179b9 \
                    file://common/term/LICENSE.md;md5=bdbafa2477d7362d3d6f2833a16c25e5 \
                    file://libgcc-binaries/COPYING.RUNTIME;md5=fe60d87048567d4fe8c8a0ed2448bcc8 \
                    file://libgcc-binaries/COPYING;md5=64305ff51f34d0aca60d187e21a09d59 \
                    file://libgcc-binaries/COPYING3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://freestanding-headers/LICENSE.md;md5=9621911223dde43748ad798e3804a3ed \
                    file://limine-efi/COPYING;md5=c27a4b4a954b36c8afddf7587fd749be \
                    file://cross-detect/LICENSE.md;md5=9621911223dde43748ad798e3804a3ed"

SRC_URI = "https://github.com/limine-bootloader/limine/releases/download/v${PV}/limine-${PV}.tar.gz \
           file://remove-ext4-encrypt-warning.patch"

SRC_URI[md5sum] = "c50f870026282a07261329598129bc42"
SRC_URI[sha1sum] = "0dc40e951ff469cdf9dbf29c2cf3af5d1055e03e"
SRC_URI[sha256sum] = "cffb1b4c2f17c8dcd1b0acfc892f18b4c356e899b7f3cb73dfdb6f58cdcc2a63"

inherit autotools

DEPENDS = "mtools-native nasm-native coreutils-native"

# ref: https://github.com/limine-bootloader/limine/issues/193
export CROSS_TOOLCHAIN="${TARGET_SYS}"

#20220819 make this work for i686 host...
#EXTRA_OECONF = "--enable-all"
EXTRA_OECONF = "--enable-bios --enable-limine-deploy --enable-bios-cd --enable-bios-pxe"
EXTRA_OECONF:append:x86-64 = " --enable-uefi-ia32 --enable-uefi-x86_64 --enable-cd-efi"

