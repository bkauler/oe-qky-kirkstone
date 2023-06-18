#20230609 testing qemu in easy 5.3.2, discovered only alsa support, no pa...
#20230617 federico: usb-host (libusb) and virtio-9p-pci (virtfs) missing.

#PR_NUM is defined in local.conf... bump r3 to r4... 20230617 r5
PR = "r${@int(PR_NUM) + 2}"

PACKAGECONFIG:append:class-target = " pulsedio"

#20230617 add lots more...
PACKAGECONFIG:append:class-target = " virtfs libusb libcap-ng ssh gcrypt \
                                    gnutls xkbcommon libudev libxml2 attr"

#20230617 now it wants this...
DEPENDS:append:class-target = " libtasn1"
