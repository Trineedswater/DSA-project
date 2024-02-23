/*
 * Abstract data type to use in Heap data structure
 */

public class HeapEntry
{
    private int priority;
    private Object value;

    public HeapEntry(Object inVal, int inPrio)
    {
        value = inVal;
        priority = inPrio;
    }

    public HeapEntry()
    {
        value = null;
        priority = 0;
    }

    public void setVal(Object inVal)
    {
        value = inVal;
    }

    public Object getVal()
    {
        return value;
    }

    public void setPrio(int inPrio)
    {
        priority = inPrio;
    }

    public int getPrio()
    {
        return priority;
    } 
    
    public String display()
    {
        String val;
        if (value == null)
        {
            val = "null";
        }
        else
        {
            val = value.toString();
        }
        return "[" + priority + "," + val + "]";
    }
}