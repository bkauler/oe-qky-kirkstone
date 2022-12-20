
#compile without nasm, for aarch64...
DEPENDS = "gnutls zlib libpcre"

# ref: https://stackoverflow.com/questions/38296756/what-is-the-idiomatic-way-in-cmake-to-add-the-fpic-compiler-option
OECMAKE_C_FLAGS += "-fPIC"
OECMAKE_CXX_FLAGS += "-fPIC"

