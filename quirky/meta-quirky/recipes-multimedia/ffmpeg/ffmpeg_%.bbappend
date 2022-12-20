
#20221203b rolled ffmpeg back 5.0.1 to 4.4
#20221201  20221203 removed: gsm  
PACKAGECONFIG:append = " fdk-aac gpl libopus libvorbis mp3lame openssl sdl2 \
                         speex vaapi vdpau vpx x264 x265 xcb xv jack"

#20221203 add libcdio-paranoia  remove wavpack
PACKAGECONFIG[cdio] = "--enable-libcdio,--disable-libcdio,libcdio libcdio-paranoia"
#PACKAGECONFIG[wavpack] = "--enable-libwavpack,--disable-libwavpack,wavpack"
PACKAGECONFIG[opencore] = "--enable-libopencore-amrnb --enable-libopencore-amrwb --enable-version3,,opencore-amr"
PACKAGECONFIG[v4l] = "--enable-libv4l2,,v4l-utils"
PACKAGECONFIG[libdc1394] = "--enable-libdc1394,--disable-libdc1394,libdc1394"
PACKAGECONFIG[libbluray] = "--enable-libbluray,,libbluray"
PACKAGECONFIG[libwebp] = "--enable-libwebp,,libwebp"
PACKAGECONFIG[xvidcore] = "--enable-libxvid,,xvidcore"

#20221203 Unknown option "--enable-libwavpack"  remove 'wavpack'
PACKAGECONFIG:append = " cdio opencore v4l libdc1394 libbluray libwebp xvidcore"

EXTRA_OECONF:append = " --enable-nonfree"
