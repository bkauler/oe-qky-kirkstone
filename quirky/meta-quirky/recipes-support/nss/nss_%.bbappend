
#20221201
do_install:append() {
 TD="${S}/tentative-dist"
 cp -L ${TD}/${libdir}/libcrmf.a ${D}/${libdir}
}
