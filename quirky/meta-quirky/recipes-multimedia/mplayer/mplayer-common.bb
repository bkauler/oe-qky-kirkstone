# Copyright Matthias Hentges <devel@hentges.net> (c) 2006
# License: MIT (see COPYING.MIT)

SUMMARY = "Preconfigured mplayer preferences"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PV = "0.0.1"

#PR = "r1"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

SRC_URI = "file://mplayer.conf"

# Yes, really /usr/etc!!!
do_install() {
    install -d "${D}/usr${sysconfdir}/mplayer"

    install -m 0644 ${WORKDIR}/mplayer.conf "${D}/usr${sysconfdir}/mplayer"
}

FILES:${PN} = "/usr${sysconfdir}/mplayer"

inherit allarch
