import java.io.*;
 import java.util.Scanner;
 
 public class A
 {
 
    static int N;
 
    static int reverse(int number)
    {
        int reverse = 0;
        while (number > 0)
        {
            reverse = 10 * reverse + number % 10;
            number /= 10;
        }
        return reverse;
    }
 
    static int[] times = new int[1000001];
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "A-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        for (int check = 1; check <= 11; check++)
            times[check] = check;
 
        for (int check = 12; check <= 1000000; check++)
        {
            times[check] = 1000001;
        }
 
        for (int check = 12; check <= 1000000; check++)
        {
            int reverse = reverse(check);
            times[check] = Math.min(times[check], times[check - 1] + 1);
            if (reverse < times.length - 1)
                times[reverse] = Math.min(times[reverse], times[check] + 1);
        }
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            N = in.nextInt();
            int noOfTimes = times[N];
 
            System.out.printf("Case #%d: %d%n", caseNo, noOfTimes);
            out.printf("Case #%d: %d%n", caseNo, noOfTimes);
        }
        in.close();
        out.close();
 
    }
 
 }
