package stegtool.util;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.awt.Color;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class UtilMethods {
    
    //to get contents of a text file
    public static String getFileData(String fname) {
        String text = "";
        int ch;
        try {
            InputStream is = new FileInputStream(fname);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while( (ch = br.read()) != -1 ) {
                 text += ""+(char)ch;
            }
        }
        catch(Exception e) {
            throw new RuntimeException("ERROR: File operation failed.@UtilMethods.java_getFileData()");
            //System.out.println("ERROR: File operation failed.@UtilMethods.java_getFileData()");
        }
        return text;
    }
    
    public static void setFileData(String name,String data) {
        try {
            File file = new File(name);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        }
        catch(IOException e) {
            throw new RuntimeException("\nError: Couldn't write data to file. UtilMethods.java@setFileData().");
            //System.out.println("\nError: Couldn't write data to file. UtilMethods.java@setFileData().");
        }
    }
    
    //to get binary format of given string
    public static String textToBinary(String text) {
        String bin = "";
        StringBuffer sb = null;
        for(int i=0;i<text.length();i++) {
            sb = new StringBuffer(Integer.toBinaryString((int)text.charAt(i)));
            bin += binaryLeftPadding(sb);
        }
        return bin;
    }
    
    //to left padding the binary numbers
    public static String binaryLeftPadding(StringBuffer bin) {
        while(bin.length() < 8) {
            bin.insert(0,"0");
        }
        return bin.toString();
    }
    
    //to get text from given binary
    public static String binaryToText(String bin) {
        String text = null;
        for(int i=0;i<bin.length()-8;i+=8) {
            int chr = Integer.parseInt(bin.substring(i,i+8),2);
            text += (char)chr;
            if(text.contains("~~~")) break;
        }
        return text;
    }   
}
