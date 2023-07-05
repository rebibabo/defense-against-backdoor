package contest_2017.round_1c;
 
 import java.io.File;
 import java.io.IOException;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.ArrayList;
 import java.util.List;
 
 import abtric.utility.Solution;
 
 
 public class B extends Solution {
 
     
     private static final String stringPath = "B-small-attempt0";
     
 
     public static void main(String[] args) {
         try {
 
             
             
 
             
             List<String> rawInputFile = Files.readAllLines(FileSystems.getDefault().getPath("in", stringPath + ".in"));
             List<String> inputFile = new ArrayList<>();
             int T = Integer.parseInt(rawInputFile.remove(0));
             inputFile.add(Integer.toString(T));
             for (int i = 0; i < T; i++) {
                 String[] ACAJ = rawInputFile.remove(0).split(" ");
                 int AC = Integer.parseInt(ACAJ[0]);
                 String[] ACs = new String[AC];
                 for (int j = 0; j < AC; j++) {
                     ACs[j] = rawInputFile.remove(0);
                 }
                 int AJ = Integer.parseInt(ACAJ[1]);
                 String[] AJs = new String[AJ];
                 for (int j = 0; j < AJ; j++) {
                     AJs[j] = rawInputFile.remove(0);
                 }
                 inputFile.add(Integer.toString(AC));
                 inputFile.add(Integer.toString(AJ));
                 String ACstring = AC > 0 ? ACs[0] : "";
                 for (int j = 1; j < AC; j++) {
                     ACstring += " " + ACs[j];
                 }
                 inputFile.add(ACstring);
                 String AJstring = AJ > 0 ? AJs[0] : "";
                 for (int j = 1; j < AJ; j++) {
                     AJstring += " " + AJs[j];
                 }
                 inputFile.add(AJstring);
             }
 
             String[] result = new B(inputFile).solve();
 
             write("out" + File.separatorChar + stringPath + ".out", result);
 
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     protected B(String stringPath) throws IOException {
         super(stringPath);
     }
 
     protected B(final List<String> inputFile) {
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
 
                 final int AC = Integer.parseInt(m_inputFile.get(i * 4 + 1));
                 final int AJ = Integer.parseInt(m_inputFile.get(i * 4 + 2));
                 final String[] ACs_raw = m_inputFile.get(i * 4 + 3).split(" ");
                 final String[] AJs_raw = m_inputFile.get(i * 4 + 4).split(" ");
                 final int[] ACstarts = new int[AC];
                 final int[] ACends = new int[AC];
                 final int[] AJstarts = new int[AJ];
                 final int[] AJends = new int[AJ];
                 for (int j = 0; j < AC; j++) {
                     ACstarts[j] = Integer.parseInt(ACs_raw[j * 2]);
                     ACends[j] = Integer.parseInt(ACs_raw[j * 2 + 1]);
                 }
                 for (int j = 0; j < AJ; j++) {
                     AJstarts[j] = Integer.parseInt(AJs_raw[j * 2]);
                     AJends[j] = Integer.parseInt(AJs_raw[j * 2 + 1]);
                 }
 
                 byte[] carer = new byte[24 * 60];
                 int timeJ = 0;
                 for (int j = 0; j < AC; j++) {
                     for (int k = ACstarts[j]; k < ACends[j]; k++) {
                         carer[k] = 2;
                     }
                     timeJ += ACends[j] - ACstarts[j];
                 }
                 int timeC = 0;
                 for (int j = 0; j < AJ; j++) {
                     for (int k = AJstarts[j]; k < AJends[j]; k++) {
                         carer[k] = 1;
                     }
                     timeC += AJends[j] - AJstarts[j];
                 }
 
                 int solution = 0;
 
                 int[] maxRight = new int[2];
                 int counter = 0;
                 int j = 0;
                 int last = 0;
                 while (counter < 24 * 60) {
                     if (last == 0) {
                         if (carer[j] != 0) {
                             last = carer[j];
                             counter++;
                             maxRight[last - 1]++;
                             solution++;
                         }
                     } else {
                         if (carer[j] != last && carer[j] != 0) {
                             last = carer[j];
                             solution++;
                         }
                         counter++;
                         maxRight[last - 1]++;
                     }
                     j = (j + 1) % (24 * 60);
                 }
                 if (carer[(((j - 1) % (24 * 60)) + (24 * 60)) % (24 * 60)] == carer[j]) {
                     solution--;
                 }
 
                 int[] maxLeft = new int[2];
                 counter = 0;
                 j = 24 * 60 - 1;
                 last = 0;
                 while (counter < 24 * 60) {
                     if (last == 0) {
                         if (carer[j] != 0) {
                             last = carer[j];
                             counter++;
                             maxLeft[last - 1]++;
                         }
                     } else {
                         if (carer[j] != last && carer[j] != 0) {
                             last = carer[j];
                         }
                         counter++;
                         maxLeft[last - 1]++;
                     }
                     j = (((j - 1) % (24 * 60)) + (24 * 60)) % (24 * 60);
                 }
 
                 boolean solutionPossible = (maxRight[0] >= 720 || maxLeft[0] >= 720)
                         && (maxRight[1] >= 720 || maxLeft[1] >= 720);
                 if (solution <= 1) {
                     solutionPossible = true;
                     solution = 2;
                 }
                 System.out.println(i + ": maxRight [" + maxRight[0] + "|" + maxRight[1] + "], maxLeft [" + maxLeft[0]
                         + "|" + maxLeft[1] + "], solution " + solutionPossible + "(" + solution + ")");
 
                 m_results[i] = Integer.toString(solution);
                 long duration = System.currentTimeMillis() - startTime;
                 synchronized (m_lock) {
                     m_done++;
                     System.out.println(String.format("%03d/%03d (%dms)", m_done, m_numOfProblems, duration));
                 }
             }
         }
     }
 }
