# Recipe created by recipetool
# recipetool create -o mjpegtools_2.2.1.bb https://downloads.sourceforge.net/project/mjpeg/mjpegtools/2.2.1/mjpegtools-2.2.1.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://mplex/COPYING;md5=8ca43cbc842c2336e835926c2166c28b \
                    file://y4mscaler/COPYING;md5=393a5ca445f6965873eca0259a17f833"

SRC_URI = "https://downloads.sourceforge.net/project/mjpeg/mjpegtools/${PV}/mjpegtools-${PV}.tar.gz"
SRC_URI[md5sum] = "168e0131c0b8a2e31df7a73eb602fc32"
SRC_URI[sha256sum] = "b180536d7d9960b05e0023a197b00dcb100929a49aab71d19d55f4a1b210f49a"

DEPENDS = "libpng gtk+ libx11 libsdl libjpeg-turbo libdv libsdl-gfx \
           libsdl-mixer libsdl-image libsdl-ttf"

inherit pkgconfig autotools

EXTRA_OECONF = "--without-libquicktime"

HOMEPAGE = "https://sourceforge.net/projects/mjpeg/"
SUMMARY = "mpeg video capture, editing, playback and compression"

#ERROR: mjpegtools-2.2.1-r6 do_package_qa: QA Issue: /usr/bin/lavtc.sh contained in package mjpegtools requires /bin/bash, but no providers found in RDEPENDS_mjpegtools? [file-rdeps]
ERROR_QA:remove = "file-rdeps"
