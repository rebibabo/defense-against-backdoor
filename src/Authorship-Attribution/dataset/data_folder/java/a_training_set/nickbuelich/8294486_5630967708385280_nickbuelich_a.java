import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class A {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("A.in"));
        PrintWriter out = new PrintWriter(new File("A.out"));
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            long D = sc.nextLong();
            int N = sc.nextInt();
            long[] K = new long[N];
            long[] S = new long[N];
            double ans = 0;
            for(int a=0;a<N;a++){
                K[a]=sc.nextLong();
                S[a]=sc.nextLong();
                ans = Math.max(ans, (D-K[a]+0.0)/S[a]);
            }
            
            System.out.printf("Case #%d: %f%n", t, D/ans);
            out.printf("Case #%d: %f%n", t, D/ans);
        }
        out.close();
    }
 }
