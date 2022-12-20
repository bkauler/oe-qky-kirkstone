# Recipe created by recipetool
# recipetool create -o audacious-plugins_3.10.1.bb https://distfiles.audacious-media-player.org/audacious-plugins-3.10.1.tar.bz2
# 20201012 removed pulseaudio dep.
# 20211027 add jack dep. add pulseaudio dep.

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=e4232be386c99ea029d1af8123ace1f2"

SRC_URI = "https://distfiles.audacious-media-player.org/audacious-plugins-${PV}.tar.bz2"
SRC_URI[md5sum] = "c54e49a3a32c0df2beaf4fdf14a4bd75"
SRC_URI[sha1sum] = "d2e549a402baa59b0181aabad6d7481369e8930e"
SRC_URI[sha256sum] = "eec3177631f99729bf0e94223b627406cc648c70e6646e35613c7b55040a2642"
SRC_URI[sha384sum] = "53ee6a3105359aae2079d83b4a69d10cbdf4cec86ca622e9f558fa97d37edbf94e1f8ef9c3d980053de11d842b9effc4"
SRC_URI[sha512sum] = "454e9ce4061e92a0ecda40f562d4cc7453fc0019fd76b25dbe9e319319fa37c22f9785cb29563e8074de8a88e6130106aca1e431790297e1b4636dc974fde565"


DEPENDS = "ffmpeg virtual/libgl gtk+ libsdl audacious glib-2.0 libsdl2 lame \
   libxml2 faad2 dbus dbus-glib dbus-native dbus-glib-native libsndfile1 libnotify \
   alsa-lib neon faac mpg123 libvorbis flac jack pulseaudio wavpack"

inherit pkgconfig gettext autotools-brokensep 

EXTRA_OECONF = "--with-libsdl=2 --with-ffmpeg=ffmpeg --with-system-libxml2=yes \
    --enable-pulse --enable-notify --enable-alsa --enable-neon --enable-sndfile \
    --enable-mpg123 --enable-vorbis --enable-flac --enable-jack"

HOMEPAGE = "https://audacious-media-player.org/"
SUMMARY = "audio player"
