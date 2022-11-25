package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.io.*;
import java.net.Socket;

import network.MultiChatClient;


public class TeacherUI extends JFrame {
	private MultiChatClient mcc;
	private JPanel contentPane;
	public String teacherName;
	private JList list ;
	private JList list_1 ;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private Socket socket;
	private BufferedReader response;
	private BufferedWriter request;
	private String serverIp = "127.0.0.1";
	DefaultListModel listModel;

	
	public TeacherUI(String teacherName) {
		this.teacherName  = teacherName;  //이름 설정
		this.serverIp = serverIp;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(teacherName + "교수님께서 로그인중");
		lblNewLabel.setBounds(28, 22, 268, 15);
		contentPane.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);  //패널 
		panel_1.setBounds(226, 82, 196, 169);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		list_1 = new JList(); //결과 볼수 있는 곳
		list_1.setBounds(12, 30, 172, 129);
		panel_1.add(list_1);
		
		 lblNewLabel_2 = new JLabel(""); //결과 0건, 10건 설명 넣는  곳
		lblNewLabel_2.setBounds(12, 10, 172, 15);
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("로그아웃");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다" );
			}
		});
		btnNewButton.setBounds(325, 18, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("문제등록");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String quizEx = JOptionPane.showInputDialog("문제를 입력하세요 ");
				String answerEx = JOptionPane.showInputDialog("답안을 입력하세요 ");
				String response = "0/0" + "/" + quizEx + "/" + answerEx + "\r\n";
				// 두 값중 하나라도 안넣으면 문제 추가 X
				if(!(quizEx == null) && !(answerEx == null)) {
					requestServer(response, serverIp);
				}
				//System.out.println(response);
			}
		});
		btnNewButton_1.setBounds(25, 49, 97, 23);
		contentPane.add(btnNewButton_1);
		lblNewLabel_2.setText("시험응시 결과 버튼을 눌러주세요");
		
		JButton btnNewButton_2 = new JButton("결과보기");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String request = "0/2" + "\r\n";
				String response = requestServer(request, serverIp);
				// 이 부분은 '[,]' 이거 날리는 부분입니다.
				response = response.replace(String.valueOf(response.charAt(0)), "");
				response = response.replace(String.valueOf(response.charAt(response.length() - 1)), "");
				String[] resArr = response.split(",");

				if(resArr.length == 0) {
					lblNewLabel_1.setText("제출된 답안이 없습니다.");
				} else {
					listModel = new DefaultListModel();
					for (String res : resArr) {
						System.out.println(res);
						listModel.addElement(res);
					}

					lblNewLabel_1.setText("제출된 답안이 " + resArr.length + "건 있습니다.");
					list_1.setModel(listModel);
				}

				/*if(total.challengeInfo.size() == 0) {
					lblNewLabel_2.setText("시험응시 결과 " + total.challengeInfo.size() + "건 입니다.");
				}else {
					lblNewLabel_2.setText("시험응시 결과 " + total.challengeInfo.size() + "건 입니다.");
					list_1.setListData(total.challengeInfo);
				}*/
			}
		});
		btnNewButton_2.setBounds(273, 49, 97, 23);
		contentPane.add(btnNewButton_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(28, 82, 186, 169);
		contentPane.add(panel);
		panel.setLayout(null);
		
		list = new JList();  //등록된 문제 볼수 있는 곳
		list.setBounds(12, 31, 162, 128);
		panel.add(list);
		
		lblNewLabel_1 = new JLabel(""); //등록된 문제 몇문항?
		lblNewLabel_1.setBounds(12, 10, 162, 15);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setText("문제 보기 버튼을 눌러주세요");

		JButton btnNewButton_3 = new JButton("등록된 문제보기");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String request = "0/1" + "\r\n";
				String response = requestServer(request, serverIp);
				// 이 부분은 '[,]' 이거 날리는 부분입니다.
				response = response.replace(String.valueOf(response.charAt(0)), "");
				response = response.replace(String.valueOf(response.charAt(response.length() - 1)), "");
				String[] resArr = response.split(",");

				if(resArr.length == 0) {
					lblNewLabel_1.setText("등록된 문제가 없습니다.");
				} else {
					listModel = new DefaultListModel();
					for (String res : resArr) {
						System.out.println(res);
						listModel.addElement(res);
					}

					lblNewLabel_1.setText("등록된 문제 "+ resArr.length +"건 있습니다.");
					list.setModel(listModel);
				}
			}
		});
		btnNewButton_3.setBounds(134, 49, 127, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("채팅을 원하시면, 클릭하세요.");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mcc = new MultiChatClient("127.0.0.1");
			}
		});
		btnNewButton_4.setBounds(28, 270, 394, 23);
		contentPane.add(btnNewButton_4);
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
			//String[] strArr = responseOutput.split("/");
			System.out.println(responseOutput);

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return responseOutput;
	}
}
