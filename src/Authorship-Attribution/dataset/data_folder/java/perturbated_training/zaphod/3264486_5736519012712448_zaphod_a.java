import java.io.*;
 import java.util.Scanner;
 
 public class A
 {
    static int K;
 
    static void flip(char[] pancakes, int start)
    {
        for (int i = start; i < start + K; i++)
            if (pancakes[i] == '+')
                pancakes[i] = '-';
            else
                pancakes[i] = '+';
    }
 
    static boolean allHappy(char[] pancakes)
    {
        
        for (int i = pancakes.length-1; i >= 0; i--)
            if (pancakes[i] == '-')
                return false;
 
        return true;
    }
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "A-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            char[] pancakes = in.next().toCharArray();
            K = in.nextInt();
            
            int noOfFlips = 0;
            for (int i = 0; i <= pancakes.length - K ; i++)
            {
                if (pancakes[i] == '-')
                {
                    flip(pancakes, i);
                        noOfFlips++;
                }
            }
 
 
 
 
 
            if (noOfFlips == -1 || !allHappy(pancakes))
            {
                out.printf("Case #%d: IMPOSSIBLE%n", caseNo);
                System.out.printf("Case #%d: IMPOSSIBLE%n", caseNo);
            }
            else
            {
                out.printf("Case #%d: %d%n", caseNo, noOfFlips);
                System.out.printf("Case #%d: %d%n", caseNo, noOfFlips);
            }
        }
        in.close();
        out.close();
    }
 
 }
