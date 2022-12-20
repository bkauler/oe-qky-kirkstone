# Recipe created by recipetool
# recipetool create -o usbview_2.0.bb http://www.kroah.com/linux-usb/usbview-2.0.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://www.kroah.com/linux-usb/usbview-${PV}.tar.gz"
SRC_URI[md5sum] = "565f2e79f3924d14ab5b4d8aced557e5"
SRC_URI[sha1sum] = "69a3ff7965fb39d2fc9eff1a46c4c4fc90a1fc5f"
SRC_URI[sha256sum] = "42511e4d43ddc5805bab0dea8aeeea54cc00462d229e04066ed5fb7c065d1b6b"
SRC_URI[sha384sum] = "c810ad034712b1b8f0e6af8c5b1470eb3f539409408304ae99e26cd37d08c5ce496198738373988c99f08ff404c18779"
SRC_URI[sha512sum] = "87b3d0b0c734c94e5527f19575c0460f5c0b894dac29936a759d573058c9c007ad59c9be32a5c6d485cba258193efe4ca15bdd6f3ca31df321ed230778b5404f"

# have no idea if really needs libusb* or usbutils...
DEPENDS = "gtk+3 pango glib-2.0 cairo atk libusb1 libusb-compat usbutils"

inherit pkgconfig autotools

EXTRA_OECONF = ""

HOMEPAGE = "http://www.kroah.com/linux-usb/"
SUMMARY = "Displays the topography of the devices that are plugged into the USB bus."

