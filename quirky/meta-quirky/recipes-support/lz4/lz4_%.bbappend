
#20220515 want static lib, for squashfs-tools-static
EXTRA_OEMAKE = "PREFIX=${prefix} CC='${CC}' DESTDIR=${D} LIBDIR=${libdir} INCLUDEDIR=${includedir} BUILD_STATIC=yes"
