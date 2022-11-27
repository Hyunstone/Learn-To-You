package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import network.MultiChatClient;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class StudentUI extends JFrame {
	private MultiChatClient mcc;
	private JPanel contentPane;
	private JTextField textField;
	public String  studentName;
	private JList list;
	private JLabel lblNewLabel_2;
	private JPanel panel;
	private String serverIp = "127.0.0.1";
	private BufferedReader response;
	private BufferedWriter request;
	private Socket socket;
	DefaultListModel listModel;

	private String quizNumber;

	public StudentUI(String studentName) {
		this.studentName  = studentName;  //이름 설정
		this.serverIp = serverIp;

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
		list.setBounds(25, 79, 130, 132);
		contentPane.add(list);

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if(!event.getValueIsAdjusting()) {
					String quizText = (String) list.getSelectedValue();
					quizNumber = String.valueOf(quizText.charAt(0));
					lblNewLabel_2.setText(quizText);
					textField.setText("");
				}
			}
		});

		JButton quizRefreshButton = new JButton("문제 불러오기");
		quizRefreshButton.setFont(new Font("맑은 고딕",Font.BOLD, 10));
		quizRefreshButton.setBounds(150, 55, 100, 23);
		quizRefreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String request = "1/0" + "\r\n";
				String response = requestServer(request, serverIp);
				// 이 부분은 '[,]' 이거 날리는 부분입니다.
				response = response.replace(String.valueOf(response.charAt(0)), "");
				response = response.replace(String.valueOf(response.charAt(response.length() - 1)), "");
				String[] resArr = response.split(",");

				if(resArr.length == 0) {
					lblNewLabel.setText("등록된 문제가 없습니다.");
				} else {
					listModel = new DefaultListModel();
					for (String res : resArr) {
						System.out.println(res);
						res = res.trim();
						listModel.addElement(res);
					}

					lblNewLabel.setText("등록된 문제 "+ resArr.length +"건 있습니다.");
					list.setModel(listModel);
				}
			}
		});
		contentPane.add(quizRefreshButton);

		JButton pointCheckButton = new JButton("현제 점수 확인");
		pointCheckButton.setFont(new Font("맑은 고딕",Font.BOLD, 10));
		pointCheckButton.setBounds(250, 55, 100, 23);

		pointCheckButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String request = "1/2/" + studentName + "\r\n";
				String response = requestServer(request, serverIp);
				JOptionPane.showMessageDialog(null, response);
			}
		});
		contentPane.add(pointCheckButton);

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
				String request = "1/1" ;
				request = request + "/" + studentName + "/ " + quizNumber + "/" + textField.getText() + "\r\n";
				requestServer(request, serverIp);
				textField.setText("");
			}
		});
		btnNewButton_1.setBounds(153, 122, 61, 41);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("채팅을 원하시면, 클릭하세요.");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mcc = new MultiChatClient("127.0.0.1", studentName);
			}
		});
		btnNewButton_2.setBounds(25, 268, 379, 23);
		contentPane.add(btnNewButton_2);
	}

	public String requestServer(String requestString, String ip) {
		String responseOutput = null;
		try {
			// 소켓 생성
			socket = new Socket(ip, 8889);
			//System.out.println("[Client]Server 연결 성공!!");

			response = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			request = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			request.write(requestString);
			request.flush();

			responseOutput = response.readLine();
			System.out.println(responseOutput);

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return responseOutput;
	}
}
