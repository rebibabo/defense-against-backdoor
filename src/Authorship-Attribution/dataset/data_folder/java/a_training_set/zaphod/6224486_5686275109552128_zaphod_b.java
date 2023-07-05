import java.io.*;
 import java.util.Collections;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 public class B
 {
    static int D;
    static int smallMinutes;
 
    public static PriorityQueue<Integer> regularMinute(PriorityQueue<Integer> pq)
    {
        PriorityQueue<Integer> newPQ = new PriorityQueue<Integer>(D,
                Collections.reverseOrder());
        for (int next : pq)
        {
            int newStack = next - 1;
            if (newStack > 0)
                newPQ.add(newStack);
        }
        return newPQ;
    }
 
    public static PriorityQueue<Integer> specialMinute(
            PriorityQueue<Integer> pq, int firstHalf, int secondHalf)
    {
        PriorityQueue<Integer> newPQ = new PriorityQueue<Integer>(D,
                Collections.reverseOrder());
        newPQ.addAll(pq);
        
        
        newPQ.remove();
        newPQ.add(firstHalf);
        newPQ.add(secondHalf);
        return newPQ;
    }
 
    public static void findTime(PriorityQueue<Integer> pq, int minutesSoFar)
    {
        if (minutesSoFar >= smallMinutes)
            return;
        if (pq.isEmpty())
        {
            if (minutesSoFar < smallMinutes)
                smallMinutes = minutesSoFar;
            return;
        }
 
        findTime(regularMinute(pq), minutesSoFar + 1);
 
        
        int largest = pq.peek();
        int lower = largest - largest / 2;
        for (int firstHalf = largest - 2; firstHalf >= lower; firstHalf--)
            findTime(specialMinute(pq, firstHalf, largest - firstHalf),
                    minutesSoFar + 1);
    }
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "B-small1";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            D = in.nextInt();
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>(D,
                    Collections.reverseOrder());
            for (int i = 0; i < D; i++)
            {
                pq.add(in.nextInt());
            }
            System.out.println(pq);
            smallMinutes = Integer.MAX_VALUE;
            findTime(pq, 0);
 
            System.out.printf("Case #%d: %d%n", caseNo, smallMinutes);
            out.printf("Case #%d: %d%n", caseNo, smallMinutes);
 
        }
        in.close();
        out.close();
 
    }
 
 }
