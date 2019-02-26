package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteServer extends Remote {

	void imprimirMensagem(String mensagem) throws RemoteException;
	
	void setServidorTela(ServidorTela servidorTela) throws RemoteException;
	
}
