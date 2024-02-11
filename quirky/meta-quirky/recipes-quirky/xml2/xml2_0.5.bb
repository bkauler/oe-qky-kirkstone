# Recipe created by recipetool
# recipetool create -o xml2_0.5.bb http://deb.debian.org/debian/pool/main/x/xml2/xml2_0.5.orig.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"

SRC_URI = "http://deb.debian.org/debian/pool/main/x/xml2/xml2_${PV}.orig.tar.gz"

SRC_URI[md5sum] = "48eacf64b01ca3a4a5afb1a36f5906e6"
SRC_URI[sha256sum] = "e3203a5d3e5d4c634374e229acdbbe03fea41e8ccdef6a594a3ea50a50d29705"

DEPENDS = "libxml2"

inherit pkgconfig autotools

EXTRA_OECONF = ""

HOMEPAGE = "https://packages.debian.org/trixie/xml2"
SUMMARY = "Convert xml, html, csv to and from line-oriented format"
