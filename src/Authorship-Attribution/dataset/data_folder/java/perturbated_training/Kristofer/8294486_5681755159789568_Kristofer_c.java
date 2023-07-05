
 
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
 
 import static com.google.common.collect.Lists.newArrayList;
 
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
             int N = getInt();
             int Q = getInt();
             Horse[] horses = new Horse[N - 1];
             for (int i = 0; i < N; i++) {
                 int distance = getInt();
                 int speed = getInt();
                 if (i != N - 1) {
                     horses[i] = new Horse(distance, speed);
                 }
             }
             int[] distances = new int[N - 1];
             for (int i = 0; i < N; i++) {
                 for (int j = 0; j < N; j++) {
                     int d = getInt();
                     if (i + 1 == j) {
                         distances[i] = d;
                     }
                 }
             }
 
             int U = -1;
             int V = -1;
             for (int i = 0; i < Q; i++) {
                 U = getInt();
                 V = getInt();
             }
 
             if (Q != 1 || U != 1 || V != N) {
                 return "not implemented";
             }
 
             for (int i = 0; i < N - 1; i++) {
                 horses[i].setReach(i, distances);
             }
 
             Horse[][] selection = new Horse[N - 1][N - 1];
             double[][] time = new double[N - 1][N - 1];
 
             Horse candidate = horses[0];
             double distance = 0;
             for (int i = 0; i < candidate.reach; i++) {
                 distance += distances[i];
                 selection[0][i] = candidate;
                 time[0][i] = distance / candidate.speed;
             }
 
             for (int fromCity = 1; fromCity < N - 1; fromCity++) {
                 candidate = horses[fromCity];
                 distance = 0;
                 for (int i = fromCity; i < N - 1; i++) {
                     distance += distances[i];
                     Horse previousHorse = selection[fromCity - 1][i];
                     if (candidate.reach > i && (previousHorse == null || candidate.speed > previousHorse.speed)) {
                         selection[fromCity][i] = candidate;
                         time[fromCity][i] = time[fromCity - 1][i - 1] + (distance / candidate.speed);
                     } else if (previousHorse != null && previousHorse.reach > i) {
                         selection[fromCity][i] = previousHorse;
                         time[fromCity][i] = time[fromCity - 1][i];
                     }
                 }
             }
 
             return String.format("%.6f", time[N - 2][N - 2]);
         }
     }
 
     private class Horse {
         private final double distance;
         public final double speed;
         public int reach;
 
         private Horse(int distance, int speed) {
             this.distance = distance;
             this.speed = speed;
         }
 
         public void setReach(int i, int[] distances) {
             double d = this.distance;
             for (int j = i; j < distances.length; j++) {
                 d -= distances[j];
                 if (d < 0) {
                     this.reach = j;
                     return;
                 }
             }
             this.reach = distances.length;
         }
     }
 }
