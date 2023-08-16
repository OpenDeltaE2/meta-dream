DESCRIPTION = "OpenDelta branding lib DM7080"
MAINTAINER = "OpenDelta Developers"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "python"

LICENSE = "CLOSED"

inherit autotools-brokensep gitpkgv pythonnative

SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

PR[vardepsexclude] += "DATE"

do_configure[nostamp] = "1"

SRC_URI="git://github.com/OpenDeltaE2/branding-module.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
    --with-visionversion="12.2" \
    --with-visionrevision="master" \
    --with-developername="master" \
    --with-boxbrand="dreambox" \
    --with-oever="OpenDelta-OE" \
    --with-distro="areadeltasat" \
    --with-boxtype="DM7080" \
    --with-machinebuild="DM7080" \
    --with-imageversion="12.2" \
    --with-imagebuild="master" \
    --with-imagedevbuild="000" \
    --with-imagetype="master" \
    --with-feedsurl="https://download.areadeltasat.net/feed-12" \
    --with-imagedir="" \
    --with-imagefs="tar.xz" \
    --with-mtdbootfs="" \
    --with-mtdrootfs="" \
    --with-mtdkernel="mtd2" \
    --with-rootfile="rootfs.bin" \
    --with-kernelfile="kernel.bin" \
    --with-mkubifs="" \
    --with-ubinize="" \
    --with-arch="mips32el" \
    --with-tfpu="hard" \
    --with-display-type="colorlcd128" \
    --with-small-flash="False" \
    --with-kernelversion="3.4" \
    --with-transcoding="False" \
    --with-multitranscoding="False" \
    --with-middle-flash="False" \
    --with-multilib="False" \
    --with-hdmi="True" \
    --with-yuv="False" \
    --with-rca="False" \
    --with-av-jack="False" \
    --with-scart="True" \
    --with-dvi="False" \
    --with-svideo="False" \
    --with-hdmi-in-hd="True" \
    --with-hdmi-in-fhd="False" \
    --with-wol="False" \
    --with-ci="True" \
    --with-blindscanbinary="" \
    --with-socfamily="bcm7435" \
    --with-vfd-symbol="False" \
    --with-rctype="" \
    --with-rcname="" \
    --with-rcidnum="" \
    --with-fhdskin="True" \
    "

FILES:${PN} = "${libdir}/enigma2/python/*.so"
FILES:${PN}-dev += "${libdir}/enigma2/python/*.la"
FILES:${PN}-staticdev += "${libdir}/enigma2/python/*.a"
FILES:${PN}-dbg += "${libdir}/enigma2/python/.debug"
