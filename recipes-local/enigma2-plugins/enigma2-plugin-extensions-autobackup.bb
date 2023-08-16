SUMMARY = "Backup settings and restore them automatically"
DESCRIPTION = "Can create daily backups. Backups created will be restored automaticaly after a new flash."
require conf/license/openpli-gplv2.inc

SRC_URI = "git://gitlab.com/jack2015/e2openplugin-AutoBackup.git;protocol=https;branch=master"

inherit gitpkgv distutils-openplugins gettext

S = "${WORKDIR}/git"
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

do_install:append() {
    find ${D}/ -name '*.po' -exec rm {} \;
    find ${D}/ -name '*.sh' -exec chmod a+x {} \;
}

python populate_packages:prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
}
