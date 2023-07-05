package contest_2017.round_1b;
 
 import java.io.File;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 
 import abtric.utility.Solution;
 
 
 public class B extends Solution {
 
 
      private static final String stringPath = "B-small-attempt0";
     
 
     public static void main(String[] args) {
         try {
 
             
             B solver = new B(stringPath);
 
             
             
             
             
             
             
 
             String[] result = solver.solve();
 
             write("out" + File.separatorChar + stringPath + ".out", result);
 
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     protected B(String stringPath) throws IOException {
         super(stringPath);
     }
 
     protected B(final List<String> inputFile) throws IOException {
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
                 final String[] input = m_inputFile.get(i + 1).split(" ");
                 final int N = Integer.parseInt(input[0]);
                 final int R = Integer.parseInt(input[1]);
                 
                 final int Y = Integer.parseInt(input[3]);
                 
                 final int B = Integer.parseInt(input[5]);
                 
 
                 char firstChar;
                 char secondChar;
                 char thirdChar;
                 int firstCharAmount;
                 int secondCharAmount;
                 int thirdCharAmount;
 
                 boolean impossible = true;
 
                 if (R >= Y && R >= B) {
                     firstChar = 'R';
                     firstCharAmount = R;
                     if (Y >= B) {
                         secondChar = 'Y';
                         secondCharAmount = Y;
                         thirdChar = 'B';
                         thirdCharAmount = B;
                     } else {
                         secondChar = 'B';
                         secondCharAmount = B;
                         thirdChar = 'Y';
                         thirdCharAmount = Y;
                     }
                 } else if (Y >= R && Y >= B) {
                     firstChar = 'Y';
                     firstCharAmount = Y;
                     if (R >= B) {
                         secondChar = 'R';
                         secondCharAmount = R;
                         thirdChar = 'B';
                         thirdCharAmount = B;
                     } else {
                         secondChar = 'B';
                         secondCharAmount = B;
                         thirdChar = 'R';
                         thirdCharAmount = R;
                     }
                 } else {
                     firstChar = 'B';
                     firstCharAmount = B;
                     if (Y >= R) {
                         secondChar = 'Y';
                         secondCharAmount = Y;
                         thirdChar = 'R';
                         thirdCharAmount = R;
                     } else {
                         secondChar = 'R';
                         secondCharAmount = R;
                         thirdChar = 'Y';
                         thirdCharAmount = Y;
                     }
                 }
 
                 ArrayList<Character> stall = new ArrayList<>();
                 if (firstCharAmount <= secondCharAmount + thirdCharAmount) {
                     impossible = false;
                     for (int j = 0; j < firstCharAmount; j++) {
                         stall.add(firstChar);
                     }
                     for (int j = 0; j < secondCharAmount; j++) {
                         stall.add(j * 2 + 1, secondChar);
                     }
                     for (int j = secondCharAmount; j < firstCharAmount; j++) {
                         stall.add(j * 2 + 1, thirdChar);
                     }
                     for (int j = 0; j < thirdCharAmount - (firstCharAmount - secondCharAmount); j++) {
                         stall.add(j * 3 + 1, thirdChar);
                     }
                 }
 
                 String solution = "IMPOSSIBLE";
                 if (!impossible) {
                     solution = "";
                     for (int j = 0; j < stall.size(); j++) {
                         solution += stall.get(j);
                     }
                 }
 
                 m_results[i] = solution;
                 long duration = System.currentTimeMillis() - startTime;
                 synchronized (m_lock) {
                     m_done++;
                     System.out.println(String.format("%03d/%03d (%dms)", m_done, m_numOfProblems, duration));
                 }
             }
         }
     }
 }
