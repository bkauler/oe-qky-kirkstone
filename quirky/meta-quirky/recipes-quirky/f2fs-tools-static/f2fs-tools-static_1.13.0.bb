SUMMARY = "Tools for Flash-Friendly File System (F2FS)"
HOMEPAGE = "https://git.kernel.org/pub/scm/linux/kernel/git/jaegeuk/f2fs-tools.git"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=362b4b2594cd362b874a97718faa51d3"

# util-linux to provide libuuid
DEPENDS = "util-linux file attr"

# v1.13.0
SRCREV = "284f77f0075a16a2ad1f3b0fb89b7f64a1bc755d"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/jaegeuk/f2fs-tools.git;branch=master \
           file://0001-f2fs-tools-Use-srcdir-prefix-to-denote-include-path.patch \
           "
S = "${WORKDIR}/git"

#CFLAGS += " -static"
LDFLAGS += " --static"

do_configure:prepend() {
 echo '
mkfs_f2fs_LDFLAGS = -all-static' > ${S}/mkfs/Makefile.am
 echo '
fsck_f2fs_LDFLAGS = -all-static' > ${S}/fsck/Makefile.am

 sed -i -e 's%^f2fscrypt_LDFLAGS =%f2fscrypt_LDFLAGS = -static%' ${S}/tools/Makefile.am
}

inherit pkgconfig autotools

#BBCLASSEXTEND = "native"
