/*This class specifically defines the nodes that will compose the linked lists
 * and their own methods*/

class Node

{

    protected String data;

    protected Node link;

 

    /*  Constructor  */

    public Node()

    {

        link = null;

        data = null;

    }    

    /*  Constructor  */

    public Node(String d,Node n)

    {

        data = d;

        link = n;

    }    

    /*  Function to set link to next Node  */

    public void setLink(Node n)

    {

        link = n;

    }    

    /*  Function to set data to current Node  */

    public void setData(String d)

    {

        data = d;

    }    

    /*  Function to get link to next node  */

    public Node getLink()

    {

        return link;

    }    

    /*  Function to get data from current Node  */

    public String getData()

    {

        return data;

    }

}