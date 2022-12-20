
# qt configure gives warning, have removed these:
#  iconv libproxy pcre

# 20210121 PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"

#PACKAGECONFIG_append = " sm harfbuzz gif sql-sqlite mtdev cups fontconfig icu kms openssl"

# 181222 to compile qtstyleplugins with support for gtk2 theming, need accessibility...
# 20211217 added 'gtk'
PACKAGECONFIG:append = " sm harfbuzz gif sql-sqlite mtdev cups fontconfig \
                 icu kms openssl accessibility gtk glib xcb \
                 "

DEPENDS += " gtk+ ghostscript cups-filters librsvg"

QT_CONFIG_FLAGS += " -no-sse3 -no-ssse3 -no-sse4.1 -no-sse4.2 \
                   -no-avx -no-avx2 -no-avx512"
