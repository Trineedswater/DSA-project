/*
 * Abstract data type implemented in previous practical
 */

public class DSAHashEntry 
{
    private String key;
    private Object value;
    private int state; // 0 = free , 1 = used , 2 = previously used

    public DSAHashEntry()
    {
        key = "";
        value = "";
        state = 0;
    }

    public DSAHashEntry(String inKey, Object inValue)
    {
        key = inKey;
        value = inValue;
        state = 1;
    }

    public DSAHashEntry(DSAHashEntry inHashEntry)
    {
        key = inHashEntry.getKey();
        value = inHashEntry.getValue();
        state = inHashEntry.getState();
    }

    public void setKey(String inKey)
    {
        key = inKey;
    }

    public void setValue(Object inValue)
    {
        value = inValue;
    }

    public void setUsed()
    {
        state = 1;
    }

    public void setFree()
    {
        state = 0;
    }

    public void setPrevUsed()
    {
        state = 2;
    }

    public int getState()
    {
        return state;
    }

    public Object getValue()
    {
        return value;
    }

    public String getKey()
    {
        return key;
    }
}
