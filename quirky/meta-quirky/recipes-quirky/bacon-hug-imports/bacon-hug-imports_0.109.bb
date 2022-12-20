# Recipe created by recipetool
#  recipetool create -o bacon-hug-imports_0.104.bb http://www.basic-converter.org/hug_imports.bac
#20180417 hug 0.105, bacon 3.7.2
#20200919 hug 0.109, bacon 4.1  20220811 bacon 4.5

HOMEPAGE = "http://basic-converter.org/"
SUMMARY = "High level GUI for BaCon"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://hug_imports109.bac"

SRC_URI[md5sum] = ""
SRC_URI[sha256sum] = ""
# this will prevent lack of checksum from throwing an error...
BB_STRICT_CHECKSUM = "0"

S = "${WORKDIR}"
# note, do_unpack will copy files/hug_imports109.bac to $WORKDIR

do_configure() {
 mv -f hug_imports109.bac hug_imports.bac
}

do_compile() {
    true
}

do_install () {
    install -d ${D}/usr/share/BaCon
    sed -i -e 's%libhug\.so%/usr/lib/libhug.so%' hug_imports.bac
    install -m644 hug_imports.bac ${D}/usr/share/BaCon
}

FILES:${PN} = "/usr/share/BaCon"
