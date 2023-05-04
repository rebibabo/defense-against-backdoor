
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
 
 public class A {
     private static final String INPUT = null; 
 
     public static void main(String[] args) throws Exception {
         new A().run();
     }
 
     private final PrintStream out;
     private final BufferedReader reader;
     private StringTokenizer tokenizer = new StringTokenizer("");
 
     public A() throws Exception {
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
             int N = getInt();
             int K = getInt();
 
             Pancake[] pancakes = new Pancake[N];
             for (int i = 0; i < N; i++) {
                 long r = getInt();
                 long h = getInt();
                 pancakes[i] = new Pancake(r, h);
             }
 
             Arrays.sort(pancakes, (o1, o2) -> {
                 int compare = Double.compare(o1.value, o2.value);
                 if (compare != 0) {
                     return compare;
                 }
                 return Double.compare(o1.radius, o2.radius);
             });
 
             double bestvalue = -1;
             for (int largest = 0; largest < N; largest++) {
                 int k = K - 1;
                 double sum = pancakes[largest].value;
                 double maxrad = pancakes[largest].radius;
                 for (int i = N - 1; i >= 0; i--) {
                     if (k > 0 && i != largest) {
                         Pancake pancake = pancakes[i];
                         if (pancake.radius <= maxrad) {
                             sum += pancake.value;
                             k--;
                         }
                     }
                 }
                 double value = maxrad * maxrad + 2 * sum;
                 if (k == 0 && value >  bestvalue) {
                     bestvalue = value;
                 }
             }
 
             double ans = Math.PI * bestvalue;
             return String.format("%.6f", ans);
         }
 
         private class Pancake {
             final double radius;
             final double height;
             final double value;
 
             private Pancake(double radius, double height) {
                 this.radius = radius;
                 this.height = height;
                 this.value = radius * height;
 
             }
         }
     }
 }
