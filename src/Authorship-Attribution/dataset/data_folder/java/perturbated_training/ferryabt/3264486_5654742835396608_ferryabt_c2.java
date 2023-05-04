package contest_2017.qualification;
 
 import java.io.IOException;
 import java.math.BigInteger;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.ArrayList;
 import java.util.List;
 
 import abtric.utility.Solution;
 import abtric.utility.Writer;
 
 
 public class C2 extends Solution {
 
     public static void main(String[] args) {
         try {
 
              String stringPath = "C-small-1-attempt0";
             
             
             List<String> rawInputFile = Files.readAllLines(FileSystems.getDefault().getPath("in", stringPath + ".in"));
             C2 solver = new C2(rawInputFile);
             String[] result = solver.solve();
             Writer.write("out/" + stringPath + ".out", result);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     protected C2(List<String> inputFile) throws IOException {
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
                 final BigInteger N = new BigInteger(S[0]);
                 final BigInteger K = new BigInteger(S[1]);
 
                 ArrayList<BigInteger> freeGroups = new ArrayList<>();
                 freeGroups.add(N);
 
                 BigInteger zero = new BigInteger("0");
                 BigInteger one = new BigInteger("1");
                 BigInteger two = new BigInteger("2");
                 BigInteger L_S = zero;
                 BigInteger R_S = zero;
                 for (BigInteger j = zero; j.compareTo(K) < 0; j = j.add(one)) {
                     int biggestGroup = 0;
                     for (int k = 1; k < freeGroups.size(); k++) {
                         if (freeGroups.get(biggestGroup).compareTo(freeGroups.get(k)) < 0) {
                             biggestGroup = k;
                         }
                     }
                     if (freeGroups.get(biggestGroup).mod(two).compareTo(zero) == 0) {
                         L_S = freeGroups.get(biggestGroup).divide(two).subtract(one);
                         R_S = freeGroups.get(biggestGroup).divide(two);
                     } else {
                         L_S = freeGroups.get(biggestGroup).subtract(one).divide(two);
                         R_S = freeGroups.get(biggestGroup).subtract(one).divide(two);
                     }
                     ArrayList<BigInteger> newList = new ArrayList<>();
                     for (int k = 0; k < biggestGroup; k++) {
                         newList.add(freeGroups.get(k));
                     }
                     if (L_S.compareTo(zero) > 0) {
                         newList.add(L_S);
                     }
                     if (R_S.compareTo(zero) > 0) {
                         newList.add(R_S);
                     }
                     for (int k = biggestGroup + 1; k < freeGroups.size(); k++) {
                         newList.add(freeGroups.get(k));
                     }
                     freeGroups = newList;
 
                 }
 
                 m_results[i] = (L_S.compareTo(R_S) <= 0 ? R_S : L_S).toString() + " "
                         + (L_S.compareTo(R_S) <= 0 ? L_S : R_S).toString();
 
                 synchronized (m_lock) {
                     m_done++;
                     System.out.println(m_done + "/" + m_numOfProblems);
                 }
             }
         }
     }
 
 }
