package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import server.IRemoteServer;

public class ClienteTela extends JFrame {
	
	private IRemoteServer serverObj;

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblMensagem;
	private JButton btnEnviar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					
					Path policyFile = Paths.get("resources","security.policy");
					
					InputStream fis = ClienteTela.class.getClassLoader().
								getResourceAsStream("serverconfig.properties");
						
					Properties prop = new Properties();
					
					prop.load(fis);
					
					String ipServer = prop.getProperty("server_ip");
					String porta = prop.getProperty("server_port");
					
					System.setProperty("java.rmi.server.hostname", ipServer);
					
					System.setProperty("java.security.policy", policyFile.toFile().
							getCanonicalPath());
		
					System.setSecurityManager(new SecurityManager());
					
					Registry r = LocateRegistry.getRegistry(ipServer, Integer.parseInt(porta));
					
					IRemoteServer serverObj = (IRemoteServer) r.lookup("conversationServer");
					
					ClienteTela frame = new ClienteTela(serverObj);
					frame.setVisible(true);
						
				} catch (Exception e) {
					e.printStackTrace();
				}
				
					
			}});
	}

	/**
	 * Create the frame.
	 */
	public ClienteTela(IRemoteServer serverObj) {
		
		this.serverObj = serverObj;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMensagem = new JLabel("Mensagem:");
		lblMensagem.setBounds(61, 77, 61, 14);
		contentPane.add(lblMensagem);
		
		textField = new JTextField();
		textField.setBounds(132, 74, 155, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					getServerObject().imprimirMensagem(textField.getText());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnEnviar.setBounds(158, 137, 89, 23);
		contentPane.add(btnEnviar);
	}
	
	public IRemoteServer getServerObject(){
		return this.serverObj;
	}
	
}
