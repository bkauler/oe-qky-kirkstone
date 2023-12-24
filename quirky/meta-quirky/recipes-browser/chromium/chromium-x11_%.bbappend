
#20221217
GN_ARGS += "v8_symbol_level=0"
#20231221 remove:  use-vaapi
PACKAGECONFIG += "cups proprietary-codecs"
PACKAGECONFIG:remove = "upower"
DEPENDS += "lcms lcms-native openjpeg libpng zlib zlib-native"
GN_ARGS += "use_system_lcms2=true use_system_libdrm=true \
    use_system_libopenjpeg2=true use_system_libpng=true use_system_zlib=true"

#20221226
PARALLEL_MAKE = "-j 4"

#20231107 these deps are required...
RDEPENDS:append = " libcxx upower"

#20231221  20231223 NO, get a compile error...
#GN_ARGS:remove = "enable_pdf=true"
#GN_ARGS:append = " enable_pdf=false"

