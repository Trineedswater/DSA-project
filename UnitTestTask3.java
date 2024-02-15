/*
 * Test harness for task 3 of Assignment
 */

public class UnitTestTask3 
{
    public static void main(String[] args)
    {
        GraphMap map = new GraphMap();
        map.readVerticesFromFile("UAVdata.txt");
        map.readEdgesFromFile("location.txt");
        map.displayAsMatrix();
        AssignmentMenu menu = new AssignmentMenu(map);
        map = menu.main();

        map.displayAsList();
    }
}
