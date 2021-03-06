package code;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.event.*;

import firebase.Firebase_login;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.GVTTreeBuilderEvent;

class login extends Setting {
	private Frame mainFrame;
	private JPanel subpanel;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JLabel headerLabel;
	private Button b1;
	
	private JTextField tf1;
	private JTextField tf2;
	
	public login() throws MalformedURLException {
	    prepareGUI();
	}
	
	private void prepareGUI() throws MalformedURLException {
		// Frame 기본 셋팅
		mainFrame = new Frame("박리다매 무인가게");
	    mainFrame.setSize(width ,height);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(resizable);
	    mainFrame.setVisible(true);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
    	mainFrame.setIconImage(img.getImage());	    // Icon 변경

		// Left image
    	// SVG 이미지 불러오기
    	String path = "file:///" + System.getProperty("user.dir") + "/src/img/";
    	path += "login_img.svg";
    	
    	JSVGCanvas svgCanvas = new JSVGCanvas();
    	svgCanvas.setURI(new URL(path).toString());
    	svgCanvas.setBackground(new Color(3,60,89));
	    
		// Left title
		headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setVerticalAlignment(JLabel.EAST);
		headerLabel.setText("로그인");
		headerLabel.setFont(font1);
		headerLabel.setForeground(title);

		// Right content
		panelRight = new JPanel(new GridLayout(3, 1));
		panelRight.setBackground(background);
		panelRight.setBorder(BorderFactory.createEmptyBorder(150, 0, 150, 0));

		int margin = (height > 1000) ? 80 : 40;
		int margin2 = (height > 1000) ? 150 : 100;
		
		JPanel form = new JPanel(new GridLayout(2,2,0,10));
		form.setBackground(background);
		form.setBorder(BorderFactory.createEmptyBorder(margin, margin2, margin, margin2));
		
		JLabel label1 = new JLabel("아이디");
		label1.setFont(font3);
		label1.setForeground(fontcolor);
		tf1 = new JTextField("", 15);
		
		tf1.selectAll();
		form.add(label1);
		form.add(tf1);

		JLabel label2 = new JLabel("비밀번호");
		label2.setFont(font3);
		label2.setForeground(fontcolor);
		tf2 = new JTextField("", 15);
		//tf2.setEchoChar('●');
		form.add(label2);
		form.add(tf2);

		// 버튼 생성
		JPanel btns = new JPanel(new FlowLayout());

		RoundedButton check = new RoundedButton("확인");
		RoundedButton cancel = new RoundedButton("취소");
	      
		// 버튼 설정
		check.setFocusPainted(false); 
	    check.setFont(font3);
	    cancel.setFocusPainted(false); 
	    cancel.setFont(font3);
		
	    btns.add(check);
	    btns.add(cancel);
	    btns.setBackground(background);
	    
	    // 버튼 클릭 이벤트
	    tf1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		tf2.requestFocus();
	    	}
	    });
	    tf2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		event();
	    	}
	    });
	    check.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		event();
			}
	    });
	    cancel.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	    	   new Start();
	    	   
	    	   // 프레임 전환
	    	   mainFrame.setVisible(false);
	       }
	    });
	     
		panelRight.add(headerLabel);
		panelRight.add(form);
		panelRight.add(btns);
		
		subpanel = new JPanel(new GridLayout(1, 2));
		subpanel.add(svgCanvas);
		subpanel.add(panelRight);
	     
	    mainFrame.add(subpanel);
	    mainFrame.setVisible(true);
	}
	
	private void event() {
		// 데이터 저장 변수 선언
 	  setId(tf1.getText());
 	  setPw(tf2.getText());
 	  Firebase_login login = new Firebase_login();
 	   

 	   if(getId().length() == 0) {
 		   JOptionPane.showMessageDialog(null
 				   , "아이디를 입력해주세요."
 				   , "박리다매 무인가게"
 				   , JOptionPane.ERROR_MESSAGE
 				   );
			} else if(getPw().length() == 0) {
				JOptionPane.showMessageDialog(null
						, "비밀번호를 입력해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
						);
			} else if(login.login()==false) {
				JOptionPane.showMessageDialog(null
						, "아이디 또는 비밀번호 입력 오류. 다시 한번 확인 해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
						);
			} else {
				JOptionPane.showMessageDialog(null
					, "환영합니다!"
					, "박리다매 무인가게"
					, JOptionPane.PLAIN_MESSAGE
				);
				
				// 테스트 계정 확인용
		    	print();
						
				new Manage(getId(), getPw());
	    		mainFrame.dispose();
			}
	}
	
    class MyPanel extends JPanel{   
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(im,0,0,getWidth(),getHeight(),this);
        }
    }
}