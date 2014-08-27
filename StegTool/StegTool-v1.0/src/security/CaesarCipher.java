package stegtool.security;
public class CaesarCipher {
	public String encrypt(String str, int key) {
		String encrypted = "";
		for(int i = 0; i < str.length(); i++) {
			int c = str.charAt(i);
			if (Character.isUpperCase(c)) {
				c = c + (key % 26);
				if (c > 'Z')
				c = c - 26;
			} 
			else if (Character.isLowerCase(c)) {
				c = c + (key % 26);
				if (c > 'z')
				c = c - 26;
			}
			else if(c >=48 && c <= 57) {
			    c -= 15;
			}
			encrypted += (char) c;
		}
	    return alterCase(encrypted);
	}

	public String decrypt(String str, int key) {
	    str = alterCase(str);
		String decrypted = "";
		for(int i = 0; i < str.length(); i++) {
			int c = str.charAt(i);
			if (Character.isUpperCase(c)) {
				c = c - (key % 26);
				if (c < 'A')
				c = c + 26;
			} 
			else if (Character.isLowerCase(c)) {
				c = c - (key % 26);
				if (c < 'a')
				c = c + 26;
			}
			else if(c >= 33 && c<=42) {
			    c += 15;
			}
			decrypted += (char) c;
		}
		return decrypted;
	}
	public String alterCase(String txt) {
	    StringBuilder sb = new StringBuilder();
	    for(int i=0;i<txt.length();i++) {
	        char ch = txt.charAt(i);
	        if( (ch>=65 && ch<=90) || (ch>=97 && ch<=122)) 
	            sb.append((char)(ch^32)); 
            else sb.append((char)ch);
	    }
	    return sb.toString();
	}
	
	
}
