# Recipe created by recipetool
# recipetool create -o xsane-0.999.bb http://www.xsane.org/download/xsane-0.999.tar.gz

SUMMARY = "XSane is a graphical frontend for scanners. It uses the library SANE."
HOMEPAGE = "http://www.xsane.org/"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://xsane.COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

# patches from: https://packages.ubuntu.com/kinetic/xsane
SRC_URI = "http://deb.debian.org/debian/pool/main/x/xsane/xsane_${PV}.orig.tar.gz \
    file://0925-utf8.patch \
    file://0600-man_misleading.patch \
    file://0125-desktop_file.patch \
    file://0170-typo.patch \
    file://0605-typo_manpage.patch \
    file://0120-deb_printing_defaults.patch \
    file://0100-deb_docviewer.patch \
    file://0105-deb_gimp_acquire_menu.patch \
    file://0110-deb_inhibit_clickthrough.patch \
    file://0130-fix_options_handling_fix.patch \
    file://0140-fix_pdf_xref.patch \
    file://0135-fix_pdf_floats.patch \
    file://0610-fix_broken_links.patch \
    file://0910-fix_message_typo.patch \
    file://0900-i18n_po_update_es_add_gl.patch \
    file://0905-i18n_po_update_fr.patch \
    file://0150-fix_preview_mouse_events.patch \
    file://0155-fix_spin_button_pagesize.patch \
    file://0160-fix_tighten_default_umask.patch \
    file://0145-fix_png15.patch \
    file://0115-deb_non_working_help_menu.patch \
    file://0165-xsane-0.999-lcms2.patch \
    file://0001-lcms2_configure.patch \
    file://0005-m4.patch \
    file://0010-fix_missing_sane-config.patch \
    file://0915-i18n_typo_geometrie.patch \
    file://0175-icm_profile_field.patch \
    file://0920-add_Name_Comment_pt_BR.patch \
"

SRC_URI[md5sum] = "9927f21e1ab6ba96315e7f0e30746deb"
SRC_URI[sha256sum] = "5782d23e67dc961c81eef13a87b17eb0144cae3d1ffc5cf7e0322da751482b4b"

S = "${WORKDIR}/xsane-${PV}"

DEPENDS = "zlib libjpeg-turbo tiff gtk+ sane-backends libpng lcms \
          "

# removed autotools-brokensep
inherit pkgconfig gettext autotools-brokensep

# po problem, disable nls... no, see fix further down.
EXTRA_OECONF = "--disable-gimp2 --disable-gimp --enable-gtk2 --disable-gtktest \
                --disable-sanetest --disable-gimptest"

# use the existing 'configure' script...
XXXdo_configure () {
 oe_runconf
}

# not needed, see libpng15 patch above
## ref: http://www.linuxfromscratch.org/blfs/view/svn/pst/xsane.html
#do_configure_prepend() {
# sed -i -e 's/png_ptr->jmpbuf/png_jmpbuf(png_ptr)/' ${S}/src/xsane-save.c
#}

do_configure:prepend() {
 # 180729 remove the run-as-root warning...
 sed -i -e 's%^#ifndef HAVE_OS2_H%#ifdef XYZABCDEF123%' ${S}/src/xsane.c
 
 #ERROR: Use of AM_GNU_GETTEXT without [external] argument is no longer supported
 sed -i -e 's%^AM_GNU_GETTEXT%AM_GNU_GETTEXT([external])%' ${S}/configure.in
}

do_compile:prepend() {
 #make[1]: RANLIB@: No such file or directory
 sed -i -e 's%^RANLIB = %# RANLIB = %' ${B}/lib/Makefile
 
 #still got po build fail...
 echo -e '\n\nall:\n\ninstall:\n\nall-yes:\n\nall-no:\n\nuninstall:\n\nclean:\n\n' > ${B}/po/Makefile
}

#make[1]: MKINSTALLDIRS@: No such file or directory
do_install() {
 install -d ${D}/usr/bin
 install -m755 ${B}/src/xsane ${D}/usr/bin
 install -d ${D}/usr/share/sane/xsane
 for A in Mustek-logo.xpm Plustek-logo.xpm sane-epson-logo.xpm sane-hp-logo.xpm sane-umax-logo.xpm sane-xsane-logo.xpm UMAX-logo.xpm xsane-calibration.pnm xsane-eula.txt xsane-gpl.txt xsane-logo.xpm xsane-startimage.pnm xsane-style.rc
 do
  if [ -e ${B}/src/${A} ];then
   install ${B}/src/${A} ${D}/usr/share/sane/xsane
  fi
 done
 install -d ${D}/usr/share/applications
 install ${B}/src/xsane.desktop ${D}/usr/share/applications
 install -d ${D}/usr/share/pixmaps
 install ${B}/src/xsane.xpm ${D}/usr/share/pixmaps
 install -d ${D}/usr/share/doc/nls/xsane
 install ${B}/po/xsane.pot ${D}/usr/share/doc/nls/xsane
}
