
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/* Student process. Will be run by the students when they want to send the request to the Advisor*/
public class StudentProcess {

	/**
	 * @param args
	 * @throws Exception 
	 */
	

	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		/*main method here creates the instance of the remote object implementation,
		 exports the remote object, and then binds that instance to a name in a Java RMI registry*/
		
		Registry address = LocateRegistry.getRegistry("localhost", 2000);
		final MQSInterface li = (MQSInterface) address.lookup("MQS");
	
		
		/* Here we take input from the user. Name and Course are then stored in two different Strings*/
		/*Name and Course Strings are then concatenated and the resulting String is inserted in the Queue from MQS*/
		Scanner scan = new Scanner(System.in);
		char ch;
		String request;
    	String name;
    	String course;
		
		do
		{
			
            System.out.println("Enter your name:- ");
            name = scan.next();
            System.out.println("Enter course number you want cleared:- ");
            course = scan.next();
            
            request = name.concat(course);	//Concatenation of two entries
            
            li.insert( request );			//remote call to the insert method from class MessageQueuingServer 
            System.out.println("Data sent!");
            
        	
			System.out.println("\nDo you want to add more courses? \n(Type y to add more or n to exit):- ");
	
	        ch = scan.next().charAt(0);
	        
	       
        
		}while(ch == 'Y'|| ch == 'y');
		 
		if(ch == 'N' || ch == 'n')
	    {
	        	System.out.println("All your requests have been sent, check notifications for decision");	//When the user wishes to stop sending requests
	        	System.exit(0);
	     }
		
	}

}
