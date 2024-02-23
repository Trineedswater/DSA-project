/*
 * Test harness for Heap
 */

import java.util.*;

public class UnitTestHeap
{
    public static void main(String[] args)
    {
        run();

        //run2();
        
    }

    public static void run()
    {
        Heap heap = new Heap();
        
        for (int i = 0; i < 5; i++)
        {
            heap.add(i, i);
        }
        Object[] dis = heap.display();
        for (Object object : dis) {
            System.out.print(Integer.toString((int)object) + " ");
        }
        System.out.println("");
        heap.add(10, 10);
        dis = heap.display();
        for (Object object : dis) {
            System.out.print(Integer.toString((int)object) + " ");
        }
        
        heap.add(5, 5);
        dis = heap.display();
        System.out.println("");
        for (Object object : dis) {
            System.out.print(Integer.toString((int)object) + " ");
        }

        System.out.println("");
        for (int i = 0; i < 5; i++)
        {
            System.out.println(heap.remove().toString());
        }
    }

    public static void run2()
    {
        System.out.println("sortin!");
        Random random = new Random();
        int[] intarr = new int[100];
        for(int i = 0, n = intarr.length; i < n; i++)
        {
            intarr[i] = random.nextInt(10);
            System.out.print(intarr[i] + " "); //show array going in
        }
        System.out.println();

        Heap heapSorting = new Heap();
        heapSorting.heapSort(intarr);
        for (int i : intarr)
        {
            System.out.print(i + " "); //show sorted array
        }
    }
}