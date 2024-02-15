/*
 * test harness for DSAHashtable used in previous practicals
 */
public class UnitTestHash 
{
    public static void main(String[] arg)
    {
        testOne(); //outputs for both tests will be a CSV format
        testTwo(); //testing read / write
        
    } 
    
    public static void testTwo()
    {
        DSAHashTable hashTable = new DSAHashTable();
        hashTable.put("5","five");
        hashTable.put("6","six");
        hashTable.put("7", "seven");
        hashTable.writeCSV("testtwo.csv");
    
        DSAHashTable table2 = new DSAHashTable();
        table2.readCSV("testone.csv");
        table2.writeCSV("testthree.csv");
        System.out.println(table2.get("DSAhelp"));
    }

    static public void testOne()
    {
        DSAHashTable hashTable = new DSAHashTable();
        
        hashTable.put("12","twelve");
        hashTable.put("21", "twentyOne");
        System.out.println("insert finish");
        
        System.out.println(hashTable.get("12"));
        System.out.println(hashTable.get("21"));
        assert hashTable.hasKey("12");
        
        for (int i = 0; i < 50; i++)
        {
            int r = (int)Math.random() * 10;
            int r2 = (int)Math.random() * 10;
            int r3 = (int)Math.random() * 10;
            String s = Integer.toString(r);
            s = s + Integer.toString(i) + Integer.toString(r2) + Integer.toString(r3);
            hashTable.put(s,s);
        }
        System.out.println("finish maxing table");

        hashTable.put("DSAhelp","DSAstruggling");
        hashTable.writeCSV("testOne.csv");
        System.out.println(hashTable.remove("DSAhelp"));
    }

}
