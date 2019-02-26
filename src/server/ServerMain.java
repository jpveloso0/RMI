package server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

public class ServerMain {

	public static void main(String[] args) {
		
		
			
		Path policyFile = Paths.get("resources","security.policy");
		
		InputStream fis = ServerMain.class.getClassLoader().
					getResourceAsStream("serverconfig.properties");
			
		Properties prop = new Properties();
		
		try {
			prop.load(fis);
			
			String ipServer = prop.getProperty("server_ip");
			String porta = prop.getProperty("server_port");
			
			System.setProperty("java.rmi.server.hostname", ipServer);
			
			System.setProperty("java.security.policy", policyFile.toFile().
								getCanonicalPath());
			
			System.setSecurityManager(new SecurityManager());
			
			LocateRegistry.createRegistry(Integer.parseInt(porta));
			
			IRemoteServer serverObj = new ServerImpl();
			
			ServidorTela servidorTela = new ServidorTela();
			serverObj.setServidorTela(servidorTela);
			
			Naming.rebind("//"+ipServer+":"+porta+"/conversationServer", serverObj);
			
			servidorTela.frame.setVisible(true);
			
			while(true){
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
