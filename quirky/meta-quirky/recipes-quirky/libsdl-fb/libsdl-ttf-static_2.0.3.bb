SUMMARY = "Simple DirectMedia Layer truetype font library"
SECTION = "libs"

LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=252890d9eee26aab7b432e8b8a616475"

SRC_URI = "http://www.libsdl.org/projects/SDL_ttf/release/SDL_ttf-${PV}.tar.gz \
           file://use.pkg-config.for.freetype2.patch \
           file://sdl-config"

SRC_URI[md5sum] = "29d12d1b883bf834c291c93f52ba8dc5"
SRC_URI[sha1sum] = "82e223ae8b2f5a2d99603ae107f292ee5b340696"
SRC_URI[sha256sum] = "7e2e7b46088a7b9594b255b58123598d49c5c3d11bcc3c9a8f80969cd9caa6c5"

S = "${WORKDIR}/SDL_ttf-${PV}"

inherit autotools pkgconfig

DEPENDS = "libsdl-fb-static freetype-static"

LDFLAGS += "-lm"

#20230118 should really replace with pkg-config...
#export SDL_CONFIG = "${STAGING_BINDIR_CROSS}/sdl-config"

EXTRA_OECONF:remove = "--disable-static"
EXTRA_OECONF += "--disable-shared --enable-static --without-x"

do_configure:prepend() {
    # make autoreconf happy
    touch ${S}/NEWS ${S}/AUTHORS ${S}/ChangeLog

 #   # Removing these files fixes a libtool version mismatch.
 #   MACROS="libtool.m4 lt~obsolete.m4 ltoptions.m4 ltsugar.m4 ltversion.m4"
 #
 #   for i in ${MACROS}; do
 #       rm -f ${S}/acinclude/$i
 #   done
 
 #20230118
 rm -f ${S}/acinclude.m4
 rm -f ${S}/aclocal.m4
 
 #20230118
 cp -f ${WORKDIR}/sdl-config ${WORKDIR}/recipe-sysroot/usr/bin/crossscripts/
 chmod 755 ${WORKDIR}/recipe-sysroot/usr/bin/crossscripts/sdl-config
}
