package Qualifier;
 
 import java.util.Scanner;
 
 public class CDijkstra {
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             int length = sc.nextInt();
             int repetitions = sc.nextInt();
             String word = sc.next();
             String[] values = new String[length];
             for (int x = 0; x < length; x++) {
                 values[x] = word.charAt(x) + "";
             }
             String fullCycle = "1";
             for (int x = 0; x < length; x++) {
                 fullCycle = mult(fullCycle, values[x]);
             }
             String[] toMake = {"i", "j", "k"};
             System.out.printf("Case #%d: %s%n", t, canMake(toMake, 0, "", word, repetitions, fullCycle)?"YES":"NO");
         }
         sc.close();
     }
     static boolean canMake (String[] toMake, int index, String currWord, String word, int repetitions, String fullCycle) {
         if (index == toMake.length - 1) {
            String val = "1";
            for (int x = 0; x < currWord.length(); x++) {
                val = mult(val, currWord.charAt(x) + "");
            }
            for (int x = 0; x < repetitions % 4; x++) {
                val = mult(val, fullCycle);
            }
            return val.equals(toMake[index]);
         } else {
             String val = "1";
             for (int x = 0; x < currWord.length(); x++) {
                 val = mult(val, currWord.charAt(x) + "");
                 if (val.equals(toMake[index])) return canMake(toMake, index + 1, currWord.substring(x + 1, currWord.length()), word, repetitions, fullCycle);
             }
             for (int r = 0; r < 4; r++) {
                 currWord = word;
                 repetitions--;
                 if (repetitions < 0) return false;
                 for (int x = 0; x < currWord.length(); x++) {
                     val = mult(val, currWord.charAt(x) + "");
                     if (val.equals(toMake[index])) return canMake(toMake, index + 1, currWord.substring(x + 1, currWord.length()), word, repetitions, fullCycle);
                 }
             }
             return false;
         }
     }
     static String mult(String a, String b) {
         boolean positive = true;
         if (a.charAt(0) == '-') positive = !positive;
         if (b.charAt(0) == '-') positive = !positive;
         a = a.charAt(a.length() - 1) + "";
         b = b.charAt(b.length() - 1) + "";
         String[] values = {};
         if (a.equals("1")) {
             values = new String[] {"1", "i", "j", "k"};
         } else if (a.equals("i")) {
             values = new String[] {"i", "-1", "k", "-j"};
         } else if (a.equals("j")) {
             values = new String[] {"j", "-k", "-1", "i"};
         } else if (a.equals("k")) {
             values = new String[] {"k", "j", "-i", "-1"};
         }
         int index = 0;
         if (b.equals("1")) {
             index = 0;
         } else if (b.equals("i")) {
             index = 1;
         } else if (b.equals("j")) {
             index = 2;
         } else if (b.equals("k")) {
             index = 3;
         }
         String value = values[index];
         if (value.charAt(0) == '-') {
             value = value.charAt(1) + "";
             positive = !positive;
         }
         return (positive?"":"-") + value;
     }
 }
