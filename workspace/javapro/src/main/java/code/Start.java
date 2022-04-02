package code;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;

public class Start extends Setting {
	private Frame mainFrame;
	private JPanel controlPanel;
		
	public Start(){
		i = new ImageIcon("src/img/main.png");
		im = i.getImage();
		
		prepareGUI();
    }
	
	private void prepareGUI() {
		// �۾� ��� Ȯ�ο�
		// String path = System.getProperty("user.dir"); 
		// System.out.println("���� �۾� ���: " + path);
        
		// Frame �⺻ ����
		mainFrame = new Frame("�ڸ��ٸ� ���ΰ���"); 
	    mainFrame.setSize(1280 ,1024);
		mainFrame.setLocationRelativeTo(null);
	    mainFrame.setResizable(false);
	    mainFrame.setVisible(true);
	    mainFrame.addWindowListener(new WindowAdapter() {
	       public void windowClosing(WindowEvent windowEvent) {
	          System.exit(0);
	       }
	    });
    	mainFrame.setIconImage(img.getImage());	    // Icon ����
	    
		// ȭ�� ũ��
		width = mainFrame.getWidth();
		height = mainFrame.getHeight();
	    
		//System.out.println(width + " " + height);
		
	    MyPanel panel = new MyPanel();
	    panel.setLayout(new FlowLayout(FlowLayout.CENTER, width+500, 10));
	   
	    // ��ư ����
	    RoundedButton loginbtn = new RoundedButton("  �α���  ");
	    RoundedButton joinbtn = new RoundedButton("ȸ������");
	    
	    // ��ư ����
	    loginbtn.setFocusPainted(false); 
	    loginbtn.setFont(font2);
	    joinbtn.setFocusPainted(false); 
	    joinbtn.setFont(font2);
	    
	    // ��ư Ŭ�� �̺�Ʈ
	    loginbtn.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new login(); //������ ��ȯ
	           mainFrame.setVisible(false);
	       }
	    });
	      
	    joinbtn.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new join(); //������ ��ȯ
	           mainFrame.setVisible(false);
	       }
	    });

	    panel.add(loginbtn);
	    panel.add(joinbtn);
	    panel.setBorder(BorderFactory.createEmptyBorder((int)(height/1.8), 0, 0, 0));
	    
	    mainFrame.add(panel);
	    mainFrame.setVisible(true);
	}
	
    class MyPanel extends JPanel{   
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(im,0,0,getWidth(),getHeight(),this);
        }
    }
}