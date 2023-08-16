DESCRIPTION = "OpenDelta branding lib DM800SE"
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
    --with-boxtype="dm800se" \
    --with-machinebuild="dm800se" \
    --with-imageversion="12.2" \
    --with-imagebuild="master" \
    --with-imagedevbuild="000" \
    --with-imagetype="master" \
    --with-feedsurl="https://download.areadeltasat.net/feed-12" \
    --with-imagedir="" \
    --with-imagefs="jffs2.nfi" \
    --with-mtdbootfs="jffs2" \
    --with-mtdrootfs="jffs2" \
    --with-mtdkernel="mtd2" \
    --with-rootfile="rootfs.bin" \
    --with-kernelfile="kernel.bin" \
    --with-mkubifs="-m 512 -e 15KiB -c 3735 -x favor_lzo -X 1 -F -j 4MiB" \
    --with-ubinize="-m 512 -p 16KiB -s 512" \
    --with-arch="mips32el" \
    --with-tfpu="hard" \
    --with-display-type="colorlcd" \
    --with-small-flash="True" \
    --with-kernelversion="3.2" \
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
    --with-hdmi-in-hd="False" \
    --with-hdmi-in-fhd="False" \
    --with-wol="False" \
    --with-ci="False" \
    --with-blindscanbinary="" \
    --with-socfamily="bcm7405" \
    --with-vfd-symbol="False" \
    --with-rctype="" \
    --with-rcname="" \
    --with-rcidnum="" \
    --with-fhdskin="False" \
    "

FILES:${PN} = "${libdir}/enigma2/python/*.so"
FILES:${PN}-dev += "${libdir}/enigma2/python/*.la"
FILES:${PN}-staticdev += "${libdir}/enigma2/python/*.a"
FILES:${PN}-dbg += "${libdir}/enigma2/python/.debug"
