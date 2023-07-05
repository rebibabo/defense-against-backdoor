
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import java.math.BigInteger;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class B {
     private static final String INPUT = null; 
 
     public static void main(String[] args) throws Exception {
         new B().run();
     }
 
     private final PrintStream out;
     private final BufferedReader reader;
     private StringTokenizer tokenizer = new StringTokenizer("");
 
     public B() throws Exception {
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
             int A = getInt();
             int B = getInt();
             Xrange[] data = new Xrange[A + B];
             for (int i = 0; i < A; i++) {
                 data[i] = new Xrange(0, getInt(), getInt());
             }
             for (int i = 0; i < B; i++) {
                 data[A + i] = new Xrange(1, getInt(), getInt());
             }
             Arrays.sort(data, (o1, o2) -> Integer.compare(o1.from, o2.from));
 
             if (A + B == 1) {
                 return "2";
             }
 
             if (A == 1 && B == 1) {
                 return "2";
             }
             if (A + B == 2) {
                 if (A == 2 || B == 2) {
                     int spent = (data[0].to - data[0].from) + (data[1].to - data[1].from);
                     int dist1 = data[1].from - data[0].to;
                     int dist2 = (24 * 60 - data[1].to) + data[0].from;
 
                     if (spent + dist1 <= 720) {
                         return "2";
                     }
                     if (spent + dist2 <= 720) {
                         return "2";
                     }
                     return "4";
                 }
             }
 
             return "not implemented";
         }
 
         private class Xrange {
             private final int person;
             private final int from;
             private final int to;
 
             public Xrange(int person, int from, int to) {
                 this.person = person;
                 this.from = from;
                 this.to = to;
             }
         }
     }
 }
