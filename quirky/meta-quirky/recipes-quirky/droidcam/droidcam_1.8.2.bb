# Recipe created by recipetool
# recipetool create -o droidcam_1.8.2.bb https://github.com/dev47apps/droidcam/archive/refs/tags/v1.8.2.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=49c7858b03c2cbeaa2ab5c0618d54a84"

SRC_URI = "https://github.com/dev47apps/droidcam/archive/refs/tags/v${PV}.tar.gz"
SRC_URI[md5sum] = "565fc0bc83577f9492b35b0688dfdbaf"
SRC_URI[sha256sum] = "9f18c6768572571c59bfe08ac70e24612e08fc3d887c127ff47899252d3dc662"

DEPENDS = "libjpeg-turbo ffmpeg alsa-lib speex libplist libusbmuxd libx11 libva libvdpau libdrm libxcb"

LDFLAGS += "-lm"

do_configure() {
 true
}

do_compile() {
 ${CC} ${CFLAGS} src/droidcam-cli.c src/connection.c src/settings.c src/decoder.c src/decoder_snd.c src/decoder_v4l2.c src/av.c src/usb.c src/queue.c -o droidcam-cli  -lturbojpeg -lswscale -lavutil -lspeex -lasound -lpthread -lusbmuxd-2.0 ${LDFLAGS}
}

do_install() {
 install -d ${D}/usr/bin
 install -m 755 droidcam-cli ${D}/usr/bin
}

HOMEPAGE = "https://www.dev47apps.com/"
SUMMARY = "turn android and iphone into a webcam"
