DESCRIPTION = "OpenDelta branding lib DM900"
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
    --with-boxtype="dm900" \
    --with-machinebuild="dm900" \
    --with-imageversion="12.2" \
    --with-imagebuild="master" \
    --with-imagedevbuild="000" \
    --with-imagetype="master" \
    --with-feedsurl="https://download.areadeltasat.net/feed-12" \
    --with-imagedir="dm900" \
    --with-imagefs="tar.bz2" \
    --with-mtdbootfs="mmcblk0p3" \
    --with-mtdrootfs="mmcblk0p2" \
    --with-mtdkernel="mmcblk0p1" \
    --with-rootfile="rootfs.tar.bz2" \
    --with-kernelfile="kernel.bin" \
    --with-mkubifs="" \
    --with-ubinize="" \
    --with-forcemode="" \
    --with-arch="cortexa15hf-neon-vfpv4" \
    --with-tfpu="hard" \
    --with-display-type="colorlcd390" \
    --with-small-flash="False" \
    --with-middle-flash="False" \
    --with-transcoding="False" \
    --with-multitranscoding="False" \
    --with-multilib="False" \
    --with-hdmi="True" \
    --with-yuv="False" \
    --with-rca="False" \
    --with-av-jack="False" \
    --with-scart="False" \
    --with-dvi="False" \
    --with-svideo="False" \
    --with-hdmi-in-hd="False" \
    --with-hdmi-in-fhd="True" \
    --with-wol="False" \
    --with-ci="True" \
    --with-blindscanbinary="False" \
    --with-socfamily="bcm7252s" \
    --with-vfd-symbol="False" \
    --with-kernelversion="3.14" \
    --with-rctype="" \
    --with-rcname="" \
    --with-rcidnum="" \
    --with-fhdskin="True" \
    "

FILES:${PN} = "${libdir}/enigma2/python/*.so"
FILES:${PN}-dev += "${libdir}/enigma2/python/*.la"
FILES:${PN}-staticdev += "${libdir}/enigma2/python/*.a"
FILES:${PN}-dbg += "${libdir}/enigma2/python/.debug"
