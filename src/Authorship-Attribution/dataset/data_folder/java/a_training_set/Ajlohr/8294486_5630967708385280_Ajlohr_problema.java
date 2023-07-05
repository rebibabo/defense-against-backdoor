package Round1B;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class ProblemA {
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner sc = new Scanner(new FileInputStream("A-small-attempt0.in"));
        PrintStream out = new PrintStream(new FileOutputStream("out1.txt"));
        long t = sc.nextLong();
        sc.nextLine();
        for(int curcase=1;curcase<=t;curcase++)
        {
            int D = sc.nextInt();
            int N = sc.nextInt();
            int[] K = new int[N];
            int[] S = new int[N];
            for(int i=0;i<N;i++)
            {
                K[i] = sc.nextInt();
                S[i] = sc.nextInt();
            }
            double fastest = Double.MAX_VALUE;
            for(int i=0;i<N;i++)
            {
                fastest = Math.min(fastest, ((double)S[i]*D)/(D-K[i]));
            }
            
            out.println("Case #"+curcase+": "+fastest);
        }
        
    }
 }
