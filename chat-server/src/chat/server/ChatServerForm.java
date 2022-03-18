package chat.server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import chatLibs.ChatMessageSocket; //import chatLibs

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class ChatServerForm extends JFrame {

	private ChatMessageSocket mSocket; //init ChatMessageSocket
	
	private JPanel contentPane;
	private JTextField txtPort;
	private JTextField txtMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatServerForm frame = new ChatServerForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatServerForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 388);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Chat Server");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = new JLabel("Port:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("Message:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextPane txpMessageBoard = new JTextPane();
		
		txtPort = new JTextField();
		txtPort.setText("9888");
		txtPort.setColumns(10);
		
		txtMessage = new JTextField();
		txtMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send"); //add action for Send button
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtMessage.getText().equals("")) {
					return;
				}
				mSocket.send(txtMessage.getText());
				
			}
		});
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnListen = new JButton("Listen"); //add action for Listen button
		btnListen.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try {
					int port = Integer.parseInt(txtPort.getText());
					ServerSocket serverSocket = new ServerSocket(port);
					
					Thread th = new Thread() {
						public void run() {
							try {
								txpMessageBoard.setText(txpMessageBoard.getText() + "\nListening... ");
								Socket socket = serverSocket.accept();
								txpMessageBoard.setText(txpMessageBoard.getText() + "\nClient Connected!... ");
								mSocket = new ChatMessageSocket(socket, txpMessageBoard);
								
							} catch (Exception e) {
								txpMessageBoard.setText("Error: " + e.getMessage());
								e.printStackTrace();
								
							}
							
						}
					};
					th.start();

					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnListen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(txpMessageBoard, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblNewLabel_2)
												.addGap(18)
												.addComponent(txtMessage, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblNewLabel_1)
												.addGap(18)
												.addComponent(txtPort, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(btnSend, Alignment.LEADING)
											.addComponent(btnListen)))
									.addComponent(separator, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
								.addContainerGap()))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(171))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(9)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnListen))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txpMessageBoard, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(txtMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSend))
					.addGap(31))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
