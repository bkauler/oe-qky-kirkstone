# Recipe created by recipetool 2020-09-19
# recipetool create -o libglade_2.6.4.bb http://ftp.gnome.org/pub/gnome/sources/libglade/2.6/libglade-2.6.4.tar.bz2

LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=55ca817ccb7d5b5b66355690e9abc605"

SRC_URI = "http://ftp.gnome.org/pub/gnome/sources/libglade/2.6/libglade-${PV}.tar.bz2 \
    file://glade-cruft.patch \
    file://python_environment.patch \
    file://0001-configure.in-remove-deprecated-GNOME_COMMON_INIT.patch \
    "

SRC_URI[md5sum] = "d1776b40f4e166b5e9c107f1c8fe4139"
SRC_URI[sha1sum] = "8465851733b8a0efbe57c43efc0f140d8e2d2edb"
SRC_URI[sha256sum] = "64361e7647839d36ed8336d992fd210d3e8139882269bed47dc4674980165dec"
SRC_URI[sha384sum] = "ef2df6925aac643e60afd27adc21cfc3c829a21c73f759b4d8c596e0010fcae83bbd33141708fdb655750728a92ebcf8"
SRC_URI[sha512sum] = "b725842febaf4c1f0d305a629ae1c61ea2de24dd6f41937e806c078fada2cea483195ef40f5238ce2045e47130c92559e984f677de667b840dd7fff0f8559735"

# error: "glib-gettextize: command not found"
# but it is there, installed by 'glib-2.0' ...fix set dep 'glib-2.0-native'
# error: "intltoolize: command not found" ...fix, set dep 'intltool-native'
DEPENDS = "glib-2.0 libxml2 atk gtk+ intltool-native glib-2.0-native"

# NOTE: if this software is not capable of being built in a separate build directory
# from the source, you should replace autotools with autotools-brokensep in the
# inherit line
inherit gettext pkgconfig autotools

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = ""

#ok, seems didn't need to do this...
#20200920 /usr/bin/libglade-convert in target pkg wants python2
ERROR_QA:remove = "file-rdeps"
