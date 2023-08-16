SUMMARY = "KeyAdder for Add BISS, PowerVU, Irdeto and Tandberg keys to current service."
DESCRIPTION = "KeyAdder for Add BISS, PowerVU, Irdeto and Tandberg keys to current service."
LICENSE = "GPL-3.0-only"
AUTHOR = "RAED"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"

SRC_URI = "${GIT_SITE}/KeyAdder.git;protocol=https;branch=master"

inherit gitpkgv distutils-openplugins gettext

S = "${WORKDIR}/git"
SRCREV = "${AUTOREV}"
PV = "2.5+git${SRCPV}"
PKGV = "2.5+git${GITPKGV}"

python populate_packages:prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
}
