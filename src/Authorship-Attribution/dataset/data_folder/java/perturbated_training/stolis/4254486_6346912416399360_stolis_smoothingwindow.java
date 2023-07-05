package year2015.round3;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.PriorityQueue;
 import java.util.Queue;
 import java.util.Scanner;
 
 public class SmoothingWindow {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("B-small-attempt2.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             int N = in.nextInt();
             int K = in.nextInt();
             int[] sum = new int[N-K+1];
             for (int n=0; n<sum.length; n++) {
                 sum[n] = in.nextInt();
             }
             int[] diff = new int[sum.length-1];
             for (int n=0; n<diff.length; n++) {
                 diff[n] = sum[n+1]-sum[n];
             }
             Queue<Integer> queue = new PriorityQueue<Integer>();
             for (int start=0; start<K; start++) {
                 int min = 0;
                 int max = 0;
                 int current = 0;
                 for (int i=start; i<diff.length; i+=K) {
                     current += diff[i];
                     min = Math.min(min, current);
                     max = Math.max(max, current);
                 }
                 queue.add(max-min);
             }
             if (sum[0]%K != 0) {
                 int shifts = sum[0]%K;
                 for (int i=0; i<shifts; i++) {
                     int value = queue.poll();
                     queue.add(value+1);
                 }
             }
             int answer = 0;
             while (!queue.isEmpty()) {
                 answer = Math.max(answer, queue.poll());
             }
             
             out.println("Case #"+(t+1)+": "+answer);
         }
 
         out.close();
     }
     
 }
