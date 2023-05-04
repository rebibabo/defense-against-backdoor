package year2015.round2;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;
 import java.util.Scanner;
 
 public class DrumDecorator {
     static int count;
     static int R;
     static int C;
     static int[][] A;
     static List<int[][]> list = new ArrayList<int[][]>();
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("D-small-attempt1.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             R = in.nextInt();
             C = in.nextInt();
             A = new int[R][C];
             list.clear();
             solve(0,0);
             
             out.println("Case #"+(t+1)+": "+list.size());
         }
 
         out.close();
     }
 
     static void solve(int r, int c) {
         for (int val=1; val<=3; val++) {
             A[r][c] = val;
             if (isOK(r,c)) {
                 if (r == R-1 && c == C-1) {
                     add();
                 } else {
                     int rr;
                     int cc;
                     if (c == C-1) {
                         rr = r+1;
                         cc = 0;
                     } else {
                         rr = r;
                         cc = c+1;
                     }
                     solve(rr,cc);
                 }
             }
             A[r][c] = 0;
         }
     }
 
     static void add() {
         int[][] B = new int[R][C];
         boolean isNew = true;
         for (int shift=0; shift<C; shift++) {
             for (int r=0; r<R; r++) {
                 for (int c=0; c<C; c++) {
                     B[r][c] = A[r][(c+shift)%C];
                 }
             }
             for (int[][] C : list) {
                 if (Arrays.deepEquals(B, C)) {
                     isNew = false;
                 }
             }
         }
         if (isNew) {
             list.add(B);
         }
     }
 
     static boolean isOK(int r, int c) {
         int val = A[r][c];
         int[] stats = stats(r,c);
         if (stats[val] > val || stats[val]+stats[0] < val) return false;
 
         int cc = (c+1)%C;
         val = A[r][cc];
         if (val != 0) {
             stats = stats(r,cc);
             if (stats[val] > val || stats[val]+stats[0] < val) return false;
         }
 
         cc = (c+C-1)%C;
         val = A[r][cc];
         if (val != 0) {
             stats = stats(r,cc);
             if (stats[val] > val || stats[val]+stats[0] < val) return false;
         }
         if (r > 0) {
             val = A[r-1][c];
             if (val != 0) {
                 stats = stats(r-1,c);
                 if (stats[val] > val || stats[val]+stats[0] < val) return false;
             }
         }
         if (r+1 < R) {
             val = A[r+1][c];
             if (val != 0) {
                 stats = stats(r+1,c);
                 if (stats[val] > val || stats[val]+stats[0] < val) return false;            
             }
         }
         return true;
     }
 
     static int[] stats(int r, int c) {
         int[] stats = new int[4];
         stats[A[r][(c+1)%C]]++;
         stats[A[r][(c+C-1)%C]]++;
         if (r > 0) {
             stats[A[r-1][c]]++;
         }
         if (r+1 < R) {
             stats[A[r+1][c]]++;
         }
         return stats;
     }
     
 }
