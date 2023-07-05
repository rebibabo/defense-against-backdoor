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
     public static final int INF = 1000000000;
     public static final int MOD = 9901;
     public static final int UNDEF = -3;
 
     
     
     private void run(int caseNumber) throws Exception {
         int N = INT();
         Map<String, Integer>[] map = new Map[2];
         for (int i = 0; i < map.length; i++) {
             map[i]=new HashMap<>();
         }
 
         int[][] a = new int[N][2];
         for (int i = 0; i < a.length; i++) {
             String s0=STR();
             String s1=STR();
             int i0 = idx(s0, map[0]); 
             int i1 = idx(s1, map[1]); 
             a[i][0] = i0;
             a[i][1] = i1;
         }
         
         
         if(debugTestCases.length>0 && Arrays.binarySearch(debugTestCases, caseNumber)<0) {
             if(writeToFile) {
                 out.printf("Case #%d: skip\n", caseNumber);
             }
             return;
         }
 
         
         
         int[][] seen = new int[1<<N][2];
         for (int msk = 0; msk < seen.length; msk++) {
             for (int j = 0; j < N; j++) {
                 if (has(msk,j)) {
                     seen[msk][0] |= 1<<a[j][0];
                     seen[msk][1] |= 1<<a[j][1];
                 }
             }
         }
         Map<Integer, Integer> done = new HashMap<>();
         Set<Integer> cur = new HashSet<>();
         int init = 0;
         cur.add(0);
         done.put(init, new Integer(0));
         while (true) {
             
             Set<Integer> next = new HashSet<>();
             for (int msk : cur) {
                 int v = done.get(msk);
                 for (int i = 0; i < N; i++) {
                     if (has(msk,i)) continue;
                     
                     int nmsk = msk | (1<<i);
                     int nv = v;
                     if (has(seen[msk][0], a[i][0]) && has(seen[msk][1], a[i][1])) {
                         nv+=1;
                     }
                     if (!done.containsKey(nmsk) || done.get(nmsk) < nv) {
                         next.add(nmsk);
                         done.put(nmsk, nv);
                     }
                 }
             }
             if (next.isEmpty()) {
                 break;
             }
             cur = next;
 
         }
         
 
 
 
 
 
 
 
         int ret = done.get((1<<N)-1);
 
         
         out.printf("Case #%d: %s\n", caseNumber, ret);
 
 
     }
     
     static class XY implements Comparable<XY> {
         int x;
         int y;
 
         public XY(int x, int y) {
             this.x = x;
             this.y = y;
         }
 
         public String toString() {
             return "(" + x + ", " + y + ")";
         }
 
         public int hashCode() {
             final int M = 31;
             int r = M * M + M * x + y;
             return (int) r;
         }
 
         public boolean equals(Object a) {
             if (a == null || getClass() != a.getClass())
                 return false;
             XY o = (XY) a;
             return x == o.x && y == o.y;
         }
 
         public int compareTo(XY o) {
             
             if (x != o.x)
                 return x < o.x ? -1 : 1;
             return y < o.y ? -1 : (y == o.y ? 0 : 1);
         }
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
 
     private int idx(String s, Map<String, Integer> map) {
         int r;
         if (!map.containsKey(s)) {
             r = map.size();
             map.put(s, r);
         } else {
             r = map.get(s);
         }
         return r;
     }
 
 
     static boolean printInput=false;
 
 
     static boolean printDoubleCheck=true;
 
 
     static boolean redirectStdoutToFile=false;
 
 
     
     static boolean writeToFile=true; 
 
 
     static String[] testFilenames = new String[] {
         "C-test.in",
         "C-small-attempt0.in",
 
 
 
 
 
 
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
