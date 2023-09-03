# Recipe created by recipetool
# recipetool create -o disktype_9-pext4.bb https://downloads.sourceforge.net/project/disktype/disktype/9/disktype-9.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=489af7eed5a3474b50d6723ff28ec508"

# 20210121 patches from debian: https://packages.debian.org/sid/disktype
SRC_URI = "https://downloads.sourceforge.net/project/disktype/disktype/9/disktype-9.tar.gz \
           file://10_add_new_FS_to_manpage.patch \
           file://20_add_ext4_btrfs_grub_improv.patch \
           file://30_add_GCC_hardening.patch \
           file://40_add_support_exfat.patch \
           file://50_add_support_f2fs.patch \
           file://60_update_README.patch"

SRC_URI[md5sum] = "25a673f162b9c01cd565109202559489"
SRC_URI[sha256sum] = "b6701254d88412bc5d2db869037745f65f94b900b59184157d072f35832c1111"

S = "${WORKDIR}/${BPN}-9"

# NOTE: this is a Makefile-only piece of software.
DEPENDS = "zlib bzip2 cpio tar"

do_configure () {
    sed -i -e 's%^CC = .*%# %' Makefile
    sed -i -e 's%^CPPFLAGS = %CPPFLAGS += -DUSE_IOCTL_LINUX %' Makefile
    sed -i -e 's%^CFLAGS   = %CFLAGS   += %' Makefile
    sed -i -e 's%^LDFLAGS  =%# %' Makefile
    sed -i -e 's%^%%' Makefile
}

do_compile () {
	oe_runmake NOSYS=1
}

do_install () {
    install -d ${D}${sbindir}
    install -m755 disktype ${D}${sbindir}
}


HOMEPAGE = "http://disktype.sourceforge.net/"
SUMMARY = "Disk type identification utility"
