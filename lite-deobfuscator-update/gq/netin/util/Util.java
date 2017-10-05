package gq.netin.util;

import java.io.File;

public class Util
{
    private static final String ROAMING;
    public static final String LITEDEOB_PATH;
    public static final String NETINDEV_PATH;
    
    static {
        ROAMING = "lite-deobfuscator";
        LITEDEOB_PATH = String.valueOf(Util.ROAMING) + "/netindev/lite-deob-reloaded/";
        NETINDEV_PATH = String.valueOf(Util.ROAMING) + "/netindev";
    }
    
    public static void mkdir(final String string) {
        new File(string).mkdir();
    }
    
    public static void deletePath(final File file) {
        if (file.isDirectory()) {
            final String[] files = file.list();
            for (int i = 0; i < files.length; ++i) {
                deletePath(new File(file, files[i]));
            }
        }
        file.delete();
    }
    
}
