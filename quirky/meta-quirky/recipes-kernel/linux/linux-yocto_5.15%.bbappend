
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

#complete config file. note, this is in a sub-folder 'genericx86-64'...
SRC_URI += "file://defconfig \
            file://cap_sys_mount-1.patch \
            file://cap_sys_mount-2.patch"

#or, can specify a config fragment(s):
#SRC_URI += "file://8250.cfg"






