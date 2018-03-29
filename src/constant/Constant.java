package constant;

import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static final String PRE_PREPARE_TAG = "<pre-prepare>";

    public static final String PREPARE_TAG = "<prepare>";

    public static final int POTR = 7777;

    public static final String VERSION = "version_0";

    public static final int CHECKPOINT = 60;

    public static final int BLOCK_GAP = 10;

    public static final String SEPARTOR = "- - - - - - - - - -";

    public static final String LOG_BASEPATH = "C:\\Users\\DSY\\blockchain\\log\\";

    public static List<String> address = new ArrayList<>();
    static {
        address.add("127.0.0.1");
        address.add("192.168.0.112");
        address.add("192.168.0.124");
        address.add("192.168.0.127");
        address.add("192.168.0.116");
    }
}
