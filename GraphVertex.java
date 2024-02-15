/*
 * Abstract data type used in previous practicals
 */

import java.util.Iterator;

public class GraphVertex
{
    private String label; // name of vertex
    private Object value; // whatever you want to store in the vertex
    private DSALinkedList list; // list of Strings to hold adjacent vertices
    private boolean visited;

    // Constructor 1, create node with label (all nodes must have a label)
    public GraphVertex(String inLabel)
    {
        label = inLabel;
        value = new Location(inLabel);
        list = new DSALinkedList();
        visited = false;
    }

    // Constructor 2, create node with label and store a value
    public GraphVertex(String inLabel, Object inVal)
    {
        label = inLabel;
        value = inVal;
        list = new DSALinkedList();
        visited = false;
    }

    public void addEdge(String to, double dis)
    {
        String edgeName = label + to; // name of edge will be the direction of the edge "to" is the name of the 
        GraphEdge newEdge = new GraphEdge(label, to, edgeName, dis);
        list.insertLast(newEdge);
    }

    public String getLabel()
    {
        return label;
    }

    public Object getValue()
    {
        return value;
    }

    public DSALinkedList getAdjacent()
    {
        DSALinkedList ret = list.cloneList();
        return ret;
    }

    //visited
    public boolean getVisited()
    {
        return visited;
    }

    public void setVisited()
    {
        visited = true;
    }

    public void clearVisited()
    {
        visited = false;
    }
    // visited

    public String display()
    {
        StringBuilder ret = new StringBuilder("[");
        Iterator tempList = list.iterator();
        ret.append(label); // adds the label to the string
        
        // String of adjacent nodes
        StringBuilder adjacentMembers = new StringBuilder();
        while(tempList.hasNext())
        {
            GraphEdge tempEdge = (GraphEdge)tempList.next();
            adjacentMembers.append(tempEdge.display());
        }

        if (value != null)
        {
            ret.append("," + value.toString()); // if there are adjacent vertices, add a comma to separate them
        }
            ret.append("," + adjacentMembers.toString()); // adds list of vertices to string
            ret.append("]");
        return ret.toString();
    }

    // iterate through adjacent list and remove node with label pLabel
    public Double removeAdj(String pLabel)
    {
        Iterator adjIter = list.iterator();
        DSALinkedList newList = new DSALinkedList();
        Double ret = null;

        while (adjIter.hasNext())
        {
            GraphEdge jarEdge = (GraphEdge)adjIter.next();

            if (jarEdge.getTo().compareTo(pLabel) == 0)
            {
                ret = jarEdge.getDis();
            }
            else
            {
                newList.insertLast(jarEdge);
            }
        }
        list = newList;
        return ret;
    }
}
