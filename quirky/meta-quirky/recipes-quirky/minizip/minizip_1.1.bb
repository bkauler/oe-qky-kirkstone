# Recipe created by recipetool
# recipetool create -o minizip_1.1.bb http://deb.debian.org/debian/pool/main/m/minizip/minizip_1.1.orig.tar.xz

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "http://deb.debian.org/debian/pool/main/m/minizip/minizip_${PV}.orig.tar.xz \
           file://include.patch \
           file://automake.patch \
           file://traversal.patch \
           file://zconf.h \
           file://zlib.h"

SRC_URI[md5sum] = "e1f720f8ce48ba142becd3acf8d33a00"
SRC_URI[sha1sum] = "22037612d49e9ddd70848eca9ad4ff5752c86897"
SRC_URI[sha256sum] = "7d8da446d3b6799e7851f077a66551a46b80fc3de708549e79dbd3e49e842ba1"
SRC_URI[sha384sum] = "b0efc8789fa2e8cb1a63070c48a2b91eca67794608f2c9951cda5565697a4589fecbe52ea604832f6977f9025a2d8a6e"
SRC_URI[sha512sum] = "d93a60b8533a79fd192d4af129bcfa8bc5f9ec2dcd07b8c6b575d770a1188b792d59a25b289331f1c917bd057a18d4cfb5f26836e5829d3821b393d298ebc97b"

S = "${WORKDIR}/${BPN}"

inherit autotools

DEPENDS = "zlib"

do_compile:prepend() {
 cp -f ${WORKDIR}/zconf.h ${S}/
 cp -f ${WORKDIR}/zlib.h ${S}/
}

SUMMARY = "minimalistic zip library"
HOMEPAGE = "http://www.winimage.com/zLibDll/minizip.html"
