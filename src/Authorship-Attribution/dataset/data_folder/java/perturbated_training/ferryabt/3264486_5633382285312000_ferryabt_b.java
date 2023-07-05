package contest_2017.qualification;
 
 import java.io.IOException;
 import java.math.BigInteger;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.List;
 
 import abtric.utility.Solution;
 import abtric.utility.Writer;
 
 
 public class B extends Solution {
 
     public static void main(String[] args) {
         try {
             
             String stringPath = "B-small-attempt1";
             
             List<String> rawInputFile = Files.readAllLines(FileSystems.getDefault().getPath("in", stringPath + ".in"));
             B solver = new B(rawInputFile);
             String[] result = solver.solve();
             Writer.write("out/" + stringPath + ".out", result);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     protected B(List<String> inputFile) throws IOException {
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
                 final BigInteger N = new BigInteger(m_inputFile.get(1 + i));
 
                 
                 final BigInteger ten = new BigInteger("10");
 
                 BigInteger solution = N;
 
                 while (!isNonDecending(solution)) {
                     int maxK = solution.toString().length() - 1;
                     int k = maxK;
                     String s = solution.toString();
                     char currentDigit = s.charAt(k);
                     while (currentDigit == '9') {
                         k--;
                         currentDigit = s.charAt(k);
                     }
 
                     solution = solution.subtract(new BigInteger(Integer.toString(Character.digit(currentDigit, 10) + 1))
                             .multiply(ten.pow(maxK - k)));
                 }
 
                 m_results[i] = solution.toString();
 
                 synchronized (m_lock) {
                     m_done++;
                     System.out.println(m_done + "/" + m_numOfProblems);
                 }
             }
         }
 
         private boolean isNonDecending(BigInteger i) {
             String s = i.toString();
             for (int j = 0; j < s.length() - 1; j++) {
                 if (s.charAt(j) > s.charAt(j + 1)) {
                     return false;
                 }
             }
             return true;
         }
     }
 
 }
