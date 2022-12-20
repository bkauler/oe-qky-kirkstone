# Recipe created by recipetool
# recipetool create -o bacon_3.9.3.bb http://www.basic-converter.org/museum/bacon-3.9.3.tar.gz

HOMEPAGE = "https://basic-converter.org/"
SUMMARY = "A BASIC language compiler"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8ca82a6ccae989e3ea64c98f9b351f3b"

SRC_URI = "http://www.basic-converter.org/museum/bacon-${PV}.tar.gz \
           file://fix-open-gcc9.patch"

SRC_URI[md5sum] = "3cd5967225fedc67e552efcb817c40a1"
SRC_URI[sha256sum] = "7f907f4ede68704eefd076733f617438c4baba98e9a1e8676ea1a00c4f8476ae"

DEPENDS = "coreutils-native util-linux-native"

inherit native

do_configure() {
 true
}

do_compile:prepend() {
 #"which pwd" does not work, as pwd is a bash builtin...
 sed -i -e 's%which pwd%which head%' bacon.sh
}

do_compile() {
    mkdir -p build
    # -n convert to C only, -a rebuild libbacon.a, -p preserve temporary files,
    # -y automatically delete temporary files ...
    #bash bacon.sh -y -a -d build bacon.bac
    #...this works for x86_64 target. but for other targets need to do this...
    bash bacon.sh -n -p -a -d build bacon.bac
    cd build
    ${CC} -fPIC -O2 ${CFLAGS} -c bacon.bac.c
    for aFILE in bacon.base64.c bacon.binary.c bacon.chop.c bacon.chr.c bacon.cmdline.c bacon.concat.c bacon.count.c bacon.curdir.c bacon.datename.c bacon.dec.c bacon.dirname.c bacon.epoch.c bacon.error.c bacon.eval.c bacon.exec.c bacon.extract.c bacon.filelen.c bacon.filetime.c bacon.filetype.c bacon.fill.c bacon.flatten.c bacon.getenviron.c bacon.getkey.c bacon.getpeer.c bacon.getxy.c bacon.hash.c bacon.hex.c bacon.host.c bacon.hostname.c bacon.insert.c bacon.instr.c bacon.instrrev.c bacon.lcase.c bacon.left.c bacon.load.c bacon.makedir.c bacon.malloc.c bacon.memcheck.c bacon.mid.c bacon.minmax.c bacon.os.c bacon.peek.c bacon.regex.c bacon.remove.c bacon.replace.c bacon.reverse.c bacon.right.c bacon.rip.c bacon.screen.c bacon.search.c bacon.sort.c bacon.spc.c bacon.str.c bacon.sum.c bacon.tab.c bacon.tally.c bacon.time.c bacon.timer.c bacon.token.c bacon.ucase.c bacon.utf8.c bacon.version.c bacon.wait.c bacon.walk.c
    do
     ${CC} -fPIC -O2 ${CFLAGS} -c ${aFILE}
    done
    ${AR} -r libbacon.a bacon.base64.o bacon.binary.o bacon.chop.o bacon.chr.o bacon.cmdline.o bacon.concat.o bacon.count.o bacon.curdir.o bacon.datename.o bacon.dec.o bacon.dirname.o bacon.epoch.o bacon.error.o bacon.eval.o bacon.exec.o bacon.extract.o bacon.filelen.o bacon.filetime.o bacon.filetype.o bacon.fill.o bacon.flatten.o bacon.getenviron.o bacon.getkey.o bacon.getpeer.o bacon.getxy.o bacon.hash.o bacon.hex.o bacon.host.o bacon.hostname.o bacon.insert.o bacon.instr.o bacon.instrrev.o bacon.lcase.o bacon.left.o bacon.load.o bacon.makedir.o bacon.malloc.o bacon.memcheck.o bacon.mid.o bacon.minmax.o bacon.os.o bacon.peek.o bacon.regex.o bacon.remove.o bacon.replace.o bacon.reverse.o bacon.right.o bacon.rip.o bacon.screen.o bacon.search.o bacon.sort.o bacon.spc.o bacon.str.o bacon.sum.o bacon.tab.o bacon.tally.o bacon.time.o bacon.timer.o bacon.token.o bacon.ucase.o bacon.utf8.o bacon.version.o bacon.wait.o bacon.walk.o
    ${CC} -o bacon bacon.bac.o -L. -lbacon -lm ${LDFLAGS}
    cd ..
}

# note: in -native recipes, must use $bindir, NOT /usr/bin!
do_install() {
 install -d ${D}${bindir}
 install -m755 build/bacon ${D}${bindir}
 install -m755 bacon.sh ${D}${bindir}
}

