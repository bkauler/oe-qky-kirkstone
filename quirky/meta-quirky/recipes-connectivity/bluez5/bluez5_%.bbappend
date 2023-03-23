#20210110
#ref: https://bkhome.org/news/202010/bluetoothctl-utility-internationalized.html
#how bluetoothctl.pot generated:
# cd client
# xgettext -d bluetoothctl -s --no-wrap -o bluetoothctl.pot --keyword=_ main.c gatt.c advertising.c agent.c

#PR = "r1"
# 20210121 PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# 20211027 new internationalization patch...
SRC_URI:append = "\
  file://bluez-5p62-bluetoothctl-internationalize.patch \
  file://bluetoothctl.pot \
  file://0001-bcm43xx-Add-bcm43xx-3wire-variant.patch \
  file://0002-bcm43xx-The-UART-speed-must-be-reset-after-the-firmw.patch \
  file://0004-Move-the-43xx-firmware-into-lib-firmware.patch \
"

#note, there is also do_install_append in main recipe, so this one just gets appended...
do_install:append() {
 install -d ${D}/usr/share/doc/nls/bluetoothctl
 install -m 644 ${WORKDIR}/bluetoothctl.pot ${D}/usr/share/doc/nls/bluetoothctl/
}

FILES:${PN} += "/usr/share/doc/nls/bluetoothctl/bluetoothctl.pot"

#20230321
EXTRA_OECONF:append = " --enable-hid2hci"
