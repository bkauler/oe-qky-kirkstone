
#20211209 currently r5, bump it...
#PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

#20211209 gpgme-config is broken...
do_install:append() {
 mkdir -p ${D}/usr/bin
 echo '#!/bin/sh
 PARAM1="$@"
 if [ "$PARAM1" == "--version" ];then
  PARAM1="--modversion"
 fi
 pkg-config $PARAM1 gpgme' > ${D}/usr/bin/gpgme-config
 chmod 755 ${D}/usr/bin/gpgme-config
}
