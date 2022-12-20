# Recipe created by recipetool
# recipetool create -o jimtcl_0.77.bb https://github.com/msteveb/jimtcl/archive/0.77.tar.gz

#NOTE: configure is broken in versions 0.78, 0.79

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d69300147248518155ea330e78019033"

SRC_URI = "https://github.com/msteveb/jimtcl/archive/${PV}.tar.gz"
SRC_URI[md5sum] = "8ebf5bc4bdadf2bb43629024c340b6a9"
SRC_URI[sha1sum] = "12f921997c140593b2dc82885fb15c2dad8012f0"
SRC_URI[sha256sum] = "0874c50ab932c68940c29c48c014266a322c54ff357a0919386f32cc341eb3b2"
SRC_URI[sha384sum] = "e97ce6f0a477bc9489406af4368724df254feefc28502d059444e5df0d7b6c8166f4ffc76a1899e89d96e6f2fccd4194"
SRC_URI[sha512sum] = "47c50abcb06917ec85c19c3ff553bfc635c9fcc4e9ec707d8454adffade071e1f0051ee24b9a9d1dc7b83f5794229629b2de4eb37d0e3e8d6a1ba6bd72564901"

inherit autotools-brokensep pkgconfig gettext

DEPENDS = "tcl tcl-native openssl sqlite3 zlib"

EXTRA_OECONF = "--full --disable-docs"

#unable to rebuild configure script...
do_configure() {
 #Error: Unknown option --static
 #OE is forcing unknown options to configure, ignore them...
 sed -i -e 's%user-error "Unknown option.*%return 0%' autosetup/autosetup
 oe_runconf
}
