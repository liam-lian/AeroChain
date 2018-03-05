package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getLogs(String path){
        List<String> result = new ArrayList<>();
        String basePath = "C:\\Users\\DSY\\blockchain\\log\\";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(basePath + path)));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                result.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
