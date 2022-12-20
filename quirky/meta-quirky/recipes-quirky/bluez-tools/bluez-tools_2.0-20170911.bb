# Recipe created by recipetool
# recipetool create -o bluez-tools_2.0-20170911.bb http://deb.debian.org/debian/pool/main/b/bluez-tools/bluez-tools_2.0~20170911.0.7cb788c.orig.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

# note, currently oe dunfell has gcc 9
SRC_URI = "http://deb.debian.org/debian/pool/main/b/bluez-tools/bluez-tools_2.0~20170911.0.7cb788c.orig.tar.gz \
 file://0001-Fix-build-with-gcc-10.patch"

SRC_URI[md5sum] = "a38babd2b66aff763f8e2058e76b9f1d"
SRC_URI[sha1sum] = "627ad80acb98d020fdd60665fc2f4ffac57db237"
SRC_URI[sha256sum] = "1032cc874f34259bb5a325200753473b07e3ab1b2bccc0d8393d16a6a8dd6a52"
SRC_URI[sha384sum] = "f65f7b23133507e90ce40fb594985839a7447548bb776ca68da9119f5689fec5adff4d7048831e425b81e11b48d75b1f"
SRC_URI[sha512sum] = "847eeff54327e1a15c7d65d14e27cf1b5bbec1cf478482f84d1c1688c9f5ba19e86b8bebb5cd5db26d0be8debe67753568c0216d22ee6c83e830b8e28e813d7c"

S = "${WORKDIR}/${BPN}-2.0~20170911.0.7cb788c"

DEPENDS = "glib-2.0 readline bluez5 dbus-glib dbus-glib-native udev"

inherit pkgconfig autotools

EXTRA_OECONF = ""

HOMEPAGE = "https://github.com/khvzak/bluez-tools"
SUMMARY = "Commandline tools for bluetooth"
