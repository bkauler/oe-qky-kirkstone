
# 20210121 PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"

#remove pam...
REQUIRED_DISTRO_FEATURES = ""

#easyos buster 2.5.5 build system, jan 23, 2021
#target: build-aarch64
#ERROR: smbnetfs-0.6.1+gitAUTOINC+a117eec8de-r1 do_prepare_recipe_sysroot: The file /usr/lib/python3.8/site-packages/_ldb_text.py is installed by both libldb and samba, aborting
#ERROR: Logfile of failure stored in: /mnt/sda1/nvme/oe-builds/oe-quirky/build-aarch64/tmp/work/aarch64-poky-linux/smbnetfs/0.6.1+gitAUTOINC+a117eec8de-r1/temp/log.do_prepare_recipe_sysroot.17983
#ERROR: Task (/mnt/sda1/nvme/oe-builds/oe-quirky/meta-filesystems/recipes-filesystems/smbnetfs/smbnetfs_git.bb:do_prepare_recipe_sysroot) failed with exit code '1'
#...the problem was a clash install of libldb and samba. have removed libldb
#   dep from samba recipe, no longer building libldb.

#20210123
#| checking for glib-2.0 >= 2.30... no
#| configure: error: Package requirements (glib-2.0 >= 2.30) were not met:
# fix...
DEPENDS:append = " glib-2.0"

