
#20221223 do_package clash with gdk-pixbuf-xlib
# meta-quirky/recipes-quirky/gdk-pixbuf-xlib/gdk-pixbuf-xlib_2.40.2.bb
#encountered the clash with zarfy do_package
# meta-quirky/recipes-quirky/zarfy/zarfy_0.1.0.bb
PACKAGES:remove = "gdk-pixbuf-xlib"

