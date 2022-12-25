#20220729 oh gtk+3, just like gtk2, cups printing missing!
# no /usr/lib/gtk-3.0/3.0.0/printbackends needs to have libprintbackend-cups.so
#20221225 ditto for gtk4

PACKAGECONFIG:append = " cups"

#in gtk_%.bbappend added these, so do it here also, but leave out libxinerama 
DEPENDS:append = " xinput pixman freetype fontconfig"
