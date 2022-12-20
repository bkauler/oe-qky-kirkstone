
# 20180718
# the official dialog recipe only uses ascii curses, want wide-char support...

#PR = "r1"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

EXTRA_OECONF = "--with-ncursesw \
                --enable-nls \
                --enable-widec \
                --disable-rpath-hack"
