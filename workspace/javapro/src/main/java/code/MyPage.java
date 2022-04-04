package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MyPage extends Setting {
	public JPanel panel;

	public JPanel Read;
	private JPanel Write;

	public JButton homebtn1;
	public JButton homebtn2;
	
	public MyPage() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);
		homebtn1 = new JButton("", logo);
		homebtn2 = new JButton("", logo);
		Read();
		Write();
	}

	public void setVisible(boolean tf) {
		panel.setVisible(tf);
	}

	public void Read() {
		// Read 세팅
		Read = new JPanel();
		Read.setBackground(background);
		Read.setLayout(new BorderLayout());
		
		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(header_back);
		
		// home 버튼 생성
		homebtn1.setRolloverIcon(logo_over); 	// rolloverIcon용 이미지 등록
		homebtn1.setPressedIcon(img); 	// rolloverIcon용 이미지 등록
		homebtn1.setContentAreaFilled(false); 	// 배경 채우기
		homebtn1.setBorderPainted(false); 		// 외각선
		homebtn1.setFocusPainted(false); 		// 선택 외각선

		JPanel text = new JPanel(new GridLayout(2, 1, 0, -50));
		text.setBackground(header_back);
		
		JLabel title = new JLabel("마이페이지");
		title.setFont(font2);
		JLabel subtitle = new JLabel(brand + "점 " + name + "님 환영합니다.");
		subtitle.setFont(font3);

		text.add(title);
		text.add(subtitle);
		
		header.add(homebtn1, BorderLayout.WEST);
		header.add(text, BorderLayout.CENTER);
		
		// 정보 보기
		JPanel center = new JPanel(new GridLayout(2, 1));
		center.setBackground(background);
		center.setBorder(BorderFactory.createEmptyBorder(height/7, 0, 0, 0));
		JPanel datas = new JPanel();
		datas.setBackground(background);
		JPanel data = new JPanel(new GridLayout(7, 2, 180, 27));
		data.setBackground(background);
		JPanel btn = new JPanel();
		btn.setBackground(background);

		JLabel L[] = new JLabel[6];
		JLabel R[] = new JLabel[6];
		
		L[0] = new JLabel("이름");
		L[1] = new JLabel("아이디");
		L[2] = new JLabel("비밀번호");
		L[3] = new JLabel("지점명");
		L[4] = new JLabel("직원");
		L[5] = new JLabel("직원 월급");
		
		R[0] = new JLabel(name);
		R[1] = new JLabel(id);
		R[2] = new JLabel(pw);
		R[3] = new JLabel(brand);
		R[4] = new JLabel((emp)?"유":"무");
		R[5] = new JLabel(Integer.toString(empsal));
		
		for(int i=0; i<6; i++ ) {
			R[i].setFont(font3);
			L[i].setFont(font3);
			data.add(L[i]);
			data.add(R[i]);
		}
		
		datas.add(data);
		
		RoundedButton change = new RoundedButton("정보수정");
		change.setFont(font3);
		btn.add(change);
		btn.setBorder(BorderFactory.createEmptyBorder(height/10, 0, 0, 0));
		
		// 버튼 이벤트
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Read.setVisible(false); // 화면 전환
				
				// reloading
				Write.setVisible(false);
				Write();

				Write.setVisible(true);
			}
		});
		
		center.add(datas, BorderLayout.CENTER);
		center.add(btn, BorderLayout.SOUTH);
		
		Read.add(header, BorderLayout.NORTH);
		Read.add(center, BorderLayout.CENTER);

		Read.setVisible(true);
		panel.add(Read);

	}

	private void Write() {
		// Write 세팅
		Write = new JPanel();
		Write.setBackground(background);
		Write.setLayout(new BorderLayout());
		
		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(header_back);
		
		// home 버튼 생성
		homebtn2.setRolloverIcon(logo_over); 	// rolloverIcon용 이미지 등록
		homebtn2.setContentAreaFilled(false); 	// 배경 채우기
		homebtn2.setBorderPainted(false); 		// 외각선
		homebtn2.setFocusPainted(false); 		// 선택 외각선
		JPanel text = new JPanel(new GridLayout(2, 1, 0, -50));
		text.setBackground(header_back);
		
		JLabel title = new JLabel("마이페이지");
		title.setFont(font2);
		JLabel subtitle = new JLabel(brand + "점 " + name + "님 환영합니다.");
		subtitle.setFont(font3);

		text.add(title);
		text.add(subtitle);
		
		header.add(homebtn2, BorderLayout.WEST);
		header.add(text, BorderLayout.CENTER);
		
		// 정보 보기
		JPanel center = new JPanel(new GridLayout(2, 1));
		center.setBackground(background);
		center.setBorder(BorderFactory.createEmptyBorder(height/7, 0, 0, 0));
		JPanel datas = new JPanel();
		datas.setBackground(background);
		JPanel data = new JPanel(new GridLayout(9, 2, 180, 15));
		data.setBackground(background);
		
		JPanel btns = new JPanel();
		btns.setBackground(background);
		JPanel btn = new JPanel(new GridLayout(1, 2, 30, 0));
		btn.setBackground(background);

		JLabel L1 = new JLabel("이름");
		L1.setFont(font3);
		JLabel L2 = new JLabel("아이디");
		L2.setFont(font3);
		JLabel L3 = new JLabel("비밀번호");
		L3.setFont(font3);
		JLabel L4 = new JLabel("지점명");
		L4.setFont(font3);
		JLabel L5 = new JLabel("직원");
		L5.setFont(font3);
		JLabel L6 = new JLabel("직원 월급");
		L6.setFont(font3);
		
		TextField R1 = new TextField(name);
		R1.setFont(font6);
		JLabel R2 = new JLabel(id);
		R2.setFont(font6);
		TextField R3 = new TextField(pw);
		R3.setFont(font6);
		TextField R4 = new TextField(brand);
		R4.setFont(font6);
		TextField R6 = new TextField(Integer.toString(empsal));
		R6.setFont(font6);
		
		Panel staff = new Panel();
		CheckboxGroup g = new CheckboxGroup();
		final JRadioButton ra1 = new JRadioButton("유", (emp)? true:false);
		ra1.setFont(font6);
		ra1.setBackground(background);
		JRadioButton ra2 = new JRadioButton("무", (emp) ? false:true);
		ra2.setFont(font6);
		ra2.setBackground(background);
		ButtonGroup group = new ButtonGroup();
		if(emp == false) {
			L6.setVisible(false);
			R6.setVisible(false);
		}
		
		group.add(ra1);
		group.add(ra2);
		staff.add(ra1);
		staff.add(ra2);
		
		// 직원 유무 radio 이벤트
	    ra1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L6.setVisible(true);
				R6.setVisible(true);
			}
		});
		ra2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L6.setVisible(false);
				R6.setVisible(false);
			}
		});
		
		data.add(L1);
		data.add(R1);
		data.add(L2);
		data.add(R2);
		data.add(L3);
		data.add(R3);
		data.add(L4);
		data.add(R4);
		data.add(L5);
		data.add(staff);
		data.add(L6);
		data.add(R6);
		
		datas.add(data);
		
		RoundedButton check = new RoundedButton("확인");
		check.setFont(font3);
		RoundedButton cancel = new RoundedButton("취소");
		cancel.setFont(font3);
		btn.add(check);
		btn.add(cancel);
		btn.setBorder(BorderFactory.createEmptyBorder(height/10, 0, 0, 0));
		
		btns.add(btn);
		
		// 버튼 이벤트
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 예외 처리
				if(R1.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "이름을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R1.getText().length() > 12) {
	 				JOptionPane.showMessageDialog(null
		 					, "이름이 너무 깁니다. 12자 이내로 입력해 주세요."
		 					, "박리다매 무인가게"
		 					, JOptionPane.ERROR_MESSAGE
	 					);
 				} else if(!is.isString2(R1.getText())) {           
 					JOptionPane.showMessageDialog(null
 							, "이름에 특수문자 또는 공백을 포함하고 있습니다.\n해당 문자를 제외하고 다시 입력해 주세요."
 							, "박리다매 무인가게"
 							, JOptionPane.ERROR_MESSAGE
	                );
 	            } else if(R3.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "비밀번호를 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R3.getText().length() < 8) {
 					JOptionPane.showMessageDialog(null
							, "비밀번호가 너무 짧습니다. 8~16자 이내로 입력해 주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(R3.getText().length() > 16) {
 					JOptionPane.showMessageDialog(null
							, "비밀번호가 너무 깁니다. 8~16자 이내로 입력해 주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(!is.isString1(R3.getText())) {         
 					JOptionPane.showMessageDialog(null
	 						, "비밀번호는 영숫자를 사용하여 입력해 주세요."
	 						, "박리다매 무인가게"
	 						, JOptionPane.ERROR_MESSAGE
					);
 	            } else if(R4.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "지점명을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R4.getText().length() > 16) {
 					JOptionPane.showMessageDialog(null
						, "지점명이 너무 깁니다. 16자 이내로 입력해 주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(ra1.isSelected() && (R6.getText()).length() == 0) {		// 예외 처리 직원 월급
					JOptionPane.showMessageDialog(null
							, "직원 월급을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(ra1.isSelected() && !is.isNum(R6.getText())) {
					JOptionPane.showMessageDialog(null
							, "직원 월급은 숫자만 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else {
					int n = JOptionPane.showConfirmDialog(
							null
							, "변경사항을 저장하시겠습니까?"
							, "박리다매 무인가게"
							, JOptionPane.YES_NO_OPTION
							, JOptionPane.WARNING_MESSAGE
					);
					
					if(n == 0) {
						// 변경 데이터 저장
						name = R1.getText();
						id = R2.getText();
						pw = R3.getText();
						brand = R4.getText();
						emp = (ra1.isSelected() == true) ? true : false;
						empsal = (R6.getText().length() != 0) ? Integer.parseInt(R6.getText()) : 0;

						// reloading
						panel.remove(0);   
						Read.setVisible(true);
						Read();
						
						JOptionPane.showMessageDialog(null
								, "정상적으로 정보 수정 완료!"
								, "박리다매 무인가게"
								, JOptionPane.PLAIN_MESSAGE
						);

						print();
						
						Read.setVisible(true);
						Write.setVisible(false);
					}
				}
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Read.setVisible(true); // 화면 전환
				Write.setVisible(false);
			}
		});
		
		center.add(datas, BorderLayout.CENTER);
		center.add(btns, BorderLayout.SOUTH);
		
		Write.add(header, BorderLayout.NORTH);
		Write.add(center, BorderLayout.CENTER);

		Write.setVisible(false);
		panel.add(Write);
	}
}