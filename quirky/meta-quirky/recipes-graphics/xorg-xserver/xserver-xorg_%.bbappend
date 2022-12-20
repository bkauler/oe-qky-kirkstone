
##20220316 bump r8 to r9...
#PR = "r${@int(PR_NUM) + 1}"

# meta/recipes-graphics/xorg-xserver/xserver-xorg.inc has this:
# XSERVER_RRECOMMENDS = "xkeyboard-config rgb xserver-xf86-config xkbcomp xf86-input-libinput"
# i have gone back to the original -input-* pkgs, remove -libinput:
XSERVER_RRECOMMENDS = "xkeyboard-config rgb xserver-xf86-config xkbcomp"

# 20180622
# xephyr needs: xcb-util-renderutil xcb-util-keysyms xcb-util-image xcb-util-wm
DEPENDS += "xinerama xcb-util-renderutil xcb-util-keysyms xcb-util-image xcb-util-wm"

# 20220316...
## 20180622
#EXTRA_OECONF += "--enable-kdrive --enable-xnest --enable-xephyr --enable-xinerama"

# 20220316
EXTRA_OEMESON:remove = "-Dxnest=false"
EXTRA_OEMESON += " \
                 -Dxnest=true \
                 -Dxephyr=true \
                 -Dxinerama=true \
"

#20201001 do we need this?
PACKAGECONFIG:append = " xinerama"
