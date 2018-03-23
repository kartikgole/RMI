import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class AdvisorProcess {

	/**
	 * @param args
	 * @throws Exception 
	 */
	
	
	/*main method here creates the instance of the remote object implementation,
	 exports the remote object, and then binds that instance to a name in a Java RMI registry*/
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Registry address = LocateRegistry.getRegistry("localhost", 2000);
		final MQSInterface li = (MQSInterface) address.lookup("MQS");
		
		//As you can see, I chose not to sleep the thread here, to avoid unnecessary loop prints on the IDE console
		
		
			/*		
		do
		{
			System.out.println("You haven't received any new request, thread will sleep now");
			Thread.sleep(3000);
		}while(li.isEmpty());
		 	*/
		
		 
		if(li.isEmpty())			//Condition for when the Queue is Empty
		{
			System.out.println("You haven't received any new request, run this class again later");
			
		}
		else
		{	
			li.display();			//Remote call to display all the requests received
			li.RandomResponse();	//Remote call to generate a random response on those requests
			li.remove();			//Delete all the contents of the Queue after the decision is finalized
			
		}
		
	}

}
