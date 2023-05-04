package contest_2017.qualification;
 
 import java.io.IOException;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.List;
 
 import abtric.utility.Solution;
 import abtric.utility.Writer;
 
 
 public class A extends Solution {
 
     public static void main(String[] args) {
         try {
 
              String stringPath = "A-small-attempt0";
             
             List<String> rawInputFile = Files.readAllLines(FileSystems.getDefault().getPath("in", stringPath + ".in"));
             A solver = new A(rawInputFile);
             String[] result = solver.solve();
             Writer.write("out/" + stringPath + ".out", result);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     protected A(List<String> inputFile) throws IOException {
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
                 final String[] S = m_inputFile.get(1 + i).split(" ");
                 final String pancakes = S[0];
                 final int K = Integer.parseInt(S[1]);
 
                 boolean[] t = new boolean[pancakes.length()];
                 for (int j = 0; j < t.length; j++) {
                     t[j] = pancakes.charAt(j) == '+';
                 }
 
                 int solution = 0;
 
                 for (int j = 0; j <= t.length - K; j++) {
                     if (!t[j]) {
                         solution++;
                         for (int k = 0; k < K; k++) {
                             t[j + k] = !t[j + k];
                         }
                     }
                 }
                 boolean impossible = false;
                 for (int j = t.length - K + 1; j < t.length; j++) {
                     if (!t[j]) {
                         impossible = true;
                         break;
                     }
                 }
 
                 if (impossible) {
                     m_results[i] = "IMPOSSIBLE";
                 } else {
                     m_results[i] = Integer.toString(solution);
                 }
 
                 synchronized (m_lock) {
                     m_done++;
                     System.out.println(m_done + "/" + m_numOfProblems);
                 }
             }
         }
     }
 
 }
