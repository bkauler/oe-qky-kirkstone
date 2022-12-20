
# meta/recipes-core/packagegroups/packagegroup-base.bb pulls in 'avahi' and 'libnss-mdns'
# required for zeroconf. remove...
RDEPENDS:packagegroup-base-zeroconf = ""
RDEPENDS:packagegroup-base-zeroconf:append:libc-glibc = ""

# note, have 'DISTRO_FEATURES_remove = "zeroconf"' in local.conf
# which seems to have done the trick, don't know if this .bbappend is needed.
