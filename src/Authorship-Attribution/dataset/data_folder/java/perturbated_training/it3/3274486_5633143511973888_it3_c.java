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
 public class C {
 
     private static final String NO = "IMPOSSIBLE";
     public static final long MAX = Long.MAX_VALUE;
     public static final int INF = 1000000009;
     public static final int MOD = 1000000007;
     public static final int UNDEF = -3;
 
     
     
     static int ALL = 10000;
     private void run(int caseNumber) throws Exception {
         
         int N = INT();
         int K = INT();
         double U = DOUBLE();
         int Uint = (int) round(ALL*U);
 
         int[] pint = new int[N];
         double[] p = new double[N];
         for (int i = 0; i < pint.length; i++) {
             p[i] = DOUBLE();
             pint[i] = (int) round(ALL*p[i]);
         }
         
         
         
         if(debugTestCases.length>0 && Arrays.binarySearch(debugTestCases, caseNumber)<0) {
             if(writeToFile) {
                 out.printf("Case #%d: skip\n", caseNumber);
             }
             return;
         }
 
         
         
 
         double res = small(N, U, p);
 
         out.printf("Case #%d: %s\n", caseNumber, res);
 
 
     }
 
     private double small(int n, double u, double[] p) {
         if (n==1) {
             p[0] += u;
             return p[0];
         }
         
         sort(p);
         while (u > 0) {
             int to = 0;
             while (to < n-1 && p[to]==p[to+1]) {
                 to++;
             }
             double d = to == n-1 ? 2 : p[to+1]-p[to];
             if (d*(to+1) >= u) {
                 double add = u/(to+1);
                 for (int i = 0; i <= to; i++) {
                     p[i] += add;
                 }
                 break;
             }
             u -= d*(to+1);
             for (int i = 0; i <= to; i++) {
                 p[i] = p[to+1];
             }
         }
         double res = 1;
         for (int i = 0; i < p.length; i++) {
             res *= p[i];
         }
         return res;
     }
     
     private double small(int n, int u, int[] p) {
         if (n==1) {
             p[0] += u;
             return 1.0*p[0]/ALL;
         }
         
         sort(p);
         while (u > 0) {
             int to = 0;
             while (to < n-1 && p[to]==p[to+1]) {
                 to++;
             }
             int d = to == n-1 ? ALL : p[to+1]-p[to];
             if (d*(to+1) >= u) {
                 double add = u/(to+1);
                 for (int i = 0; i <= to; i++) {
                     p[i] += add;
                 }
                 break;
             }
             u -= d*(to+1);
             for (int i = 0; i <= to; i++) {
                 p[i] = p[to+1];
             }
         }
         double res = 1;
         for (int i = 0; i < p.length; i++) {
             res *= 1.0*p[i]/ALL;
         }
         return res;
     }
 
     static double dpSlow(int N, int U, int[] p) {
         sort(p);
         
         double[][] pok = new double[N+1][U+1];
         pok[0][U] = 1;
         for (int i = 0; i < pok.length-1; i++) {
             for (int rem = 0; rem < pok[i].length; rem++) {
                 for (int use = 0; use <= min(rem, ALL-p[i]); use++) {
                     int np = p[i]+use;
                     int nrem = rem-use;
                     double nv = pok[i][rem]*np/ALL;
                     
                     if (pok[i+1][nrem] < nv) {
                         pok[i+1][nrem] = nv;
                     }
                 }
             }
         }
         double res = 0;
         for (int i = 0; i < pok[N].length; i++) {
             
             if (res < pok[N][i]) {
                 res = pok[N][i];
             }
         }
         return res;
     }
 
     
     static boolean printInput=false;
 
 
     static boolean printDoubleCheck=true;
 
 
     static boolean redirectStdoutToFile=false;
 
 
     
     static boolean writeToFile=true; 
 
 
     static String[] testFilenames = new String[] {
         "C-test.in",
 
         "C-small-1-attempt1.in",
         "C-small-1-attempt2.in",
 
 
 
 
 
 
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
                 new C().run(i);
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
                 if (!gcjDoubleEq(a, r, tol)) {
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
 
     private static boolean gcjDoubleEq(double e, double r, double tol) {
         if (Double.isNaN(e)) {
             return Double.isNaN(r);
         } else if (Double.isInfinite(e)) {
             if (e > 0) {
                 return r > 0 && Double.isInfinite(r);
             } else {
                 return r < 0 && Double.isInfinite(r);
             }
         } else if (Double.isNaN(r) || Double.isInfinite(r)) {
             return false;
         } else if (Math.abs(r - e) < tol) {
             return true;
         } else {
             double min = Math.min(e * (1.0 - tol), e * (1.0 + tol));
             double max = Math.max(e * (1.0 - tol), e * (1.0 + tol));
             return r > min && r < max;
         }
     }
 
     private static void myTest() throws Exception {
 
         ALL = 10;
         int num=100;
         for (int i = 1; i <= num; ++i) {
 
             int N = Rn.i(1,10);
             int[] p = Rn.a(N, 0, ALL);
             int s = 0;
             for (int j = 0; j < p.length; j++) {
                 s+=p[j];
             }
 
             int U = Rn.i(0, min(10,N*ALL-s));
             sort(p);
 
             double ans = dpSlow(N, U, p);
             System.out.println("\n  U=" + U);
             System.out.println("  p[" + p.length + "]=" + Arrays.toString(p));
             System.out.println("  ans=" + ans);
 
 
 
 
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
