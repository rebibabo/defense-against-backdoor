import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 
 public class Main {
 
     public static void main(String[] args) throws IOException {
 
         Scanner in = new Scanner(new FileInputStream("input.txt"));
         System.setOut(new PrintStream("output.txt"));
         int T = in.nextInt();
         for (int t = 1; t <= T; t++) {
             int N = in.nextInt();
             int P = in.nextInt();
             int r[] = new int[N];
             Pair[][] q = new Pair[N][P];
            
             for (int i = 0; i < N; i++ ){
                 r[i] = in.nextInt();
             }
 
             for (int i = 0; i < N; i++) {
                 for (int j = 0; j < P; j++) {
                     int x = in.nextInt();
                     int ma = x * 10 / (9 * r[i]);
                     int mi = x * 10 / (11 * r[i]);
                     if ((x * 10) % (11 * r[i]) != 0)mi++;
 
                     
 
                     q[i][j] = ma >= mi ? new Pair(mi, ma) : new Pair(-1, -1);
                 }
 
                 Arrays.sort(q[i]);
             }
 
             int idx[] = new int[N];
             int ret = 0;
             for (int k = 1; k < 1200000;) {
                 boolean fin = false;
                 boolean find = true;
 
                 for (int i = 0; i < N; i++) {
                     while (idx[i] < P && q[i][idx[i]].ma < k)idx[i]++;
                     if (idx[i] == P) {
                         fin = true;
                         break;
                     } else {
                         if (q[i][idx[i]].mi > k)find = false;
                     }
                 }
                 if (fin)break;
                 if (find) {
                     ret++;
                     for (int i = 0; i < N; i++) {
                         idx[i]++;
                     }
                 } else {
                     k++;
                 }
 
             }
 
 
             System.out.println("Case #" + t + ": " + ret);
 
         }
     }
 
     public static class Pair implements Comparable{
         public int mi;
         public int ma;
         public Pair(int mi, int ma) {
             this.mi = mi;
             this.ma = ma;
         }
 
         @Override
         public int compareTo(Object o) {
             Pair p =(Pair) o;
             if (this.mi == p.mi) {
                 return this.ma - p.ma;
             }
 
             return this.mi - p.mi;
         }
     }
 }
