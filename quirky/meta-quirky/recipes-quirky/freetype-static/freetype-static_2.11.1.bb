SUMMARY = "Freetype font rendering library"
DESCRIPTION = "FreeType is a software font engine that is designed to be small, efficient, \
highly customizable, and portable while capable of producing high-quality output (glyph \
images). It can be used in graphics libraries, display servers, font conversion tools, text \
image generation tools, and many other products as well."
HOMEPAGE = "http://www.freetype.org/"
BUGTRACKER = "https://savannah.nongnu.org/bugs/?group=freetype"
SECTION = "libs"

LICENSE = "FTL | GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=a5927784d823d443c6cae55701d01553 \
                    file://docs/FTL.TXT;md5=9f37b4e6afa3fef9dba8932b16bd3f97 \
                    file://docs/GPLv2.TXT;md5=8ef380476f642c20ebf40fecb0add2ec"

SRC_URI = "${SAVANNAH_NONGNU_MIRROR}/freetype/freetype-${PV}.tar.xz \ 
           file://CVE-2022-27404.patch \
           file://CVE-2022-27405.patch \
           file://CVE-2022-27406.patch \
           "
SRC_URI[sha256sum] = "3333ae7cfda88429c97a7ae63b7d01ab398076c3b67182e960e5684050f2c5c8"

S = "${WORKDIR}/freetype-${PV}"

UPSTREAM_CHECK_REGEX = "freetype-(?P<pver>\d+(\.\d+)+)"

inherit autotools pkgconfig multilib_header

# Adapt autotools to work with the minimal autoconf usage in freetype
AUTOTOOLS_SCRIPT_PATH = "${S}/builds/unix"
CONFIGURE_SCRIPT = "${S}/configure"
EXTRA_AUTORECONF += "--exclude=autoheader --exclude=automake"

PACKAGECONFIG ??= ""

#20230117 bring in png, zlib...
DEPENDS = "libpng-static zlib"

EXTRA_OECONF = "CC_BUILD='${BUILD_CC}'"

#cutdown...
#20230117 removed: --with-brotli=no  added: --with-png=yes
EXTRA_OECONF += "--without-ats --without-quickdraw-carbon \
    --without-quickdraw-toolbox --without-fsref --without-fsspec \
    --without-old-mac-fonts --with-harfbuzz=no \
    --with-png=yes --with-bzip2=no --with-zlib=yes \
    --disable-shared --enable-static"

TARGET_CPPFLAGS += "-D_FILE_OFFSET_BITS=64"

do_configure:prepend() {
 #really cutdown...
 sed -i '/^#define TT_CONFIG_OPTION_SUBPIXEL_HINTING/d' ${S}/include/freetype/config/ftconfig.h
 sed -i '/^#define FT_CONFIG_OPTION_USE_LZW/d' ${S}/include/freetype/config/ftconfig.h
 sed -i '/^#define FT_CONFIG_OPTION_USE_ZLIB/d' ${S}/include/freetype/config/ftconfig.h
 sed -i '/^#define FT_CONFIG_OPTION_POSTSCRIPT_NAMES/d' ${S}/include/freetype/config/ftconfig.h
 sed -i '/^#define FT_CONFIG_OPTION_ADOBE_GLYPH_LIST/d' ${S}/include/freetype/config/ftconfig.h
 sed -i '/^#define FT_CONFIG_OPTION_MAC_FONTS/d' ${S}/include/freetype/config/ftconfig.h
 sed -i '/^#define TT_CONFIG_OPTION_POSTSCRIPT_NAMES/d' ${S}/include/freetype/config/ftconfig.h
 sed -i '/^#define TT_CONFIG_OPTION_SFNT_NAMES/d' ${S}/include/freetype/config/ftconfig.h
 sed -i '/^#define TT_CONFIG_OPTION_BYTECODE_INTERPRETER/d' ${S}/include/freetype/config/ftconfig.h
 sed -i '/^#define TT_CONFIG_OPTION_GX_VAR_SUPPORT/d' ${S}/include/freetype/config/ftconfig.h
 sed -i '/^#define TT_CONFIG_OPTION_BDF/d' ${S}/include/freetype/config/ftconfig.h
 sed -i '/^#define TT_CONFIG_OPTION_EMBEDDED_BITMAPS/d' ${S}/include/freetype/config/ftconfig.h
 
 sed -i '/^FONT_MODULES .* type1/d' ${S}/modules.cfg
 sed -i '/^FONT_MODULES .* cff/d' ${S}/modules.cfg
 sed -i '/^FONT_MODULES .* cid/d' ${S}/modules.cfg
 sed -i '/^FONT_MODULES .* pfr/d' ${S}/modules.cfg
 sed -i '/^FONT_MODULES .* type42/d' ${S}/modules.cfg
 sed -i '/^FONT_MODULES .* winfonts/d' ${S}/modules.cfg
 sed -i '/^FONT_MODULES .* pcf/d' ${S}/modules.cfg
 sed -i '/^FONT_MODULES .* bdf/d' ${S}/modules.cfg
 sed -i '/^FONT_MODULES .* sfnt/d' ${S}/modules.cfg
 sed -i '/^HINTING_MODULES .* pshinter/d' ${S}/modules.cfg
 sed -i '/^RASTER_MODULES .* raster/d' ${S}/modules.cfg
 sed -i '/^RASTER_MODULES .* sdf/d' ${S}/modules.cfg
 sed -i '/^AUX_MODULES .* gzip/d' ${S}/modules.cfg
 sed -i '/^AUX_MODULES .* lzw/d' ${S}/modules.cfg
 sed -i '/^AUX_MODULES .* bzip2/d' ${S}/modules.cfg
 sed -i '/^AUX_MODULES .* psaux/d' ${S}/modules.cfg
 sed -i '/^AUX_MODULES .* psnames/d' ${S}/modules.cfg
 sed -i '/^BASE_EXTENSIONS .* ftbdf/d' ${S}/modules.cfg
 sed -i '/^BASE_EXTENSIONS .* ftcid/d' ${S}/modules.cfg
 sed -i '/^BASE_EXTENSIONS .* ftfstype/d' ${S}/modules.cfg
 sed -i '/^BASE_EXTENSIONS .* ftgasp/d' ${S}/modules.cfg
 sed -i '/^BASE_EXTENSIONS .* ftmm/d' ${S}/modules.cfg
 sed -i '/^BASE_EXTENSIONS .* ftotval/d' ${S}/modules.cfg
 sed -i '/^BASE_EXTENSIONS .* ftpatent/d' ${S}/modules.cfg
 sed -i '/^BASE_EXTENSIONS .* ftpfr/d' ${S}/modules.cfg
 sed -i '/^BASE_EXTENSIONS .* fttype1/d' ${S}/modules.cfg
 sed -i '/^BASE_EXTENSIONS .* ftwinfnt/d' ${S}/modules.cfg
}

do_install:append() {
	oe_multilib_header freetype2/freetype/config/ftconfig.h
}

#BBCLASSEXTEND = "native nativesdk"
