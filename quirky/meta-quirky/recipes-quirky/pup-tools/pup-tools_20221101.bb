# Recipe created by recipetool
# recipetool create -o pup-tools_20220827.bb https://distro.ibiblio.org/easyos/source/alphabetical/p/pup-tools-20220827.tar.gz
# 20220824 bacon-native rolled back to version 3.9.3
# 20220827 new debdb2pupdb.nim (requires dep nim-native)
# compiling on x86_64 OS, for a i686 target:
#  CC=i686-poky-linux-gcc  -m32 -march=i686 -fstack-protector-strong  -D_FORTIFY_SOURCE=2 -Wformat -Wformat-security -Werror=format-security --sysroot=/mnt/build/oe-builds/oe-quirky/build-i686/tmp/work/i686-poky-linux/pup-tools/20220828-r4/recipe-sysroot
#  CFLAGS= -O2 -pipe -g -feliminate-unused-debug-types -fmacro-prefix-map=/mnt/build/oe-builds/oe-quirky/build-i686/tmp/work/i686-poky-linux/pup-tools/20220828-r4=/usr/src/debug/pup-tools/20220828-r4                      -fdebug-prefix-map=/mnt/build/oe-builds/oe-quirky/build-i686/tmp/work/i686-poky-linux/pup-tools/20220828-r4=/usr/src/debug/pup-tools/20220828-r4                      -fdebug-prefix-map=/mnt/build/oe-builds/oe-quirky/build-i686/tmp/work/i686-poky-linux/pup-tools/20220828-r4/recipe-sysroot=                      -fdebug-prefix-map=/mnt/build/oe-builds/oe-quirky/build-i686/tmp/work/i686-poky-linux/pup-tools/20220828-r4/recipe-sysroot-native= 
# 20220829 debdb2pupdb install to /usr/local/debget
# 20220903 bacon 4.5.0.2 20220906 back to bacon 3.9.3
# recipetool create -o pup-tools_20221101.bb https://distro.ibiblio.org/easyos/source/alphabetical/p/pup-tools-20221101.tar.gz

## 20220906 PR_NUM is defined in local.conf, currently r9
#PR = "r${@int(PR_NUM) + 1}"

HOMEPAGE = "https://bkhome.org/news"
SUMMARY = "Core utilities used in EasyOS and Puppy and derivatives"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "https://distro.ibiblio.org/easyos/source/alphabetical/p/pup-tools-${PV}.tar.gz \
  file://hug.bacV0.109"

SRC_URI[md5sum] = "c814dff8bc1b85eb5234b59cd398e77b"
SRC_URI[sha1sum] = "9c86057292ed5efbdcc52577f3ff338626f89374"
SRC_URI[sha256sum] = "8912f8b241d3bdb421e05f523d1278b80031b6e617067ce043097c1b608245ef"

DEPENDS = "gtk+ m4-native bacon-native nim-native flex-native"

inherit gettext pkgconfig

#nah, do not make any difference...
##trying to get a smaller executable...
#CC_remove = "-fstack-protector-strong"
#CC_remove = "-D_FORTIFY_SOURCE=2"
##might as well get rid of these also...
#CC_remove = "-Wformat"
#CC_remove = "-Wformat-security"
#CC_remove = "-Werror=format-security"

do_configure() {
 cp -f ${WORKDIR}/hug.bacV0.109 bacon/hug.bac
}

do_compile () {
 
 #new nim app...
 case "${TARGET_ARCH}" in
  x86_64) xTARGET_ARCH=amd64 ;;
  aarch64) xTARGET_ARCH=arm64 ;;
  i686) xTARGET_ARCH=i386 ;;
  *) xTARGET_ARCH="${TARGET_ARCH}" ;;
 esac
 cd nim
  #compile debdb2pupdb...
  yCC="${CC}"
  export xCC="${CC#* }"
  export CC="${CC/ */}"
  #note: nim will read etc/nim/nim.cfg installed by nim-native
  #these sizes are for i686 32-bit target...
  #nim c --lib:${WORKDIR}/recipe-sysroot-native/usr/lib/nim --opt:speed --mm:orc -d:useMalloc --passC:"-flto ${xCC}" --passL:"-flto ${xCC} -ldl" --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:env --skipUserCfg --skipParentCfg --skipProjCfg --nimcache:nimcache debdb2pupdb
  # ...stripped binary is 82KB
  #nim c --lib:${WORKDIR}/recipe-sysroot-native/usr/lib/nim --opt:size --mm:orc -d:useMalloc --passC:"-flto ${xCC}" --passL:"-flto ${xCC} -ldl" --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:env --skipUserCfg --skipParentCfg --skipProjCfg --nimcache:nimcache debdb2pupdb
  # ...stripped binary is 94KB !!!
  #nim c --lib:${WORKDIR}/recipe-sysroot-native/usr/lib/nim --opt:size --mm:orc --passC:"${xCC}" --passL:"${xCC} -ldl" --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:env --skipUserCfg --skipParentCfg --skipProjCfg --nimcache:nimcache debdb2pupdb
  # ...stripped binary is 122KB
  #nim c --lib:${WORKDIR}/recipe-sysroot-native/usr/lib/nim --opt:none --mm:orc --passC:"${xCC} -Os" --passL:"${xCC} -Os -ldl" --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:env --skipUserCfg --skipParentCfg --skipProjCfg --nimcache:nimcache debdb2pupdb
  # ...stripped binary is 74KB
  nim c --lib:${WORKDIR}/recipe-sysroot-native/usr/lib/nim --opt:none --mm:orc -d:useMalloc --passC:"-flto ${xCC} -Os" --passL:"-flto ${xCC} -Os -ldl" --cpu:${xTARGET_ARCH} --os:linux -d:release --cc:env --skipUserCfg --skipParentCfg --skipProjCfg --nimcache:nimcache debdb2pupdb
  # ...only 54K !!!
  CC="${yCC}"
  export CC
 cd ..
 
 cd bacon
  mkdir -p build
  
  #welcome1stboot: change inbuilt hug.bac instead of hug_imports.bac & hug.so...
  sed -i -e 's%^REM INCLUDE "/usr/share/BaCon/hug.bac"%INCLUDE "/usr/share/BaCon/hug.bac"%' welcome1stboot.bac
  sed -i -e 's%^INCLUDE "/usr/share/BaCon/hug_imports.bac"%REM INCLUDE "/usr/share/BaCon/hug_imports.bac"%' welcome1stboot.bac
  sed -i -e 's%/usr/share/BaCon%.%' welcome1stboot.bac #hug.bac is local.
  
  #pup_event_ipc: fix
  sed -i -e "s%'CONST IN_MODIFY=2%CONST IN_MODIFY=2%" pup_event_ipc.bac #170608
  
  #popup: fix
  sed -i -e 's%/usr/share/BaCon%.%' popup.bac #hug.bac is local.
  
  # reqd for bacon 3.x: -a rebuild libbacon.a
  for aF in welcome1stboot debdb2pupdb find_cat popup pup_event_ipc
  do
   bacon -a -d build -c "${CC}" -o "${CFLAGS}" -l "${LDFLAGS}" ${aF}.bac
   if [ $? -eq 0 ];then
    cp -a -f build/${aF} ./
   fi
   rm -f build/*
  done
 cd ..

 #20190405 now have pup_event_frontend_d.c ...
 #20200212 now have bitflip.c ...
 #20211013 now have getpixelcolor.c ...
 #20220807 now have debdb2pupdb.c, find_cat.c ...
 #20220816 staying with bacon debdb2pupdb.bac and find_cat.bac
 cd gcc
 for aFILE in bitflip printcols truncate vercmp getlocalip pup_event_frontend_d
 do
  ${CC} -o ${aFILE} ${aFILE}.c ${CFLAGS} ${LDFLAGS}
 done
 ${CC} -lX11 getcurpos.c -o getcurpos ${CFLAGS} ${LDFLAGS}
 ${CC} -lX11 getpixelcolor.c -o getpixelcolor ${CFLAGS} ${LDFLAGS}
 cd ..
}

do_install () {
 install -d ${D}${bindir}
 install -d ${D}${sbindir}
 install -d ${D}/usr/local/petget
 install -d ${D}/usr/local/pup_event
 install -d ${D}/usr/local/debget

 install -m755 gcc/vercmp ${D}${bindir}
 install -m755 gcc/truncate ${D}${bindir}
 install -m755 gcc/bitflip ${D}${sbindir}
 install -m755 gcc/getcurpos ${D}${sbindir}
 install -m755 gcc/getpixelcolor ${D}${sbindir}
 install -m755 gcc/printcols ${D}${sbindir}
 install -m755 gcc/getlocalip ${D}${sbindir}
 #install -m755 gcc/debdb2pupdb ${D}/usr/local/petget
 #install -m755 gcc/find_cat ${D}/usr/local/petget
 install -m755 gcc/pup_event_frontend_d ${D}/usr/local/pup_event

 install -m755 bacon/popup ${D}${sbindir}
 install -m755 bacon/welcome1stboot ${D}${sbindir}
 #install -m755 bacon/debdb2pupdb ${D}/usr/local/petget
 install -m755 bacon/find_cat ${D}/usr/local/petget
 install -m755 bacon/pup_event_ipc ${D}/usr/local/pup_event
 
 install -m755 nim/debdb2pupdb ${D}/usr/local/debget 
}

FILES:${PN} += "/usr/local/petget /usr/local/pup_event /usr/local/debget"
