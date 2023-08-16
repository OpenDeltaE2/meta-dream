SUMMARY = "drivers for grautec oleed display for dreambox"
MAINTAINER = "grautec"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
PACKAGE_ARCH = "${MACHINE_ARCH}"

require conf/license/license-gplv2.inc

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
VER ="1.0"
PR = "r0"

INHIBIT_PACKAGE_STRIP = "1"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/grautec;protocol=https;branch=master"

S = "${WORKDIR}/git"

FILES:${PN} = "/usr/* ${sysconfdir}"
FILES:${PN}-dbg = "${sysconfdir}/grautec/*/.debug/*.ko"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rc3.d
    install -d ${D}${sysconfdir}/rc6.d
    chmod -R 777 ${S}/dreambox
    cp -rp ${S}/dreambox/* ${D}/
    ln -sf ../init.d/usbtftdisplay.sh ${D}${sysconfdir}/rc3.d/S90usbtftdisplay
    ln -sf ../init.d/killusbtftdisplay.sh ${D}${sysconfdir}/rc6.d/K50killusbtftdisplay
}

do_package_qa() {
}