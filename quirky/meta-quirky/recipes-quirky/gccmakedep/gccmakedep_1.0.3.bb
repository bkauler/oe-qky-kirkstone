# Recipe created by recipetool
# recipetool create -o gccmakedep_1.0.3.bb https://www.x.org/releases/individual/util/gccmakedep-1.0.3.tar.gz

DEPENDS = "virtual/libx11"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4641deddaa80fe7ca88e944e1fd94a94"

SRC_URI = "https://www.x.org/releases/individual/util/gccmakedep-${PV}.tar.gz"
SRC_URI[md5sum] = "127ddb6131eb4a56fdf6644a63ade788"
SRC_URI[sha256sum] = "f9e2e7a590e27f84b6708ab7a81e546399b949bf652fb9b95193e0e543e6a548"

inherit autotools

EXTRA_OECONF = "--disable-selective-werror"

HOMEPAGE = "http://www.X.org"
SUMMARY = "Dependency generator"
