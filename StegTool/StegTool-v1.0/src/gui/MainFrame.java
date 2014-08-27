package stegtool.gui;

import stegtool.gui.EmbedInterface;
import stegtool.gui.ExtractInterface;
import stegtool.cmd.UnEditableCMD;


//import awt components
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

//importing swing components
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.AbstractDocument;

public class MainFrame{
	private JFrame mainFrame;
	public static JPanel mainPanel;
	private JPanel innerPanel2,Panel2,bannerPanel;	
	private JTabbedPane tabbedPane;
	private JTabbedPane lsbTabbedPane;
	private JPanel statusPanel;
	public static JTextArea console;
	public String defaultText = "[cli@stegtool] $ ";
	public MainFrame(){
		prepareGUI();
	}
	public void prepareGUI(){
		mainFrame=new JFrame("St3gT00l v1.0");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(710,400);
		tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(100, 120));
        //Panel 1
        
        //Panel1 ended
        Panel2 = new JPanel(new BorderLayout());
		tabbedPane.addTab("EMBED",new EmbedInterface());
		tabbedPane.addTab("EXTRACT",new ExtractInterface());
        tabbedPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        tabbedPane.setBackgroundAt(0,Color.GRAY);
        tabbedPane.setBackgroundAt(1,Color.GRAY);
        
        statusPanel=new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), 70));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		console = new JTextArea(defaultText,2,50);
		Font font = new Font("Verdana",Font.BOLD,12);
		console.setBackground(Color.black);
		console.setFont(font);
		console.setForeground(Color.green);
		console.setLineWrap(true);
		((AbstractDocument)console.getDocument()).setDocumentFilter(new UnEditableCMD());
		JScrollPane sb = new JScrollPane(console,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		statusPanel.add(sb);
		mainFrame.add(statusPanel,BorderLayout.SOUTH);
        mainFrame.add(tabbedPane,BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);        
        mainFrame.setBounds(200,150,710,400);
        mainFrame.setResizable(false);
	}
	public static void main(String args[]){
		new MainFrame();
	}
}
