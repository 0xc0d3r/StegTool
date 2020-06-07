package stegtool.cmd;
import stegtool.algo.LSBAlgo;


import java.io.File;
public class ProcessCommand {
    private static final String PROMPT = "[cli@stegtool] $ ";
    String text;
    public String processCommand(String cmd) {
        if(cmd.isEmpty()) {
            text = "\n"+PROMPT;
        }
        else if(cmd.equals("man stegtool")) {
            String help = "Embed:\n    embed cover.png Message.txt stego.png password\n"+
            "\nExtract:\n    extract stego.png Output.txt password\n";
            text = "\n"+help+PROMPT;
        }
        else if(cmd.startsWith("embed")) {
            
            int invalidOptions = 0;
            String[] opt = CommandParsing.parseCommand(cmd);
            File cf = new File(opt[1]);
            File mf = new File(opt[2]);
            File sf = new File(opt[3]);
            File sfd = new File(sf.getParent());
            if(!cf.exists()) {
                text = "\nstegtool: \'"+opt[1]+"\' can't found to read\n"+PROMPT;
                invalidOptions++;
            }
            if(!mf.exists()) {
                text = "\nstegtool: \'"+opt[2]+"\' can't found to read\n"+PROMPT;
                invalidOptions++;
            }
            if(sf.isDirectory()) {
                text = "\nstegtool: can't write pixel data to \'"+opt[3]+"\'...\n"+PROMPT;
                invalidOptions++;
            }
            if(!sfd.exists()) {
                text = "\nstegtool: \'"+sf.getParent()+"\' doesn't exist\n"+PROMPT;
                invalidOptions++;
            }
            if(invalidOptions == 0) {
                new LSBAlgo(opt);
                text = 
                "\n[+] Data embedding process started...\n"+
                "[+] Accessing pixels and inserting data...\n"+
                "[+] Data embedded successfully in \""+opt[3]+"\"...\n"+PROMPT;
            }
            
        }
        else if(cmd.startsWith("extract")) {
            int invalidOptions = 0;
            String[] opt = CommandParsing.parseCommand(cmd);
            File sf = new File(opt[1]);
            File mf = new File(opt[2]);
            File mfd = new File(mf.getParent());
            if(!sf.exists()) {
                text = "\nstegtool: \'"+opt[1]+"\' can't found to read\n"+PROMPT;
                invalidOptions++;
            }
            if(mf.isDirectory()) {
                text = "\nstegtool: can't write text data to \'"+opt[2]+"\'...\n"+PROMPT;
                invalidOptions++;
            }
            if(!mfd.exists()) {
                text = "\nstegtool: \'"+mf.getParent()+"\' doesn't exist\n"+PROMPT;
                invalidOptions++;
            }
            if(invalidOptions == 0) {
                new LSBAlgo(new String[]{opt[0],opt[1],opt[2],"",opt[3]});
                text = 
                "\n[+] Data extracting process started...\n"+
                "[+] Accessing pixels and getting data...\n"+
                "[+] Data extracted successfully & written to \""+opt[2]+"\"...\n"+PROMPT;
            }
        }
        else{
            text = "\n"+cmd+": command not found\n"+ PROMPT;
        }
        return text;
    }
}
