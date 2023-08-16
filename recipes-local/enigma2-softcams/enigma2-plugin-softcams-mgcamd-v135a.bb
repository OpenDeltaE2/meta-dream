require conf/license/openpli-gplv2.inc
SUMMARY = "mgcamd softcam for mipsel version 1.35a"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "already-stripped"
DEPENDS += "enigma2-plugin-softcams-libcrypto-compat-1.0.2 virtual/crypt zlib"
RDEPENDS:${PN} += "enigma2-plugin-softcams-libcrypto-compat-1.0.2 libxcrypt-compat zlib"

PV = "1.35a"

SRC_URI += " \
	file://ignore.list.example \
	file://mgcamd.mipsel \
	file://mg_cfg \
	file://newcamd.list.example \
	file://priority.list.example \
	file://replace.list.example \
	file://softcam.mgcamd135 \
	"

S = "${WORKDIR}"

FILES:${PN} += "/usr/keys /usr/bin /etc"

CONFFILES = "/usr/keys/mg_cfg /usr/keys/ignore.list /usr/keys/newcamd.list /usr/keys/priority.list /usr/keys/replace.list"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/mgcamd.mipsel ${D}/usr/bin/mgcamd135
	install -d ${D}/usr/keys
	install -m 0644 ${S}/mg_cfg ${D}/usr/keys/mg_cfg
	install -m 0644 ${S}/ignore.list.example ${D}/usr/keys/ignore.list.example
	install -m 0644 ${S}/newcamd.list.example ${D}/usr/keys/newcamd.list.example
	install -m 0644 ${S}/priority.list.example ${D}/usr/keys/priority.list.example
	install -m 0644 ${S}/replace.list.example ${D}/usr/keys/replace.list.example
	touch ${D}/usr/keys/ignore.list
	touch ${D}/usr/keys/newcamd.list
	touch ${D}/usr/keys/priority.list
	touch ${D}/usr/keys/replace.list
	install -d ${D}/etc/init.d
	install -m 0755 ${S}/softcam.mgcamd135 ${D}/etc/init.d/softcam.mgcamd135
}

CAMNAME = "mgcamd135"
CAMPATH = "/etc/init.d/softcam.${CAMNAME}"
CAMLINK = "/etc/init.d/softcam"
pkg_postinst:${PN}() {
	if [ ! -e "$D/etc/rcS.d/S96softcam" ]
	then
		ln -sf "../init.d/softcam" "$D/etc/rcS.d/S96softcam"
	fi

	if [ -f "$D/lib/ld-2.30.so" ]; then
		ln -sf "ld-2.30.so" "$D/lib/ld-linux.so.3"
	elif [ -f "$D/lib/ld-2.31.so" ]; then
		ln -sf "ld-2.31.so" "$D/lib/ld-linux.so.3"
	elif [ -f "$D/lib/ld-2.32.so" ]; then
		ln -sf "ld-2.32.so" "$D/lib/ld-linux.so.3"
	elif [ -f "$D/lib/ld-2.33.so" ]; then
		ln -sf "ld-2.33.so" "$D/lib/ld-linux.so.3"
	elif [ -f "$D/lib/ld-2.34.so" ]; then
		ln -sf "ld-2.34.so" "$D/lib/ld-linux.so.3"
	fi

	if [ ! -e "$D${CAMLINK}" ] || [ "/etc/init.d/softcam.None" = "`readlink -f $D${CAMLINK}`" ] || [ "softcam.None" = "`readlink -f $D${CAMLINK}`" ]
	then
		ln -sf "softcam.${CAMNAME}" "$D${CAMLINK}"
		$D${CAMPATH} restart > /dev/null 2>&1
	else
		$D${CAMLINK} stop > /dev/null 2>&1
		ln -sf "softcam.${CAMNAME}" "$D${CAMLINK}"
		$D${CAMPATH} restart > /dev/null 2>&1
	fi
}

# Stop this cam (if running), and move softlink to None if we're the current cam
pkg_prerm:${PN}:prepend() {
	rm -f "$D/lib/ld-linux.so.3" > /dev/null 2>&1
	if  [ "/etc/init.d/softcam.${CAMNAME}" = "`readlink -f $D${CAMLINK}`" ] || [ "softcam.${CAMNAME}" = "`readlink -f $D${CAMLINK}`" ]
	then
		$D${CAMPATH} stop > /dev/null 2>&1
		ln -sf "softcam.None" "$D${CAMLINK}"
	fi
}
