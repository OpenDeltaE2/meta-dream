SUMMARY = "Enigma2 Skin MX HQ9W"
MAINTAINER = "OBH"
require conf/license/openpli-gplv2.inc

RDEPENDS:${PN} = "enigma2-plugin-systemplugins-bh-skin-support"

inherit gitpkgv allarch

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
SRCREV = "${AUTOREV}"

SRC_URI= "git://gitlab.com/jack2015/skin-BH-PLI.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

FILES:${PN} = "/usr/"

do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/enigma2/MX_HQ9W
    cp -r ${S}${datadir}/enigma2/MX_HQ9W/* ${D}${datadir}/enigma2/MX_HQ9W
    chmod -R a+rX ${D}${datadir}/enigma2
}
