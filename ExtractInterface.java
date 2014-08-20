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
import java.io.File;
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


public class ExtractInterface extends JPanel{
	private JPanel innerPanel,lowerPanel,InpPanel,PassPanel,OutPanel;
	private JFileChooser InimgChooser;
	private JFileChooser OutFolChooser;
	private JButton InputFileButton;
	private JButton OutFileButton;	
	private JButton Extract;
	private JTextField FileField;
	private JTextField OutField;	
	private JLabel StatusLabel;
	private JPasswordField PassField;
	private JPasswordField ConfPassField;
	public ExtractInterface(){
		InimgChooser=new JFileChooser();
        StatusLabel=new JLabel("******StegTool-EXTRACT******",JLabel.CENTER);
        InputFileButton=new JButton("Browse");
        innerPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,25,0));
		InpPanel=new JPanel(new FlowLayout());
        FileField=new JTextField(50);
        InpPanel.add(FileField);
        InpPanel.add(InputFileButton);
        InpPanel.setBorder(BorderFactory.createTitledBorder("Stego File"));
        
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
      	//For Password
      	PassField=new JPasswordField(56);
      	PassPanel=new JPanel(new FlowLayout());
      	PassPanel.setBorder(BorderFactory.createTitledBorder("Password"));
      	PassPanel.add(PassField);
      	
        
        //select Output Directory
		OutField=new JTextField(50);
		OutFileButton=new JButton("Browse");
		OutPanel=new JPanel(new FlowLayout());
        OutPanel.add(OutField);
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
				java.io.File file = OutFolChooser.getSelectedFile();
		        OutField.setText("" 
		           + OutFolChooser.getSelectedFile());
            }
         }
      	});
      	
      	innerPanel.add(InpPanel);
      	innerPanel.add(OutPanel);
      	innerPanel.add(PassPanel);      	
        lowerPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        Extract=new JButton("EXTRACT");
        Extract.setBounds(100,100,40,30);
        Extract.setMnemonic(KeyEvent.VK_E);
        Extract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String sfile = FileField.getText();
                String mfile = OutField.getText();
                String pwd = new String(PassField.getPassword());
                File sf = new File(sfile);
                File mf = new File(mfile);
                if(sfile.equals("")) {
                    JOptionPane.showMessageDialog(null,"Please choose stego file","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(!sf.exists()) {
                   JOptionPane.showMessageDialog(null,"Stego file doesn't exist","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(mfile.equals("")) {
                    JOptionPane.showMessageDialog(null,"Please choose output text file path","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(!mf.exists()) {
                    JOptionPane.showMessageDialog(null,"Please browse valid path","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(pwd.equals("")) {
                    JOptionPane.showMessageDialog(null,"Please enter password","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    String opt[] = new String[]{"extract",sfile,mfile+"/StegTool_Extract_Output.txt","",pwd};
                    new LSBAlgo(opt);
                    if(LSBStatus.extractSuccess) {
                        String msg = "Data extracted and written to \""+mfile+"/StegTool_Extract_Output.txt\" successfully...";
                        JOptionPane.showMessageDialog(null,msg,"Success",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        setLayout(new BorderLayout());
        lowerPanel.add(Extract);
        add(lowerPanel,BorderLayout.SOUTH);
        add(StatusLabel,BorderLayout.NORTH);
        add(innerPanel,BorderLayout.CENTER);
	}
}
