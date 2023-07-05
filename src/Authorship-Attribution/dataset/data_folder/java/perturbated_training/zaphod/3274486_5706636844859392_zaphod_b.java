import java.io.*;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class B
 {
    static int AC, AJ;
    static Activity []C, J;
    static final int NOON = 720;
    
    public static void main(String[] args) throws IOException
    {
        String fileName = "B-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            AC = in.nextInt();
            AJ = in.nextInt();
            C = new Activity[AC];
            J = new Activity[AJ];
            for (int i = 0; i < AC; i++)
            {
                C[i] = new Activity(in.nextInt(), in.nextInt());
            }
            for (int i = 0; i < AJ; i++)
            {
                J[i] = new Activity(in.nextInt(), in.nextInt());
            }
            Arrays.sort(C);
            
            Arrays.sort(J);
            int  noOfExchanges = 2;
            
            if (AJ == 2)
            {
                if (J[0].totalTime(J[1]) < 720)
                {
                    noOfExchanges = 3;
                    if (J[0].start >= NOON || J[1].end <= NOON)
                        noOfExchanges = 2;
                }
                else
                {
                    noOfExchanges = 5;
                }
                    
            }
            else if (AC == 2)
            {
                if (C[0].totalTime(C[1]) < 720)
                {
                    noOfExchanges = 3;
                    if (C[0].start >= NOON || C[1].end <= NOON)
                        noOfExchanges = 2;
                }
                else
                {
                    noOfExchanges = 4;
                }
            }
            else
            {
                if ((AC == 1 && C[0].contains(NOON))||(AJ == 1 && J[0].contains(NOON)))
                      noOfExchanges = 4;
            }
            
        
                
            System.out.printf("Case #%d: %d%n", caseNo, noOfExchanges);
            out.printf("Case #%d: %d%n", caseNo, noOfExchanges);
        }
        in.close();
        out.close();
 
    }
 
 }
 
  class Activity implements Comparable<Activity>
 {
    int start, end;
 
    public Activity(int start, int end)
    {
        this.start = start;
        this.end = end;
    }
    public String toString()
    {
        return start + " "+ end;
    }
    
    public boolean contains (int time)
    {
        return (time> start && time < end);
    }
    
    public boolean before (int time)
    {
        return end < time;
    }
    public boolean after(int time)
    {
        return start < time;
    }
    
    public int totalTime(Activity other)
    {
        return other.end - this.start;
    }
    @Override
    public int compareTo(Activity other)
    {
        return this.start - other.start;
    }
 }
