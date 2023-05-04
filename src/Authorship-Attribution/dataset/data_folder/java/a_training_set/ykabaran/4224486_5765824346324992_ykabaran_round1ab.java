package googlecodejam2015;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 
 
 public class Round1AB implements GoogleCodeJam2015.Problem {
 
   private int B;
   private int N;
   private int[] M;
   private int sol;
 
   @Override
   public void setup(BufferedReader input) throws IOException {
     String[] args = input.readLine().split(" ");
     B = Integer.parseInt(args[0]);
     N = Integer.parseInt(args[1]);
 
     args = input.readLine().split(" ");
     M = new int[B];
     for (int i = 0; i < B; ++i) {
       M[i] = Integer.parseInt(args[i]);
     }
   }
 
   @Override
   public void solve() {
     for (int i = 0; i < B; ++i) {
       long cMin = 0;
       long cMax = N;
       long cCurr = (cMax - cMin) / 2;
       while (true) {
         long minute = cCurr * M[i];
         long sumBefore = 0;
         for (int j = 0; j < B; ++j) {
           sumBefore += minute / M[j];
           long mod = (minute % M[j]);
           if (mod > 0 || (j < i && mod == 0)) {
             ++sumBefore;
           }
         }
         if (sumBefore == N - 1) {
           sol = (i + 1);
           return;
         } else {
           if (sumBefore > N - 1) {
             cMax = cCurr;
           } else {
             cMin = cCurr;
           }
           long cNew = cMin + (cMax - cMin) / 2;
           if (cNew == cCurr) {
             break;
           }
           cCurr = cNew;
         }
       }
     }
   }
 
   @Override
   public String getSolution() {
     return Integer.toString(sol);
   }
 
 }
