
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;


/*This is the main class that constructs the Queue using linked lists*/

class MessageQueuingServer extends UnicastRemoteObject implements MQSInterface

{
	/* Each remote method declare java.rmi.RemoteException in their throws clauses, 
	* in addition to any application-specific exceptions */
	
    protected Node front, rear;

    public int size;

 

    /* Constructor for the Queue*/

    public MessageQueuingServer() throws RemoteException

    {

        front = null;

        rear = null;

        size = 0;

    }    

    /*  This function checks if the Queue is empty */

    public boolean isEmpty()

    {

        return front == null;

    }    

    /*  This function gets the size of the Queue */

    public int getSize()

    {

        return size;

    }    

    /*  This function enters the data entered by the user into the Queue from rear */

    public void insert(String data) throws IOException 

    {

        Node nptr = new Node(data, null);
        File file = new File("QueueContents.txt");
		FileWriter fileWriter = new FileWriter(file);
        if (rear == null)

        {

            front = nptr;

            rear = nptr;

        }

        else

        {

            rear.setLink(nptr);

            rear = rear.getLink();

        }

        size++ ;

    }    

    /*  Function to remove all elements from the queue. 
     	This is only called after the advisor has made the decision and stored in a file that will be used to notify the student */

    public void remove()

    {
    	 do
     	{
    	       Node ptr = front;

    	       front = ptr.getLink();        

    	       if (front == null)

    	       rear = null;

    	        size-- ;
    	        
     	}while(front != null);
    	
    	 if (isEmpty())
    	 {
    		 System.out.println("Requests have been cleared from the MQS");
    	 }

    }    

    /*  Function to display the requests they received in the Queue to the Advisor */

    public void display()

    {

        System.out.print("\nHello Advisor, You've received the following requests :- \n");

        if (size == 0)

        {

            System.out.print("You haven't received any new Requests!\n");

            return ;

        }

        Node ptr = front;

        while (ptr != rear.getLink() )

        {

            System.out.print(ptr.getData()+" ");

            ptr = ptr.getLink();

        }

        System.out.println();        

    }
    
    
    /*This Function firstly creates a file named decision.txt
     * Then a random string is selected from the list responses[] that has the two decision 
     * using the Random() function from java.util
     * The decision is then written in the file decision.txt and it is closed*/
    
    public void RandomResponse() throws IOException
    {
    	char ch;
    	Scanner scan = new Scanner(System.in);
    	System.out.println("Do you want to generate a Random response? Press y/n");
    	File file = new File("decision.txt");
		FileWriter fileWriter = new FileWriter(file);
    	ch = scan.next().charAt(0);
    	if(ch == 'Y'|| ch == 'y')
    	{
	    	String responses[] = {
	    			"Congratulations, Your courses have been cleared :)",
	    			"Sorry, Your courses cannot be cleared :(",
	    			
	    	};
	    	
	    	Random rndm = new Random();
	    	
	    	String randomString = responses[rndm.nextInt(responses.length)];
	    	System.out.println("The randomly generated decision:- ");
	    	System.out.println(randomString);
	    	fileWriter.write(randomString);
	    	fileWriter.flush();
			fileWriter.close();
	   
	    	
    	}
    }
    
    /*This method is remotely called by the NotificationProcess class
     * Starts by looking up the file decision.txt that has the admission decision stored
     * Use the buffered reader then to store it in a string named line
     * Print it to the user*/
    
    public void notifservice() throws IOException
    { 
    	try{
    		File file = new File("decision.txt");
    		BufferedReader br = new BufferedReader(new FileReader("decision.txt"));
    	
    	String line = null;
    	   while ((line = br.readLine()) != null) {
    		   System.out.println("\nHello Student, Advisor has made the decision!");
    		   System.out.println(line);
    	       file.delete();
    	   }
    	}
    	catch(FileNotFoundException e)
    	{
    		System.out.println("No new decisions to be displayed");
    	}
    }
    
    /*main method here creates the instance of the remote object implementation,
	 exports the remote object, and then binds that instance to a name in a Java RMI registry*/
    
    public static void main(String[] args) throws RemoteException 

    {
    	MessageQueuingServer lq = new MessageQueuingServer();
    	java.rmi.registry.LocateRegistry.createRegistry(2000).rebind("MQS", lq);
    	System.out.println("The Message Queuing Server is running");
    }
    

}