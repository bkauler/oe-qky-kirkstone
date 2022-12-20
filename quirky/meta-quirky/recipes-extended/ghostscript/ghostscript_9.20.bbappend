
#181202 r1: ghostscript recipe creates a big 'gs' binary, without libgs
#           however, there are some pkgs that want libgs as a dep.
#181209 r2: /usr/bin/gsc, gsx installed, suspect cups-filters looks for 'gs'.

#ref1: https://patchwork.openembedded.org/patch/137913/
#ref2: https://patchwork.openembedded.org/patch/137914/
# ...however, he only wants libgs, not the rest of ghostscript.

#oe-qky-src/create-oe-quirky script now has this line in it (see ref1):
# echo 'SECURITY_CFLAGS_pn-ghostscript = "${SECURITY_NO_PIE_CFLAGS}"' >> ../oe-quirky/meta/conf/distro/include/security_flags.inc

#20210419 remove this. it was -r4 but no longer need the bump...
#PR = "r2"
# 20210121 PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 3}"

do_configure:prepend () {
	mkdir -p obj
	mkdir -p soobj
	if [ -e ${WORKDIR}/objarch.h ]; then
		cp ${WORKDIR}/objarch.h obj/arch.h
		cp ${WORKDIR}/objarch.h soobj/arch.h
	fi
}

do_configure:append () {
	# copy tools from the native ghostscript build
	if [ "${PN}" != "ghostscript-native" ]; then
		mkdir -p obj/aux soobj
		mkdir -p obj/aux soobj/aux
		for i in genarch genconf mkromfs echogs gendev genht; do
			cp ${STAGING_BINDIR_NATIVE}/ghostscript-${PV}/$i obj/aux/$i
			cp ${STAGING_BINDIR_NATIVE}/ghostscript-${PV}/$i soobj/aux/$i
		done
	fi
}

do_compile:class-target:append () {
    oe_runmake so
}

# 20210419 crap docs no explanation of "do_install_class-target", but it means
# to install in the target, as distinct from a native install...
do_install:class-target:append () {
    oe_runmake install-so DESTDIR=${D}
    #ref: http://www.linuxfromscratch.org/blfs/view/8.2/pst/gs.html
    install -m644 ${S}/base/*.h ${D}/usr/include/ghostscript
    #181209 at runtime 'gstoraster' in 'cups-filters' pkg unable to find 'gs'...
    # note, probably should use ${bindir} here...
    ln -s gsc ${D}/usr/bin/gs
    
    #20210419 want these scripts in the final pkg...
    for aFILE in dvipdf eps2eps gsbj gsdj gsdj500 gslj gslp gsnd pdf2dsc pdf2ps pf2afm pfbtopfa pphs printafm ps2ascii ps2epsi ps2pdf ps2pdf12 ps2pdf13 ps2pdf14 ps2pdfwr ps2ps ps2ps2 ps2txt
    do
     if [ -x ${S}/lib/${aFILE} ];then
      install ${S}/lib/${aFILE} ${D}/usr/bin/
     fi
    done
}

PACKAGES =+ "${PN}-lib"

FILES:${PN}-lib = "${libdir}/lib*.so.*"
