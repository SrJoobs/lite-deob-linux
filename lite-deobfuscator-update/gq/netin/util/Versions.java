package gq.netin.util;

import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Versions {
	
	public static boolean hasNewVersion(final Type type) throws Exception {
		final BufferedReader reader = new BufferedReader(
				new InputStreamReader(new URL(type.getVersionLink()).openStream()));
		final double line = Double.parseDouble(reader.readLine());
		return line != type.getCurrentVersion() && type.getCurrentVersion() < line;
	}

	public static double getCurrentVersion(final char type) {
		final File versionsPath = new File(String.valueOf(Util.LITEDEOB_PATH) + "/versions");
		final File[] versions = { new File(versionsPath + "/litedeob.dat"), new File(versionsPath + "/krakatau.dat") };
		final File litedeobVersion = versions[0];
		final File krakatauVersion = versions[1];
		double toReturn = 0.0;
		try {
			switch (type) {
			case 'L': {
				final BufferedReader readerLite = new BufferedReader(new FileReader(litedeobVersion));
				toReturn = Double.parseDouble(readerLite.readLine());
				readerLite.close();
				break;
			}
			case 'K': {
				final BufferedReader readerKrakatau = new BufferedReader(new FileReader(krakatauVersion));
				toReturn = Double.parseDouble(readerKrakatau.readLine());
				readerKrakatau.close();
				break;
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Could not start..");
			Util.deletePath(new File(Util.NETINDEV_PATH));
			System.exit(0);
		}
		return toReturn;
	}
}
