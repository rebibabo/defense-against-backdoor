
 import com.google.common.collect.Maps;
 import com.google.common.collect.Sets;
 
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.BitSet;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
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
         private Map<String, Integer> dictionary;
         private int counter;
 
         public Solver(int caseNumber) {
             this.caseNumber = caseNumber;
         }
 
         public String solve() throws Exception {
             
             int N = getInt() - 2;
             dictionary = Maps.newHashMap();
             counter = 0;
             BitSet english = split(readLine());
             BitSet french = split(readLine());
 
             List<BitSet> lines = new ArrayList<BitSet>();
             for (int i = 0; i < N; i++) {
                 lines.add(split(readLine()));
             }
             int answer = Integer.MAX_VALUE;
             int combinations = 1 << N;
             BitSet english2 = new BitSet(counter);
             BitSet french2 = new BitSet(counter);
             for (int i = 0; i < combinations; i++) {
                 english2.clear();
                 english2.or(english);
 
                 french2.clear();
                 french2.or(french);
                 for (int j = 0; j < N; j++) {
                     if ((i & (1 << j)) == 0) {
                         english2.or(lines.get(j));
                     } else {
                         french2.or(lines.get(j));
                     }
                 }
                 english2.and(french2);
                 answer = Math.min(answer, english2.cardinality());
             }
             return "" + answer;
         }
 
         private BitSet split(String s) {
             String[] split = s.split(" ");
             int maxValue = 0;
             for (String s1 : split) {
                 Integer integer = dictionary.get(s1);
                 if (integer == null) {
                     int value = counter++;
                     dictionary.put(s1, value);
                     maxValue = Math.max(maxValue, value);
                 } else {
                     maxValue = Math.max(maxValue, integer);
                 }
             }
             BitSet bitSet = new BitSet(maxValue);
             for (String s1 : split) {
                 bitSet.set(dictionary.get(s1));
             }
             return bitSet;
         }
     }
 }
