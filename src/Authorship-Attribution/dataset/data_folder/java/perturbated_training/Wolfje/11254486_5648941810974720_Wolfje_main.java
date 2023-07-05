import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "A-(small|large).*[.]in";
             File directory = new File(".");
             File[] candidates = directory.listFiles(new FilenameFilter() {
                 public boolean accept(File dir, String name) {
                     return name.matches(regex);
                 }
             });
             File toRun = null;
             for (File candidate : candidates) {
                 if (toRun == null || candidate.lastModified() > toRun.lastModified())
                     toRun = candidate;
             }
             inputStream = new FileInputStream(toRun);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         OutputStream outputStream;
         try {
             outputStream = new FileOutputStream("a.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         Scanner in = new Scanner(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskA solver = new TaskA();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskA {
         String[] digits = new String[]{"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE",
                 "SIX", "SEVEN", "EIGHT", "NINE"};
         int[][] dCounts = new int[10][26];
 
         public void solve(int testNumber, Scanner in, PrintWriter out) {
             dCounts = new int[10][26];
             for (int i = 0; i < digits.length; i++) {
                 for (int j = 0; j < digits[i].length(); j++) {
                     dCounts[i][digits[i].charAt(j) - 'A']++;
                 }
             }
             String s = in.next();
             int[] count = new int[26];
             for (int i = 0; i < s.length(); i++) {
                 count[s.charAt(i) - 'A']++;
             }
             int[] result = new int[10];
             int zeroes = count['Z' - 'A'];
             result[0] = zeroes;
             for (int i = 0; i < 26; i++) count[i] -= zeroes * dCounts[0][i];
             int twos = count['W' - 'A'];
             result[2] = twos;
             for (int i = 0; i < 26; i++) count[i] -= twos * dCounts[2][i];
             int sixes = count['X' - 'A'];
             result[6] = sixes;
             for (int i = 0; i < 26; i++) count[i] -= sixes * dCounts[6][i];
             int totalR = count['R' - 'A'];
             int totalV = count['V' - 'A'];
             int[] tmp = new int[26];
             for (int threes = 0; threes <= totalR; threes++) {
                 for (int fives = 0; fives <= totalV; fives++) {
                     int ones = count['O' - 'A'] - (totalR - threes);
                     if (ones < 0) continue;
                     int eights = count['H' - 'A'] - threes;
                     if (eights < 0) continue;
                     int nines = (count['N' - 'A'] - ones - (totalV - fives)) / 2;
                     if (nines < 0) continue;
                     Arrays.fill(tmp, 0);
                     for (int i = 0; i < 26; i++) {
                         tmp[i] += ones * dCounts[1][i] + eights * dCounts[8][i] + nines * dCounts[9][i];
                         tmp[i] += threes * dCounts[3][i] + (totalR - threes) * dCounts[4][i];
                         tmp[i] += fives * dCounts[5][i] + (totalV - fives) * dCounts[7][i];
                     }
                     boolean ok = true;
                     for (int i = 0; i < 26; i++) ok &= tmp[i] == count[i];
                     if (ok) {
                         result[1] = ones;
                         result[3] = threes;
                         result[4] = totalR - threes;
                         result[5] = fives;
                         result[7] = totalV - fives;
                         result[8] = eights;
                         result[9] = nines;
                     }
                 }
             }
             out.printf("Case #%d: ", testNumber);
             for (int d = 0; d < 10; d++) {
                 for (int j = 0; j < result[d]; j++) out.print(d);
             }
             out.println();
 
         }
 
     }
 }
 
