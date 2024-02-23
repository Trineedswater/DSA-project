/*
 * Heap data structure
 */

public class Heap
{
    private HeapEntry[] heap; //stores entries
    private int count; //stores number of entries into heap

    public Heap()
    {
        final int size = 3;
        count = 0;
        heap = new HeapEntry[size];
        for (int i = 0; i < size; i++)
        {
            heap[i] = new HeapEntry();
        }
    }

    public int getnum()
    {
        return count;
    }

    private void resize(boolean polarity)
    {
        int newsize;
        if(polarity)
        {
            newsize = (heap.length) * 3;
            newsize++;
            HeapEntry[] newHeap = new HeapEntry[newsize];
            
            int i = 0;
            for(HeapEntry en : heap)
            {
                newHeap[i] = en;
                i++;
            }
            while (i < newsize)
            {
                newHeap[i] = new HeapEntry();
                i++;
            }
            heap = newHeap;
        }
        else
        {
            newsize = (heap.length - 1) / 2;
            newsize++;

            HeapEntry[] newHeap = new HeapEntry[newsize];
            for(int i = 0; i < newsize; i++)
            {
                newHeap[i] = heap[i];
            }
        }
        
    }

    public void add(int prio, Object val)
    {
        if ((count * 100 / heap.length) > 90)
        {
            resize(true);
        }

        heap[count].setPrio(prio);
        heap[count].setVal(val);
        trickleUp(count);
        count++;
    }

    public Object remove()
    {
        int lastEntry = count - 1;
        HeapEntry ret = heap[0];
        HeapEntry jar = heap[lastEntry];
        heap[0] = jar;
        heap[lastEntry] = new HeapEntry();

        trickleDown(heap, 0, count);
        count--;
        if ((count * 100 / heap.length) < 40)
        {
            resize(false);
        }
        return ret.getVal();
    }

    public Object[] display()
    {
        Object[] ret = new Object[count];
        for (int i = 0, n = ret.length; i < n; i++) 
        {
            ret[i] = heap[i].getVal();
        }
        return ret;
    }

    // while the current index is not at root, or the priority of current index is greater than parent's
    // swap the current HeapEntry with parents HeapEntry
    private void trickleUp(int index)
    {
        int parentIdx = (index - 1) / 2;

        while (index > 0 && heap[index].getPrio() > heap[parentIdx].getPrio())
        {
            HeapEntry temp = heap[parentIdx];
            heap[parentIdx] = heap[index];
            heap[index] = temp;
            index = parentIdx;
            parentIdx = (index - 1) / 2;
        }
    }

    private void trickleDown(HeapEntry[] heapIn, int index,int numitems)
    {
        int lChild = index * 2 + 1;
        int rChild = lChild + 1;
        boolean keepGoing = true;

        while(keepGoing && (lChild < numitems))
        {
            keepGoing = false;
            int largeIdx = lChild;
            if (rChild < numitems)
            {
                if (heapIn[lChild].getPrio() < heapIn[rChild].getPrio())
                {
                    largeIdx = rChild;
                }
            }
            if (heapIn[largeIdx].getPrio() > heapIn[index].getPrio())
            {
                HeapEntry tempHeapEntry = heapIn[index];
                heapIn[index] = heapIn[largeIdx];
                heapIn[largeIdx] = tempHeapEntry;
                keepGoing = true;
            }
            index = largeIdx;
            lChild = index * 2 + 1;
            rChild = lChild + 1;
        }
    }

    //trickle down does not create a stable sort
    public HeapEntry[] heapify(HeapEntry[] arrin, int numitems)
    {
        for (int i = ((arrin.length / 2) - 1); i >= 0; i--)
        {
            trickleDown(arrin, i, numitems);
        }
        return arrin;
    }

    public int[] heapSort(int[] arrin)
    {
        HeapEntry[] tempEntries = new HeapEntry[arrin.length];

        for(int i = 0, n = arrin.length; i < n; i++)
        {
            tempEntries[i] = new HeapEntry(arrin[i], arrin[i]);
        }
        HeapEntry[] secondEntries = heapify(tempEntries, arrin.length);

        for(int i = (arrin.length - 1); i > 0; i--)
        {
            HeapEntry tempEntry = secondEntries[i];
            secondEntries[i] = secondEntries[0];
            secondEntries[0] = tempEntry;
            trickleDown(secondEntries, 0, i);
        }

        for(int i = 0, n = arrin.length; i < n; i++)
        {
            arrin[i] = secondEntries[i].getPrio();
        }
        return arrin;
    }
}