# Recipe created by recipetool

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

#20230405 patches from debian...
SRC_URI = "https://gnupg.org/ftp/gcrypt/gnupg/gnupg-${PV}.tar.bz2 \
  file://0005-gpg-Change-default-digest-algorithm-to-SHA256.patch \
  file://0006-fix-for-gcc10.patch"

SRC_URI[md5sum] = "b1df02c73572f27bc859ac05ff2259ab"
SRC_URI[sha256sum] = "c9462f17e651b6507848c08c430c791287cd75491f8b5a8b50c6ed46b12678ba"

S = "${WORKDIR}/gnupg-${PV}"

#DEPENDS = "libcap bzip2 zlib"
DEPENDS = "zlib"

#inherit perlnative gettext autotools
inherit perlnative autotools

# EXTRA_OECONF = " --enable-minimal"
# ...cuts down too much, is broken
EXTRA_OECONF = " --with-tar=/bin --disable-nls --disable-keyserver-path --disable-mailto --disable-finger --disable-hkp --disable-ldap --disable-keyserver-helpers --disable-photo-viewers --disable-bzip2 --disable-twofish --disable-blowfish --disable-idea --disable-agent-support --disable-card-support --disable-gnupg-iconv --disable-asm --enable-dev-random --enable-static-rnd=linux --without-readline --without-libusb --disable-camellia"

LDFLAGS += "-static"

#use /dev/urandom so it doesn't block...
do_compile:prepend() {
 sed -i -e 's%.* NAME_OF_DEV_RANDOM .*%#define NAME_OF_DEV_RANDOM "/dev/urandom"%' ${B}/config.h
 
 #20210828 compile error in build/intl
 echo -e 'all:\n\nall-yes:\n\nall-no:\n\nall-no-yes:\n\ncheck:\n\ninstall:\n\ninstall-exec:\n\ninstall-data:\n\ninstall-strip:\n\ninstalldirs:\n\nuninstall:\n\nclean:\n\n' > ${B}/intl/Makefile
}

# what we end up with:
# Supported algorithms:
# Pubkey: RSA, RSA-E, RSA-S, ELG-E, DSA
# Cipher: 3DES, CAST5, AES, AES192, AES256
# Hash: MD5, SHA1, RIPEMD160, SHA256, SHA384, SHA512, SHA224
# Compression: Uncompressed, ZIP, ZLIB

HOMEPAGE = "https://gnupg.org/"
SUMMARY = "Gnu Privacy Guard"
