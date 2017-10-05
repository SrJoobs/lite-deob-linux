package gq.netin.util;

public enum Type {


	LITE_DEOB("LITE_DEOB", 0, Versions.getCurrentVersion('L'), "https://sabugu.000webhostapp.com/lite-deob/litedeob.dat","https://sabugu.000webhostapp.com/lite-deob/litedeob.jar", "litedeob.jar"), 
	KRAKATAU("KRAKATAU", 1, Versions.getCurrentVersion('K'), "https://sabugu.000webhostapp.com/lite-deob/krakatau.dat", "https://github.com/Storyyeller/Krakatau/archive/master.zip", "Krakatau-master.zip"),
	ASTYLE("ASTYLE", 2, Versions.getCurrentVersion('A'), "https://sabugu.000webhostapp.com/lite-deob/astyle.dat", "https://sabugu.000webhostapp.com/lite-deob/astyle.zip", "astyle.zip");

	private double currentVersion;
	private String versionCheckLink;
	private String downloadLink;
	private String fileLink;

	private Type(final String s, final int n, final double currentVersion, final String versionCheckLink, final String downloadLink, final String fileLink) {
		this.currentVersion = currentVersion;
		this.versionCheckLink = versionCheckLink;
		this.downloadLink = downloadLink;
		this.fileLink = fileLink;
	}

	public double getCurrentVersion() {
		return this.currentVersion;
	}

	public String getVersionLink() {
		return this.versionCheckLink;
	}

	public String getDownloadLink() {
		return this.downloadLink;
	}

	public String getFileLink() {
		return this.fileLink;
	}

	
}
