
#20221225 recompile, bump r0 to r1...
#PR_NUM is defined in local.conf...
PR = "r${@int(PR_NUM) + 1}"

do_configure:prepend() {
 sed -i -e 's%^check-if-root:%check-if-rootXXX:%' ${S}/Makefile.in
 echo -e '\n\ncheck-if-root:\n\n' >> ${S}/Makefile.in
}

DEPENDS += " libxinerama "

do_compile:prepend() {
 #have saved all the tarballs that libreoffice downloads...
 mkdir -p ${S}/external/tarballs
 #$TMPDIR is in the 'tmp' folder...
 if [ -d ${TMPDIR}/../../../tarballs-libreoffice ];then
  cp -a -u ${TMPDIR}/../../../tarballs-libreoffice/* ${S}/external/tarballs/
 fi
 #disable this check...
 #sed -i -e 's%^check-if-root:%check-if-root:\n\ncheck-if-rootXXX:%' ${B}/Makefile
 
 #attempting hacks for compile fails...
 mkdir -p ${S}/icon-themes/export
 mkdir -p ${B}/workdir/CustomTarget/postprocess/images
 touch ${B}/workdir/CustomTarget/postprocess/images/colibre_links.txt.tmp
 touch ${B}/workdir/CustomTarget/postprocess/images/export_links.txt.tmp

}

LDFLAGS += " -Wl,--allow-shlib-undefined "

#see also above, hack for compile fail.
EXTRA_OECONF += " --with-theme=colibre "

# do_install in libreoffice.bb fails, it has been renamed to disable it.
# replaced with this one...
do_install() {
 #probably should use 'install' utility, and $datadir and $libdir,
 #but this is easier...
 
 mkdir -p ${D}/usr/lib
 cp -a ${B}/instdir ${D}/usr/lib/libreoffice
 rm -rf ${D}/usr/lib/libreoffice/sdk
 #this is broken result of hack during compile...
 rm -f ${D}/usr/lib/libreoffice/share/config/images_export.zip
 
 mkdir -p ${D}/usr/share
 cp -a ${S}/sysui/desktop/appstream-appdata ${D}/usr/share/appdata
 mkdir -p ${D}/usr/share/icons/hicolor
 cp -a ${S}/sysui/desktop/icons/hicolor/48x48 ${D}/usr/share/icons/hicolor/
 cp -a ${S}/sysui/desktop/menus ${D}/usr/share/applications
 mkdir -p ${D}/usr/share/fonts/TTF
 ln -s ../../../lib/libreoffice/share/fonts/truetype/opens___.ttf ${D}/usr/share/fonts/TTF/opens___.ttf
 
 #from original do_install...
     # install LibreOfficeKit (gobject-introspection) manually - became necessary since 6.4.x
    install -m 0755 -d ${D}${libdir}/girepository-1.0
    install -m 0644 ${B}/workdir/CustomTarget/sysui/share/libreoffice/LOKDocView-0.1.typelib ${D}${libdir}/girepository-1.0/
    install -m 0755 -d ${D}${libdir}/gir-1.0
    install -m 0644 ${B}/workdir/CustomTarget/sysui/share/libreoffice/LOKDocView-0.1.gir ${D}${libdir}/gir-1.0/
    install -m 0755 ${B}/instdir/program/liblibreofficekitgtk.so ${D}${libdir}/
    # install LibreOfficeKit headers
    install -m 0755 -d ${D}${includedir}/LibreOfficeKit
    install -m 0644 ${S}/include/LibreOfficeKit/* ${D}${includedir}/LibreOfficeKit/

    # unoconv
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/git/unoconv/unoconv ${D}/${bindir}

    # remove some unneeded files
    rm -rf ${D}${libdir}/libreoffice/readmes
    rm -rf ${D}${libdir}/libreoffice/share/theme_definitions/ios
    rmdir ${D}${libdir}/libreoffice/share/theme_definitions

}

#20221201 the above worked. now import options from dunfell...
#20221202 have to disable gst, coz don't want that dep...
DEPENDS += " libx11 gtk+3 neon neon-native ghostscript "
EXTRA_OECONF:remove = "--with-lang=ALL"
EXTRA_OECONF:remove = "--enable-python=system"
EXTRA_OECONF += " --with-system-neon --without-fonts --without-krb5 --disable-odk \
                 --with-lang='en-GB fr de' --with-locales='en fr de' \
                 --disable-scripting-javascript --disable-scripting-beanshell \
                 --disable-lotuswordpro --enable-cups --disable-vlc --disable-qt5 \
                 --disable-sdremote --disable-dbus --enable-python=no \
                 --disable-debug --disable-evolution2 --enable-pdfimport \
                 --enable-firebird-sdbc --disable-gstreamer-1-0 "

#20221225
PACKAGECONFIG:remove = "mariadb"
PARALLEL_MAKE = "-j 4"
