# Recipe created by recipetool
# recipetool create -o hostname_3.21.bb http://deb.debian.org/debian/pool/main/h/hostname/hostname_3.21.tar.gz

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=86dc5e6760b0845ece4d5be3a9d397d9"

#20200919 new d/l:
SRC_URI = "http://deb.debian.org/debian/pool/main/h/hostname/hostname_${PV}.tar.gz"
SRC_URI[md5sum] = "c89bf2e3f2877e707e8aad8c8e805269"
SRC_URI[sha1sum] = "fb996e8ee67bb9cbfd40a12fca630d7ec7f0a768"
SRC_URI[sha256sum] = "566193a99f97a58f80b1537efe207c798bb88436c31c7dfc6dd4471d888a4a4f"
SRC_URI[sha384sum] = "07de1080a75a95525ba46633b7b62af164e27135444d1dea77ecea8108cc65fe1423c7573d0c5ec487c38642c86037ff"
SRC_URI[sha512sum] = "48862c53259a40fa749430ddf98510b2d7cdd243c19f3e31dde6258645386281f09243b8f9713f6e7ba9ea7216f992c6f017086cf74e62a654aa8b9165eb2171"

S = "${WORKDIR}/${BPN}"

do_configure () {
	true
}

do_compile () {
	oe_runmake
}

do_install () {
	#20230905 $bindir required for usrmerge...
	oe_runmake install BASEDIR=${D} BINDIR=${bindir}
}

HOMEPAGE = "https://packages.ubuntu.com/source/disco/hostname"
SUMMARY = "hostname utility"
