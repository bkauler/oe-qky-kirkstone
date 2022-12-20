# Recipe created by recipetool
# recipetool create -o libglvnd_1.4.0.bb https://gitlab.freedesktop.org/glvnd/libglvnd/-/archive/v1.4.0/libglvnd-v1.4.0.tar.gz

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/util/uthash/LICENSE;md5=736712b5904dd42f8678df7172bc5f2b \
                    file://src/util/cJSON/LICENSE;md5=149df980cbf8193681bc3d17164a049e"

SRC_URI = "https://gitlab.freedesktop.org/glvnd/libglvnd/-/archive/v${PV}/libglvnd-v${PV}.tar.gz"
SRC_URI[md5sum] = "31093d0f039352f471e828eadf292a80"
SRC_URI[sha256sum] = "33b8b993adf47a21bc1c46bcf970927edeb9884390d5b09b1aed051d600c0b2f"

S = "${WORKDIR}/${BPN}-v${PV}"

DEPENDS = "xorgproto libx11 libxext mesa libgltf libglu libvdpau-va-gl gnutls"

inherit pkgconfig autotools

EXTRA_OECONF = "--enable-glx"

HOMEPAGE = "https://gitlab.freedesktop.org/glvnd/libglvnd"
SUMMARY = "GL Vendor-Neutral Dispatch library"
