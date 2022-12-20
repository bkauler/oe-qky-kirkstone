
#patches from debian and gentoo
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://01-sensors-detect-udevadm.patch \
            file://02-sensors-conf-convert-improvements.patch \
            file://08-sensors-detect-lm85.patch \
            file://09-sensors-detect-sch5524.patch \
            file://11-fancontrol-check.patch \
            file://15-path-max.patch \
            file://lm-sensors-3.6.0-w83627ehf-nct6775.patch"
