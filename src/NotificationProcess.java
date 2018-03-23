import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class NotificationProcess {

	/**
	 * @param args
	 */
	
	/*main method here creates the instance of the remote object implementation,
	 exports the remote object, and then binds that instance to a name in a Java RMI registry*/
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Registry address = LocateRegistry.getRegistry("localhost", 2000);
		final MQSInterface li = (MQSInterface) address.lookup("MQS");
	
		li.notifservice();  //Remote call to the notifservice method of class MessageQueuingServer
			
		
	}

}
