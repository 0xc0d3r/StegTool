package stegtool.gui;

import stegtool.algo.LSBAlgo;
import stegtool.util.LSBStatus;

//import awt components
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

//importing swing components
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import java.io.File;


public class EmbedInterface extends JPanel{
	private JPanel innerPanel,InpPanel,MsgPanel,OutPanel,PassPanel,lowerPanel;
	private JFileChooser InimgChooser;
	private JComboBox<String> jcb;
	private JFileChooser MsgFileChooser;
	private JFileChooser OutFolChooser;
	private JButton InputFileButton;
	private JButton MsgFileButton;
	private JButton OutFileButton;	
	private JButton Embed;
	private JTextField FileField;
	private JTextField MsgField;
	private JTextField OutField;	
	private JPasswordField PassField;
	private JPasswordField ConfPassField;
	private JLabel HeaderLabel;
	
	public static String OutDir = "";
	public EmbedInterface(){
		InimgChooser=new JFileChooser();
        HeaderLabel=new JLabel("******StegTool-EMBED******",JLabel.CENTER);
        InputFileButton=new JButton("Browse");
        innerPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,25,0));
        FileField=new JTextField(50);
        InpPanel=new JPanel(new FlowLayout());
        InpPanel.add(FileField);
        InpPanel.add(InputFileButton);
        InpPanel.setBorder(BorderFactory.createTitledBorder("Cover File"));
//        innerPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        
		//For input Image Filters and all the stuff
        InimgChooser=new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("(.png/.jpg/.bmp/.jpeg)","jpg","jpeg","png","bmp");
        InimgChooser.setFileFilter(filter);
		InimgChooser.setAcceptAllFileFilterUsed(false);
        InputFileButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int returnVal = InimgChooser.showOpenDialog(innerPanel);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
				java.io.File file = InimgChooser.getSelectedFile();
		        FileField.setText("" 
		           + file.getPath());
            }
         }
      	});
      	
      	//input message File
		MsgField=new JTextField(50);
		MsgFileButton=new JButton("Browse");
		MsgPanel=new JPanel(new FlowLayout());
        MsgPanel.add(MsgField);
       	MsgPanel.add(MsgFileButton); 
       	MsgPanel.setBorder(BorderFactory.createTitledBorder("Message File")); 
        MsgFileChooser=new JFileChooser();
        FileFilter txtfilter = new FileNameExtensionFilter("(.txt) files","txt");
        MsgFileChooser.setFileFilter(txtfilter);
		MsgFileChooser.setAcceptAllFileFilterUsed(false);
        MsgFileButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int returnVal = MsgFileChooser.showOpenDialog(innerPanel);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
				java.io.File file = MsgFileChooser.getSelectedFile();
		        MsgField.setText("" + file.getPath());
            }
         }
      	});
        
        //select Output Directory
		OutField=new JTextField(43);
		OutFileButton=new JButton("Browse");
		OutPanel=new JPanel(new FlowLayout());
		jcb = new JComboBox<String>();
        jcb.addItem(".png");
        jcb.addItem(".bmp");
        jcb.addItem(".jpg");
        jcb.setEnabled(false);
        jcb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String opfol = OutDir+"/";
                String fType = jcb.getSelectedItem().toString();
                String opfname = "";
                if(opfol.endsWith(".bmp") || opfol.endsWith(".jpg") || opfol.endsWith(".png") ) {
                    opfname = opfol;
                }
                else {
                    opfname = opfol+"StegoFile"+fType;
                }
                OutField.setText(opfname);
            }
        });
        OutPanel.add(OutField);
        OutPanel.add(jcb);
        OutPanel.add(OutFileButton);  
        OutPanel.setBorder(BorderFactory.createTitledBorder("OutPut Folder"));
        OutFolChooser=new JFileChooser();
        OutFolChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        OutFolChooser.setAcceptAllFileFilterUsed(false);
        OutFileButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int returnVal = OutFolChooser.showOpenDialog(innerPanel);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = OutFolChooser.getSelectedFile();
				OutDir = file.getPath();
		        OutField.setText(""+ OutFolChooser.getSelectedFile());
	           	if( OutField.getText() != null) jcb.setEnabled(true);
            }
         }
      	});
        
      	//Password
      	PassField=new JPasswordField(28);
      	ConfPassField=new JPasswordField(29);
      	PassPanel=new JPanel(new FlowLayout());
      	PassPanel.setBorder(BorderFactory.createTitledBorder("Password&Confirm"));
      	PassPanel.add(PassField);
      	PassPanel.add(ConfPassField);
      	
      	innerPanel.add(InpPanel);
      	innerPanel.add(MsgPanel);      	
      	innerPanel.add(OutPanel);
      	innerPanel.add(PassPanel);      	
        lowerPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        Embed=new JButton("EMBED");
        Embed.setMnemonic(KeyEvent.VK_E);
        Embed.setBounds(100,100,40,30);
        Embed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String cfile = FileField.getText();
                String mfile = MsgField.getText();
                String sfile = OutField.getText();
                String pwd = new String(PassField.getPassword());
                String cpwd = new String(ConfPassField.getPassword());
                File cf = new File(cfile);
                File mf = new File(mfile);
                File sf = new File(sfile);
                if(cfile.endsWith(".jpg") && sfile.endsWith(".jpg")) {
					JOptionPane.showMessageDialog(null,"This version can't process both .jpg files as input","Error",JOptionPane.ERROR_MESSAGE);
				}
                else if(cfile.equals("")) {
                    JOptionPane.showMessageDialog(null,"Please choose cover file","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(!cf.exists()) {
                    JOptionPane.showMessageDialog(null,"Cover file doesn't exist","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(mfile.equals("")) {
                    JOptionPane.showMessageDialog(null,"Please choose text file","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(!mf.exists()) {
                    JOptionPane.showMessageDialog(null,"Text file doesn't exist","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(sfile.equals("")) {
                    JOptionPane.showMessageDialog(null,"Please choose stego file","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(sf.isDirectory()) {
                    JOptionPane.showMessageDialog(null,"Please choose stego file type","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(pwd.equals("") || cpwd.equals("")) {
                    JOptionPane.showMessageDialog(null,"Please enter passwords","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if(pwd.equals(cpwd)) {
                        String opt[] = 
                        new String[]{"embed",FileField.getText(),MsgField.getText(),OutField.getText(),pwd};
                        new LSBAlgo(opt);
                        if(LSBStatus.embedSuccess) {
                            String msg = "Data embedded successfully in \""+OutField.getText()+"\"...";
                            JOptionPane.showMessageDialog(null,msg,"Success",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            String msg = "Error occured while embedding data in \""+OutField.getText()+"\"...\n";
                            JOptionPane.showMessageDialog(null,msg,"Error",JOptionPane.ERROR_MESSAGE);
                        }
                  	}
                  	else {
                  	    JOptionPane.showMessageDialog(null,"Password & Confirm password doesn't match.","Error",JOptionPane.ERROR_MESSAGE);
                  	}
              	}
            }
        });
        setLayout(new BorderLayout());
        lowerPanel.add(Embed);
        add(lowerPanel,BorderLayout.SOUTH);
        add(HeaderLabel,BorderLayout.NORTH);
        add(innerPanel,BorderLayout.CENTER);
	}
}
