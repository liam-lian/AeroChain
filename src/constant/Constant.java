package constant;

import com.sun.deploy.util.SystemUtils;

import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static final String PRE_PREPARE_TAG = "<pre-prepare>";

    public static final String PREPARE_TAG = "<prepare>";

    public static final int PORT = 7777;

    public static final int CHECKPOINT = 60;

    public static final int BLOCK_GAP = 10;

    public static final String SEPARTOR = "- - - - - - - - - -";

    public static final String WINDOWS_BASEPATH = "C:\\Users\\DSY\\blockchain\\";

    public static final String MAC_BASEPATH = "/Users/dingsiye/projects/AeroChain";

    public static final String WINDOWS_LOG_BASEPATH = "C:\\Users\\DSY\\blockchain\\log\\";

    public static final String MAC_LOG_BASEPATH = "/Users/dingsiye/projects/AeroChain/log";

    public static List<String> address = new ArrayList<>();
    static {
        address.add("127.0.0.1");
        address.add("192.168.0.112");
        address.add("192.168.0.124");
        address.add("192.168.0.127");
        address.add("192.168.0.116");
    }

    public static final String CLIENT_LOG_ADDRESS = MAC_LOG_BASEPATH + "client";

    public static final String UTF_8 = "UTF-8";
}
