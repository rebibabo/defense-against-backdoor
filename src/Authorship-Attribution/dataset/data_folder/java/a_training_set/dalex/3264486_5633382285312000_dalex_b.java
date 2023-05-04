import java.io.*;
 import java.util.Locale;
 import java.util.StringTokenizer;
 
 public class B {
     private void solveTest() throws IOException {
         long n = readLong();
         for (long res = n; res >= 0; res--) {
             if (isTidy(res)) {
                 writer.println(res);
                 return;
             }
         }
         throw new AssertionError();
     }
 
     private boolean isTidy(long res) {
         char[] s = Long.toString(res).toCharArray();
         for (int i = 1; i < s.length; i++) {
             if (s[i - 1] > s[i]) {
                 return false;
             }
         }
         return true;
     }
 
     private void solve() throws IOException {
         int T = readInt();
         for (int t = 1; t <= T; t++) {
             writer.printf("Case #%d: ", t);
             solveTest();
         }
     }
 
     
     private static final File INPUT_FILE = new File("B-small-attempt0.in");
     private static final File OUTPUT_FILE = new File("output.txt");
 
     public static void main(String[] args) throws IOException {
         new B().run();
     }
 
     private void run() throws IOException {
         Locale.setDefault(Locale.US);
         reader = new BufferedReader(new FileReader(INPUT_FILE));
         writer = new PrintWriter(OUTPUT_FILE);
         try {
             solve();
         } finally {
             reader.close();
             writer.close();
         }
     }
 
     private BufferedReader reader;
     private StringTokenizer tokenizer;
     private PrintWriter writer;
 
     private int readInt() throws IOException {
         return Integer.parseInt(readString());
     }
 
     private long readLong() throws IOException {
         return Long.parseLong(readString());
     }
 
     private String readString() throws IOException {
         while (tokenizer == null || !tokenizer.hasMoreTokens()) {
             tokenizer = new StringTokenizer(reader.readLine());
         }
         return tokenizer.nextToken();
     }
 }
