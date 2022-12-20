
# 170506 currently do not want 'tk'...
RDEPENDS:${PN} = ""

do_install:append() {
 rm -f ${D}/usr/bin/xorriso-tcltk
}
