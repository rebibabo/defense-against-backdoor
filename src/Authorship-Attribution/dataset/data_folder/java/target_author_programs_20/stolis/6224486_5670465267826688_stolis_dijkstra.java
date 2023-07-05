package year2015.qualification;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class Dijkstra {
     
     
     
     
     
     
     
     
     static int[][] times = new int[][] {
         {0,1,2,3,4,5,6,7},
         {1,4,3,6,5,0,7,2},
         {2,7,4,1,6,3,0,5},
         {3,2,5,4,7,6,1,0},
         {4,5,6,7,0,1,2,3},
         {5,0,7,2,1,4,3,6},
         {6,3,0,5,2,7,4,1},
         {7,6,1,0,3,2,5,4}
     };
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("C-small-attempt0.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             int L = in.nextInt();
             long X = in.nextLong();
             if (X >= 16) {
                 X = 12+X%4;
             }
             String s = in.next();
             int[] A = new int[(int)(L*X)];
             for (int x=0; x<X; x++) {
                 for (int l=0; l<L; l++) {
                     char c = s.charAt(l);
                     int value;
                     if (c == 'i') {
                         value = 1;
                     } else if (c == 'j') {
                         value = 2;
                     } else {
                         value = 3;
                     }
                     A[x*L+l] = value;
                 }
             }
             boolean ok = false;
             int index = 0;
             int value = 0;
             while (index < A.length && value != 1) {
                 value = times[value][A[index]];
                 index++;
             }
             if (index < A.length) {
                 value = 0;
                 while (index < A.length && value != 2) {
                     value = times[value][A[index]];
                     index++;
                 }
                 if (index < A.length) {
                     value = 0;
                     while (index < A.length) {
                         value = times[value][A[index]];
                         index++;
                     }
                     ok = value == 3;
                 }
             }
             out.println("Case #"+(t+1)+": "+(ok ? "YES" : "NO"));
         }
 
         out.close();
     }
     
 }
