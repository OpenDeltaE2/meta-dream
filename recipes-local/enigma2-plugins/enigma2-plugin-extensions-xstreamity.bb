SUMMARY = "IPTV Xtream Codes playlists player by KiddaC"
HOMEPAGE = "https://www.linuxsat-support.com"
MAINTAINER = "kiddac"
PRIORITY = "optional"
require conf/license/license-gplv2.inc

RDEPENDS:${PN} = "python-argparse python-image python-imaging python-lzma python-multiprocessing python-requests"

inherit gitpkgv allarch

SRCREV = "${AUTOREV}"
PV = "3.58+git${SRCPV}"
PKGV = "3.58+git${GITPKGV}"
PR = "r0"

SRC_URI = "git://gitlab.com/jack2015/XStreamity.git;protocol=https;branch=master"

S = "${WORKDIR}/git"
FILES:${PN} = "/usr/"

do_compile() {
    python2 -O -m compileall ${S}
}

do_install() {
    install -d ${D}${libdir}/enigma2/python/Components/Converter
    install -d ${D}${libdir}/enigma2/python/Components/Renderer
    install -d ${D}${libdir}/enigma2/python/Plugins/Extensions/XStreamity
    cp -rf ${S}/XStreamity/usr/lib/enigma2/python/Components/Converter/* ${D}${libdir}/enigma2/python/Components/Converter
    cp -rf ${S}/XStreamity/usr/lib/enigma2/python/Components/Renderer/* ${D}${libdir}/enigma2/python/Components/Renderer
    cp -rf ${S}/XStreamity/usr/lib/enigma2/python/Plugins/Extensions/XStreamity/* ${D}${libdir}/enigma2/python/Plugins/Extensions/XStreamity
    find ${D}/ -name '*.py' -exec rm {} \;
    find ${D}/ -name '*.po' -exec rm {} \;
    find ${D}/ -name '*.sh' -exec chmod a+x {} \;
}

pkg_preinst:${PN}() {
#!/bin/sh
if [ -f "$D${sysconfdir}/enigma2/X-Streamity/playlists.json" ]; then
	rm -f $D${sysconfdir}/enigma2/X-Streamity/playlists.json > /dev/null 2>&1
fi

if [ -f "$D${sysconfdir}/enigma2/xstreamity/playlists.json" ]; then
	rm -f $D${sysconfdir}/enigma2/xstreamity/playlists.json > /dev/null 2>&1
fi

if [ -f "$D${sysconfdir}/enigma2/xstreamity/x-playlists.json" ]; then
	rm -f $D${sysconfdir}/enigma2/xstreamity/x-playlists.json > /dev/null 2>&1
fi
}
