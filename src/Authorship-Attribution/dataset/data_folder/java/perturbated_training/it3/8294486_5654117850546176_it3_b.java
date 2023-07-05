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
 public class B {
 
     private static final String NO = "IMPOSSIBLE";
     public static final long MAX = Long.MAX_VALUE;
     public static final int INF = 1000000000;
     public static final int MOD = 9901; 
     public static final int UNDEF = -3;
 
     
     
     int C = 8;
     final static int R=1, Y=2, B=4, O=R+Y, G=Y+B, V=R+B;
     final static char[] S = new char[] {'x', 'R', 'Y', 'O', 'B'};
     final static String SS = new String(S);
     private void run(int caseNumber) throws Exception {
         
         int N = INT();
         int[] cnt = new int[C];
         cnt[R] = INT();
         cnt[O] = INT();
         cnt[Y] = INT();
         cnt[G] = INT();
         cnt[B] = INT();
         cnt[V] = INT();
 
 
 
 
 
 
         
         
         if(debugTestCases.length>0 && Arrays.binarySearch(debugTestCases, caseNumber)<0) {
             if(writeToFile) {
                 out.printf("Case #%d: skip\n", caseNumber);
             }
             return;
         }
 
         
         
 
 
 
         String res = small(N, cnt);
         
         out.printf("Case #%d: %s\n", caseNumber, res);
 
 
     }
 
     private String small(int N, int[] cnt) {
         int r = cnt[R], y = cnt[Y], b=cnt[B];
         if (r > N/2 || y > N/2 || b > N/2) {
             return NO;
         }
         
         XY[] a = new XY[3];
         a[0] = new XY(cnt[R],R);
         a[1] = new XY(cnt[Y],Y);
         a[2] = new XY(cnt[B],B);
         char[] res = new char[N];
         int prev = 0;
         for (int i = 0; i < res.length; i++) {
             sort(a);
             boolean ok = false;
             for (int j = a.length-1; j >= 0; j--) {
                 if (a[j].cnt > 0 && prev != a[j].color) {
                     prev = a[j].color;
                     a[j].cnt--;
                     ok = true;
                     break;
                 }
             }
 
 
 
             res[i] = S[prev];
         }
         if (res[0]==res[N-1]) {
             if (res[N-2] != res[0]) {
                 
                 char tmpForSwap = res[N-1];
                 res[N-1] = res[N-2];
                 res[N-2] = tmpForSwap;
             } else {
                 System.out.println("error");
             }
         }
         check(cnt, res);
         return new String(res);
     }
     
     private void check(int[] cnt, char[] res) {
         int[] num = new int[C];
         for (int i = 0; i < res.length; i++) {
             num[SS.indexOf(res[i])]++;
             if (res[i] == res[(i+1)%res.length]) {
                 System.out.println("  cnt[" + cnt.length + "]=" + Arrays.toString(cnt));
                 System.out.println("  res=" + new String(res));
                 System.out.println("ERROR same: "+i);
                 System.exit(1);
             }
         }
         if (!Arrays.equals(cnt,num)) {
             System.out.println("ERROR not eq: "+new String(res));
         }
     }
 
     static class XY implements Comparable<XY> {
         int cnt;
         int color;
 
         public XY(int x, int y) {
             this.cnt = x;
             this.color = y;
         }
 
         public String toString() {
             return "(" + cnt + ", " + color + ")";
         }
 
         public int hashCode() {
             final int M = 31;
             int res = M * M + M * cnt + color;
             return (int) res;
         }
 
         public boolean equals(Object obj) {
             if (obj == null || getClass() != obj.getClass())
                 return false;
             XY other = (XY) obj;
             return cnt == other.cnt && color == other.color;
         }
 
         public int compareTo(XY other) {
             
             if (cnt != other.cnt)
                 return cnt < other.cnt ? -1 : 1;
             return color < other.color ? -1 : (color == other.color ? 0 : 1);
         }
     }
 
     void dp(int N, int[] cnt) {
         
 
         boolean[][][] ok = new boolean[N][C][C];
         for (int i = 0; i < ok[0].length; i++) {
             if (cnt[i] > 0) {                
                 ok[0][i][i] = true;
 
             }
         }
         for (int i = 0; i < ok.length-1; i++) {
             for (int fst = 0; fst < ok[i].length; fst++) {
                 for (int prev = 0; prev < ok[i][fst].length; prev++) {
                     if (ok[i][fst][prev]) {
                         for (int c = 1; c < C; c++) {
 
                         }
                     }
                 }
             }
         }
     }
 
     
     static boolean printInput=false;
 
 
     static boolean printDoubleCheck=true;
 
     
     static boolean redirectStdoutToFile=false;
 
 
     
     static boolean writeToFile=true; 
 
 
     static String[] testFilenames = new String[] {
 
 
         "B-small-attempt1.in",
 
 
 
 
 
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
