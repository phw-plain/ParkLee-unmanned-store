package code;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;

public class Start extends Setting {
	private JPanel controlPanel;
		
	public Start(){
		i = new ImageIcon("src/img/main.png");
		i = imageSetSize(i, width, height);
		
		prepareGUI();
    }
	
	private void prepareGUI() {
		// 작업 경로 확인용
		// String path = System.getProperty("user.dir"); 
		// System.out.println("현재 작업 경로: " + path);
        
		// Frame 기본 세팅
		startFrame = new Frame("박리다매 무인가게"); 
		startFrame.setSize(width, height);
		startFrame.setLocationRelativeTo(null);
		startFrame.setResizable(resizable);
		startFrame.setVisible(true);
		startFrame.addWindowListener(new WindowAdapter() {
	       public void windowClosing(WindowEvent windowEvent) {
	          System.exit(0);
	       }
	    });
		startFrame.setIconImage(img.getImage());	    // Icon 변경
		
		// 화면 크기
		width = startFrame.getWidth();
		height = startFrame.getHeight();

		startFrame.setVisible(false);
		
	    JPanel panel = new JPanel(new BorderLayout()) {
            public void paintComponent(Graphics g) {
                g.drawImage(i.getImage(), 0, 0, null);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
	   
	    JPanel header = new JPanel(new BorderLayout());
	    header.setBackground(maincolor);
	    JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, width+500, 10));
	    footer.setBackground(new Color(255,255,255,0));
	    footer.setBorder(BorderFactory.createEmptyBorder(height/2, 0, 0, 0));

	    ImageIcon rebtn = new ImageIcon("src/img/setting.png");
	    
	    // 버튼 생성
	    RoundedButton loginbtn = new RoundedButton(" 로그인 ");
	    RoundedButton joinbtn = new RoundedButton("회원가입");
	    JButton resolution = new JButton("", rebtn);
	    
	    // 버튼 설정
	    loginbtn.setFocusPainted(false); 
	    loginbtn.setFont(font2);
	    joinbtn.setFocusPainted(false); 
	    joinbtn.setFont(font2);
	    resolution.setContentAreaFilled(false);
	    resolution.setBorderPainted(false);
	    resolution.setFocusPainted(false);	//choice
	    
	    // 버튼 클릭 이벤트
	    resolution.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	    	   new SetWindow();
	       }
	    });
	    loginbtn.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           try {
				new login();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} //프레임 전환
	           startFrame.dispose();
	       }
	    });
	      
	    joinbtn.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new join(); //프레임 전환
	           startFrame.dispose();
	       }
	    });

	    header.add(resolution, BorderLayout.EAST);
	    footer.add(loginbtn);
	    footer.add(joinbtn);
	    
	    panel.add(header, BorderLayout.NORTH);
	    panel.add(footer, BorderLayout.CENTER);

	    startFrame.add(panel);
	    startFrame.setVisible(true);
	}

}