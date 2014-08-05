package stegtool.cmd;

import stegtool.algo.LSBAlgo;

import jline.TerminalFactory;
import jline.console.ConsoleReader;
import jline.console.completer.FileNameCompleter;

import java.io.IOException;

import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

public class UnEditableCMD extends DocumentFilter {

    private static final String PROMPT = "[cli@stegtool] $ ";
    public UnEditableCMD() {
        try {
            ConsoleReader con = new ConsoleReader();
            con.addCompleter(new FileNameCompleter());
            con.setPrompt(PROMPT);
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    @Override 
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string,AttributeSet attr) 
    throws BadLocationException {
        if(string == null) {
            return;
        }
        else{
            replace(fb, offset, 0, string, attr);
        }
    }

    @Override 
    public void remove(DocumentFilter.FilterBypass fb, int offset,int length) throws BadLocationException {
        replace(fb, offset, length, "", null);
    }

    @Override 
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length,String text, AttributeSet attrs) 
    throws BadLocationException {
        Document doc = fb.getDocument();
        Element root = doc.getDefaultRootElement();
        int count = root.getElementCount();
        int index = root.getElementIndex(offset);
        Element cur = root.getElement(index);
        int promptPosition = cur.getStartOffset()+PROMPT.length();
        //As Reverend Gonzo says:
        if(index==count-1 && offset-promptPosition>=0) {
            if(text.equals("\n")) {
                String cmd = doc.getText(promptPosition, offset-promptPosition);
                ProcessCommand pc = new ProcessCommand();
                text = pc.processCommand(cmd);
            }
            fb.replace(offset, length, text, attrs);
        }
    }
}
