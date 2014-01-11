package cn.scau.scaubook.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IOUtil {
    public static String read(String path) throws IOException{
        if(path == null)
            return null;
        File file = new File(path);
        BufferedReader bufferedReader = null;
        StringBuffer sb = new StringBuffer();
        try{
            bufferedReader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = bufferedReader.readLine())!=null){
                sb.append(temp);
            }
        }finally{
            bufferedReader.close();
        }
        return sb.toString();
    }
}
