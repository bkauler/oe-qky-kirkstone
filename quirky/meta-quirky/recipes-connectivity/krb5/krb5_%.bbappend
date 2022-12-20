
#20221209
do_install:append() {
 #do not start the daemon, samba will do it...
 rm -rf ${D}/etc/init.d
}
