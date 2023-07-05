
 import com.google.common.collect.Lists;
 
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import java.math.BigInteger;
 import java.util.List;
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
         private List<Hiker> hikers;
         private int n;
 
         public Solver(int caseNumber) {
             this.caseNumber = caseNumber;
         }
 
         public String solve() throws Exception {
             debug("solving case %d", caseNumber);
 
             n = getInt();
             hikers = Lists.newArrayList();
             for (int i = 0; i < n; i++) {
                 int D = getInt();
                 int H = getInt();
                 int M = getInt();
                 for (int j = 0; j < H; j++) {
                     hikers.add(new Hiker(D, 360.0 / (M + j)));
                 }
             }
 
             
 
             if (hikers.size() <= 1) {
                 return "" + 0;
             }
 
             if (hikers.size() > 2) {
                 return "unknown";
             }
 
 
             Hiker h1 = hikers.get(0);
             Hiker h2 = hikers.get(1);
 
             double t1 = (360 - h1.h) / h1.v;
             double t2 = (360 - h2.h) / h2.v;
             double t = Math.min(t1, t2);
 
             h1.h += h1.v * t;
             h2.h += h2.v * t;
 
             Hiker firstLap;
             Hiker notyetLap;
             if (h1.h == h2.h) {
                 return "0";
             } else if (h1.h > h2.h) {
                 firstLap = h1;
                 notyetLap = h2;
             } else {
                 firstLap = h2;
                 notyetLap = h1;
             }
 
             t1 = (720 - firstLap.h) / firstLap.v;
             t2 = (360 - notyetLap.h) / notyetLap.v;
             if (t1 <= t2) {
                 return "1";
             } else {
                 return "0";
             }
         }
 
         private class Hiker {
             private double h;
             private double v;
 
             public Hiker(double h, double v) {
                 this.h = h;
                 this.v = v;
             }
         }
     }
 }
