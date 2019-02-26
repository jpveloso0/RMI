package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements IRemoteServer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8573037441198148061L;
	private ServidorTela servidorTela;
	
	public ServerImpl() throws RemoteException{
		super();
	}
	
	@Override
	public void imprimirMensagem(String mensagem) throws RemoteException {
		servidorTela.textArea.setText(
									servidorTela.textArea.getText()+"\n"+mensagem);
	}

	@Override
	public void setServidorTela(ServidorTela servidorTela) throws RemoteException {
		this.servidorTela = servidorTela;
	}

	
	
}
