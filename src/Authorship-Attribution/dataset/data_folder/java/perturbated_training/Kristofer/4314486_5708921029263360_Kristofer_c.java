
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import java.math.BigInteger;
 import java.util.StringTokenizer;
 
 public class C {
     private static final String INPUT = null; 
 
     public static void main(String[] args) throws Exception {
         new C().run();
     }
 
     private final PrintStream out;
     private final BufferedReader reader;
     private StringTokenizer tokenizer = new StringTokenizer("");
 
     public C() throws Exception {
         String problem = getClass().getSimpleName();
         if (INPUT == null) {
             File input = findInput(problem);
             if (input == null) {
                 throw new IOException("No input file found");
             }
             File output = new File(input.getParent(), input.getName().replace(".in", ".out"));
             System.err.println("input:  " + input.getPath());
             System.err.println("output: " + output.getPath());
             out = new PrintStream(new FileOutputStream(output));
             reader = new BufferedReader(new FileReader(input));
         } else if (INPUT.equals("stdin")) {
             System.err.println("input:  stdin");
             System.err.println("output: stdout");
             out = System.out;
             reader = new BufferedReader(new InputStreamReader(System.in));
         } else {
             System.err.println("input:  " + problem + "-" + INPUT + ".in");
             System.err.println("output: " + problem + "-" + INPUT + ".out");
             out = new PrintStream(new FileOutputStream("source/" + problem + "-" + INPUT + ".out"));
             reader = new BufferedReader(new FileReader("source/" + problem + "-" + INPUT + ".in"));
         }
     }
 
     public static File findInput(String problem) throws Exception {
         File dir = new File("source");
         long bestTimestamp = -1;
         File bestFile = null;
         for (File file : dir.listFiles()) {
            if (file.getName().startsWith(problem + "-") && file.getName().endsWith(".in")) {
                long timestamp = file.lastModified();
                if (timestamp > bestTimestamp) {
                    bestTimestamp = timestamp;
                    bestFile = file;
                }
            }
         }
         return bestFile;
     }
 
     public void run() {
         try {
             runCases();
         } finally {
             out.close();
         }
     }
 
     public void debug(String s, Object... args) {
         System.err.printf("DEBUG: " + s + "\n", args);
     }
 
     private void runCases() {
         try {
             int cases = getInt();
             for (int c = 1; c <= cases; c++) {
                 try {
                     String answer = new Solver(c).solve();
                     String s = "Case #" + c + ": " + answer;
                     out.println(s);
                     if (out != System.out) {
                         System.out.println(s);
                     }
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         } finally {
             debug("done with all!");
         }
     }
 
     public String readLine() {
         try {
             return reader.readLine();
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
 
     public String getToken() {
         while (true) {
             if (tokenizer.hasMoreTokens()) {
                 return tokenizer.nextToken();
             }
             String s = readLine();
             if (s == null) {
                 return null;
             }
             tokenizer = new StringTokenizer(s, " \t\n\r");
         }
     }
 
     public double getDouble() {
         return Double.parseDouble(getToken());
     }
 
     public int getInt() {
         return Integer.parseInt(getToken());
     }
 
     public long getLong() {
         return Long.parseLong(getToken());
     }
 
     public BigInteger getBigInt() {
         return new BigInteger(getToken());
     }
 
     public BigDecimal getBigDec() {
         return new BigDecimal(getToken());
     }
 
     public class Solver {
         private final int caseNumber;
 
         public Solver(int caseNumber) {
             this.caseNumber = caseNumber;
         }
 
         public String solve() throws Exception {
             debug("solving case %d", caseNumber);
 
             int J = getInt();
             int P = getInt();
             int S = getInt();
             int K = getInt();
 
             int pairJP[][] = new int[J][P];
             int pairJS[][] = new int[J][S];
             int pairPS[][] = new int[P][S];
 
             boolean used[][][] = new boolean[J][P][S];
 
             int count = 0;
 
             StringBuilder sb = new StringBuilder();
 
             for (int k = 0; k < K; k++) {
                 for (int j = 0; j < J; j++) {
                     for (int p = 0; p < P; p++) {
                         for (int s = 0; s < S; s++) {
                             if (!used[j][p][s] && pairJP[j][p] + pairJS[j][s] + pairPS[p][s] <= K) {
                                 count++;
 
                                 if (pairJP[j][p] > 0) {
                                     K--;
                                 } else {
                                     pairJP[j][p]++;
                                 }
                                 if (pairJS[j][s] > 0) {
                                     K--;
                                 } else {
                                     pairJS[j][s]++;
                                 }
                                 if (pairPS[p][s] > 0) {
                                     K--;
                                 } else {
                                     pairPS[p][s]++;
                                 }
 
                                 used[j][p][s] = true;
 
                                 sb.append("\n");
                                 sb.append(1 + j);
                                 sb.append(" ");
                                 sb.append(1 + p);
                                 sb.append(" ");
                                 sb.append(1 + s);
                             }
                         }
                     }
                 }
             }
 
             return count + sb.toString();
         }
     }
 }
