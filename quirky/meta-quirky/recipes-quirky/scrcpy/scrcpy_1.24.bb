# Recipe created by recipetool
# recipetool create -o scrcpy_1.24.bb https://github.com/Genymobile/scrcpy/archive/refs/tags/v1.24.tar.gz

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b9bd894f552781161655c6580e458989"

SRC_URI = "https://github.com/Genymobile/scrcpy/archive/refs/tags/v${PV}.tar.gz \
           file://scrcpy-server-v1.24"

SRC_URI[md5sum] = "a06005d880ca863181266820d91ac396"
SRC_URI[sha256sum] = "e3054ad453ac577b941f8df0eabc94e842affc6e1d10ba8d21cededfa2eacc73"

DEPENDS = "android-tools ffmpeg libsdl2 libusb1 libx11 libxml2"

inherit meson pkgconfig gettext

EXTRA_OEMESON += " \
  --buildtype=release \
  -Db_lto=true \
  -Dprebuilt_server=${WORKDIR}/scrcpy-server-v1.24 \
"

HOMEPAGE = "https://github.com/Genymobile/scrcpy"
SUMMARY = "Display and control your Android device"
