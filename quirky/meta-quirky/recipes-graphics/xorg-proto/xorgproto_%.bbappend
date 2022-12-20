
#2020-09-18
#this is really stupid, bitbake gives error when trying to create rootfs, that cannot find xorgproto.
#that's because the only files have gone into the -dev and -doc parts.

#hack fix...
do_install:append() {
 mkdir -p ${D}/usr/bin
 touch ${D}/usr/bin/xorgproto-do-nothing-file
}

FILES:${PN} += "${bindir}/xorgproto-do-nothing-file"

