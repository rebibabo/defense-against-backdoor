package Round1C;
 
 import java.util.Scanner;
 
 public class BTypewriterMonkey {
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             int keyboardSize = sc.nextInt();
             int targetSize = sc.nextInt();
             int monkeySize = sc.nextInt();
             sc.nextLine();
             char[] keyboardArray = sc.nextLine().toCharArray();
             String targetString = sc.nextLine();
             int[] guessVals = new int[monkeySize];
             int mostBananas = 0;
             long totalBananas = 0;
             double totalConfigurations = 0;
             do {
                 int bananasGiven = 0;
                 String guess = interpret(guessVals, keyboardArray);
                 for (int x = 0; x <= guess.length() - targetString.length(); x++) {
                     if (guess.startsWith(targetString, x)) bananasGiven++;
                 }
                 totalBananas += bananasGiven;
                 mostBananas = Math.max(mostBananas, bananasGiven);
                 totalConfigurations++;
                 increment(guessVals, keyboardSize);
             } while (!isEmpty(guessVals));
             double averageBananas = totalBananas / totalConfigurations;
             System.out.printf("Case #%d: %f%n", t, mostBananas - averageBananas);
         }
         sc.close();
     }
     static boolean isEmpty (int[] array) {
         for (int val : array) if (val != 0) return false;
         return true;
     }
     static String interpret (int[] guessVals, char[] keyboardArray) {
         char[] result = new char[guessVals.length];
         for (int x = 0; x < guessVals.length; x++) {
             result[x] = keyboardArray[guessVals[x]];
         }
         return new String(result);
     }
     static void increment (int[] array, int max) {
         int index = array.length - 1;
         while (true) {
             array[index]++;
             if (array[index] == max) array[index] = 0;
             else break;
             index--;
             if (index < 0) break;
         }
     }
 }
