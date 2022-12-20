
#20221217
GN_ARGS += "v8_symbol_level=0"
PACKAGECONFIG += "cups proprietary-codecs use-vaapi"
PACKAGECONFIG:remove = "upower"
DEPENDS += "lcms lcms-native openjpeg libpng zlib zlib-native"
GN_ARGS += "use_system_lcms2=true use_system_libdrm=true \
    use_system_libopenjpeg2=true use_system_libpng=true use_system_zlib=true"

