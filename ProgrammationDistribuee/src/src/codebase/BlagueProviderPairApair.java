package codebase;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
public interface BlagueProviderPairApair extends Remote{
	
	String getNom() throws RemoteException;
	String[] getAllNames(BlagueProviderPairApair a) throws RemoteException;
	Blague getBlague(Object nom) throws RemoteException, BlagueAbsenteException;
    Map getCollection() throws RemoteException;
}
