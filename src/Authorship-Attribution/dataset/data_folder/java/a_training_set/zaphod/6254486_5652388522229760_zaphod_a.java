import java.io.*;
 import java.util.Scanner;
 
 public class A
 {
 
    private static boolean[] usedDigits;
 
    public static boolean allUsed()
    {
        for (int i = 0; i < 10; i++)
            if (!usedDigits[i])
                return false;
        return true;
    }
 
    public static void tallyDigits(int N)
    {
        while (N >= 10)
        {
            int nextDigit = N % 10;
            usedDigits[nextDigit] = true;
            N /= 10;
        }
        usedDigits[N] = true;
    }
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "A-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            int baseN = in.nextInt();
            
            if (baseN == 0)
            {
                out.printf("Case #%d: INSOMNIA%n", caseNo);
                System.out.printf("Case #%d: INSOMNIA%n", caseNo);
            }
            else
            {
                int N = baseN;
                usedDigits = new boolean[10];
                tallyDigits(N);
                while (!allUsed())
                {
                    N += baseN;
                    tallyDigits(N);
                }
                out.printf("Case #%d: %d%n", caseNo, N);
                System.out.printf("Case #%d: %d%n", caseNo, N);
            }
        }
        in.close();
        out.close();
    }
 
 }
