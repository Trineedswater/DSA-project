/*
 * Abstract data type used in LinkedList
 */

import java.io.Serializable;

public class ListNode implements Serializable
{
    private Object value;
    private ListNode next;
    private ListNode prev;

    public ListNode(Object val)
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

    public ListNode getNext()
    {
        return next;
    }

    public void setNext(ListNode nextIn)
    {
        this.next = nextIn;
    }

    public ListNode getPrev()
    {
        return prev;
    }

    public void setPrev(ListNode prevIn)
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
