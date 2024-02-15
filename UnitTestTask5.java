/*
 * Test harness for task 5 of Assignment
 */

public class UnitTestTask5
{
    public static void main(String[] args)
    {
        GraphMap map = new GraphMap();
        map.readVerticesFromFile("UAVdata.txt");
        map.readEdgesFromFile("location.txt");
        Location[] dfsFromMap = map.depthFirstSearch();

        DSAHeap heap = new DSAHeap();

        for (Location loc : dfsFromMap)
        {
            heap.add(loc.getRisk(), loc);
        }

        Object[] locArr = heap.display();
        for (Object obj : locArr) 
        {
            Location loc = (Location)obj;
            System.out.println(loc.display());
        }
        

    }
}