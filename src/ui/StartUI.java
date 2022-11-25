package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Total;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class StartUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private Total total ;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	public TeacherUI teacherUI;
	public StudentUI studentUI;


	public StartUI(Total total) {
		this.total = total;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("등록구분");
		lblNewLabel.setBounds(89, 85, 57, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(182, 82, 183, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("닉네임");
		lblNewLabel_1.setBounds(89, 143, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(182, 140, 183, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();  //("등록구분");
				String pwd = textField_1.getText(); //("닉네임");
				
				if (!(total.info.loginInfo.containsKey(id))) {
					lblNewLabel_3.setText("등록된 구분자가 없습니다!");
				} else {
					for (Map.Entry<String, String> entry : total.info.loginInfo.entrySet() ) {
			            String key = entry.getKey();
			            String value = entry.getValue();
			            if(key.equals(id)&&value.equals(pwd) ) {
			            	
			            	if(key.equals("교수")) {
			            		setVisible(false);
			            		teacherUI = new TeacherUI(value);
			            		teacherUI.setVisible(true);
			            	} else {
			            		setVisible(false);
			            		studentUI = new StudentUI(value);
			            		studentUI.setVisible(true);
			            	}
			            }
			        }
					
					lblNewLabel_4.setText("구분자와 닉네임이 다릅니다!");
					textField.setText("");  //("초기화");
					textField_1.setText(""); //("초기화");
				}
			}
		});
		

		btnNewButton.setBounds(174, 203, 97, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("퀴즈 세상에 오신걸 환영합니다. 로그인 후 사용부탁드립니다.");
		lblNewLabel_2.setBounds(58, 25, 348, 31);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");  // 등록구분자가 있는지 체크
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(182, 115, 183, 15);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel(""); // 구분자와 닉네임이 다릅니다!
		lblNewLabel_4.setForeground(Color.ORANGE);
		lblNewLabel_4.setBounds(182, 171, 183, 15);
		contentPane.add(lblNewLabel_4);
	}

}
