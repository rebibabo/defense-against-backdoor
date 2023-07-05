package contest_2017.round_1c;
 
 import java.io.File;
 import java.io.IOException;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 
 import abtric.utility.Solution;
 
 
 public class A extends Solution {
 
 
 
      private static final String stringPath = "A-small-attempt1";
     
 
     public static void main(String[] args) {
         try {
 
             
             
 
             
             List<String> rawInputFile = Files.readAllLines(FileSystems.getDefault().getPath("in", stringPath + ".in"));
             List<String> inputFile = new ArrayList<>();
             int T = Integer.parseInt(rawInputFile.remove(0));
             inputFile.add(Integer.toString(T));
             for (int i = 0; i < T; i++) {
                 String[] NK = rawInputFile.remove(0).split(" ");
                 int N = Integer.parseInt(NK[0]);
                 int K = Integer.parseInt(NK[1]);
 
                 String pancakes = rawInputFile.remove(0);
                 for (int j = 1; j < N; j++) {
                     pancakes += " " + rawInputFile.remove(0);
                 }
                 inputFile.add(Integer.toString(N));
                 inputFile.add(Integer.toString(K));
                 inputFile.add(pancakes);
             }
 
             String[] result = new A(inputFile).solve();
             write("out" + File.separatorChar + stringPath + ".out", result);
 
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     protected A(String stringPath) throws IOException {
         super(stringPath);
     }
 
     protected A(final List<String> inputFile) {
         super(inputFile);
     }
 
     protected Runnable getNewRunnable(final List<Integer> i) {
         return new SolveRunnable(i);
     }
 
     private class SolveRunnable implements Runnable {
         private final List<Integer> I;
 
         private SolveRunnable(final List<Integer> i) {
             I = i;
         }
 
         public void run() {
             for (Integer i : I) {
                 long startTime = System.currentTimeMillis();
                 final int N = Integer.parseInt(m_inputFile.get(i * 3 + 1));
                 final int K = Integer.parseInt(m_inputFile.get(i * 3 + 2));
                 final String[] pancakes_raw = m_inputFile.get(i * 3 + 3).split(" ");
                 int[] R = new int[N];
                 int[] H = new int[N];
                 ArrayList<Pancake> pancakes = new ArrayList<>();
                 for (int j = 0; j < N; j++) {
                     R[j] = Integer.parseInt(pancakes_raw[j * 2]);
                     H[j] = Integer.parseInt(pancakes_raw[j * 2 + 1]);
                     pancakes.add(new Pancake(R[j], H[j]));
                 }
                 Collections.sort(pancakes);
                 Collections.reverse(pancakes);
 
                 double solution = 0;
                 for (int j = 0; j <= N - K; j++) {
                     solution = Math.max(solution, Math.pow(pancakes.get(j).R, 2) * Math.PI
                             + pancakes.get(j).R * 2 * Math.PI * pancakes.get(j).H + solve(pancakes, K - 1, j + 1));
                 }
 
                 
                 
                 
                 
                 
                 
                 
 
                 m_results[i] = String.format("%09f", solution);
                 long duration = System.currentTimeMillis() - startTime;
                 synchronized (m_lock) {
                     m_done++;
                     System.out.println(String.format("%03d/%03d (%dms)", m_done, m_numOfProblems, duration));
                 }
             }
         }
 
         private double solve(ArrayList<Pancake> pancakes, int k, int left) {
             if (k == 0 || pancakes.size() - left < k) {
                 return 0;
             }
             double solution = 0;
             for (int j = left; j < pancakes.size() - (k - 1); j++) {
                 solution = Math.max(2 * Math.PI * pancakes.get(j).R * pancakes.get(j).H + solve(pancakes, k - 1, j + 1),
                         solution);
             }
             return solution;
         }
 
         private class Pancake implements Comparable<Pancake> {
             int R;
             int H;
 
             private Pancake(int radius, int height) {
                 R = radius;
                 H = height;
             }
 
             @Override
             public int compareTo(Pancake o) {
                 if (Integer.compare(R, o.R) == 0) {
                     return Integer.compare(H, o.H);
                 }
                 return Integer.compare(R, o.R);
             }
 
         }
     }
 }
