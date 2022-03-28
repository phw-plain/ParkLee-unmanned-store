package code;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;

class login extends Setting {
	private Frame mainFrame;
	private JPanel subpanel;
	private MyPanel panelLeft;
	private JPanel panelRight;
	private JLabel headerLabel;
	private Button b1;
	
	public login() {
		i = new ImageIcon("src/img/login_img.png");
		im = i.getImage();
		
	    prepareGUI();
	}
	
	private void prepareGUI() {
		// Frame 기본 셋팅
		mainFrame = new Frame("박리다매 무인가게");
	    mainFrame.setSize(1280 ,1024);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
	    mainFrame.setVisible(true);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
    	mainFrame.setIconImage(img.getImage());	    // Icon 변경

		// Left image
		panelLeft = new MyPanel();
	    
		// Left title
		headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setVerticalAlignment(JLabel.EAST);
		headerLabel.setText("로그인");
		headerLabel.setFont(font1);
		headerLabel.setForeground(title);

		// Right content
		Panel p = new Panel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));

		Panel form = new Panel(new GridLayout(3,2,0,5));
		
		JLabel label1 = new JLabel("아이디");
		label1.setFont(font3);
		final TextField tf1 = new TextField("", 15);
		tf1.selectAll();
		form.add(label1);
		form.add(tf1);

		JLabel label2 = new JLabel("비밀번호");
		label2.setFont(font3);
		final TextField tf2 = new TextField("", 15);
		tf2.setEchoChar('●');
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
	    check.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   // 데이터 저장 변수 선언
		    	   String input_id = tf1.getText();
		    	   String input_pw = tf2.getText();
					
		    	   // 데이터 불러오기
		    	   String id = "admin";
		    	   String pw = "admin1234";
		    	   
		    	   if(input_id.length() == 0) {
		    		   JOptionPane.showMessageDialog(null
		    				   , "아이디를 입력해주세요."
		    				   , "박리다매 무인가게"
		    				   , JOptionPane.ERROR_MESSAGE
		    				   );
					} else if(input_pw.length() == 0) {
						JOptionPane.showMessageDialog(null
								, "비밀번호를 입력해주세요."
								, "박리다매 무인가게"
								, JOptionPane.ERROR_MESSAGE
								);
					} else if(id == null|| pw == null) {
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
						new Manage();
						mainFrame.setVisible(false);
					}
		       }
	    });
	    cancel.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new Start(); // 프레임 전환
	           mainFrame.setVisible(false);
	       }
	    });
	
	    p.add(form);
	    p.add(btns);
	    p.setBackground(background);
	     
		panelRight = new JPanel();
		panelRight.setBackground(background);
		panelRight.setLayout(new GridLayout(3, 1));
		panelRight.add(headerLabel);
		panelRight.add(p);
		
		subpanel = new JPanel();
		subpanel.setLayout(new GridLayout(1, 2));
		subpanel.add(panelLeft, BorderLayout.SOUTH);
		subpanel.add(panelRight, BorderLayout.NORTH);
	     
	    mainFrame.add(subpanel);
	    mainFrame.setVisible(true);
	}
	
    class MyPanel extends JPanel{   
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(im,0,0,getWidth(),getHeight(),this);
        }
    }
}