
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
             int L = getInt();
             int X = getInt();
             int[] values = convert(getToken());
 
             int goal = mul(mul(i, j), k);
 
             int product = 1;
             for (int value : values) {
                 product = mul(product, value);
             }
 
             int pow = power(product, X);
             if (pow != goal) {
                 return "NO";
             }
 
             int consumedPowers = 0;
             int consumedTokens = 0;
 
             
             product = ONE;
             iLoop: for (int powerIter = 0; powerIter < 4; powerIter++) {
                 while (consumedTokens < L) {
                     product = mul(product, values[consumedTokens]);
                     consumedTokens++;
                     if (product == i) {
                         break iLoop;
                     }
                 }
                 consumedTokens = 0;
                 consumedPowers++;
                 if (consumedPowers == X) {
                     return "NO";
                 }
             }
             if (product != i) {
                 return "NO";
             }
 
             
             product = ONE;
             jLoop: for (int powerIter = 0; powerIter < 4; powerIter++) {
                 while (consumedTokens < L) {
                     product = mul(product, values[consumedTokens]);
                     consumedTokens++;
                     if (product == j) {
                         break jLoop;
                     }
                 }
                 consumedTokens = 0;
                 consumedPowers++;
                 if (consumedPowers == X) {
                     return "NO";
                 }
             }
             if (product != j) {
                 return "NO";
             }
 
             return "YES";
         }
 
         private int power(int value, int exponent) {
             if (exponent < 0) {
                 throw new RuntimeException("Exponent can't be < 0");
             }
             if (exponent == 0) {
                 return ONE;
             }
             if (exponent == 1) {
                 return value;
             }
             if (exponent == 2) {
                 return mul(value, value);
             }
             if (exponent == 3) {
                 return mul(mul(value, value), value);
             }
             int halfExponent = exponent / 2;
             return mul(power(value, halfExponent), power(value, exponent - halfExponent));
         }
 
         private int[] convert(String token) {
             int length = token.length();
             int[] res = new int[length];
             for (int iter = 0; iter < length; iter++) {
                 res[iter] = convert(token.charAt(iter));
             }
             return res;
         }
 
         private int convert(char c) {
             switch (c) {
                 case 'i': return i;
                 case 'j': return j;
                 case 'k': return k;
                 default: throw new RuntimeException("Invalid token: " + c);
             }
         }
     }
 
 
     static final int ONE = 1;
     static final int i = 2;
     static final int j = 3;
     static final int k = 4;
 
     static final int[] mulTable = new int[5*5];
     public static void def(int a, int b, int res) {
         mulTable[a + b * 5] = res;
     }
     static {
         def(ONE, ONE, ONE);
         def(ONE, i, i);
         def(ONE, j, j);
         def(ONE, k, k);
         def(i, ONE, i);
         def(j, ONE, j);
         def(k, ONE, k);
 
         def(i, i, -1);
         def(j, j, -1);
         def(k, k, -1);
 
         def(i, j, k);
         def(i, k, -j);
 
         def(j, i, -k);
         def(j, k, i);
 
         def(k, i, j);
         def(k, j, -i);
     }
 
     static int mul(int a, int b) {
         int sign = Integer.signum(a * b);
         int res = mulTable[Math.abs(a) + Math.abs(b) * 5];
         if (res == 0) {
             throw new RuntimeException("Can't multiply " + a + " with " + b);
         }
         return sign * res;
     }
 }
