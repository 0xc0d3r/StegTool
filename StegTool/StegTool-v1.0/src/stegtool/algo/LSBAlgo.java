package stegtool.algo;

import stegtool.util.Image;
import stegtool.util.UtilMethods;
import stegtool.util.LSBStatus;
import stegtool.security.ConvertData;


//importing required io classes
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JOptionPane;

public class LSBAlgo{
    private Image img;
    private String action; //{embed,extract}
    private String coverImage;//Input image file path
    private String textFile;//text message file path
    private String stegoImage;//output image file path
    private String password;//password
    
    private String txtMsg = null;
    
    ConvertData cd = new ConvertData();
    public LSBAlgo(String []cla) {
        action = cla[0];
        coverImage = cla[1];
        textFile = cla[2];
        stegoImage = cla[3];
        password = cla[4];
        String msg = "";
        try{
            img = new Image(cla[1]);
        }
        catch(ArrayIndexOutOfBoundsException ae) {
            System.out.println("StegTool: Usage..");
        }
        catch(RuntimeException re) {
            System.out.println("StegTool: Can't found image to read...");
        }
        
        if(action.equals("embed")) {
            txtMsg = UtilMethods.getFileData(textFile);
            embedData();
        }
        
        if(action.equals("extract")) {
            extractData();
        }
    }
    
    public void embedData() {
        String binTxt = null,ref="~~~",msg = "";
        txtMsg = cd.xorEncrypt(txtMsg,password)+ref;
        binTxt = UtilMethods.textToBinary(txtMsg);
        int index = 0;
        boolean noError = true;
        for(int rows=0; rows<img.width();rows++) {
            for(int cols=0;cols<img.height();cols++) {
                Color clr = img.getRGBValue(rows,cols);
                int newRed,newGreen,newBlue,rem;
                
                //changing red pixel value
                newRed = clr.getRed();
                rem = newRed & 1;
                if( index < binTxt.length() && (Integer.parseInt(""+binTxt.charAt(index++)))!=(rem) ) {
                    if(rem == 1) newRed -= 1;
                    else newRed += 1;
                }
                
                //changing green pixel value
                newGreen = clr.getGreen();
                rem = newGreen & 1;
                if( index < binTxt.length() && (Integer.parseInt(""+binTxt.charAt(index++)))!=(rem) ) {
                    if(rem == 1) newGreen -= 1;
                    else newGreen += 1;
                }
                
                //changing blue pixel value
                newBlue = clr.getBlue();
                rem = newBlue & 1;
                if( index < binTxt.length() && (Integer.parseInt(""+binTxt.charAt(index++)))!=(rem) ) {
                    if(rem == 1) newBlue -= 1;
                    else newBlue += 1;
                }
                try {
                    img.setRGBValue(rows,cols,new Color(newRed,newGreen,newBlue));//setting pixel
                }
                catch(RuntimeException re) {
                    noError = false;
                    msg = "Can't set color to null...";
                    JOptionPane.showMessageDialog(null,msg,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(noError) {
            if(img.saveImage(stegoImage)) {//saving image
                LSBStatus.embedSuccess = true;
            }
            else {
                LSBStatus.embedSuccess = false;
            }
        }
    }
    
    public void extractData() {
        String bin = "",plainText = "",msg = "";
        boolean noError = true;
        for (int i = 0; i < img.width(); i++) {
            for (int j = 0; j < img.height(); j++) {
                Color c = img.getRGBValue(i, j);
                if (c.getRed() % 2 == 1) bin += "1";
                else bin += "0";
                if (c.getGreen() % 2 == 1) bin += "1";
                else bin += "0";
                if (c.getBlue() % 2 == 1) bin += "1";
                else bin += "0";
            }
            if(bin.length() > 8 ){
                plainText = UtilMethods.binaryToText(bin);
            }
            if(plainText.contains("~~~")) {
                plainText = plainText.substring(4,plainText.length()-3);
                break;
            }
        }
        String str = cd.xorDecrypt(plainText,password);
        try {
            UtilMethods.setFileData(textFile,str);
        }
        catch(RuntimeException ioe) {
            noError = false;
            msg = "StegTool: can't write data to \""+textFile+"\"...\n";
            JOptionPane.showMessageDialog(null,msg,"Error",JOptionPane.ERROR_MESSAGE);
        }
        if(noError){
            LSBStatus.extractSuccess = true;
        }  
    }
}
