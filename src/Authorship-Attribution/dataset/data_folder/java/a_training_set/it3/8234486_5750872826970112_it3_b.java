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
 
 import java.util.*;
 import java.util.concurrent.*;
 import java.math.BigDecimal;
 import java.math.BigInteger;
 import java.awt.geom.*;
 import java.io.*;
 
 
 
 
 
 @SuppressWarnings("unchecked")
 public class B {
     private static final String NO = "IMPOSSIBLE";
     public static final long MAX = Long.MAX_VALUE;
     public static final int INF = 1000000000;
     public static final int MOD = 9901; 
     public static final int UNDEF = -3;
 
     
     
     private void run(int caseNumber) throws Exception {
         int N = INT();
         double V = DOUBLE();
         double X = DOUBLE();
         double[] R = new double[N];
         double[] C = new double[N];
         double maxT=-1, minT=1e10;
         for (int i = 0; i < N; i++) {
             R[i] = DOUBLE();
             C[i] = DOUBLE();
             maxT = Math.max(maxT, C[i]);
             minT = Math.min(minT, C[i]);
         }
         
         
         
         if(debugTestCases.length>0 && Arrays.binarySearch(debugTestCases, caseNumber)<0) {
             if(writeToFile) {
                 out.printf("Case #%d: skip\n", caseNumber);
             }
 
             return;
         }
 
         
         
         if (X>maxT || X<minT) {
             out.printf("Case #%d: %s\n", caseNumber, NO);
             return;
         }
         
         if (N==1) {
             if (X!=C[0]) {                
                 out.printf("Case #%d: %s\n", caseNumber, NO);
             } else {
                 out.printf("Case #%d: %s\n", caseNumber, V/R[0]);                
             }
             return;
         }
         
 
         double ret = small(V,X,R,C);
 
         
         out.printf("Case #%d: %s\n", caseNumber, ret);
 
 
     }
 
 
     
     private double small(double V, double deg, double[] R, double[] C) {
         int n = C.length;
         if (n!=2) {
             return -1;
         }
         
         double ret = 1e10;
         if (deg==C[0] && deg==C[1]) {
             ret = V/(R[0]+R[1]);
         } else if (deg==C[0]) {
             ret = V/R[0];            
         } else if (deg==C[1]) {
             ret = V/R[1];
         } else {
             
             
             double V0=((C[1]-deg)*V)/(C[1]-C[0]), V1=-((C[0]-deg)*V)/(C[1]-C[0]);
             ret = max(V0/R[0],V1/R[1]);
         }
         
         return ret;
     }
 
     
     public static double[] gauss(double[][] a, double[] b) {
         final double EPS = 1e-11;
 
         double[] x = new double[a[0].length];
 
         int row = 0;
         for (int i = 0; i < a[0].length; ++i) {
             
             int maxr = row;
             for (int k = row + 1; k < a.length; ++k) {
                 if (abs(a[k][i]) > abs(a[maxr][i])) {
                     maxr = k;
                 }
             }
 
             if (a[maxr][i] != 0) {
                 if (maxr != row) {
                     
                     double[] ta = a[row];
                     a[row] = a[maxr];
                     a[maxr] = ta;
 
                     double tb = b[row];
                     b[row] = b[maxr];
                     b[maxr] = tb;
                 }
 
                 for (int k = row + 1; k < a.length; ++k) {
                     if (a[k][i] != 0) {
                         double mul = -a[k][i] / a[row][i];
                         a[k][i] = 0; 
                         for (int l = i + 1; l < a[0].length; ++l) {
                             a[k][l] += mul * a[row][l];
                         }
                         b[k] += mul * b[row];
                     }
                 }
                 row++;
             }
         }
 
         
         for (int i = a.length - 1; i >= 0; i--) {
             
             int j = 0;
             
             while (j < a[i].length && abs(a[i][j]) < EPS)
                 j++;
 
             if (j < a[i].length) {
                 
                 x[j] = b[i];
                 for (int k = a[0].length - 1; k >= j + 1; k--) {
                     if (x[k] != 0 && a[i][k] != 0) 
                         x[j] -= a[i][k] * x[k];
                 }
                 x[j] /= a[i][j];
             } else if (abs(b[i]) > EPS) {
                 
                 return null; 
             }
         }
         return x;
     }
 
     static boolean printInput=false;
 
 
     static boolean printDoubleCheck=true;
 
     
     static boolean redirectStdoutToFile=false;
 
 
     
     static boolean writeToFile=true; 
 
 
     static String[] testFilenames = new String[] {
         "B-test.in",
         "B-small-attempt0.in",
 
 
 
 
 
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
                 new B().run(i);
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
         List<Solver> solvers = new ArrayList<Solver>();
         for (int i = 1; i <= totalCase; i++) {
             if (printInput) System.out.println("\n### Case "+i);
 
             solvers.add(new Solver());
         }
 
         List <Future<Object>> results = executor.invokeAll(solvers);
 
         int caseNumber=1;
         for (Future<Object> result : results) {
             
             out.printf("Case #%d: %s\n", caseNumber, result.get());
             caseNumber++;
         }
         executor.shutdown();
     }
     static class Solver implements Callable<Object> {
         final int n;
 
         
         public Solver() throws IOException {
             n = INT();
             throw new RuntimeException("TODO: do reading/solving in Solver()");
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
 
         int num=100000;
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
