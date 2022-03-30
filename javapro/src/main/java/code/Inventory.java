package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Inventory extends Setting {
	public JPanel panel;

	private JPanel View;
	private JPanel Modify;
	private JPanel Add;

	public JButton homebtn1;
	public JButton homebtn2;
	public JButton homebtn3;
	
	private JLabel btnView[] = new JLabel[3];
	private JLabel btnModify[] = new JLabel[3];
	private JLabel btnAdd[] = new JLabel[3];
	
	private DefaultTableModel model;
	private Vector<Vector> dataSet = new Vector<>();
	private Vector<String> colNames = new Vector<>();

	private Vector<String> code = new Vector<>();
	private Vector<String> name = new Vector<>();
	private Vector<String> category = new Vector<>();
	private Vector<String> standard = new Vector<>();
	private Vector<Integer> cnt = new Vector<>();
	private Vector<Integer> price = new Vector<>();
	private Vector<String> note = new Vector<>();
	
	public Inventory() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);

		// 제고관리 cloumn 설정
		colNames.add("제품코드");
		colNames.add("상품명");
		colNames.add("분 류");
		colNames.add("규 격");
		colNames.add("수 량");
		colNames.add("금 액");
		colNames.add("비 고");
		
		// 데이터 불러오기	(배열 사용하기)
		code.add("AD1004");
		code.add("BC2075");
		code.add("TR1200");
		name.add("초코송이");
		name.add("칠성사이다");
		name.add("허니버터칩");
		category.add("스낵");
		category.add("음료");
		category.add("스낵");
		standard.add("240g");
		standard.add("1.5L");
		standard.add("600g");
		cnt.add(3);
		cnt.add(15);
		cnt.add(7);
		price.add(1200);
		price.add(2700);
		price.add(1600);
		note.add("/");
		note.add("/");
		note.add("/");
		
		View();
		Modify();
		Add();
	}

	public void setVisible(boolean tf) {
		panel.setVisible(tf);
	}

	private void View() {
		// View 세팅
		View = new JPanel();
		View.setBackground(background);
		View.setLayout(new BorderLayout());

		// navigation
		JPanel nav = new JPanel(new BorderLayout());
		nav.setBackground(background);
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);
		JPanel menubar = new JPanel(new GridLayout(1, 10, 0, 0));
		menubar.setBackground(menu_back);

		// header
		homebtn1 = new JButton("", logo);
		homebtn1.setRolloverIcon(logo_over);
		homebtn1.setContentAreaFilled(false);
		homebtn1.setBorderPainted(false);
		homebtn1.setFocusPainted(false);

		JLabel title = new JLabel("재고 관리");
		title.setFont(font2);

		header.add(homebtn1, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menubar
		btnView[0] = new JLabel("재고 보기");
		btnView[0].setForeground(menu_over);
		btnModify[0] = new JLabel("재고 편집");
		btnAdd[0] = new JLabel("재고 추가");
		JLabel blank = new JLabel();
		JLabel blank2 = new JLabel();

		btnView[0].setFont(font4);
		btnModify[0].setFont(font4);
		btnAdd[0].setFont(font4);

		MouseExitedListener1 listener1 = new MouseExitedListener1(); // 이벤트객체
		MouseExitedListener2 listener2 = new MouseExitedListener2();
		MouseExitedListener3 listener3 = new MouseExitedListener3();

		btnView[0].addMouseListener(listener1);
		btnModify[0].addMouseListener(listener2);
		btnAdd[0].addMouseListener(listener3);

		menubar.add(btnView[0]);
		menubar.add(btnModify[0]);
		menubar.add(btnAdd[0]);
		menubar.add(blank);
		menubar.add(blank2);

		menubar.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 0));

		nav.add(header, BorderLayout.CENTER);
		nav.add(menubar, BorderLayout.SOUTH);

		// inventory view
		JPanel inventory = new JPanel(new BorderLayout());

		// search
		JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		search.setBackground(background);
		search.setBorder(BorderFactory.createEmptyBorder(50, 240, 0, 0));
		TextField input = new TextField("", 20);
		HalfRoundedButton check = new HalfRoundedButton("🔍");

		search.add(input);
		search.add(check);

		// list
		JPanel list = new JPanel();
		
		dataLoad();

		model = new DefaultTableModel(dataSet, colNames);
		JTable tableView = new JTable(model);
		tableView.setFont(font4);
		tableView.setRowHeight(30);								// 행간 조절
		tableView.setGridColor(Color.gray);						// 격자색
		tableView.getTableHeader().setReorderingAllowed(false); // 이동 불가
		tableView.getTableHeader().setResizingAllowed(false); 	// 크기 조절 불가
		tableView.setEnabled(false);							// 셀 선택 불가
		
		JScrollPane scrollList = new JScrollPane(tableView);
		scrollList.setFont(font4);
		scrollList.setPreferredSize(new Dimension(800, 600));	// 테이블 사이즈 조절

		list.setBackground(background);
		list.add(scrollList);

		DefaultTableCellRenderer dtcr;
		for (int i = 0; i < tableView.getColumnCount(); i++) { 
			dtcr = new DefaultTableCellRenderer();	// 셀 내용 정렬 
			if(i < 4) 
				dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			else 
				dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
			TableColumnModel tcm = tableView.getColumnModel();
			tcm.getColumn(i).setCellRenderer(dtcr);
			tableView.getColumnModel().getColumn(i).setPreferredWidth(250);	// JTable 의 컬럼 길이 조절
		}

		inventory.add(search, BorderLayout.NORTH);
		inventory.add(list, BorderLayout.CENTER);

		View.add(nav, BorderLayout.NORTH);
		View.add(inventory, BorderLayout.CENTER);

		View.setVisible(true);
		panel.add(View);
	}

	private void Modify() {
		// Modify 세팅
		Modify = new JPanel();
		Modify.setBackground(background);
		Modify.setLayout(new BorderLayout());

		// navigation
		JPanel nav = new JPanel(new BorderLayout());
		nav.setBackground(background);
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);
		JPanel menubar = new JPanel(new GridLayout(1, 10, 0, 0));
		menubar.setBackground(menu_back);

		// header
		homebtn2 = new JButton("", logo);
		homebtn2.setRolloverIcon(logo_over);
		homebtn2.setContentAreaFilled(false);
		homebtn2.setBorderPainted(false);
		homebtn2.setFocusPainted(false);

		JLabel title = new JLabel("재고 관리");
		title.setFont(font2);

		header.add(homebtn2, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menubar
		btnView[1] = new JLabel("재고 보기");
		btnModify[1] = new JLabel("재고 편집");
		btnModify[1].setForeground(menu_over);
		btnAdd[1] = new JLabel("재고 추가");
		JLabel blank = new JLabel();
		JLabel blank2 = new JLabel();

		btnView[1].setFont(font4);
		btnModify[1].setFont(font4);
		btnAdd[1].setFont(font4);

		MouseExitedListener1 listener1 = new MouseExitedListener1(); // 이벤트객체
		MouseExitedListener2 listener2 = new MouseExitedListener2();
		MouseExitedListener3 listener3 = new MouseExitedListener3();

		btnView[1].addMouseListener(listener1);
		btnModify[1].addMouseListener(listener2);
		btnAdd[1].addMouseListener(listener3);

		menubar.add(btnView[1]);
		menubar.add(btnModify[1]);
		menubar.add(btnAdd[1]);
		menubar.add(blank);
		menubar.add(blank2);

		menubar.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 0));

		nav.add(header, BorderLayout.CENTER);
		nav.add(menubar, BorderLayout.SOUTH);
		
		// inventory modify
		JPanel inventory = new JPanel(new BorderLayout());
		inventory.setBackground(background);
		
		JLabel subtitle1 = new JLabel("재고 편집");
		subtitle1.setFont(font2);
		subtitle1.setHorizontalAlignment(JLabel.CENTER);
		subtitle1.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		
		// 재고 선택
		JPanel choose = new JPanel();
		choose.setBackground(background);
		choose.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		Choice ch = new Choice();
		
		for(int i=0; i<name.size(); i++) {
			ch.add(name.get(i));
		}
		
		choose.add(ch);
		
		// 수정 & 삭제
		JPanel btns1 = new JPanel();
		btns1.setBackground(background);
		btns1.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
		RoundedButton rp = new RoundedButton("재고수정");
		rp.setFont(font3);
		RoundedButton rm = new RoundedButton("재고삭제");
		rm.setFont(font3);
		
		btns1.add(rp);
		btns1.add(rm);
		
		inventory.add(subtitle1, BorderLayout.NORTH);
		inventory.add(choose, BorderLayout.CENTER);
		inventory.add(btns1, BorderLayout.SOUTH);
		
		// inventory replace
		JPanel replace = new JPanel(new BorderLayout());
		replace.setBackground(background);

		JPanel btns2 = new JPanel();
		btns2.setBackground(background);
		btns2.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
		RoundedButton check1 = new RoundedButton("확인");
		check1.setFont(font3);
		RoundedButton cancel1 = new RoundedButton("취소");
		cancel1.setFont(font3);
		
		btns2.add(check1);
		btns2.add(cancel1);
		
		JLabel subtitle2 = new JLabel("재고 수정");
		subtitle2.setFont(font2);
		subtitle2.setHorizontalAlignment(JLabel.CENTER);
		subtitle2.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		
		JPanel datas = new JPanel();
		datas.setBackground(background);
		datas.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		
		JPanel data = new JPanel(new GridLayout(7, 2, 0, 30));
		data.setBackground(background);
		JLabel L1 = new JLabel(colNames.get(0));
		L1.setFont(font3);
		JLabel L2 = new JLabel(colNames.get(1));
		L2.setFont(font3);
		JLabel L3 = new JLabel(colNames.get(2));
		L3.setFont(font3);
		JLabel L4 = new JLabel(colNames.get(3));
		L4.setFont(font3);
		JLabel L5 = new JLabel(colNames.get(4));
		L5.setFont(font3);
		JLabel L6 = new JLabel(colNames.get(5));
		L6.setFont(font3);
		JLabel L7 = new JLabel(colNames.get(6));
		L7.setFont(font3);
		
		JLabel R1 = new JLabel("");
		R1.setFont(font6);
		TextField R2 = new TextField("", 20);
		R2.setFont(font6);
		TextField R3 = new TextField("", 20);
		R3.setFont(font6);
		TextField R4 = new TextField("", 20);
		R4.setFont(font6);
		TextField R5 = new TextField("", 20);
		R5.setFont(font6);
		TextField R6 = new TextField("", 20);
		R6.setFont(font6);
		TextField R7 = new TextField("/", 20);
		R6.setFont(font6);
		
		data.add(L1);
		data.add(R1);
		data.add(L2);
		data.add(R2);
		data.add(L3);
		data.add(R3);
		data.add(L4);
		data.add(R4);
		data.add(L5);
		data.add(R5);
		data.add(L6);
		data.add(R6);
		data.add(L7);
		data.add(R7);
		
		datas.add(data);
		
		replace.add(subtitle2, BorderLayout.NORTH);		
		replace.add(datas, BorderLayout.CENTER);
		replace.add(btns2, BorderLayout.SOUTH);
		
		// 버튼 이벤트
		rp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.setVisible(false);
				replace.setVisible(true);
				
				int index = ch.getSelectedIndex();
				R1.setText(code.get(index));
				R2.setText(name.get(index));
				R3.setText(category.get(index));
				R4.setText(standard.get(index));
				R5.setText(Integer.toString(cnt.get(index)));
				R6.setText(Integer.toString(price.get(index)));
				R7.setText(note.get(index));
				
			}
		});
		rm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 삭제 기능 구현
				int n = JOptionPane.showConfirmDialog(
						null
						, "해당 상품을 삭제하시겠습니까?"
						, "박리다매 무인가게"
						, JOptionPane.YES_NO_OPTION
						, JOptionPane.WARNING_MESSAGE
				);
				if(n == 0) {
					// 데이터 삭제
					int index = ch.getSelectedIndex();
					
					dataSet.remove(index);
					code.remove(index);
					name.remove(index);
					category.remove(index);
					standard.remove(index);
					cnt.remove(index);
					price.remove(index);
					note.remove(index);
					
					// repaint
					ch.remove(index);
					dataLoad();		
					model.fireTableDataChanged();

					JOptionPane.showMessageDialog(null
							, "정상적으로 재고 삭제 완료!"
							, "박리다매 무인가게"
							, JOptionPane.PLAIN_MESSAGE
					);
				}
			}
		});
		check1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = ch.getSelectedIndex();
				int n = 0;
				// 예외 처리
				if(R2.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "상품명을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R3.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "분류를 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R4.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "규격을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R5.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "수량을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!is.isNum(R5.getText())) {
					JOptionPane.showMessageDialog(null
							, "수량은 숫자만 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R6.getText().length() == 0) {	
					JOptionPane.showMessageDialog(null
							, "금액을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!is.isNum(R6.getText())) {
					JOptionPane.showMessageDialog(null
							, "금액은 숫자만 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R7.getText().length() == 0) {
					R7.setText("/");
				} else if( R2.getText().equals(name.get(index))
						&&  R3.getText().equals(category.get(index))
						&&  R4.getText().equals(standard.get(index))
						&&  Integer.parseInt(R5.getText()) == cnt.get(index)
						&&  Integer.parseInt(R6.getText()) == price.get(index)
						&&  R7.getText().equals(note.get(index))) {
					JOptionPane.showMessageDialog(null
							, "변경사항이 없습니다!"
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
					n = -1;
				}
				if (n == -1) {
					inventory.setVisible(true);
					replace.setVisible(false);
				} else {
					// 수정 기능 구현
					n = JOptionPane.showConfirmDialog(
							null
							, "변경사항을 저장하시겠습니까?"
							, "박리다매 무인가게"
							, JOptionPane.YES_NO_OPTION
							, JOptionPane.WARNING_MESSAGE
					);
					if(n == 0) {
						// 데이터 삭제
						name.set(index, R2.getText());
						category.set(index, R3.getText());
						standard.set(index, R4.getText());
						cnt.set(index, Integer.parseInt(R5.getText()));
						price.set(index, Integer.parseInt(R6.getText()));
						note.set(index, R7.getText());
						
						// repaint
						dataLoad();		
						model.fireTableDataChanged();
	
						JOptionPane.showMessageDialog(null
								, "정상적으로 재고 수정 완료!"
								, "박리다매 무인가게"
								, JOptionPane.PLAIN_MESSAGE
						);
						inventory.setVisible(true);
						replace.setVisible(false);
					}
				}
			}
		});
		cancel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.setVisible(true);
				replace.setVisible(false);
			}
		});
		
		inventory.setVisible(true);
		replace.setVisible(false);
		
		JPanel layout = new JPanel(new CardLayout());
		layout.add(inventory);
		layout.add(replace);
		
		Modify.add(nav, BorderLayout.NORTH);
		Modify.add(layout, BorderLayout.CENTER);

		Modify.setVisible(false);
		panel.add(Modify);
	}

	private void Add() {
		// Modify 세팅
		Add = new JPanel();
		Add.setBackground(background);
		Add.setLayout(new BorderLayout());

		// navigation
		JPanel nav = new JPanel(new BorderLayout());
		nav.setBackground(background);
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);
		JPanel menubar = new JPanel(new GridLayout(1, 10, 0, 0));
		menubar.setBackground(menu_back);

		// header
		homebtn3 = new JButton("", logo);
		homebtn3.setRolloverIcon(logo_over);
		homebtn3.setContentAreaFilled(false);
		homebtn3.setBorderPainted(false);
		homebtn3.setFocusPainted(false);

		JLabel title = new JLabel("재고 관리");
		title.setFont(font2);

		header.add(homebtn3, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menubar
		btnView[2] = new JLabel("재고 보기");
		btnModify[2] = new JLabel("재고 편집");
		btnAdd[2] = new JLabel("재고 추가");
		btnAdd[2].setForeground(menu_over);
		JLabel blank = new JLabel();
		JLabel blank2 = new JLabel();

		btnView[2].setFont(font4);
		btnModify[2].setFont(font4);
		btnAdd[2].setFont(font4);

		MouseExitedListener1 listener1 = new MouseExitedListener1(); // 이벤트객체
		MouseExitedListener2 listener2 = new MouseExitedListener2();
		MouseExitedListener3 listener3 = new MouseExitedListener3();

		btnView[2].addMouseListener(listener1);
		btnModify[2].addMouseListener(listener2);
		btnAdd[2].addMouseListener(listener3);

		menubar.add(btnView[2]);
		menubar.add(btnModify[2]);
		menubar.add(btnAdd[2]);
		menubar.add(blank);
		menubar.add(blank2);

		menubar.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 0));

		nav.add(header, BorderLayout.CENTER);
		nav.add(menubar, BorderLayout.SOUTH);
		
		// inventory add
		JPanel inventory = new JPanel(new BorderLayout());
		inventory.setBackground(background);
		
		JLabel subtitle = new JLabel("재고 추가");
		subtitle.setFont(font2);
		subtitle.setHorizontalAlignment(JLabel.CENTER);
		subtitle.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

		JPanel btns = new JPanel();
		btns.setBackground(background);
		btns.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
		RoundedButton add = new RoundedButton("추가");
		add.setFont(font3);
		btns.add(add);
		
		JPanel datas = new JPanel();
		datas.setBackground(background);
		datas.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		
		JPanel data = new JPanel(new GridLayout(7, 2, 0, 30));
		data.setBackground(background);
		JLabel L1 = new JLabel(colNames.get(0));
		L1.setFont(font3);
		JLabel L2 = new JLabel(colNames.get(1));
		L2.setFont(font3);
		JLabel L3 = new JLabel(colNames.get(2));
		L3.setFont(font3);
		JLabel L4 = new JLabel(colNames.get(3));
		L4.setFont(font3);
		JLabel L5 = new JLabel(colNames.get(4));
		L5.setFont(font3);
		JLabel L6 = new JLabel(colNames.get(5));
		L6.setFont(font3);
		JLabel L7 = new JLabel(colNames.get(6));
		L7.setFont(font3);
		
		TextField R1 = new TextField("", 20);
		R1.setFont(font6);
		TextField R2 = new TextField("", 20);
		R2.setFont(font6);
		TextField R3 = new TextField("", 20);
		R3.setFont(font6);
		TextField R4 = new TextField("", 20);
		R4.setFont(font6);
		TextField R5 = new TextField("", 20);
		R5.setFont(font6);
		TextField R6 = new TextField("", 20);
		R6.setFont(font6);
		TextField R7 = new TextField("/", 20);
		R6.setFont(font6);
		
		data.add(L1);
		data.add(R1);
		data.add(L2);
		data.add(R2);
		data.add(L3);
		data.add(R3);
		data.add(L4);
		data.add(R4);
		data.add(L5);
		data.add(R5);
		data.add(L6);
		data.add(R6);
		data.add(L7);
		data.add(R7);
		
		datas.add(data);
		
		inventory.add(subtitle, BorderLayout.NORTH);
		inventory.add(datas, BorderLayout.CENTER);
		inventory.add(btns, BorderLayout.SOUTH);
		
		Add.add(nav, BorderLayout.NORTH);
		Add.add(inventory, BorderLayout.CENTER);

		Add.setVisible(false);
		panel.add(Add);
	}

	public void reLoad() {
		View.setVisible(true);
		Modify.setVisible(false);
		Add.setVisible(false);
	}
	
	private void dataLoad() {
		dataSet.removeAllElements();
		Vector<String> rows;
		
		// 데이터 입력
		for (int i = 0; i < code.size(); i++) {
			rows = new Vector<>();
			rows.add(code.get(i));
			rows.add(name.get(i));
			rows.add(category.get(i));
			rows.add(standard.get(i));
			rows.add(Integer.toString(cnt.get(i)));
			rows.add(Integer.toString(price.get(i)));
			rows.add(note.get(i));
			dataSet.add(rows);
		}
	}
	
	class MouseExitedListener1 extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			View.setVisible(true);
			Modify.setVisible(false);
			Add.setVisible(false);
		}
		public void mouseEntered(MouseEvent e) {
			for(int i=0; i<3; i++)
				btnView[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	class MouseExitedListener2 extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			View.setVisible(false);
			Modify.setVisible(true);
			Add.setVisible(false);
		}
		public void mouseEntered(MouseEvent e ) {
			for(int i=0; i<3; i++)
				btnModify[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	class MouseExitedListener3 extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			View.setVisible(false);
			Modify.setVisible(false);
			Add.setVisible(true);
		}
		public void mouseEntered(MouseEvent e ) {
			for(int i=0; i<3; i++)
				btnAdd[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}
}