# Recipe created by recipetool
# recipetool create -o nim_1.6.8.bb https://nim-lang.org/download/nim-1.6.8.tar.xz

# ref: https://github.com/aguspiza/meta-nim
# ref: https://bkhome.org/news/202208/considering-adopting-nim-language.html

## 20220823 PR_NUM is defined in local.conf, currently r9
#PR = "r${@int(PR_NUM) + 1}"

HOMEPAGE = "https://nim-lang.org/"
DESCRIPTION = "nim compiler"
SECTION = "languages"
SUMMARY = "nim language compiler"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://copying.txt;md5=e202ebcd59041b078a1f8cf66709081d"

DEPENDS = "nim-native flex-native m4-native openssl-native openssl"

SRC_URI = "https://nim-lang.org/download/nim-${PV}.tar.xz"

SRC_URI[md5sum] = "d2a445ba7deeb27375e145826f35033e"
SRC_URI[sha256sum] = "0f5b65cdb60f78af41cb075c238983689a1e1f7e25c819f179862c18a484cf57"

# ERROR: nim-native-1.6.6-r9 do_populate_sysroot: The recipe nim-native is trying to install files into a shared area when those files already exist. Those files and their manifest location are:
#  /mnt/build/oe-builds/oe-quirky/build-amd64/tmp/sysroots-components/x86_64/nim-native/usr/bin/nim
# It could be the overlapping files detected are harmless in which case adding them to SSTATE_DUPWHITELIST may be the correct solution.
#SSTATE_DUPWHITELIST = "/"
# 20221204 kirkstone new name...
SSTATE_ALLOW_OVERLAP_FILES = "/"

do_configure () {
 true
}

do_compile () {
 #compile nim...
 #COMP_FLAGS="${CFLAGS}" LINK_FLAGS="${LDFLAGS}" ./build.sh --os linux --cpu ${TARGET_ARCH}
 ./build.sh --os linux --cpu ${TARGET_ARCH}
 
 case "${TARGET_ARCH}" in
  x86_64) xTARGET_ARCH=amd64 ;;
  aarch64) xTARGET_ARCH=arm64 ;;
  i686) xTARGET_ARCH=i386 ;;
  *) xTARGET_ARCH="${TARGET_ARCH}" ;;
 esac
 
 yCC="${CC}"
 xCC="${CC#* }"
 export CC="${CC/ */}"
 #note: nim will read etc/nim/nim.cfg installed by nim-native

 nim c --lib:${WORKDIR}/recipe-sysroot-native/usr/lib/nim --opt:size --mm:orc -d:useMalloc --passC:"-flto ${xCC}" --passL:"-flto ${xCC} -ldl" --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:env --skipUserCfg --skipParentCfg --skipProjCfg --nimcache:nimcache1 koch
 
 nim c --noNimblePath -p:compiler --lib:${WORKDIR}/recipe-sysroot-native/usr/lib/nim --opt:size --mm:orc --passC:"-flto ${xCC}" --passL:"-flto ${xCC} -ldl" --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:env --skipUserCfg --skipParentCfg --nimcache:nimcache2 dist/nimble/src/nimble.nim
 
 nim c --noNimblePath -p:compiler --lib:${WORKDIR}/recipe-sysroot-native/usr/lib/nim --opt:size --mm:orc --passC:"-flto ${xCC}" --passL:"-flto ${xCC} -ldl" --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:env --skipUserCfg --skipParentCfg --nimcache:nimcache3 tools/nimgrep.nim

 #"-p:compiler" doesn't work, reports
 # "Error: cannot open file: compiler/options" hmmm, "-p:." has fixed it...
 nim c --noNimblePath -p:compiler -p:. --lib:${WORKDIR}/recipe-sysroot-native/usr/lib/nim --opt:size --mm:orc --passC:"-flto ${xCC}" --passL:"-flto ${xCC} -ldl" --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:env --skipUserCfg --skipParentCfg --nimcache:nimcache4  nimsuggest/nimsuggest.nim

 export CC="${yCC}"
}

do_install () {
 ./install.sh pkg
 install -d ${D}${bindir}
 install -d ${D}${includedir}
 install -d ${D}${libdir}/nim
 install -m 755 pkg/nim/bin/nim ${D}${bindir}/
 install -m 644 pkg/nim/lib/cycle.h ${D}${includedir}/
 install -m 644 pkg/nim/lib/nimbase.h ${D}${includedir}/
 install -m 755 dist/nimble/src/nimble ${D}${bindir}/
 install -m 755 tools/nimgrep ${D}${bindir}/
 install -m 755 nimsuggest/nimsuggest ${D}${bindir}/
 install -m 755 koch ${D}${bindir}/
 (
  cd pkg/nim/lib
  for dir in $(find . -mindepth 1 -type d);do
   install -d ${D}${libdir}/nim/${dir}
  done
  for file in $(find . -mindepth 1 -type f);do
   install -m 755 "${file}" ${D}${libdir}/nim/${file}
  done
 )
 install -d ${D}/etc
 install -d ${D}/etc/nim
 install -m 644 pkg/nim/config/nim.cfg ${D}/etc/nim/nim.cfg.new
 install -m 644 pkg/nim/config/nimdoc.cfg ${D}/etc/nim/nimdoc.cfg.new
 install -m 644 pkg/nim/config/nimdoc.tex.cfg ${D}/etc/nim/nimdoc.tex.cfg.new
 install -m 644 pkg/nim/config/rename.rules.cfg ${D}/etc/nim/rename.rules.cfg.new
}

