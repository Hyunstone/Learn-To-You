package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Total;
import network.MultiChatClient;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.Color;
public class StudentUI extends JFrame {
	private MultiChatClient mcc;
	private JPanel contentPane;
	private JTextField textField;
	public String  studentName;
	private Total total ;
	private JList list;
	private JLabel lblNewLabel_2;
	private JPanel panel;

	public StudentUI(String  studentName,Total total ) {
		this.studentName  = studentName;  //이름 설정
		this.total = total;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("문제를 선택해주세요.");
		lblNewLabel.setBounds(25, 54, 130, 15);
		contentPane.add(lblNewLabel);
		
		list = new JList();
		list.setListData(total.quiz);	
		list.setBounds(25, 79, 130, 132);
		contentPane.add(list);
		
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				lblNewLabel_2.setText((String)list.getSelectedValue());
			}

		});
		JLabel lblNewLabel_1 = new JLabel(studentName + "님께서 로그인 중입니다.");
		lblNewLabel_1.setBounds(25, 10, 270, 34);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("로그아웃");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다" );
			}
		});
		btnNewButton.setBounds(307, 16, 97, 23);
		contentPane.add(btnNewButton);
		
		panel = new JPanel();
		panel.setBounds(178, 54, 226, 173);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_2 = new JLabel("문제를 선택하세요(더블클릭)"); //jlist 선택하면 문제 출력되는 부분
		lblNewLabel_2.setBackground(Color.ORANGE);
		lblNewLabel_2.setBounds(12, 23, 202, 90);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(12, 123, 129, 40);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("제출");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(153, 122, 61, 41);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("채팅을 원하시면, 클릭하세요.");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mcc = new MultiChatClient("127.0.0.1");
			}
		});
		btnNewButton_2.setBounds(25, 268, 379, 23);
		contentPane.add(btnNewButton_2);
	}
}
