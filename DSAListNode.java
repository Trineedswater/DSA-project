/*
 * Abstract data type used in previous practical
 */

import java.io.Serializable;

public class DSAListNode implements Serializable
{
    private Object value;
    private DSAListNode next;
    private DSAListNode prev;

    public DSAListNode(Object val)
    {
        this.value = val;
        this.next = null;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object val)
    {
        this.value = val;
    }

    public DSAListNode getNext()
    {
        return next;
    }

    public void setNext(DSAListNode nextIn)
    {
        this.next = nextIn;
    }

    public DSAListNode getPrev()
    {
        return prev;
    }

    public void setPrev(DSAListNode prevIn)
    {
        this.prev = prevIn;
    }

    // Tri Dao's plus ultra methods
    // returns string value of value stored at node
    public String toPrint()
    {
        String ret = "";
        ret = value.toString();
        return ret;
    }
}
