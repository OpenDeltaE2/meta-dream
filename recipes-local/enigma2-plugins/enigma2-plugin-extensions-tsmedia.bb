SUMMARY = "Streaming of all types of internet media"
DESCRIPTION = "Streaming of all types of internet media"
MAINTAINER = "Openpli Developers"
require conf/license/openpli-gplv2.inc

inherit gitpkgv allarch

RDEPENDS:${PN} += "libtorrent python-json python-pyopenssl python-compression python-html python-pycrypto python-pycurl python-textutils python-requests"

SRCREV = "${AUTOREV}"
PV = "15.4+git${SRCPV}"
PKGV = "15.4+git${GITPKGV}"

SRC_URI = "git://gitlab.com/jack2015/TSmedia.git;protocol=https;branch=master"

FILES:${PN} = "/usr/"

S = "${WORKDIR}/git"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}/usr
    cp -r ${S}/usr/* ${D}/usr/
    chmod -R a+rX ${D}/usr/
    find ${D}/ -name '*.sh' -exec chmod a+x {} \;
    find ${D}/ -name '*.so' -exec chmod a+x {} \;
}

pkg_postrm:${PN}() {
    rm -rf $D${libdir}/enigma2/python/Plugins/Extensions/TSmedia/
}
