/*
 * Data Structure from previous practical
 */
import java.io.*;

public class DSAHashTable
{
    private DSAHashEntry[] hashArray;
    private int count;

    public DSAHashTable()
    {
        final int size = 8;
        hashArray = new DSAHashEntry[size];
        for(int i = 0; i < size; i++)
        {
            hashArray[i] = new DSAHashEntry();
        }
        count = 0;
    }

    private int hash(String inKey, int arrsiz)
    {
        int ret = 0;

        for (int i = 0; i < inKey.length(); i++)
        {
            ret = ((7 * ret) + (int)inKey.charAt(i));
        }
        return ret % arrsiz;
    }

    public int getLoadFactor()
    {
        int ret = (count * 100 / hashArray.length);
        return ret;
    }

    private int findNextPrime(int n)
    {
        n++;
        for (int i = 2; i < n; i++)
        {
            if (n % i == 0)
            {
                n++;
                i =2;
            }
        }
        return n;

    }

    private void resize(boolean control)
    {
        int newSize;
        if(control)
        {
            newSize = hashArray.length * 2;
        }
        else
        {
            newSize = hashArray.length / 2;
        }
        
        newSize = findNextPrime(newSize);
        
        DSAHashEntry[] newTable = new DSAHashEntry[newSize];
        for (int i = 0; i < newSize; i++)
        {
            newTable[i] = new DSAHashEntry();
        }
    
        // rehash all items
        for (int i = 0, s = hashArray.length; i < s; i++)
        { 
            if (hashArray[i].getState() == 1)
            {
                newTable[hash(hashArray[i].getKey(), newSize)] = hashArray[i]; // rehash and insert
            }
        }

        // initialise all items in array
        for(int i = 0, n = newTable.length; i < n; i++)
        {
            if( newTable[i].equals(null) )
            {
                newTable[i] = new DSAHashEntry();
            }
        }

        hashArray = newTable;
    }

    // takes a key and outputs the index in array where the item is in
    private int find(String inKey)
    {
        int position;
        boolean found = false, giveUp = false;

        position = hash(inKey, hashArray.length);
        int original = position;

        while (!found && !giveUp)
        {
            if (hashArray[position].getState() == 0)
            {
                giveUp = true;
            }
            else if (hashArray[position].getKey().equals(inKey))
            {
                found = true;
            }
            else
            {
                position = ((position + 1) % hashArray.length);
                if(position == original)
                {
                    giveUp = true;
                }
            }
        }

        if (!found)
        {
            throw new IllegalArgumentException("Not found in hash table");
        }
        return position;
    }

    // returns true if input key exists in hash table
    public boolean hasKey(String inKey)
    {
        try
        {
            find(inKey);
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
        return true;
    }

    public Object get(String inKey)
    {
        int position;

        position = find(inKey);
        
        return hashArray[position].getValue();
    }

    public Object remove(String inKey)
    {
        int position;
        Object ret;

        position = find(inKey);
        ret = hashArray[position].getValue();

        DSAHashEntry tempEntry = new DSAHashEntry();
        tempEntry.setPrevUsed();
        hashArray[position] = tempEntry;

        if (getLoadFactor() < 25)
        {
            resize(false);
        }
        return ret;
    }

    public void put(String inKey, Object inValue)
    {
        int position;
        if (getLoadFactor() > 70)
        {
            resize(true);
        }

        position = hash(inKey, hashArray.length);
        int original = position;
        boolean found = false, giveup = false;
        DSAHashEntry tempEntry = new DSAHashEntry(inKey, inValue);
        if (hashArray[position].getState() != 1)
        {
            hashArray[position] = tempEntry;
            count++;
        }
        else
        {
            while(!found && !giveup)
            {
                position = ((position + 1) % hashArray.length);
                if (position == original)
                {
                    giveup = true;
                    throw new IllegalArgumentException("full hash table: " + hashArray.length);
                }
                if (hashArray[position].getKey().equals(inKey))
                {
                    found = true;
                }
                else if (hashArray[position].getState() != 1)
                {
                    giveup = true;
                }
            }
            if (found)
            {
                throw new IllegalArgumentException("Identitcal key found: " + inKey);
            }
            hashArray[position] = tempEntry;
            count++;
        }

    }

    public void readCSV(String fileName)
    {
        hashArray = new DSAHashEntry[8];
        for(int i = 0; i < 8; i++)
        {
            hashArray[i] = new DSAHashEntry();
        }
        count = 0;
        String row = "";
        FileReader inStream;
        BufferedReader reader;
        try
        {
            inStream = new FileReader(fileName);
            reader = new BufferedReader(inStream);
            row = reader.readLine();
            while(row != null)
            {
                String[] tok = row.split(",");
                if(tok.length > 0)
                {
                    put(tok[0],tok[1]);
                }
                row = reader.readLine();
            }
            reader.close();
            inStream.close();
            resize(true);
            resize(false);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    // output for test harnesses for Hash table will be in a CSV format
    public void writeCSV(String fileName)
    {
        FileWriter stream;
        BufferedWriter writer;
        PrintWriter pw;
        try
        {
            stream = new FileWriter(fileName);
            writer = new BufferedWriter(stream);
            pw = new PrintWriter(writer);

            for (int i = 0, n = hashArray.length; i < n; i++) 
            {
                Location loc;
                if (hashArray[i].getValue() instanceof Location)
                {
                    loc = (Location)hashArray[i].getValue();
                    pw.println(hashArray[i].getKey() + "," + loc.display());
                }
                else
                {
                    pw.println(hashArray[i].getKey() + "," + hashArray[i].getValue().toString());
                }
            }
            pw.flush();
            pw.close();
            writer.close();
            stream.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}