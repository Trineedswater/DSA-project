/*
 * Test harness for GraphMap used in previous practicals
 */

public class UnitTestGraph 
{
    public static void main(String[] args)
    {
        GraphMap map = new GraphMap();
        System.out.println("Reading Vertices from File");
        map.readVerticesFromFile("UAVdata.txt");
        System.out.println("Reading Edges from File");
        map.readEdgesFromFile("location.txt");

        System.out.println("Vertex count " + map.getVertexCount());
        if (map.hasVertex("A"))
        {
            System.out.println("Graph has vertex A");
            System.out.println(((Location)map.getVertexVal("A")).display());
            System.out.println("");
        }

        System.out.println("displaying graph as list");
        map.displayAsList();
        System.out.println("");
        if (map.hasVertex("A"))
        {
            System.out.println("Graph has vertex A");
            System.out.println(((Location)map.getVertexVal("A")).display());
            System.out.println("");
        }
        System.out.println("displaying graph as matrix");
        map.displayAsMatrix();
        System.out.println("");

        System.out.println("Depth First Search");
        Object[] dfsMap = map.depthFirstSearch();
        for (Object i : dfsMap)
        {
            Location loc = (Location)i;
            System.out.println(loc.display());
        }
        System.out.println("");
        System.out.println("Breadth First Search");
        Object[] bfsMap = map.breadthFirstSearch();
        for (Object i : bfsMap)
        {
            Location loc = (Location)i;
            System.out.println(loc.display());
        }
    }
}
