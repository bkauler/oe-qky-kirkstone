#20220729 oh, just like gtk2, cups printing missing!
# no /usr/lib/gtk-3.0/3.0.0/printbackends needs to have libprintbackend-cups.so

# PR_NUM is defined in local.conf... currently r9
#PR = "r${@int(PR_NUM) + 1}"

PACKAGECONFIG:append = " cups"

#in gtk_%.bbappend added these, so do it here also, but leave out libxinerama 
DEPENDS:append = " xinput pixman freetype fontconfig"
