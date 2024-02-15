/*
 * graph to represent map of local area, with edges defined as distance to area
 * assuming all paths are undirected
 * Data structure used to represent Graph
 */

import java.util.Iterator;
import java.io.*;
import java.io.Serializable;

public class GraphMap implements Serializable
{
    private DSALinkedList vertices;
    private DSALinkedList edges; //edges are also stored in each vertices, as adjacent edges, this list is used to easily navigate all edges in the graph without iterating through vertices

    // Constructor starts with empty graph
    public GraphMap()
    {
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
    }

    // does the vertex exist in the graph?
    public boolean hasVertex(String label)
    {
        boolean ret = false;
        
        if (vertices.isEmpty())
        {
            throw new IllegalArgumentException("Graph is empty");
        }

        // loop through vertices list and checks each vertex if the name matches the label
        Iterator iter = vertices.iterator();
        
        do
        {
            GraphVertex jar = (GraphVertex)iter.next();

            if (jar.getLabel().compareTo(label) == 0)
            {
                ret = true;
            }
        }
        while(iter.hasNext() && !ret);

        return ret;
    }

    // iterates through vertices list and adds one for each item in list
    public int getVertexCount()
    {
        int ret = 0;

        Iterator iter = vertices.iterator();

        while(iter.next() != null)
        {
            ret++;
        }

        return ret;
    }

    public boolean hasEdge(String label)
    {
        boolean ret = false;
        
        if (edges.isEmpty())
        {
            throw new IllegalArgumentException("Graph is empty");
        }

        // loop through edges list and checks each edge if the name matches the label
        Iterator iter = edges.iterator();
        
        while (iter.hasNext() && !ret)
        {
            GraphEdge jar = (GraphEdge)iter.next();
            if (jar.getLabel().compareTo(label) == 0)
            {
                ret = true;
            }
        }

        return ret;
    }

    // iterates through vertices lists, then the edges list in each, adding one for each item in edges list. finally dividing by 2
    public int getEdgeCount()
    {
        int ret = 0;

        if (edges.isEmpty())
        {
            throw new IllegalArgumentException("Graph is empty");
        }
        Iterator edgeIter = edges.iterator();

        while(edgeIter.next() != null)
        {
            ret++;
        }
        return ret;
    }

    // adds a vertex to list - no value
    public void addVertex(String label)
    {
        GraphVertex nodeIn = new GraphVertex(label);

        // check for collision
        if (vertices.isEmpty())
        {
            vertices.insertLast(nodeIn);
        }
        else
        {
            if (!hasVertex(label))
            {
                vertices.insertLast(nodeIn);
            }
        }
    }

    // adds a vertex to list
    public void addVertex(String label, Object value)
    {
        GraphVertex nodeIn = new GraphVertex(label, value);

        // check for collision
        if (vertices.isEmpty())
        {
            vertices.insertLast(nodeIn);
        }
        else
        {
            if (!hasVertex(label))
            {
                vertices.insertLast(nodeIn);
            }
        }
    }

    // adds edge from node label 1 to node "label 2"
    public void addEdge(String node1, String node2, double dis)
    {
        // Checks vertices to see if edge already exists
        Iterator edgeIter = edges.iterator();
        String edgeName = node1 + node2;
        boolean found = false;
        
        while (!found && edgeIter.hasNext())
        {
            GraphEdge nextEdge = (GraphEdge)edgeIter.next();
            if (nextEdge.getLabel().compareTo(edgeName) == 0)
            {
                found = true;
            }
        }
        if (found)
        {
            throw new IllegalArgumentException("Edge already in graph: " + edgeName);
        }
        
        
        // add new edge to edge list
        GraphEdge newEdge = new GraphEdge(node1, node2, edgeName, dis);
        edges.insertLast(newEdge);
        
        // add edge to adjacent list on both vertices
        // create a new list which takes in vertices from vertices-list one at a time, checking if it is matches the incoming label as it passes
        // undirected, this will add 2 edges to 2 vertices
        DSALinkedList newVertList = new DSALinkedList();
        while(!vertices.isEmpty())
        {
            GraphVertex jarVertex = (GraphVertex)vertices.removeFirst();

            if (jarVertex.getLabel().compareTo(node1) == 0)
            {
                jarVertex.addEdge(node2, dis);
            }
            else if (jarVertex.getLabel().compareTo(node2) == 0)
            {
                jarVertex.addEdge(node1, dis);
            }
            newVertList.insertLast(jarVertex);
        }
        // another linear search D,:
        vertices = newVertList;
    }

    // returns the vertex stored at a graph node given its label
    private GraphVertex getVertex(String label)
    {
        GraphVertex ret = null;

        if (vertices.isEmpty())
        {
            throw new IllegalArgumentException("Graph is empty");
        }

        Iterator iter = vertices.iterator();
        boolean found = false;

        do
        {
            GraphVertex jar = (GraphVertex)iter.next();

            if (label.compareTo(jar.getLabel()) == 0)
            {
                ret = jar;
                found = true;
            }
        }
        while (iter.hasNext() && !found);


        return ret;
    }

    // returns the value stored at graph node given its label

    public Object getVertexVal(String label)
    {
        GraphVertex vertex = getVertex(label);
        Object ret = vertex.getValue();
        return ret;
    }

    // get the vertex for the first label, then iterate through its adjacent list to see if it has the adjacent labeled vertex
    // this algorithm will work for both directed and undirected graphs, using label 2 as the outdegree of label
    public boolean isAdjacent(String label, String label2)
    {
        boolean ret = false;

        String edgeName = label + label2;
        Iterator edgeIter = edges.iterator();

        while (!edgeIter.hasNext() && !ret)
        {
            GraphEdge nextEdge = (GraphEdge)edgeIter.next();
            if (nextEdge.getLabel().compareTo(edgeName) == 0)
            {
                ret = true;
            }
        }

        return ret;
    }

    // Returns 2 x N matrix where N is the number of adjacent nodes
    // The first column stores the name of the adjacent node
    // The second column stores the distance between the nodes
    public Object[][] getAdjacent(String label)
    {
        GraphVertex jarNode = getVertex(label);
        DSALinkedList adjList = jarNode.getAdjacent();
        int numAdj = adjList.size();
        Object[][] ret = new Object[numAdj][2];
        for (int i = 0; i < numAdj; i++)
        {
            GraphEdge jarEdge = (GraphEdge)adjList.removeFirst();
            for (int j = 0; j < 2; j++)
            {
                ret[i][0] = jarEdge.getTo();
                ret[i][1] = jarEdge.getDis();
            }
        }
        return ret;
    }

    private GraphEdge getEdge(String node1, String node2)
    {
        String label = node1 + node2;
        GraphEdge ret = null;
        
        if (edges.isEmpty())
        {
            throw new IllegalArgumentException("Graph is empty");
        }

        Iterator iter = edges.iterator();
        boolean found = false;

        do
        {
            GraphEdge jar = (GraphEdge)iter.next();

            if (label.compareTo(jar.getLabel()) == 0)
            {
                found = true;
                ret = jar;
            }
        }
        while (iter.hasNext() && !found);

        if (!found)
        {
            throw new IllegalArgumentException("Edge not in graph\nFrom " + node1 + " to " + node2);
        }

        return ret;
    }

    public double getEdgeDis(String node1, String node2)
    {
        GraphEdge edge = getEdge(node1, node2);
        double ret = edge.getDis();
        return ret;
    }

    // remove vertex from vertices list
    // remove vertex from edges list
    // remove vertex from all adjacent lists in each graphvertex object
    public Object removeVertex(String label)
    {
        GraphVertex vertexFind = null;
        DSALinkedList newVertList = new DSALinkedList();
        DSALinkedList jarAdj = new DSALinkedList();

        while(!vertices.isEmpty())
        {
            GraphVertex jarVertex = (GraphVertex)vertices.removeFirst();

            if (jarVertex.getLabel().compareTo(label) == 0)
            {
                vertexFind = jarVertex;
                jarAdj = vertexFind.getAdjacent();
            }
            else
            {
                jarVertex.removeAdj(label);
                newVertList.insertLast(jarVertex);
            }
        }

        // remove vertex from edges list
        while (!jarAdj.isEmpty())
        {
            GraphEdge jarEdge = (GraphEdge)jarAdj.removeFirst();
            removeEdgeHelper(label, jarEdge.getTo());
        }

        vertices = newVertList;
        Object ret = vertexFind.getValue();
        return ret;
    }

    // remove edge from each vertices in vertices list
    // remove edge from edges list
    public Double removeEdge(String node1, String node2)
    {
        Double ret = removeEdgeHelper(node1, node2);
        DSALinkedList newVertices = new DSALinkedList();

        while (!vertices.isEmpty())
        {
            GraphVertex jarVertex = (GraphVertex)vertices.removeFirst();
            
            if (jarVertex.getLabel().compareTo(node1) == 0)
            {
                jarVertex.removeAdj(node2);
            }
            else if (jarVertex.getLabel().compareTo(node2) == 0)
            {
                jarVertex.removeAdj(node1);
            }
            newVertices.insertLast(jarVertex);
        }

        vertices = newVertices;
        return ret;
    }

    // remove edge from edges list
    private Double removeEdgeHelper(String node1, String node2)
    {
        Double ret = 0.;
        DSALinkedList newEdges = new DSALinkedList();
        while (!edges.isEmpty())
        {
            GraphEdge jarEdge = (GraphEdge)edges.removeFirst();
            String label1 = node1 + node2;
            String label2 = node2 + node1;
            if (jarEdge.getLabel().compareTo(label1) == 0 || jarEdge.getLabel().compareTo(label2) == 0)
            {
                ret = jarEdge.getDis();
            }
            else
            {
                newEdges.insertLast(jarEdge);
            }
        }
        edges = newEdges;
        return ret;
    }

    public void displayAsList()
    {
        String[] toPrint = getDisplayList();
        for (String string : toPrint) 
        {
            System.out.println(string);
        }
    }

    // returns String array representing graph
    // each item in array is string representation of the vertex in vertices list
    public String[] getDisplayList()
    {
        int verNum = getVertexCount();
        String[] ret = new String[verNum];
        DSALinkedList jarVert = vertices.cloneList();
    
        int i = 0;
        while (!jarVert.isEmpty())
        {
            GraphVertex vertex = (GraphVertex)jarVert.removeFirst();
            ret[i] = vertex.display();
            i++;
        }

        return ret;
    }

    public void displayAsMatrix()
    {
        int vertNum = getVertexCount();
        String[][] jarPrint = new String[vertNum + 1][vertNum + 1];
        jarPrint = getDisplayMatrix();

        for (String[] i : jarPrint)
        {
            for (String j: i)
            {
                System.out.print(j + " ");
            }
            System.out.println("");
        }
    }

    // returns a 2D String array of graph
    // nodes on top represent the possibility of outdegree of node on the left
    public String[][] getDisplayMatrix()
    {
        int vertNum = getVertexCount(); // number of vertices in graph
        String[][] ret = new String[vertNum + 1][vertNum + 1]; // 2D String array
        DSALinkedList jarList = vertices.cloneList();
        
        // names of arrays on the top and bottom
        for (int i = 1; i < (vertNum + 1); i++)
        {
            GraphVertex jarNode = (GraphVertex)jarList.removeFirst();
            ret[i][0] = jarNode.getLabel();
            ret[0][i] = jarNode.getLabel();
        }
        // would love to make the corner the same width as the longest string... somehow
        ret[0][0] = " ";

        jarList = vertices.cloneList();

        // Somewhat of a bread-first search
        int i = 0;
        while (!jarList.isEmpty())
        {
            i++;
            GraphVertex jarNode = (GraphVertex)jarList.removeFirst();
            DSALinkedList jarAdj = new DSALinkedList();
            jarAdj = jarNode.getAdjacent().cloneList();
            
            while (!jarAdj.isEmpty())
            {
                GraphEdge tempEdge = (GraphEdge) jarAdj.removeFirst();
                String jarTo = tempEdge.getTo();

                for (int j = 1; j < vertNum + 1; j++)
                {
                    if (jarTo.compareTo(ret[0][j]) == 0)
                    {
                        ret[i][j] = Double.toString(tempEdge.getDis());
                    }
                }
                for (int j = 1; j < vertNum + 1; j++)
                {
                    if (ret[i][j] == null)
                    {
                        ret[i][j] = "0";
                    }
                }
            }
        }
        // BFS
        
        return ret;
    }

    // read vertices for graph from file!
    public void readVerticesFromFile(String inName)
    {
        FileReader inStream;
        BufferedReader reader;
        String row = "";
        try
        {
            inStream = new FileReader(inName);
            reader = new BufferedReader(inStream);

            row = reader.readLine();
            while(row != null)
            {
                String[] tok = row.split(" ");
                Location loc = new Location(tok[0], Integer.parseInt(tok[1]), Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                addVertex(tok[0], loc);
                row = reader.readLine();
            }
            reader.close();
            inStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    // read edges for graph from file!
    public void readEdgesFromFile(String inName)
    {
        FileReader inStream;
        BufferedReader reader;
        String row = "";
        try
        {
            inStream = new FileReader(inName);
            reader = new BufferedReader(inStream);

            row = reader.readLine();
            String[] tok = row.split(" ");
            int numLines = Integer.parseInt(tok[1]);

            for (int i = 0; i < numLines; i++)
            {
                row = reader.readLine();
                tok = row.split(" ");
                addEdge(tok[0],tok[1],Double.parseDouble(tok[2]));
            }
            reader.close();
            inStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void resetVertices()
    {
        Iterator iter = vertices.iterator();
        while(iter.hasNext())
        {
            GraphVertex vertex = (GraphVertex)iter.next();
            vertex.clearVisited();
        }
    }

    // BFS
    public Location[] breadthFirstSearch()
    {
        DSALinkedList q = new DSALinkedList();
        DSALinkedList returnList = new DSALinkedList();
        GraphVertex node;

        // make every node unvisited
        resetVertices();
        DSALinkedList vertList = vertices.cloneList();

        node = (GraphVertex)vertList.peekFirst();
        node.setVisited();
        returnList.insertLast(node.getValue());
        q.insertLast(node);

        while (!q.isEmpty())
        {
            node = (GraphVertex)q.removeFirst();

            while (getW(node) != null)
            {
                GraphVertex nextNode = getW(node);
                returnList.insertLast(nextNode.getValue());
                nextNode.setVisited();
                q.insertLast(nextNode);
            }
        }

        int arrSiz = returnList.size();
        Location[] retArr = new Location[arrSiz];
        for(int i = 0; i < arrSiz; i++)
        {
            Location jarVertex = (Location) returnList.removeFirst();
            retArr[i] = jarVertex;
        }
        return retArr;
    }

    // DFS!
    public Location[] depthFirstSearch()
    {
        DSALinkedList s = new DSALinkedList();
        DSALinkedList returnList = new DSALinkedList();
        GraphVertex node;

        // make every node unvisited
        resetVertices();
        DSALinkedList vertList = vertices.cloneList();

        node = (GraphVertex)vertList.peekFirst();
        node.setVisited();
        returnList.insertLast(node.getValue());
        s.insertLast(node);
        while (!s.isEmpty())
        {
            while (getW(node) != null) //checks all edges
            {
                GraphVertex nextNode = getW(node);
                nextNode.setVisited();
                returnList.insertLast(nextNode.getValue());
                s.insertLast(nextNode);
                node = nextNode;                
            }
            node = (GraphVertex)s.removeLast();
        }

        int arrSiz = returnList.size();
        Location[] retArr = new Location[arrSiz];
        for(int i = 0; i < arrSiz; i++)
        {
            Location jarVertex = (Location) returnList.removeFirst();
            retArr[i] = jarVertex;
        }
        return retArr;
    }

    // returns the next unvisited node
    private GraphVertex getW(GraphVertex nodeIn)
    {
        DSALinkedList adjIter = nodeIn.getAdjacent();
        Iterator iter = adjIter.iterator();

        while (iter.hasNext())
        {
            GraphVertex nodeTemp = getVertex(((GraphEdge)iter.next()).getTo());
            if (!nodeTemp.getVisited())
            {
                return nodeTemp;
            }
        }
        return null;
    }

    // Dijkstra-Tri algorithm
    public Location[] Dijkstra(String node1, String node2)
    {
        DSALinkedList q = new DSALinkedList();
        DSALinkedList returnList = new DSALinkedList();
        GraphVertex node;
        boolean found = false;

        // make every node unvisited
        resetVertices();

        node = getVertex(node1);
        node.setVisited();
        returnList.insertLast(node.getValue());
        q.insertLast(node);

        while (!q.isEmpty() && !found)
        {
            node = (GraphVertex)q.removeFirst();
            
            // set all adjacent nodes to visited and copy them to both queue and temp-list
            DSALinkedList tempList = new DSALinkedList();
            while (getW(node) != null && !found)
            {
                GraphVertex nextNode = getW(node);
                nextNode.setVisited();
                tempList.insertLast(nextNode);
                if (nextNode.getLabel().compareTo(node2) == 0)
                {
                    found = true;
                    q.insertLast(nextNode);
                    returnList.insertLast(((GraphVertex)q.peekLast()).getValue());
                }
            }

            // insert only the node with the shortest distance into queue
            if (!tempList.isEmpty() && !found)
            {
                Double dis = Double.MAX_VALUE;
                GraphVertex shortestNode = (GraphVertex)tempList.peekFirst();
                Double u;
                // find the shortest path to the next node
                while (!tempList.isEmpty())
                {
                    GraphVertex tempVert = (GraphVertex)tempList.removeLast();
                    u = getDistance(node, tempVert);
                    if (u < dis)
                    {
                        dis = u;
                        shortestNode = tempVert;
                    }
                }
                q.insertLast(shortestNode);
                returnList.insertLast(shortestNode.getValue());
            }

        }

        int arrSiz = returnList.size();
        Location[] retArr = new Location[arrSiz];

        for (int i = 0; i < arrSiz; i++)
        {
            retArr[i] = (Location)returnList.removeFirst();
        }
        return retArr;
    }

    // returns the distance between 2 vertices
    private Double getDistance(GraphVertex node1, GraphVertex node2)
    {
        String label = node1.getLabel() + node2.getLabel();

        Double ret = 0.0;
        DSALinkedList edgeList = edges.cloneList();

        boolean found = false;
        while (!edgeList.isEmpty() && !found)
        {
            GraphEdge currEdge = (GraphEdge)edgeList.removeFirst();
            if (currEdge.getLabel().compareTo(label) == 0)
            {
                ret = currEdge.getDis();
                found = true;
            }
        }

        return ret;
    }
}
