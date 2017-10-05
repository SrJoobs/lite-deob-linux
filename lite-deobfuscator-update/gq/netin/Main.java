package gq.netin;

import gq.netin.util.Type;
import gq.netin.util.Unzipper;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import gq.netin.util.Versions;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;
import gq.netin.frame.Loading;

import javax.swing.ImageIcon;
import java.io.File;
import gq.netin.util.Util;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Main {
    public static void main(final String[] args) {
    	
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            System.out.println("Started program");
        } catch (Exception e5) {
            JOptionPane.showMessageDialog(null, "Could not start.");
            Util.deletePath(new File(Util.NETINDEV_PATH));
            System.exit(0);
        }
        final File file = new File(Util.LITEDEOB_PATH);
        if (!file.exists()) {
            final Loading loading = new Loading("Downloading...", new ImageIcon(Main.class.getResource("/gq/netin/res/reverse.png")));
            System.out.println("Check update started");
            file.mkdirs();
            final File versionsPath = new File(String.valueOf(Util.LITEDEOB_PATH) + "/versions");
            versionsPath.mkdir();
            final File[] versions = { new File(versionsPath + "/litedeob.dat"), new File(versionsPath + "/krakatau.dat") };
            File[] array;
            for (int length = (array = versions).length, i = 0; i < length; ++i) {
                final File version = array[i];
                try {
                    version.createNewFile();
                }
                catch (IOException e) {
                    loading.dispose();
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Could not start...");
                    System.exit(0);
                }
            }
            final File litedeobVersion = versions[0];
            final File krakatauVersion = versions[1];
            Util.mkdir(String.valueOf(Util.LITEDEOB_PATH) + "/krakatau");
            try {
                final BufferedReader readerLite = new BufferedReader(new InputStreamReader(new URL("http://sabugu.000webhostapp.com/lite-deob/litedeob.dat").openStream()));
                final BufferedWriter writerLite = new BufferedWriter(new FileWriter(litedeobVersion));
                writerLite.write(readerLite.readLine());
                writerLite.close();
                final BufferedReader readerKrakatau = new BufferedReader(new InputStreamReader(new URL("http://sabugu.000webhostapp.com/lite-deob/krakatau.dat").openStream()));
                final BufferedWriter writerKrakatau = new BufferedWriter(new FileWriter(krakatauVersion));
                writerKrakatau.write(readerKrakatau.readLine());
                writerKrakatau.close();
            }
            catch (Exception e2) {
                loading.dispose();
                e2.printStackTrace();
                JOptionPane.showMessageDialog(null, "Could not start...");
                Util.deletePath(new File(Util.NETINDEV_PATH));
                System.exit(0);
            }
           Type[] values;
            for (int length2 = (values = Type.values()).length, j = 0; j < length2; ++j) {
                final Type type = values[j];
                try {
                    loading.setTitle("Downloading " + type.toString().toLowerCase().replace("_", " "));
                    final URL url = new URL(type.getDownloadLink());
                    final BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
                    final FileOutputStream outputStream = new FileOutputStream(String.valueOf(Util.LITEDEOB_PATH) + "/" + type.getFileLink());
                    final byte[] buffer = new byte[1024];
                    int read;
                    while ((read = inputStream.read(buffer, 0, 1024)) != -1) {
                        outputStream.write(buffer, 0, read);
                    }
                    outputStream.close();
                    inputStream.close();
                    if (type == Type.KRAKATAU) {
                        Unzipper.unzip(String.valueOf(Util.LITEDEOB_PATH) + "/" + type.getFileLink(), String.valueOf(Util.LITEDEOB_PATH) + "/krakatau");
                        Util.deletePath(new File(String.valueOf(Util.LITEDEOB_PATH) + "/Krakatau-master.zip"));
                    }
                    if (type == Type.ASTYLE) {
                        Unzipper.unzip(String.valueOf(Util.LITEDEOB_PATH) + "/" + type.getFileLink(), String.valueOf(Util.LITEDEOB_PATH) + "/astyle");
                        Util.deletePath(new File(String.valueOf(Util.LITEDEOB_PATH) + "/astyle.zip"));
                    }
                }
                catch (IOException e3) {
                    loading.dispose();
                    e3.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Could not download " + type.toString().toLowerCase().replace("_", " ") + ".");
                    Util.deletePath(new File(Util.LITEDEOB_PATH.replace("lite-deob-reloaded/", "")));
                    System.exit(0);
                }
            }
            loading.dispose();
            try {
            	System.out.println("Check update finished.");
            	Runtime.getRuntime().exec("java -jar "+String.valueOf(Util.LITEDEOB_PATH) + "/litedeob.jar");
                System.exit(0);
            }
            catch (Exception e2) {
                e2.printStackTrace();
                JOptionPane.showMessageDialog(null, "Could not start..");
                Util.deletePath(new File(Util.NETINDEV_PATH));
                System.exit(0);
            }
            return;
        }
        final Loading updateFrame = new Loading("Updating...", new ImageIcon(Main.class.getResource("/gq/netin/res/reverse.png")));
        Type[] values2;
        for (int length3 = (values2 = Type.values()).length, k = 0; k < length3; ++k) {
            final Type type2 = values2[k];
            try {
                if (type2 != Type.ASTYLE) {
                    updateFrame.setTitle("Checking " + type2.toString().toLowerCase().replace("_", " "));
                    if (Versions.hasNewVersion(type2)) {
                        updateFrame.dispose();
                        final int choose = JOptionPane.showConfirmDialog(null, "New release available for: " + type2.toString().toLowerCase().replace("_", "-") + ".\nDo you want to download the new version?", "New version found", 0);
                        if (choose == 0) {
                            final Loading loading2 = new Loading("Updating " + type2.toString().toLowerCase().replace("_", " "), new ImageIcon(Main.class.getResource("/gq/netin/res/reverse.png")));
                            try {
                                final File versionsPath2 = new File(String.valueOf(Util.LITEDEOB_PATH) + "/versions");
                                final File[] versions2 = { new File(versionsPath2 + "/litedeob.dat"), new File(versionsPath2 + "/krakatau.dat"), new File(versionsPath2 + "/astyle.dat") };
                                final File litedeobVersion2 = versions2[0];
                                final File krakatauVersion2 = versions2[1];
                                final BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(type2.getVersionLink()).openStream()));
                                switch (type2) {
                                    case LITE_DEOB: {
                                        final BufferedWriter writerLite2 = new BufferedWriter(new FileWriter(litedeobVersion2));
                                        writerLite2.write(reader.readLine());
                                        writerLite2.close();
                                        break;
                                    }
                                    case KRAKATAU: {
                                        final BufferedWriter writerKrakatau2 = new BufferedWriter(new FileWriter(krakatauVersion2));
                                        writerKrakatau2.write(reader.readLine());
                                        writerKrakatau2.close();
                                        break;
                                    }
								default:
									break;
                                }
                                final URL url2 = new URL(type2.getDownloadLink());
                                final BufferedInputStream inputStream2 = new BufferedInputStream(url2.openStream());
                                final FileOutputStream outputStream2 = new FileOutputStream(String.valueOf(Util.LITEDEOB_PATH) + "/" + type2.getFileLink());
                                final byte[] buffer2 = new byte[1024];
                                int read2;
                                while ((read2 = inputStream2.read(buffer2, 0, 1024)) != -1) {
                                    outputStream2.write(buffer2, 0, read2);
                                }
                                outputStream2.close();
                                inputStream2.close();
                                loading2.dispose();
                                if (type2 == Type.KRAKATAU) {
                                    Util.deletePath(new File(String.valueOf(Util.LITEDEOB_PATH) + "/krakatau"));
                                    Unzipper.unzip(String.valueOf(Util.LITEDEOB_PATH) + "/" + type2.getFileLink(), String.valueOf(Util.LITEDEOB_PATH) + "/krakatau");
                                }
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Could not download " + type2.toString().toLowerCase().replace("_", " ") + ".");
                            }
                        }
                        else if (choose != 1) {
                            updateFrame.dispose();
                            System.exit(0);
                        }
                    }
                }
            }
            catch (Exception e2) {
                updateFrame.dispose();
                e2.printStackTrace();
                JOptionPane.showMessageDialog(null, "Could not check version for: " + type2.toString().toLowerCase().replace("_", " ") + ".");
            }
        }
        updateFrame.dispose();
        try {
        	System.out.println("Check update finished.");
        	Runtime.getRuntime().exec("java -jar "+String.valueOf(Util.LITEDEOB_PATH) + "/litedeob.jar");
            System.exit(0);
        }
        catch (Exception e4) {
            e4.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not start..");
            Util.deletePath(new File(Util.NETINDEV_PATH));
            System.exit(0);
        }
    }
}
