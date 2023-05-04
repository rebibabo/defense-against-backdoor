package googlecodejam2016;
 
 import googlecodejam2016.GoogleCodeJam2016.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 
 
 public class Round1AA implements Problem {
 
   private static final String ALPHABET_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   private static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
 
   private String input;
   private String output;
 
   @Override
   public void setup(BufferedReader input) throws IOException {
     this.input = input.readLine();
   }
 
   private static int getIndexOf(char c) {
     for (int i = 0; i < ALPHABET.length; ++i) {
       if (ALPHABET[i] == c) {
         return i;
       }
     }
     return -1;
   }
 
   @Override
   public void solve() {
     char[] inputArr = input.toCharArray();
     int l = inputArr.length;
 
     int firstCharIndex = -1;
     StringBuilder stringBuilder = new StringBuilder();
     for (int i = 0; i < l; ++i) {
       char currChar = inputArr[i];
       int currCharIndex = getIndexOf(currChar);
       if (i == 0 || currCharIndex >= firstCharIndex) {
         stringBuilder.insert(0, currChar);
         firstCharIndex = currCharIndex;
       } else {
         stringBuilder.append(currChar);
       }
     }
 
     output = stringBuilder.toString();
   }
 
   @Override
   public String getSolution() {
     return output;
   }
 
 }
