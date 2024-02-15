/*
 * Data structure used in previous practicals
 */

import java.io.Serializable;
import java.util.Iterator;

public class DSALinkedList implements Iterable, Serializable
{
    private DSAListNode head;
    private DSAListNode tail;

    private class DSALinkedListIterator implements Iterator
    {
        private DSAListNode iterNext;
        
        public DSALinkedListIterator(DSALinkedList list)
        {
            iterNext = list.head;
        }

        public boolean hasNext()
        {
            boolean ret = true;
            if (iterNext == null)
                ret = false;
            return ret;
        }

        public Object next()
        {
            Object val;

            if (iterNext == null)
                val = null;
            else
            {
                val = iterNext.getValue();
                iterNext = iterNext.getNext();
            }
            return val;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    //end of nested class

    public DSALinkedList()
    {
        head = null;
        tail = null;
    }

    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);
    }

    public int size()
    {
        int ret = 0;
        Iterator iter = this.iterator();
        while (iter.next() != null)
        {
            ret++;
        }
        return ret;
    }

    public String display()
    {
        String jar = "[";
        Iterator iter = this.iterator();

        while (iter.hasNext())
        {
            Object jar2 = iter.next();

            if (jar2 == null)
                jar = jar.concat("null");
            else
                jar = jar.concat(jar2.toString());
            
            if (iter.hasNext())
                jar = jar.concat(",");
        }

        jar = jar.concat("]");
        return jar;
    }

    public DSALinkedList cloneList()
    {
        DSALinkedList ret = new DSALinkedList();
        Iterator iter = this.iterator();

        while (iter.hasNext())
        {
            ret.insertLast(iter.next());
        }
        return ret;
    }

    public boolean isEmpty()
    {
        boolean ret = false;
        if (head == null)
            ret = true;
        return ret;
    }

    public Object peekFirst()
    {
        if (isEmpty())
            throw new IllegalArgumentException("Linked list is empty!");
        else
            return head.getValue();
    }

    public Object peekLast()
    {
        if (isEmpty())
            throw new IllegalArgumentException("Linked list is empty!");
        else
        {
            // DSAListNode jar = head;
            // while (jar.getNext() != null)
            // {
            //     jar = jar.getNext();
            // }
            // return jar.getValue();
            return tail.getValue();
        }
    }    

    public void insertFirst(Object newVal)
    {
        DSAListNode newNode = new DSAListNode(newVal);
        if (head == null)
        {
            head = newNode;
            tail = newNode;
            head.setNext(tail);
            tail.setPrev(head);
        }
        else
        {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
    }

    public void insertLast(Object newVal)
    {
        DSAListNode newNode = new DSAListNode(newVal);
        if (tail == null)
        {
            tail = newNode;
            head = newNode;
        }
        else
        {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    public Object removeFirst()
    {
        Object ret;
        if (head == null)
        {
            System.out.println("list 189. " + this.size());
            throw new IllegalArgumentException("Linked list is empty");
        }

        if (head == tail)
        {
            ret = head.getValue();
            head = null;
            tail = null;
        }
        else
        {
            ret = head.getValue();
            head = head.getNext();
            head.setPrev(null);
        }
        return ret;
    }

    public Object removeLast()
    {
        Object ret;
        if (tail == null)
            throw new IllegalArgumentException("Linked list is empty!");

        if (head == tail)
        {
            ret = tail.getValue();
            head = null;
            tail = null;
        }
        else
        {
            ret = tail.getValue();
            tail = tail.getPrev();
            tail.setNext(null);
        }
        // else if (head.getNext() == null)
        // {
        //     ret = head.getValue();
        //     head = null;
        // }
        // else
        // {
        //     DSAListNode prevNode = null;
        //     DSAListNode node = head;
        //     while (node.getNext() != null)
        //     {
        //         prevNode = node;
        //         node = node.getNext();
        //     }
        //     prevNode.setNext(null);
        //     ret = node.getValue();
        // }
        return ret;
    }
}
