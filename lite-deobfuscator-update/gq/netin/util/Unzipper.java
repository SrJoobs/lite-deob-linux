package gq.netin.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.FileInputStream;
import java.io.File;

public class Unzipper
{
    public static void unzip(final String zipFilePath, final String destDirectory) throws IOException {
        final File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        final ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        for (ZipEntry entry = zipIn.getNextEntry(); entry != null; entry = zipIn.getNextEntry()) {
            final String filePath = String.valueOf(destDirectory) + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                extractFile(zipIn, filePath);
            }
            else {
                final File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
        }
        zipIn.close();
    }
    
    private static void extractFile(final ZipInputStream zipIn, final String filePath) throws IOException {
        final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        final byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}
