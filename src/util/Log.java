package util;

import constant.Constant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Log {
    static final String basePath = Constant.LOG_BASEPATH;

    public static void log(String log , String path , boolean append){
        append = false;
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(basePath + path, append), "UTF-8")));
            printWriter.println(log);
            printWriter.close();
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getLogs(String path){
        List<String> result = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(basePath + path)));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
