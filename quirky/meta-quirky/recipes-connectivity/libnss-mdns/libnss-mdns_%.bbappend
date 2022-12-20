# remove avahi dep.

DEPENDS = ""

RDEPENDS_${PN} = ""

EXTRA_OECONF = "--libdir=${base_libdir} --disable-lynx --disable-avahi"
