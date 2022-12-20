
# 171128 fix printing.

#PR = "r1"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 1}"

# original:
#DEPENDS = "gnutls libpng jpeg dbus dbus-glib zlib libusb"

DEPENDS = "gnutls libpng jpeg tiff zlib libusb openssl"

# most important: "--disable-libusb" to suit my linux kernel.
# 2020-09-16 no longer recognised: --without-python --without-php --with-perl --without-java 
EXTRA_OECONF = " \
               --disable-dbus --disable-pam --enable-libpaper --disable-dnssd \
               --disable-launchd  --disable-gssapi --disable-libusb \
               --with-logdir=/var/cups/log \
               --with-system-groups=lpadmin \
               --with-cups-group=lp --with-cups-user=spot --disable-systemd \
               --disable-avahi \
               --enable-gnutls \
               --disable-relro \
               DSOFLAGS='${LDFLAGS}' \
               "

#2020-09-16 try this, gtk+3_%.bbappend says it wants 'cups-native'...
BBCLASSEXTEND = "native"
