public class main 
{
    public static void main(String[] args)
    {
        GraphMap map = new GraphMap();
        map.readVerticesFromFile("UAVdata.txt");
        map.readEdgesFromFile("location.txt");
        Menu menu = new Menu(map);
        Heap heap = new Heap();
        HashTable table = new HashTable();
        Location[] locArr = map.depthFirstSearch();
        for (Location location : locArr) 
        {
            table.put(location.getName(), location);    
            heap.add(location.getRisk(), location);   
        }
        
        while (heap.getnum() > 0)
        {
            Location loc1 = (Location)heap.remove(), loc2 = (Location)heap.remove();
            Location[] path = map.Dijkstra(loc1.getName(),loc2.getName());  

            System.out.println("Most optimal path for UAV this cycle: ");

            for (Location location : path) 
            {
                System.out.println(location.display());    
            }
            System.out.println("");
        }

        map = menu.main();
        locArr = map.depthFirstSearch();
        for (Location location : locArr) 
        {
            table.put(location.getName(), location);    
            heap.add(location.getRisk(), location);   
        }

        while (heap.getnum() > 2)
        {
            Location loc1 = (Location)heap.remove(), loc2 = (Location)heap.remove();
            Location[] path = map.Dijkstra(loc1.getName(),loc2.getName());  

            System.out.println("Most optimal path for UAV this cycle: ");

            for (Location location : path) 
            {
                System.out.println(location.display());    
            }
            System.out.println("");
        }
    }    
}
