SUMMARY = "Proprietary Qualcomm programs"
DESCRIPTION = "proprietary things copied from a full OTA"
SECTION = "examples"
PR = "r1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=0835ade698e0bcf8506ecda2f7b4f302"
#LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
#                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://initscripts \
	   file://services \
	   file://qtirun \
	   file://other"

S = "${WORKDIR}"


do_install () {
		#install -m 0644 ${WORKDIR}/wcnss_wlan.service -D ${D}/etc/systemd/system/wcnss_wlan.service
	        #install -d ${D}/etc/systemd/system/multi-user.target.wants/
		#ln -sf /etc/systemd/system/wcnss_wlan.service \
		#${D}/etc/systemd/system/multi-user.target.wa
	install -d ${D}/etc/initscripts
	install -d ${D}/lib/systemd/system/multi-user.target.wants
	install -d ${D}/usr/bin
	install -d ${D}/sbin
	cp -r ${S}/other/export-gpio ${D}/sbin/export-gpio
	cp -r ${S}/initscripts/* ${D}/etc/initscripts/
	cp -r ${S}/qtirun ${D}/usr/bin/qtirun
	cp -r ${S}/services/* ${D}/lib/systemd/system/
	ln -sf /lib/systemd/system/anki-audio-init.service ${D}/lib/systemd/system/multi-user.target.wants/
	ln -sf /lib/systemd/system/boot-successful.service ${D}/lib/systemd/system/multi-user.target.wants/
        ln -sf /lib/systemd/system/logd.service ${D}/lib/systemd/system/multi-user.target.wants/
        ln -sf /lib/systemd/system/mdsprpcd.service ${D}/lib/systemd/system/multi-user.target.wants/
        ln -sf /lib/systemd/system/mm-anki-camera.service ${D}/lib/systemd/system/multi-user.target.wants/
        ln -sf /lib/systemd/system/mm-qcamera-daemon.service ${D}/lib/systemd/system/multi-user.target.wants/
        ln -sf /lib/systemd/system/qtid.service ${D}/lib/systemd/system/multi-user.target.wants/
        ln -sf /lib/systemd/system/qti_system_daemon.service ${D}/lib/systemd/system/multi-user.target.wants/
        ln -sf /lib/systemd/system/rmt_storage.service ${D}/lib/systemd/system/multi-user.target.wants/
	ln -sf /lib/systemd/system/init_audio.service ${D}/lib/systemd/system/multi-user.target.wants/

}

FILES_${PN} = "	/lib/systemd/system \
		/lib/systemd/system/multi-user.target.wants \
		/usr/bin/qtirun \
		/etc/initscripts \
		/sbin/export-gpio"

# yocto doesn't really allow precompiled binaries
INSANE_SKIP_${PN} = "file-rdeps"
INSANE_SKIP_${PN} += " dev-elf"
INSANE_SKIP_${PN} += " dev-so"
INSANE_SKIP_${PN} += " ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

# Prevents do_package failures with:
# debugsources.list: No such file or directory:
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

