/*
 * Menu class to display data and communicate with user
 */

import java.util.Scanner;

public class Menu 
{
    GraphMap map;
    int userChoice;

    public Menu(GraphMap pMap)
    {
        map = pMap;
    }

    public GraphMap main()
    {
        menu();
        
        do
        {
            userChoice = getChoice();
            switch(userChoice)
            {
                case 1:
                    System.out.println("Please input new location in form: \"location_name location_temperature location_humidity location_wind_speed\":");
                    insertLocation();
                    break;
                case 2:
                    System.out.println("Please input name of location:");
                    removeLocation();
                    break;
                case 3:
                    System.out.println("Please input new edge in form: \"location_1_name location_2_name distance_between_locations\":");
                    insertEdge();
                    break;
                case 4:
                    System.out.println("Please input in form: \"location_1_name location_2_name\":");
                    removeEdge();
                    break;
                case 5:
                    System.out.println("Please input the name of location: ");
                    searchVertex();
                    break;
                case 6:
                    System.out.println("Please input in form: \"location_1_name location_2_name\":");
                    searchEdge();
                    break;
                case 7:
                    menu();
                    break;
                case 8:
                    map.displayAsList();
                    break;
                case 0:
                    System.out.println("Thank you! Have a nice day!");
                    break;
            }
            System.out.println("");
        }
        while (userChoice != 0);
        
        return map;
    }

    private void searchEdge()
    {
        String userString = getInput();
        String[] tok = userString.split(" ");
        Double dis = map.getEdgeDis(tok[0], tok[1]);
        System.out.println("Distance from " + tok[0] + " to " + tok[1] + " is " + dis.toString());
    }

    private void searchVertex()
    {
        String userString = getInput();
        Location loc = (Location)map.getVertexVal(userString);
        System.out.println(loc.display());
        Object[][] adjMat = map.getAdjacent(userString);
        System.out.println("Adjacent locations: ");
        for (Object[] objects : adjMat) 
        {
            String name = (String)objects[0];
            Double dis = (Double)objects[1];
            System.out.println(name + ", Distance: " + dis);
        }
    }

    private void removeEdge()
    {
        String userString = getInput();
        String[] tok = userString.split(" ");
        Double dis = map.removeEdge(tok[0], tok[1]);
        System.out.println("Removed edge: " + tok[0] + " to " + tok[1] + " Distance: " + dis);
    }

    private void insertEdge()
    {
        String userString;
        userString = getInput();
        String[] tok = userString.split(" ");
        map.addEdge(tok[0], tok[1], Double.parseDouble(tok[2]));
    }

    private void removeLocation()
    {
        String userString;
        
        userString = getInput();
        Location loc = (Location)map.removeVertex(userString);
        System.out.println("Location removed " + loc.display());
    }

    private void insertLocation()
    {
        String userString;
        
        userString = getInput();
        String[] tok = userString.split(" ");
        Location newLoc = new Location(tok[0], Integer.parseInt(tok[1]),Integer.parseInt(tok[2]),Integer.parseInt(tok[3]));
        map.addVertex(tok[0], newLoc);
    }

    private void menu()
    {
        System.out.println("");
        System.out.println("Welcome to the Map menu: ");
        System.out.println("1. Insert Location");
        System.out.println("2. Delete Location");
        System.out.println("3. Insert Edge");
        System.out.println("4. Remove Edge");
        System.out.println("5. Search Location");
        System.out.println("6. Search Edge");
        System.out.println("7. Bring up menu");
        System.out.println("8. Display the map as an adjacency list");
        System.out.println("0. Exit");
        System.out.println("");
    }

    private int getChoice()
    {
        int input = 0;
        System.out.println("please input your choice");
        

        try
        {
            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return input;
    }

    private String getInput()
    {
        String ret = null;

        try
        {
            Scanner sc = new Scanner(System.in);
            ret = sc.nextLine();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return ret;
    }
}
