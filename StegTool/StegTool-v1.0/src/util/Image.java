package stegtool.util;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.net.URL;

public final class Image {
    private BufferedImage image;
    private String filename;
    
    public Image(String fname) {
        filename = fname;
        try {
            File f = new File(filename);
            if(f.isFile())
                image = ImageIO.read(f);
        }
        catch(IOException e) {
            throw new RuntimeException("\nError: Couldn't open file. Image.java@Constructor.");
        }
        
        if(image == null) 
            throw new RuntimeException("\nError: Invalid image file. Image.java@Constructor.");
    }
    
    public int width() {
        return image.getWidth();
    }
    
    public int height() {
        return image.getHeight();
    }
    
    public Color getRGBValue(int x,int y) {
        return new Color(image.getRGB(x,y));
    }
    
    public void setRGBValue(int x,int y,Color clr) {
        if(clr ==  null)
            throw new RuntimeException("\nError : Can\'t set color to null. Image.java@setRGBValue().");
        image.setRGB(x,y,clr.getRGB());
    }
    public boolean saveImage(String fname) {
        boolean save = true;
        if(saveImage(new File(fname))) save = true;
        else save = false;
        return save;
    }
    public boolean saveImage(File f) {
        boolean save = true;
        this.filename = f.getName();
        String suffix = filename.substring(filename.lastIndexOf('.')+1);
        suffix = suffix.toLowerCase();
        if(suffix.equals("png") || suffix.equals("jpg") || suffix.equals("bmp")) {
            try {
                ImageIO.write(image,suffix,f);
            }
            catch(IOException ioe) {
                save = false;
            }
        }
        else {
            save = false;
        }
        return save;
    }
}
