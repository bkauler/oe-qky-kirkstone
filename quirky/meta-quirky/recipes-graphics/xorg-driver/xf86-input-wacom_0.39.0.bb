# Recipe created by recipetool
# recipetool create -o xf86-input-wacom_0.39.0.bb https://github.com/linuxwacom/xf86-input-wacom/releases/download/xf86-input-wacom-0.39.0/xf86-input-wacom-0.39.0.tar.bz2

SECTION = "x11/drivers"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://GPL;md5=cbbd794e2a0a289b9dfcc9f513d1996e"

SRC_URI = "https://github.com/linuxwacom/xf86-input-wacom/releases/download/xf86-input-wacom-${PV}/xf86-input-wacom-${PV}.tar.bz2"
SRC_URI[md5sum] = "9ee7bf6969002d6cfe9301354a72d7b0"
SRC_URI[sha1sum] = "eddbbc3eb97318875985344a5dd2c480aa75246b"
SRC_URI[sha256sum] = "12bf826e21bb61a9569be434e04cb1fcc68f7d8185a847ce899163f3152a1387"
SRC_URI[sha384sum] = "92c1a6125732fdf8d73cad941cd63fd459afbb09958fa49fbcbe825810b2c66395e31c5c5461f419877f49dbfedaad8c"
SRC_URI[sha512sum] = "9ad92c86c4ba3587d68e2107057c89dfe8628c0a2ec882f5a424ab4983c18ff6048489d7f6d3a8de87403744f74de1982de25327fc955bb5c21346a242e0aaa3"

DEPENDS = "virtual/xserver xorgproto util-macros libxi libx11 libxrandr libxinerama eudev libxext"

#Recipe inherits features_check but doesn't use it
#inherit pkgconfig autotools features_check
inherit pkgconfig autotools

EXTRA_OECONF = ""

HOMEPAGE = "https://github.com/linuxwacom/xf86-input-wacom"
SUMMARY = "xorg driver for wacom tablets"
