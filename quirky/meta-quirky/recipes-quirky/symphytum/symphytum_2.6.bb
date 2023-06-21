# Recipe created by recipetool
# recipetool create -o symphytum_2.6.bb https://github.com/giowck/symphytum/releases/download/v2.6/symphytum-2.6-src.tar.gz

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8d40a555790517b04835fe19508f3454"

#stupid source unpacks at same level...
#SRC_URI = "https://github.com/giowck/symphytum/releases/download/v${PV}/symphytum-${PV}-src.tar.gz"
#SRC_URI[md5sum] = "273bd2cb51a21a5dc97d24cde96c4c41"
#SRC_URI[sha1sum] = "c072bac1650aecbcc779ff310f44fba4c449aaa9"
#SRC_URI[sha256sum] = "6b04868b5b0f34e8a1d6a20f1dd41aae62e819a8c7540ac83a11ba4dcea5e8a1"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/s/symphytum-2.6.tar.gz"
SRC_URI[md5sum] = "bd2629292d18369a7da149d861d8ff4a"
SRC_URI[sha256sum] = "874cbb541254a16916083664c0e53f209fdc837e3c25ce58ab9b014b84a38c33"

DEPENDS = "qtsvg qtbase qtimageformats sqlite3 python3-setuptools python3-six \
           python3-certifi python3-chardet python3-idna python3-requests"

inherit qmake5 pkgconfig python3-dir python3native

EXTRA_QMAKEVARS_PRE = "-config release"

#nothing gets installed, do it manually...
do_install() {
 install -d ${D}/usr/bin
 install -m 755 ${B}/symphytum ${D}/usr/bin
 install -d ${D}/usr/share/applications
 echo '[Desktop Entry]
Encoding=UTF-8
Name=Symphytum simple database
Icon=symphytum.png
Comment=Symphytum simple database
Exec=symphytum
Terminal=false
Type=Application
Categories=X-Business
GenericName=Symphytum' > ${D}/usr/share/applications/symphytum.desktop
 install -d ${D}/usr/share/pixmaps
 install -m 644 ${S}/stuff/logo/symphytum_64.png ${D}/usr/share/pixmaps/symphytum.png
 #install -d ${D}/usr/share/symphytum
 #got this from stuff/installers/PKGBUILD...
 tar xzvf ${S}/stuff/installers/deb/usr/share/symphytum/sync/dropbox-*.tar.gz -C ${S}/stuff/installers/deb/usr/share/symphytum/sync/
 rm -f ${S}/stuff/installers/deb/usr/share/symphytum/sync/dropbox-*.tar.gz
 #do_package error coz ftp:ftp ...
 chown -R root:root ${S}/stuff/installers/deb/usr/share/symphytum/sync
 cp -a ${S}/stuff/installers/deb/usr/share/symphytum ${D}/usr/share/
 ##do_package error coz ftp:ftp ...
 #chown -R root:root ${D}/usr/share/symphytum/sync
 install -m 644 ${S}/LICENSE ${D}/usr/share/symphytum
}

HOMEPAGE = "https://github.com/giowck/symphytum"
SUMMARY = "Personal database"
