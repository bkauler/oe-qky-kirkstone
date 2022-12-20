LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

# 180418 warning: finally got it to build for aarch64
# however, there is a wrong install path for locales. ...so doing manual install.

#PR = "r0"
# 20210121 PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"

SRC_URI = "http://sourceforge.net/projects/xine/files/xine-ui/${PV}/xine-ui-${PV}.tar.xz"

SRC_URI[md5sum] = "33db8ab3cca004986d506dd544c2a696"
SRC_URI[sha256sum] = "b2382d075c72b1b10039e0e90310cc86c8d910358c53559ed82d3d584201f5c5"

# 171123 change libpng to libpng12
DEPENDS = "libxinerama libpng12 curl libxext libx11 libxscrnsaver xine-lib libjpeg-turbo libxxf86vm libxtst libxv libxft openssl libxt"

inherit gettext pkgconfig autotools

EXTRA_OECONF = "--enable-xft --disable-lirc --without-aalib --without-caca"

# 171123 running in Pyro64 0.5.2, xine-ui reported this:
# "libpng warning: application built with libpng-1.2.54 but running with 1.6.28"
# try this fix (see also change above):
do_configure:prepend() {
 ln -snf libpng12.pc ${WORKDIR}/recipe-sysroot/usr/lib/pkgconfig/libpng.pc
}

do_compile:prepend() {
 #180418 aarch64 hack, it wants stubs-32.h
 if [ ! -f ${WORKDIR}/recipe-sysroot/usr/include/gnu/stubs-32.h ];then
  ln -s stubs-64.h ${WORKDIR}/recipe-sysroot/usr/include/gnu/stubs-32.h
 fi
 
 #this snuck through, absolute path -I/usr/include, quick hack...
 for aMake in `find ${B} -type f -name Makefile`
 do
  sed -i -e "s%I/usr/include%I${STAGING_INCDIR}%g" ${aMake}
  sed -i -e "s%^includedir = .*%includedir = ${STAGING_INCDIR}%" ${aMake}
  sed -i -e "s%^oldincludedir = .*%oldincludedir = ${STAGING_INCDIR}%" ${aMake}
  sed -i -e "s%^prefix = /usr%prefix = ${WORKDIR}/recipe-sysroot/usr%" ${aMake}
 done
 
 # -I/usr/include still getting through! desparate...
 #sed -i -e 's%^CFLAGS = %CFLAGS = -nostdinc %' ${B}/Makefile
 sed -i -e 's% -I$(prefix)/include % %' ${B}/Makefile
 
 #20221210 bit radical, wiping out this, fixes build...
 echo -e '\n\nall:\n\ninstall:\n\nclean:\n\n' > ${B}/misc/Makefile
}

#nah, can't be bothered, can fix locale path afterward...
#do_install() {
# install -d ${D}/usr/bin
# install -m755 ${B}/src/xitk/xine ${D}/usr/bin
# install -m755 ${B}/src/xitk/xine-remote ${D}/usr/bin
# install -m755 ${B}/src/fb/fbxine ${D}/usr/bin
#
#}

#20221210 fix...
do_install:append() {
 if [ -d ${D}/mnt/build ];then
  LOCdir="$(find ${D}/mnt/build -type d -name locale)"
  if [ "$LOCdir" ];then
   mkdir -p ${D}/usr/share
   mv -f ${LOCdir} ${D}/usr/share/locale
  fi
  rm -rf ${D}/mnt
 fi
 mkdir -p ${D}/usr/share/applications
 echo '[Desktop Entry]
Encoding=UTF-8
Name=Xine multimedia player
Comment=Video Player
Exec=xine
Icon=xine.png
Terminal=false
Type=Application
Categories=X-Multimedia-mediaplayer
MimeType=x-content/video-dvd;x-content/audio-cdda;x-content/video-vcd;x-content/video-svcd;application/annodex;application/x-annodex;audio/annodex;audio/x-annodex;video/annodex;video/x-annodex;video/x-ms-asf;video/x-ms-wmv;audio/x-ms-wma;application/vnd.ms-asf;application/x-mplayer2;video/x-ms-asf-plugin;video/x-ms-wvx;video/x-ms-wax;video/mkv;video/x-matroska;video/webm;audio/mpegurl;audio/x-mpegurl;audio/x-scpls;audio/x-ms-wax;audio/x-ms-wvx;application/smil;application/x-quicktimeplayer;application/xspf+xml;video/mp2t;image/png;image/x-png;video/mng;video/x-mng;video/quicktime;video/x-quicktime;audio/x-m4a;video/mp4;audio/mp4;video/x-flv;video/flv;application/x-flash-video;video/msvideo;video/x-msvideo;video/mp2p;audio/x-aiff;audio/aiff;audio/x-pn-aiff;audio/x-flac;audio/flac;audio/x-realaudio;audio/basic;audio/x-basic;audio/x-pn-au;audio/x-pn-realaudio;audio/x-pn-realaudio-plugin;audio/x-real-audio;application/vnd.rn-realmedia;audio/x-8svx;audio/8svx;audio/x-16sv;audio/168sv;image/x-ilbm;image/ilbm;video/x-anim;video/anim;video/x-flic;application/ogg;application/x-ogm;application/x-ogm-audio;application/x-ogm-video;application/x-ogg;audio/ogg;audio/x-ogg;video/ogg;video/x-ogg;video/mpeg;video/x-mpeg;audio/ac3;audio/x-wav;audio/wav;audio/x-pn-wav;audio/x-pn-windows-acm;audio/musepack;audio/x-musepack;audio/mpeg2;audio/x-mpeg2;audio/mpeg3;audio/x-mpeg3;audio/mpeg;audio/x-mpeg;audio/x-mpegurl;audio/mpegurl;audio/mp3;audio/x-mp3;application/x-flac;x-content/video-dvd;x-content/video-svcd;x-content/video-vcd;
' > ${D}/usr/share/applications/xine.desktop
 mkdir -p ${D}/usr/share/icons/hicolor/48x48/apps
 cp -f ${S}/misc/desktops/icons/48/xine.png ${D}/usr/share/icons/hicolor/48x48/apps/
 mkdir -p ${D}/usr/share/pixmaps
 cp -f ${S}/misc/desktops/icons/48/xine.png ${D}/usr/share/pixmaps/
 mkdir -p ${D}/usr/share/xine/skins
 cp -f ${S}/misc/splash-default/xine_splash.png ${D}/usr/share/xine/skins/
 cp -f ${S}/misc/splash-default/xine-ui_logo.png ${D}/usr/share/xine/skins/
 cp -f ${S}/misc/splash-default/xine-ui_logo.mpg ${D}/usr/share/xine/skins/
}

HOMEPAGE = "https://www.xine-project.org/"
SUMMARY = "Media player, xlib based gui for xine-lib"

