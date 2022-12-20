# Recipe created by recipetool
# recipetool create -o libvdpau-va-gl_0.4.2.bb https://github.com/i-rinat/libvdpau-va-gl/releases/download/v0.4.2/libvdpau-va-gl-0.4.2.tar.gz

# 20210121 PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5a9126e7f56a0cf3247050de7f10d0f4"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

#20210124 wants to run a binary:
#| /bin/sh: /mnt/sda1/nvme/oe-builds/oe-quirky/build-aarch64/tmp/work/aarch64-poky-linux/libvdpau-va-gl/0.4.2-r1/build/glsl/shader-bundle-tool: cannot execute binary file: Exec format error

SRC_URI = "https://github.com/i-rinat/libvdpau-va-gl/releases/download/v${PV}/libvdpau-va-gl-${PV}.tar.gz \
  file://${BUILD_ARCH}/shader-bundle-tool"

SRC_URI[md5sum] = "8db21dcfd5cd14c6ec51b992e20369dc"
SRC_URI[sha256sum] = "7d9121540658eb0244859e63da171ca3869e784afbeaf202f44471275c784af4"

DEPENDS = "libx11 ffmpeg libvdpau libva mesa xserver-xorg"

inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release"

do_compile:prepend() {
 mkdir -p ${B}/glsl/
 cp -a -f ${WORKDIR}/${BUILD_ARCH}/shader-bundle-tool ${B}/glsl
 touch -d '+1 hour' ${B}/glsl/shader-bundle-tool
 #fool ninja not to overwrite shader-bundle-tool...
 sed -i -e 's%TARGET_FILE = glsl/shader-bundle-tool%TARGET_FILE = glsl/shader-bundle-toolXXX%' ${B}/build.ninja 
}

FILES:${PN} += "${libdir}/vdpau"

INSANE_SKIP:${PN} += "dev-so"

HOMEPAGE = "https://github.com/i-rinat/libvdpau-va-gl"
SUMMARY = "VDPAU driver with OpenGL/VAAPI backend"
