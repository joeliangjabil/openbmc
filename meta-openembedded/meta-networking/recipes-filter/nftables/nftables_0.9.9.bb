SUMMARY = "Netfilter Tables userspace utillites"
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d1a78fdd879a263a5e0b42d1fc565e79"

DEPENDS = "libmnl libnftnl bison-native \
           ${@bb.utils.contains('PACKAGECONFIG', 'mini-gmp', '', 'gmp', d)}"

# Ensure we reject the 0.099 version by matching at least two dots
UPSTREAM_CHECK_REGEX = "nftables-(?P<pver>\d+(\.\d+){2,}).tar.bz2"

SRC_URI = "http://www.netfilter.org/projects/nftables/files/${BP}.tar.bz2"
SRC_URI[sha256sum] = "76ef2dc7fd0d79031a8369487739a217ca83996b3a746cec5bda79da11e3f1b4"

inherit autotools manpages pkgconfig

PACKAGECONFIG ??= "python readline json"
PACKAGECONFIG[json] = "--with-json, --without-json, jansson"
PACKAGECONFIG[manpages] = "--enable-man-doc, --disable-man-doc, asciidoc-native"
PACKAGECONFIG[mini-gmp] = "--with-mini-gmp, --without-mini-gmp"
PACKAGECONFIG[python] = "--enable-python --with-python-bin=${PYTHON}, --with-python-bin="", python3"
PACKAGECONFIG[readline] = "--with-cli=readline, --without-cli, readline"
PACKAGECONFIG[xtables] = "--with-xtables, --without-xtables, iptables"

inherit ${@bb.utils.contains('PACKAGECONFIG', 'python', 'python3native', '', d)}

RRECOMMENDS:${PN} += "kernel-module-nf-tables"

PACKAGES =+ "${PN}-python"
FILES:${PN}-python = "${nonarch_libdir}/${PYTHON_DIR}"
RDEPENDS:${PN}-python = "python3-core python3-json ${PN}"
