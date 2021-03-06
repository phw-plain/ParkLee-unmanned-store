package code;
import java.awt.*;

import java.awt.event.*;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.swing.*;
import javax.swing.event.*;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import firebase.*;

class join extends Setting{
	private Frame mainFrame;
	private JPanel subpanel;
	private JLabel headerLabel;
	private Panel form;
	private Button b1;
	App Firebase = new App();
	Firebase_join joinKey = new Firebase_join();
	 
	// 아이디 중복 체크용 변수
	private boolean idcheck = false;
	
	public join() {
		prepareGUI();
	}
	
	private void prepareGUI() {
		// Frame 에 대한 셋팅
		mainFrame = new Frame("박리다매 무인가게");
		mainFrame.setSize(width, height);
		mainFrame.setResizable(resizable);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
    	mainFrame.setIconImage(img.getImage());	    // Icon 변경
		
		// 상단 제목
		headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setText("회원가입");
		headerLabel.setFont(font1);
		headerLabel.setForeground(title);
		// 회원가입 정보 입력
		form = new Panel(new GridLayout(8,4,0,30));
		form.setPreferredSize(new Dimension(800,400));
		
		JLabel blankL1 = new JLabel();
		JLabel blank = new JLabel();
		JLabel label1 = new JLabel("이름");
		label1.setFont(font3);
		label1.setForeground(fontcolor);
		final JTextField tf1 = new JTextField("", 15);
		tf1.selectAll();
		form.add(blankL1);
		form.add(label1);
		form.add(tf1);
		form.add(blank);
		
		JLabel blankL2 = new JLabel();
		Panel btnlabel = new Panel();
		btnlabel.setLayout(new BorderLayout());
		JButton b1 = new JButton("중복확인");
		b1.setFont(font3);
		b1.setBackground(Color.white);
		btnlabel.add(b1, BorderLayout.WEST);
		JLabel label2 = new JLabel("아이디");
		label2.setFont(font3);
		label2.setForeground(fontcolor);
		final JTextField tf2 = new JTextField("", 15);
		tf2.selectAll();
		form.add(blankL2);
		form.add(label2);
		form.add(tf2);
		form.add(btnlabel);
		
		JLabel blankL3 = new JLabel();
		JLabel blankR3 = new JLabel();
		JLabel label3 = new JLabel("비밀번호");
		label3.setFont(font3);
		label3.setForeground(fontcolor);
		final JTextField tf3 = new JTextField("", 15);
		tf3.selectAll(); // tf2.setEchoChar('*');
		form.add(blankL3);
		form.add(label3);
		form.add(tf3);
		form.add(blankR3);

		JLabel blankL4 = new JLabel();
		JLabel blankR4 = new JLabel();
		JLabel label4 = new JLabel("지점명");
		label4.setFont(font3);
		label4.setForeground(fontcolor);
		final JTextField tf4 = new JTextField("", 15);
		tf4.selectAll(); 
		form.add(blankL4);
		form.add(label4);
		form.add(tf4);
		form.add(blankR4);
		
		JLabel blankL5 = new JLabel();
		JLabel blankR5 = new JLabel();
		JLabel label5 = new JLabel("위치");
		label5.setFont(font3);
		label5.setForeground(fontcolor);
		final JTextField tf5 = new JTextField("", 15);
		tf5.selectAll(); 
		form.add(blankL5);
		form.add(label5);
		form.add(tf5);
		form.add(blankR5);

		JLabel blankL6 = new JLabel();
		JLabel blankR6 = new JLabel();
		JPanel radiobtn = new JPanel();
		JLabel label6 = new JLabel("직원");
		label6.setFont(font3);
		label6.setForeground(fontcolor);
		ButtonGroup group = new ButtonGroup();
		final JRadioButton ra1 = new JRadioButton("유", true);
		ra1.setFont(font3);
		ra1.setForeground(fontcolor);
		ra1.setBackground(background);
		JRadioButton ra2 = new JRadioButton("무", false);
		ra2.setFont(font3);
		ra2.setForeground(fontcolor);
		ra2.setBackground(background);
		
		group.add(ra1);
		group.add(ra2);

		radiobtn.add(ra1);
		radiobtn.add(ra2);
		radiobtn.setBorder(BorderFactory.createEmptyBorder(-10, 0, 0, 0));
		radiobtn.setBackground(background);
		
		form.add(blankL6);
		form.add(label6);
		form.add(radiobtn);
		form.add(blankR6);
		
		JLabel blankL7 = new JLabel();
		JLabel blankR7 = new JLabel();
		final JLabel label7 = new JLabel("직원 월급");
		label7.setFont(font3);
		label7.setForeground(fontcolor);
		final JTextField tf7 = new JTextField("", 15);
		tf7.selectAll(); 
		form.add(blankL7);
		form.add(label7);
		form.add(tf7);
		form.add(blankR7);

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
	    
	    
	    // 아이디 중복 체크
	    b1.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
	 			try {
					Firebase_join.getQuoteFormFirestore(tf2.getText());
				} catch (ExecutionException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	if(tf2.getText().length() == 0) {
		    		JOptionPane.showMessageDialog(null
							, "아이디를 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} 
		    	else if(existence==true) {
					JOptionPane.showMessageDialog(null
							, "사용불가능한 아이디 입니다."
		                    , "박리다매 무인가게"
		                    , JOptionPane.ERROR_MESSAGE
		            );
		            idcheck = false;
	            } else if(!is.isString1(tf2.getText())) {            
		            JOptionPane.showMessageDialog(null
		            		, "사용불가능한 아이디 입니다."
		            		, "박리다매 무인가게"
		                   	, JOptionPane.ERROR_MESSAGE
		           	);
		            idcheck = false;
		        } else if(tf2.getText().length() < 5 || tf2.getText().length() > 12) {
 					JOptionPane.showMessageDialog(null
							, "사용불가능한 아이디 입니다."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
 					);
 	                idcheck = false;
 				} else {
					JOptionPane.showMessageDialog(null
							, "사용가능한 아이디 입니다."
							, "박리다매 무인가게"
							, JOptionPane.PLAIN_MESSAGE
					);
					idcheck = true;
				}
		    }
		});
	    
	    // 직원 유무 radio 이벤트
	    ra1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label7.setVisible(true);
				tf7.setVisible(true);
			}
		});
		ra2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label7.setVisible(false);
				tf7.setVisible(false);
			}
		});
	    
	    // 확인 버튼 클릭 이벤트
 		check.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
	 			// 데이터 저장 변수 선언
	 			setName(tf1.getText());
				setId(tf2.getText());
				setPw(tf3.getText());
				setBrand(tf4.getText());
				setLocation(tf5.getText());
				setEmp((ra1.isSelected() == true) ? true : false);
				setEmpsal((tf7.getText().length() != 0) ? Integer.parseInt(tf7.getText()) : 0);
	 			boolean emp = (ra1.isSelected() == true) ? true : false;
	 		
	 			// 회원가입 예외 처리
	 			if(getName().length() == 0) {				
	 				JOptionPane.showMessageDialog(null
	 					, "이름을 입력해주세요."
	 					, "박리다매 무인가게"
	 					, JOptionPane.ERROR_MESSAGE
	 				);
	 			} else if(getName().length() > 12) {
	 				JOptionPane.showMessageDialog(null
	 					, "이름이 너무 깁니다. 12자 이내로 입력해 주세요."
	 					, "박리다매 무인가게"
	 					, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(!is.isString2(getName())) {           
 					JOptionPane.showMessageDialog(null
	 		            , "이름에 특수문자 또는 공백을 포함하고 있습니다.\n해당 문자를 제외하고 다시 입력해 주세요."
	 		            , "박리다매 무인가게"
	 		            , JOptionPane.ERROR_MESSAGE
	                );
 	            } else if(getId().length() == 0) {			
 					JOptionPane.showMessageDialog(null
 						, "아이디를 입력해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 					idcheck = false;
 				} else if(getId().length() < 5) {
 					JOptionPane.showMessageDialog(null
						, "아이디가 너무 짧습니다. 5~12자 이내로 입력해 주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 	                idcheck = false;
 				} else if(getId().length() > 12) {
 					JOptionPane.showMessageDialog(null
						, "아이디가 너무 깁니다. 5~12자 이내로 입력해 주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 	                idcheck = false;
 				} else if(!is.isString1(getId())) {           
 					JOptionPane.showMessageDialog(null
                        , "아이디는 영숫자를 사용하여 입력해 주세요."
                        , "박리다매 무인가게"
                       	, JOptionPane.ERROR_MESSAGE
					);
	               	idcheck = false;
 	            } else if(!idcheck) {
 	            	JOptionPane.showMessageDialog(null
						, "아이디 중복 체크를 해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 	                idcheck = false;
 				} else if(getPw().length() == 0) {
 					JOptionPane.showMessageDialog(null
						, "비밀번호를 입력해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(getPw().length() < 8) {
 					JOptionPane.showMessageDialog(null
						, "비밀번호가 너무 짧습니다. 8~16자 이내로 입력해 주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(getPw().length() > 16) {
 					JOptionPane.showMessageDialog(null
						, "비밀번호가 너무 깁니다. 8~16자 이내로 입력해 주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(!is.isString1(getPw())) {         
 					JOptionPane.showMessageDialog(null
 						, "비밀번호는 영숫자를 사용하여 입력해 주세요."
 						, "박리다매 무인가게"
 						, JOptionPane.ERROR_MESSAGE
					);
 	            } else if(getBrand().length() == 0) {		
 					JOptionPane.showMessageDialog(null
						, "지점명을 입력해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(getBrand().length() > 16) {
 					JOptionPane.showMessageDialog(null
						, "지점명이 너무 깁니다. 16자 이내로 입력해 주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(getLocation().length() == 0) {
 					JOptionPane.showMessageDialog(null
						, "매출대비지급액을 입력해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(getLocation().length() > 16) {
 					JOptionPane.showMessageDialog(null
						, "지점명이 너무 깁니다. 16자 이내로 입력해 주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(ra1.isSelected() == true && (tf7.getText()).length() == 0) {	
					JOptionPane.showMessageDialog(null
						, "직원 월급을 입력해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
					);
 				} else if(ra1.isSelected() == true && !is.isNum(tf7.getText())) {
 					JOptionPane.showMessageDialog(null
						, "직원 월급은 숫자만 입력해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
	 			} else {
		 			setLocation(tf5.getText());
		 			setEmp((ra1.isSelected() == true) ? true : false);
		 			setEmpsal((emp) ? Integer.parseInt(tf7.getText()) : 0);
		 		
		 			// 데이터 저장
		 			
	 				JOptionPane.showMessageDialog(null
	 						, "회원가입을 축하합니다!"
	 						, "박리다매"
	 						, JOptionPane.PLAIN_MESSAGE
	 				);
	 				try {
						joinKey.join();
					}  catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	 				Firebase_login a= new Firebase_login();
	 				a.login();
	 				new Start();
	 				
		    		mainFrame.dispose();
	 			}
	 		}
	 	});
 		
	    // 취소 버튼 클릭 이벤트
	    cancel.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new Start(); // 프레임 전환
	           mainFrame.setVisible(false);
	       }
	    });

	    JPanel center = new JPanel();
	    center.add(form);
	    center.setBackground(background);
	    int margin = (height < 1000) ? (height/15) : 50+(height/15);
	    center.setBorder(BorderFactory.createEmptyBorder(margin, 0, 0, 0));
	    
	    subpanel = new JPanel();
	    subpanel.setBackground(background);
	    subpanel.setLayout(new BorderLayout(0, 0));
	    subpanel.setBorder(BorderFactory.createEmptyBorder(50+(height/15), 0, 50+(height/15), 0));
	    subpanel.add(headerLabel, BorderLayout.NORTH);
	    subpanel.add(center, BorderLayout.CENTER);
	    subpanel.add(btns, BorderLayout.SOUTH);
	      
	    mainFrame.add(subpanel);
	    mainFrame.setVisible(true);
	}
}