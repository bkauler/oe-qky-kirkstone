# Recipe created by recipetool
# recipetool create -o libgtkhtml_2.12.bb http://distro.ibiblio.org/easyos/source/alphabetical/l/libgtkhtml-2.12.tar.gz

#ref:
#https://bkhome.org/news/202002/helpsurfer-crash-fixed.html

#2021-04-07:
#osmo crashes when create a new contact. wdlkmpx determined that is due to libgtkhtml.
#see fix for libxml 2.9.5+: https://github.com/wdlkmpx/libgtkhtml2/commits/master
#i have created patch 010-libgtkhtml-2.12-fix-libxml-2.9.5+.patch

#2021-04-07 rebuilding this pkg, so bump the "-r<n>" in pkg name... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=55ca817ccb7d5b5b66355690e9abc605"

SRC_URI = "http://distro.ibiblio.org/easyos/source/alphabetical/l/libgtkhtml-${PV}.tar.gz \
  file://010-libgtkhtml-2.12-fix-libxml-2.9.5+.patch"

SRC_URI[md5sum] = "4742e713f00078810d4f5db4e990168a"
SRC_URI[sha1sum] = "2fd808cd9f36ae2b89f678c71d379b3050ef3c62"
SRC_URI[sha256sum] = "de88263ba9665e94468d47905587966c2debc9697359af94d3dc0784d4d3dc26"
SRC_URI[sha384sum] = "bba3dc90cc104894e04bda55364608ffc55a715dcf95f92c0b95d19f2e9255be6b75b5feb3bfa41fd2c3445de3ecb284"
SRC_URI[sha512sum] = "bf7584dfec72e6e4ad315ac118f3163c2ea8cbc38d97d385c76b3b8338e90e1bd6dc91115f252da599c37a2bc6436645aeb428324ddcde936e3871b8008be170"

DEPENDS = "libsoup-2.4 glib-2.0 libxml2 gtk+ gdk-pixbuf libpng"

inherit pkgconfig gettext autotools

# to avoid 'gail' dep:
EXTRA_OECONF = "--disable-accessibility"

HOMEPAGE = "http://www.gnome.org"
SUMMARY = "A HTML engine for GNOME2"
