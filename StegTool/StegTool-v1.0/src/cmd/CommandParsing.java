package stegtool.cmd;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CommandParsing {
    public static final String pattern = "\\s+";//for detecting white spaces
    public static String[] parseCommand(String cmd) {
        Pattern wsc = Pattern.compile(pattern);
        Matcher m = wsc.matcher(cmd);
        cmd = m.replaceAll(" ");
        return split(cmd,' ');
    }
    public static String[] split(String s,char d) {
		int size=1,index=0;
		char[] sarr = s.toCharArray();
		for(char ch : sarr) {
			if(ch == d) size++;
		}
		String words[] = new String[size];
		words[index] = "";
		for(char chr : sarr) {
			if(chr == d) words[++index]="";
			else words[index]+=chr;
		}
		return words;
	}
}
