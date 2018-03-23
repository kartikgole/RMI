import java.rmi.Remote;
import java.rmi.RemoteException;



/* This remote interface extends the interface java.rmi.Remote and declares a set of remote methods. 
 * Each remote method must declare java.rmi.RemoteException (or a superclass of RemoteException) in its throws clause, 
 * in addition to any application-specific exceptions*/
public interface MQSInterface extends Remote{
	public void insert(String data) throws Exception;
	public void display() throws Exception;
	public boolean isEmpty() throws Exception;
	public void RandomResponse() throws Exception;
	public void notifservice() throws Exception;
	public void remove() throws Exception;
}
