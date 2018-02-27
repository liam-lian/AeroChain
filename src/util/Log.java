package util;

import java.io.*;

public class Log {
    public static void log(String prePrepare , String path){
        try {
            String basePath = "C:\\Users\\DSY\\blockchain\\log\\";
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(basePath + path, true), "UTF-8")));
            printWriter.println(prePrepare);
            printWriter.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
