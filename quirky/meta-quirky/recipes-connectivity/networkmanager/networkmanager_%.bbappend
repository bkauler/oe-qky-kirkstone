#BK 181112
#181113 add libnewt, so have nmtui gui. also add rp-pppoe, ppp
#181115 inbuilt dhcp client, instead of dhcpcd.
#181117 r1: add support for iwd, inbuilt replacement for wpa_supplicant
#181118 r2: add dep modemmanager
#181119 r3: support ofono
#201012 remove modemmanager
#20201024 r4: change to --enable-introspection=yes, network-manager-applet want it.
#20210827 "bluez" should be "bluez5".

#PR = "r4"
# 20210121 PR_NUM is defined in local.conf... 20220131 removed...
#PR = "r${@int(PR_NUM) + 4}"

DEPENDS = " \
    intltool-native \
    libxslt-native \
    libnl \
    dbus \
    dbus-glib \
    dbus-glib-native \
    libgudev \
    util-linux \
    libndp \
    jansson \
    curl \
    dhcpcd gnutls readline libidn zlib libunistring \
    nettle gmp libffi libpcre ncurses glib-2.0 \
    libsoup-2.4 \
    libnewt slang popt rp-pppoe ppp wpa-supplicant bluez5 \
    ofono \
"

EXTRA_OECONF = " \
    --without-tests --with-valgrind=no --with-nmtui=yes --with-nmcli=yes \
    --with-netconfig=no --with-resolvconf=yes --with-config-dhcp-default=internal \
    --with-dhcpcd=no --with-dhclient=no --with-dhcpcanon=no --with-crypto=gnutls \
    --with-libaudit=no --with-selinux=no --with-session-tracking=no \
    --with-consolekit=no --with-systemd-logind=no --with-systemd-journal=no \
    --with-wext=yes --enable-tests=no --enable-vala=no \
    --enable-concheck --disable-ovs --enable-bluez5-dun --enable-ppp \
    --enable-modify-system --disable-polkit-agent --enable-polkit=no \
    --enable-wifi --disable-ifupdown --disable-ifcfg-rh --enable-introspection=yes \
    --disable-more-warnings \
    --with-iptables=${sbindir}/iptables --with-iwd=yes \
    --with-ofono \
"

#20210827 no it should be "bluez5", not "bluez". um but i have enabled it above, so does this matter?
# (have done the amd64 and aarch64 builds 20210826 and 20210827 before this fix)
#20201001 "bluetooth" is wrong, should be "bluez"...
PACKAGECONFIG = "gnutls dnsmasq ppp wifi bluez5 \
"
