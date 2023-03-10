SUMMARY = "Shared MIME type database and specification"
DESCRIPTION = "The shared-mime-info package contains the core database of common types and the update-mime-database command used to extend it. It requires glib2 to be installed for building the update command. Additionally, it uses intltool for translations, though this is only a dependency for the maintainers."
HOMEPAGE = "http://freedesktop.org/wiki/Software/shared-mime-info"
SECTION = "base"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "libxml2 itstool-native glib-2.0 shared-mime-info-native"

SRC_URI = "git://gitlab.freedesktop.org/xdg/shared-mime-info.git;protocol=https;branch=master"
SRCREV = "829b26d85e7d89a0caee03046c3bce373f04c80a"
PV = "1.15"
S = "${WORKDIR}/git"

UPSTREAM_CHECK_GITTAGREGEX = "Release-(?P<pver>(\d+(\-\d+)+))"
UPSTREAM_VERSION_UNKNOWN = "1"

inherit autotools pkgconfig gettext python3native mime

EXTRA_OECONF = "--disable-update-mimedb"

FILES:${PN} += "${datadir}/mime"
FILES:${PN}-dev += "${datadir}/pkgconfig/shared-mime-info.pc"

# freedesktop.org.xml is only required when updating the mime database,
# package it separately
PACKAGES =+ "shared-mime-info-data"
FILES:shared-mime-info-data = "${datadir}/mime/packages/freedesktop.org.xml"
RDEPENDS:shared-mime-info-data = "shared-mime-info"

do_install () {
	autotools_do_install

	update-mime-database ${D}${datadir}/mime
}

do_install:class-native () {
	autotools_do_install

	${B}/update-mime-database ${D}${datadir}/mime
}

BBCLASSEXTEND = "native nativesdk"
