/*
 * Test harness for task 6 of Assignment
 */

public class UnitTestTask6 
{
    public static void main(String[] args)
    {
        GraphMap map = new GraphMap();
        map.readVerticesFromFile("UAVdata.txt");
        map.readEdgesFromFile("location.txt");
        Location[] dfsFromMap = map.depthFirstSearch();

        DSAHeap heap = new DSAHeap();

        System.out.println("Adding locations to heap");
        for (Location loc : dfsFromMap)
        {
            heap.add(loc.getRisk(), loc);
            System.out.println(loc.display());
        }

        System.out.println("Shortest path between 2 riskiest locations");
        Location loc1 = (Location)heap.remove(), loc2 = (Location)heap.remove();
        Location[] shortestPath = map.Dijkstra(loc1.getName(),loc2.getName());
        for (Location location : shortestPath) 
        {
            System.out.println(location.display());    
        }
    }
}
