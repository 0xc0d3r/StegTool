//package stegtool.security;
//import org.apache.commons.codec.binary.Base64;

public class ConvertData {
    /*public String encode(String plainTxt) {
        byte[] ctBytes = Base64.encodeBase64(plainTxt.getBytes());
        return new String(ctBytes);
    }
    
    public String decode(String cipherTxt) {
        byte[] ptBytes = Base64.decodeBase64(cipherTxt.getBytes());   
        return new String(ptBytes);
    }*/
    
    
    public String xorEncrypt(String plainTxt,String key) {
        StringBuilder cipherTxt = new StringBuilder();
        int ptLen = plainTxt.length();
        int keyLen = key.length();
        
        char[] ptarr = plainTxt.toCharArray();
        char[] keyarr = key.toCharArray();
        for(int i=0;i<ptarr.length;i++) {
            cipherTxt.append( (char)(ptarr[i] ^ keyarr[i % keyLen]));
        }
        return cipherTxt.toString();
    }
    
    public String xorDecrypt(String cipherTxt,String key) {
        StringBuilder plainTxt = new StringBuilder();
        int ctLen = cipherTxt.length();
        int keyLen = key.length();
        
        char[] ctarr = cipherTxt.toCharArray();
        char[] keyarr = key.toCharArray();
        for(int i=0;i<ctarr.length;i++) {
            plainTxt.append( (char)(ctarr[i] ^ keyarr[i % keyLen]));
        }
        return plainTxt.toString();
    }
}
