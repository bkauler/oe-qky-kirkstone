# Recipe created by recipetool
# recipetool create -o scons_4.0.1.bb http://prdownloads.sourceforge.net/scons/scons-4.0.1.tar.gz

SUMMARY = "Open Source next-generation build tool."
HOMEPAGE = "http://www.scons.org/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b94c6e2be9670c62b38f7118c12866d2"

SRC_URI = "http://prdownloads.sourceforge.net/scons/scons-${PV}.tar.gz"
SRC_URI[md5sum] = "1b31cdf3864efdaa71a1c49d69d5b1ca"
SRC_URI[sha1sum] = "eab56b3bbdb1f30b660adef218a609254d3e97ea"
SRC_URI[sha256sum] = "722ed104b5c624ecdc89bd4e02b094d2b14d99d47b5d0501961e47f579a2007c"
SRC_URI[sha384sum] = "d7c3aeff05d99f3234d9c114a49eb159ccbe82d43bb81b209c21c386701374638000c36738099df084d31668ab33e3ac"
SRC_URI[sha512sum] = "400b11323d48f04395b519dfafb6fce15a6dea92013f599dc4895b112629c0a06c7d2806755c74047d001d60087b94f39a118f512b7ee8de6214a4ffc7310468"

S = "${WORKDIR}/SCons-${PV}"

inherit setuptools3

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= ":platform_system == "windows""
#PACKAGECONFIG[:platform_system == "windows"] = ",,,python3-pywin32"

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS:${PN} += "python3-setuptools"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "python3-compression python3-core python3-crypt python3-ctypes python3-db python3-debugger python3-io python3-json python3-logging python3-lxml python3-math python3-netclient python3-pickle python3-pprint python3-profile python3-resource python3-shell python3-stringold python3-threading python3-unittest python3-xml"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    StringIO
#    msvcrt
#    win32api
#    win32con
#    win32process
#    winreg
