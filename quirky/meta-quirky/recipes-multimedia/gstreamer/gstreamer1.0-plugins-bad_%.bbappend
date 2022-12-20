
#20220126 seems need more deps for pitivi video player.

#PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

#20220131 list updated for new meta-gstreamer1.0 layer...
#PACKAGECONFIG_append = " assrender dc1394 faac faad fluidsynth resindvd opusparse kms libssh2 lcms2 neon openjpeg modplug v4l2codecs va x265"
#20220131 reduce to pkgs in easy pkg-list...
PACKAGECONFIG:append = " assrender dc1394 faac faad resindvd kms libssh2 lcms2 \
                         neon openjpeg modplug v4l2codecs va x265"


