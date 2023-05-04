import java.io.*;
 import java.util.Scanner;
 
 public class A
 {
    static int D, N;
    static int [] K;
    static int [] S;
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "A-small1";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            D = in.nextInt();
    
            N = in.nextInt();
            K = new int[N];
            S = new int [N];
            for (int i = 0; i < N; i++)
            {
                K[i] = in.nextInt();
                S[i] = in.nextInt();
            }
 
            double maxArrivalTime = 0;
            for (int i = 0; i < N; i++)
            {
                double arrivalTime = ((double)(D-K[i]))/S[i];
                if (arrivalTime > maxArrivalTime)
                    maxArrivalTime = arrivalTime;
                
            
            }
            
            double maxSpeed = D/maxArrivalTime;     
            System.out.printf("Case #%d: %s%n", caseNo, maxSpeed);
            out.printf("Case #%d: %s%n", caseNo, maxSpeed);
            
        }
        in.close();
        out.close();
    }
 }
