
#20221201 this patch was created for libcap 2.45, hope work for 2.66...
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " file://cap_sys_mount.patch "
