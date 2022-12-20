
# 20190328 removed 'imap', it brings in dep 'us-imap' which is a server...
PACKAGECONFIG = "mysql sqlite3 opcache openssl \
                   ${@bb.utils.filter('DISTRO_FEATURES', 'ipv6 pam', d)} \
"
