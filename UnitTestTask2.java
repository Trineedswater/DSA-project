/*
 * Test harness for Task 2 of Assignment
 */

public class UnitTestTask2 
{
    public static void main(String[] args)
    {
        GraphMap map = new GraphMap();
        map.readVerticesFromFile("UAVdata.txt");
        map.readEdgesFromFile("location.txt");

        Location[] locArr = map.Dijkstra(args[0], args[1]);
        for (Location location : locArr) 
        {
            System.out.println(location.display());    
        }

        System.out.println("");

        map.displayAsList();
    }
}
