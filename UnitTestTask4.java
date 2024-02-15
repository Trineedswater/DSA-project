/*
 * Test harness for task 4 of Assignment
 */

public class UnitTestTask4 
{
    public static void main(String[] args)
    {
        GraphMap map = new GraphMap();
        map.readVerticesFromFile("UAVdata.txt");
        map.readEdgesFromFile("location.txt");
        
        DSAHashTable table = new DSAHashTable();
        Location[] locArr = map.depthFirstSearch();
        for (Location location : locArr) 
        {
            table.put(location.getName(), location);    
        }
        table.writeCSV("hashTableDFS.csv");
    }
}
