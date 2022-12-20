SUMMARY = "Console URL download utility supporting HTTP, FTP, etc"
HOMEPAGE = "https://www.gnu.org/software/wget/"
SECTION = "console/network"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
DEPENDS = "gnutls zlib libpcre"

inherit autotools gettext texinfo update-alternatives pkgconfig

EXTRA_OECONF = "--enable-ipv6 --with-ssl=gnutls --disable-rpath --disable-iri \
                --without-libgnutls-prefix ac_cv_header_uuid_uuid_h=no"

ALTERNATIVE:${PN} = "wget"
ALTERNATIVE:${PN}:class-nativesdk = ""
ALTERNATIVE_PRIORITY = "100"

RRECOMMENDS:${PN} += "ca-certificates"

BBCLASSEXTEND += "nativesdk"

PACKAGECONFIG ??= ""
PACKAGECONFIG[libuuid] = "--with-libuuid, --without-libuuid,util-linux"

# Let aclocal use the relative path for the m4 file rather than
# absolute, otherwise there might be an "Argument list too long" error
# when it is built in a long/deep directory.
acpaths = "-I ./m4"
