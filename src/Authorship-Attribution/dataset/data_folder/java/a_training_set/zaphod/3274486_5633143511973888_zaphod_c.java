import java.io.*;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class C
 {
    static int N, K;
    static double U;
    static double[] P;
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "C-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            N = in.nextInt();
            K = in.nextInt();
            U = in.nextDouble();
            P = new double[N];
            for (int i = 0; i < N; i++)
            {
                P[i] = in.nextDouble();
            }
 
            
            Arrays.sort(P);
 
 
 
            int next = 0;
            while (next < N && U > 0.00000001)
            {
 
 
 
 
                
                while (next < N - 1 && P[next] == P[next + 1])
                {
                    next++;
                }
                
 
                if (next == N - 1)
                {
                    
                    double addIn = U / N;
                    for (int i = 0; i < N; i++)
                    {
                        P[i] += addIn;
                    }
                    U = 0;
                }
                else
                {
                    double dif = P[next + 1] - P[next];
                    int number = next + 1;
                    double addIn = Math.min(dif, U / number);
                    for (int i = 0; i <= next; i++)
                         P[i] += addIn;
                    U -= addIn * number;
                    next= 0;
                }
 
            }
 
 
 
 
 
            
            for (int i = 0; i < N; i++)
            {
                if (P[i] > 1)
                    P[i] = 1;
            }
            
            double probNotFail = 1;
            for (int i = 0; i < N; i++)
            {
                probNotFail *= P[i];
            }
            
            System.out.printf("Case #%d: %f%n", caseNo, probNotFail);
            out.printf("Case #%d: %f%n", caseNo, probNotFail);
        }
        in.close();
        out.close();
    }
 }