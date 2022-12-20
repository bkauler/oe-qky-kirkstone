# Recipe created by recipetool
# recipetool create -o bacon-hug_0.104.bb http://www.basic-converter.org/hug.bac
# 180417 now at 0.105, matching bacon 3.7.2
# 20200919 now at 0.109, matching bacon 4.1 20220811 also 4.5
# 20220824 bacon-native rolled back to version 3.9.3

HOMEPAGE = "http://basic-converter.org/"
SUMMARY = "High level GUI for BaCon"

DEPENDS = "bash bacon-native gtk+"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://hug109.bac"
SRC_URI[md5sum] = ""
SRC_URI[sha256sum] = ""
# this will prevent lack of checksum from throwing an error...
BB_STRICT_CHECKSUM = "0"

# hug.bac is not a tarball, so set S (path to source) where hug.bac is located...
# note, do_unpack will copy files/hug109.bac to $WORKDIR
S = "${WORKDIR}"

do_configure () {
 mv -f hug109.bac hug.bac
}

do_compile () {
 mkdir -p build
 # reqd for bacon 3.x: -a rebuild libbacon.a
 bacon -a -f -d build -c "${CC}" -o "${CFLAGS}" -l "-lm -shared -rdynamic -ldl ${LDFLAGS}" hug.bac
}

# ERROR: bacon-hug-0.109-r9 do_package_qa: QA Issue: -dev package bacon-hug-dev contains non-symlink .so '/usr/lib/libhug.so' [dev-elf]
ERROR_QA:remove = "dev-elf"

do_install () {
 install -d ${D}/usr/lib
 install -m755 build/hug.so ${D}/usr/lib/libhug.so
 install -d ${D}/usr/share/BaCon
 install -m 644 hug.bac ${D}/usr/share/BaCon
}

FILES:${PN} += '/usr/lib/libhug.so /usr/share/BaCon'
