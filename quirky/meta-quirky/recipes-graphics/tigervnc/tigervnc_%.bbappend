
#FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
#SRC_URI += " file://tigervnc-1.11.0-configuration_fixes-1.patch"

#REQUIRED_DISTRO_FEATURES:remove = "pam"
#DEPENDS:remove = "libpam"

EXTRA_OECMAKE += " -DENABLE_PAM=OFF"
