import static java.lang.Math.*;
 import static java.util.Arrays.*;
 import static java.util.Collections.reverse;
 import static java.lang.Integer.highestOneBit;
 import static java.lang.Integer.lowestOneBit;
 import static java.lang.Integer.numberOfLeadingZeros;
 import static java.lang.Integer.numberOfTrailingZeros;
 import static java.lang.Integer.bitCount;
 import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.BigDecimal;
 import java.math.BigInteger;
 import java.util.*;
 import java.util.concurrent.*;
 import java.util.stream.*;
 
 
 
 
 
 @SuppressWarnings("unchecked")
 public class A {
 
     private static final String NO = "IMPOSSIBLE";
     public static final long MAX = Long.MAX_VALUE;
     public static final int INF = 1000000000;
     public static final int MOD = 9901;
     public static final int UNDEF = -3;
 
     
     
     private void run(int caseNumber) throws Exception {
         int R = INT();
         int C = INT();
         char[][] a = new char[R][C];
         for (int i = 0; i < a.length; i++) {
             a[i] = STR().toCharArray();
         }
         
         
         if(debugTestCases.length>0 && Arrays.binarySearch(debugTestCases, caseNumber)<0) {
             if(writeToFile) {
                 out.printf("Case #%d: skip\n", caseNumber);
             }
             return;
         }
 
         
         
         if (empty(R, C, a)==null) {
             int[][] cnt = new int[R][C];
             char[][] v = new char[R][C];
             for (int r = 0; r < v.length; r++) {
                 for (int c = 0; c < v[r].length; c++) {
                     if (a[r][c]!='?') {
                         for (int r1 = 0; r1 < v.length; r1++) {
                             for (int c1 = 0; c1 < v[r1].length; c1++) {
                                 if (ok(r,c,r1,c1,a)) {
                                     cnt[r1][c1]++;
                                     v[r1][c1]=a[r][c];
                                 }
                             }
                         }
                     }
                 }
             }
 
 
 
 
 
             for (int i = 0; i < cnt.length; i++) {
                 for (int j = 0; j < cnt[i].length; j++) {
                     if (cnt[i][j]==1) {
                         a[i][j]=v[i][j];
                     }
                 }
             }
 
             empty(R, C, a);
         }
 
         
         out.printf("Case #%d:\n", caseNumber);
         for (int i = 0; i < a.length; i++) {
             out.println(new String(a[i]));
         }
 
 
     }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
     
     private boolean ok(int r, int c, int r1, int c1, char[][] a) {
         for (int i = min(r,r1); i <= max(r,r1); i++) {
             for (int j = min(c,c1); j <= max(c,c1); j++) {
                 if (i==r&&j==c) continue;
                 if (a[i][j]!='?') {
                     return false;
                 }
             }
         }
         return true;
     }
 
     private int idx(int r, int c, int C) {
         return r*C+c;
     }
 
     
     static int lowest(int mask) {
         return (mask & -mask);
     }
 
     
     static boolean has(int mask, int i) {
         return (mask & (1 << i)) != 0;
     }
 
     
     static int set(int mask, int i, boolean v) {
         return v ? set(mask, i) : unset(mask, i);
     }
 
     
     static int set(int mask, int i) {
         return mask | (1 << i);
     }
 
     
     static int unset(int mask, int i) {
         return mask & ~(1 << i);
     }
 
     
     static boolean contain(int set, int subset) {
         return (set & subset) == subset;
     }
 
     void bad2(int R, int C, char[][] a) {
         char[] row1 = new char[R];
         fill(row1, '?');
         for (int r = 0; r < a.length; r++) {
             for (int c = 0; c < a[r].length; c++) {
                 if (a[r][c] != '?') {                    
                     row1[r]=a[r][c];
                     break;
                 }
             }
         }
         
         char ch = '?';
         for (int r = 0; r < row1.length; r++) {
             if (row1[r] != '?') {
                 if (ch == '?') {
                     for (int j = 0; j < r; j++) {
                         row1[j] = row1[r];
                     }
                 }
                 ch = row1[r];
             } else {
                 row1[r] = ch;
             }
         }
         for (int r = 0; r < R; r++) {
 
             ch = row1[r];
             for (int c = 0; c < C; c++) {
                 if (a[r][c]!='?') {
                     if (ch == '?') {
                         for (int i = 0; i < c; i++) {
                             a[r][i]=a[r][c];
                         }
                     }
                     ch = a[r][c];
                 }
                 a[r][c] = ch;
             }
         }
     }
 
     char[][] empty(int R, int C, char[][] a) {
         boolean[] hasR = new boolean[R];
         boolean[] hasC = new boolean[C];
         for (int r = 0; r < a.length; r++) {
             for (int c = 0; c < a[r].length; c++) {
                 if (a[r][c] != '?') {                    
                     hasR[r]=true;
                     hasC[c]=true;
                 }
             }
         }
         boolean useR = true;
         boolean useC = true;
         for (int i = 0; i < hasR.length; i++) {
             if (!hasR[i]) {
                 useR=false;
             }
         }
         for (int i = 0; i < hasC.length; i++) {
             if (!hasC[i]) {
                 useC=false;
             }
         }
         if (useR) {
             for (int r = 0; r < R; r++) {
                 char ch = '?';
                 for (int c = 0; c < C; c++) {
                     if (a[r][c]!='?') {
                         if (ch == '?') {
                             for (int i = 0; i < c; i++) {
                                 a[r][i]=a[r][c];
                             }
                         }
                         ch = a[r][c];
                     }
                     a[r][c] = ch;
                 }
             }
             return a;
         } else if (useC) {
             for (int c = 0; c < C; c++) {
                 char ch = '?';
                 for (int r = 0; r < R; r++) {
                     if (a[r][c]!='?') {
                         if (ch == '?') {
                             for (int i = 0; i < r; i++) {
                                 a[i][c]=a[r][c];
                             }
                         }
                         ch = a[r][c];
                     }
                     a[r][c] = ch;
                 }
             }            
             return a;
         }        
         return null;
     }
     
     static boolean printInput=false;
 
 
     static boolean printDoubleCheck=true;
 
 
     static boolean redirectStdoutToFile=false;
 
 
     
     static boolean writeToFile=true; 
 
 
     static String[] testFilenames = new String[] {
         "A-test.in",
 
         "A-small-attempt1.in",
 
 
 
 
 
     };
 
     static int THREAD=1;
 
 
     static BufferedReader in;
     static StringTokenizer inTok = new StringTokenizer("");
     static PrintWriter out;
 
     
 
 
     static int[] debugTestCases = {};
 
     public static void main(String[] args) throws Exception {
         
         if (args.length>0) {
             testFilenames = args;
         }
 
         if (debugTestCases.length > 0) {
             System.out.println("NOTE: only run these cases: "+Arrays.toString(debugTestCases)+"\n");            
         }
         
         if (redirectStdoutToFile) {
             String filename = "output.txt";
             System.out.println("NOTE: redirect stdout to file "+filename);
             FileOutputStream fos = new FileOutputStream(filename);
             PrintStream ps = new PrintStream(fos,true);
             System.setOut(ps);
         }
 
 
 
         for (int i = 0; i < testFilenames.length; i++) {
             System.out.println((i>0?"\n\n":"") + "##### file["+i+"]: "+testFilenames[i]);
             solveFile(testFilenames[i]);
         }
     }
 
     static void solveFile(String testFilename) throws Exception {
         if (testFilename == null) {
             myTest();
             return;
         }
 
         String resultFilename=testFilename+"-res.txt";
         long start = System.currentTimeMillis();
 
         in = new BufferedReader(new FileReader(testFilename));
         int NN=INT();
         out = writeToFile ? new PrintWriter(new FileWriter(resultFilename),true)
                           : new PrintWriter(System.out, true);
         if (THREAD>1) {
             parallelSolve(NN);
         } else {
             for (int i = 1; i <= NN; i++) {
                 boolean debug=debugTestCases.length>0 && Arrays.binarySearch(debugTestCases, i)>=0;
                 if (debug) {
                     printInput = true;
                 }
 
                 if (printInput) System.out.println("\n### Case "+i);
                 new A().run(i);
                 if (debug) {
                     printInput = false;
                 }
             }
         }
         in.close();
 
         if (writeToFile) {
             out.close();
         }
 
         System.out.println("\nTime taken: "+((System.currentTimeMillis()-start)/1000.0)+" sec");
 
         if (writeToFile) {
             boolean first = true;
             String[] suffix = new String[] {
                     "", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                     "-bad", "-bad0", "-bad1", "-bad2", "-bad3", "-bad4", "-bad5", "-bad6", "-bad7", "-bad8", "-bad9", };
             for (int i = 0; i < suffix.length; i++) {
                 File ans=new File(testFilename+"-ans"+suffix[i]+".txt");
                 if(ans.exists()&&ans.length()>0) {
                     boolean same=sameFileContent(ans, new File(resultFilename), first);
                     first = false;
                     if(same) {
                         System.out.println("All pass :)          Answer == "+ans);
                         break;
                     } else {
                         System.out.println("ERROR: Answer != "+ans);
                     }
                 }
             }
         }
     }
     static void parallelSolve(int totalCase) throws Exception {
         int th = Math.min(THREAD, Runtime.getRuntime().availableProcessors());
         System.out.println("thread = "+th);
         ExecutorService executor = Executors.newFixedThreadPool(th);
         List<GcjSolver> solvers = new ArrayList<GcjSolver>();
         for (int i = 1; i <= totalCase; i++) {
             if (printInput) System.out.println("\n### Case "+i);
 
             solvers.add(new GcjSolver());
         }
 
         List <Future<Object>> results = executor.invokeAll(solvers);
 
         int caseNumber=1;
         for (Future<Object> result : results) {
             
             out.printf("Case #%d: %s\n", caseNumber, result.get());
             caseNumber++;
         }
         executor.shutdown();
     }
     static class GcjSolver implements Callable<Object> {
         final int n;
 
         
         public GcjSolver() throws IOException {
             n = INT();
             throw new RuntimeException("TODO: do reading/solving in "+getClass().getSimpleName());
         }
 
         
         
         public Object call() throws Exception {
             return n;
         }
     }
 
     private static boolean sameFileContent(File ansFile, File resFile, boolean showInfo) throws IOException {
         if (showInfo) {
             System.out.println();
         }
 
         BufferedReader ans = new BufferedReader(new FileReader(ansFile));
         BufferedReader res = new BufferedReader(new FileReader(resFile));
         boolean ok=true;
         List<Integer> wrong = new ArrayList<Integer>();
         int ln=1;
         while(true) {
             String sAns=ans.readLine();
             String sRes=res.readLine();
             if(sAns==null) {
                 if(sRes!=null) {
                     if (showInfo) {
                         System.out.println("### result has more lines than answer");
                         do {
                             System.out.println(sRes);
                         } while ((sRes=res.readLine())!=null);
                         System.out.println();
                     }
                     ok=false;
                 }
                 break;
             }
             if(sRes==null) {
                 if(sAns!=null && !sAns.isEmpty()) {
                     if (showInfo) {
                         System.out.println("### answer has more lines than result");
                         do {
                             System.out.println(sAns);
                         } while ((sAns=ans.readLine())!=null);
                         System.out.println();
                     }
                     ok=false;
                 }
                 break;
             }
             String tAns = sAns.trim();
             String tRes = sRes.trim();
             if(!tAns.equals(tRes)) {
                 if (debugTestCases.length > 0 && tRes.endsWith(": skip")) {
                     
                 } else if (matchDouble(tAns,tRes, showInfo && printDoubleCheck)) {
                     if (showInfo && printDoubleCheck) {
                         System.out.println("### line "+ln+" pass double check:");
                         System.out.println("ans = "+sAns);
                         System.out.println("res = "+sRes);
                         System.out.println();
                     }
                 } else {
                     if (showInfo) {
                         System.out.println("### line "+ln+" not match:");
                         System.out.println("ans = "+sAns);
                         System.out.println("res = "+sRes);
                         System.out.println();
                     }
                     ok=false;
                     wrong.add(ln);
                 }
             }
             ln++;
         }
         if(!ok && showInfo) {
             System.out.println("wrong : "+wrong.size()+" lines, lines start from 1: "+wrong);
             System.out.println("total : "+ln+" lines");
         }
         ans.close();
         res.close();
         return ok;
     }
     private static boolean matchDouble(String tAns, String tRes, boolean showInfo) {
         final double tol = 1e-6;
 
         if (!tAns.matches(".*[0-9]+\\.[0-9]+.*") || !tRes.matches(".*[0-9]+\\.[0-9]+.*")) {
             
             return false;
         }
 
         String[] sa= tAns.split(" ");
         String[] sr= tRes.split(" ");
         if (sa.length!=sr.length) {
             return false;
         }
         for (int i = 0; i < sr.length; i++) {
             if (sa[i].equals(sr[i])) continue;
 
             try {
                 double a = Double.parseDouble(sa[i]);
                 double r = Double.parseDouble(sr[i]);
                 if (abs(a-r)>tol) {
                     if (showInfo) {
                         System.out.println(i+"-th ans!=res: "+sa[i]+" != "+sr[i]);
                     }
                     return false;
                 }
             } catch (NumberFormatException e) {
                 return false;
             }
         }
         return true;
     }
 
 
     private static void myTest() throws Exception {
 
         int num=10000;
         for (int i = 1; i <= num; ++i) {
 
 
 
 
         }
     }
 
     static String LINE() throws IOException {
         String s=in.readLine();
         if (printInput) System.out.println(s);
         return s;
     }
     static String STR() throws IOException {
         while (!inTok.hasMoreTokens()) {
             String line = LINE();
             if (line == null) {
                 return null;
             }
             inTok = new StringTokenizer(line);
         }
         return inTok.nextToken();
     }
 
     static int INT() throws IOException { return Integer.parseInt(STR()); }
     static long LONG() throws IOException { return Long.parseLong(STR()); }
     static double DOUBLE() throws IOException { return Double.parseDouble(STR()); }
 
     static int INT(String s) { return Integer.parseInt(s); }
     static double DOUBLE(String s) { return Double.parseDouble(s); }
     static long LONG(String s) { return Long.parseLong(s); }
 }
