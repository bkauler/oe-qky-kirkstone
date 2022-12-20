
#20220109 huh, install broken...
do_install:append() {
 if [ -e ${D}/usr/bin/vim.vim ];then
  if [ ! -e ${D}/usr/bin/vim ];then
   mv -f ${D}/usr/bin/vim.vim ${D}/usr/bin/vim
  fi
 fi
}
