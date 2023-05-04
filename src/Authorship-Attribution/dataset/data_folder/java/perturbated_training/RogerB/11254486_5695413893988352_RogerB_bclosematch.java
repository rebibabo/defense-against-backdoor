package Round1B;
 
 import java.util.Scanner;
 import java.util.regex.Pattern;
 
 public class BCloseMatch {
     
     static String getLower(String input, char matchingDigit) {
         if (matchingDigit == '?') matchingDigit = '1';
         if (matchingDigit == '0') return "E";
         return input.replaceFirst(Pattern.quote("?"), "" + (char)(matchingDigit - 1)).replace('?', '9');
     }
     
     static String getHigher(String input, char matchingDigit) {
         if (matchingDigit == '?') matchingDigit = '0';
         if (matchingDigit == '9') return "E";
         return input.replaceFirst(Pattern.quote("?"), "" + (char)(matchingDigit + 1)).replace('?', '0');
     }
     
     static long[] convert(String[] input) {
         long[] result = new long[input.length];
         for (int x = 0; x < result.length; x++) result[x] = Long.parseLong(input[x]);
         return result;
     }
     
     static long diff(long[] input) {
         return Math.abs(input[1] - input[0]);
     }
     
     static int compare(String[] a, String[] b) {
         long[] aVals = convert(a);
         long[] bVals = convert(b);
         
         if (diff(aVals) < diff(bVals)) return -1;
         if (diff(aVals) > diff(bVals)) return 1;
         if (aVals[0] < bVals[0]) return -1;
         if (aVals[0] > bVals[0]) return 1;
         if (aVals[1] < bVals[1]) return -1;
         if (aVals[1] > bVals[1]) return 1;
         return 0;
     }
     
     static boolean isValid(String[] input) {
         return !(input[0].contains("E") || input[1].contains("E"));
     }
     
     static String[] bestAnswer(String a, String b, int startingIndex) {
         if (startingIndex >= a.length()) return new String[] {a, b};
         
         String aLower;
         String aMatch;
         String aHigh;
         
         String bLower;
         String bMatch;
         String bHigh;
         
         if (a.charAt(startingIndex) == '?') {
             aLower = getLower(a, b.charAt(startingIndex));
             { 
                 char matchChar = b.charAt(startingIndex);
                 if (matchChar == '?') matchChar = '0';
                 aMatch = a.replaceFirst(Pattern.quote("?"), "" + matchChar);
             }
             aHigh = getHigher(a, b.charAt(startingIndex));
         } else {
             aLower = a.replace('?', '9');
             aMatch = a;
             aHigh = a.replace('?', '0');
         }
         
         if (b.charAt(startingIndex) == '?') {
             bLower = getLower(b, a.charAt(startingIndex));
             { 
                 char matchChar = a.charAt(startingIndex);
                 if (matchChar == '?') matchChar = '0';
                 bMatch = b.replaceFirst(Pattern.quote("?"), "" + matchChar);
             }
             bHigh = getHigher(b, a.charAt(startingIndex));
         } else {
             bLower = b.replace('?', '9');
             bMatch = b;
             bHigh = b.replace('?', '0');
         }
         
         String[] matchResults = bestAnswer(aMatch, bMatch, startingIndex + 1);
         aMatch = matchResults[0];
         bMatch = matchResults[1];
         
         String[] bestScores = {aMatch, bMatch};
         String[] lowScores = {aLower, bHigh};
         String[] highScores = {aHigh, bLower};
         
         if (isValid(lowScores) && compare(lowScores, bestScores) < 0) bestScores = lowScores;
         if (isValid(highScores) && compare(highScores, bestScores) < 0) bestScores = highScores;
         
         return bestScores;
     }
     
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             String firstScore = sc.next();
             String secondScore = sc.next();
             String[] answers = bestAnswer(firstScore, secondScore, 0);
             System.out.printf("Case #%d: %s %s%n", t, answers[0], answers[1]);
         }
         sc.close();
     }
 }
