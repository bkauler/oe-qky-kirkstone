# BK 20201218 got do_install() error:
# | /bin/bash: line 0: cd: /mnt/sda1/nvme/oe-builds/oe-quirky/build-amd64/tmp/work/nocona-64-poky-linux/cifs-utils/6.10-r0/image/sbin: No such file or directory

do_install:prepend() {
    
    install -d ${D}${base_sbindir}
    
}
