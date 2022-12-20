
#20221206 comment-out, because there is also a 'libsoup'...
# meta/recipes-support/libsoup/libsoup_3.0.7.bb
# meta/recipes-support/libsoup/libsoup-2.4_2.74.2.bb
#RPROVIDES:${PN} = "libsoup"

DEPENDS += "glib-networking"
