DESCRIPTION = "A modern, C++-native, header-only, test framework for unit-tests, \
TDD and BDD - using C++11, C++14, C++17 and later."
AUTHOR = "Phil Nash, Martin Horenovsky and others"
HOMEPAGE = "https://github.com/catchorg/Catch2"
LICENSE = "BSL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e4224ccaecb14d942c71d31bef20d78c"

SRC_URI = "git://github.com/catchorg/Catch2.git;branch=v2.x"
SRCREV = "5c88067bd339465513af4aec606bd2292f1b594a"

S = "${WORKDIR}/git"

inherit cmake python3native

do_install:append() {
    rm ${D}${datadir}/Catch2/lldbinit
    rm ${D}${datadir}/Catch2/gdbinit
    rmdir ${D}${datadir}/Catch2/
}
# Header-only library
RDEPENDS:${PN}-dev = ""
RRECOMMENDS:${PN}-dbg = "${PN}-dev (= ${EXTENDPKGV})"
