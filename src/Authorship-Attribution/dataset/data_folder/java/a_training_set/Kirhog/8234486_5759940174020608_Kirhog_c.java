import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Locale;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.concurrent.CountDownLatch;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class C {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
     private Set<String> ss1;
     private Set<String> ss2;
     private int n;
     private Set<String>[] lines;
     
 
     static CountDownLatch latch;
 
     private int ans;
 
     void read() {
         n = sc.nextInt() - 2;
         sc.nextLine();
 
         ss1 = readNext();
         ss2 = readNext();
 
         lines = new Set[n];
         for (int i = 0; i < n; ++i) {
             lines[i] = readNext();
         }
     }
 
     void solve() {
         ans = Integer.MAX_VALUE;
         for (int mask = 0, end = 1 << n; mask < end; ++mask) {
             int common = calc(mask);
             ans = Math.min(ans, common);
         }
 
         latch.countDown();
     }
 
     int calc(int mask) {
         Set<String> s1 = new HashSet<String>(ss1);
         Set<String> s2 = new HashSet<String>(ss2);
         for (int i = 0; i < n; ++i) {
             if (((1 << i) & mask) != 0) {
                 s1.addAll(lines[i]);
             } else {
                 s2.addAll(lines[i]);
             }
         }
         int c = 0;
         for (String word : s1) {
             if (s2.contains(word)) {
                 ++c;
             }
         }
 
         return c;
     }
 
 
     Set<String> readNext() {
         String line = sc.nextLine();
         return new HashSet<String>(Arrays.asList(line.split(" ")));
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
 
         String file = "C-small-attempt1";
 
         String outFileName = file + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         ExecutorService executor = Executors.newFixedThreadPool(4);
 
         int cases = sc.nextInt();
         C[] solutions = new C[cases + 1];
         latch = new CountDownLatch(cases);
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             final C template = new C();
             template.caseNumber = caseNumber;
             template.read();
             executor.execute(new Runnable() {
                 @Override
                 public void run() {
                     template.solve();
                 }
             });
             solutions[caseNumber] = template;
         }
 
         latch.await();
         executor.shutdown();
 
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             out.printf("Case #%d: %d\n", caseNumber, solutions[caseNumber].ans);
             out.flush();
         }
 
         sc.close();
     }
 
     static class CJPrintStream extends PrintStream {
         public CJPrintStream(String fileName) throws FileNotFoundException {
             super(fileName);
         }
 
         @SuppressWarnings("NullableProblems")
         @Override
         public PrintStream printf(String format, Object... args) {
             System.out.printf(format, args);
             return super.printf(format, args);
         }
 
         @Override
         public void println() {
             System.out.println();
             super.println();
         }
 
         @Override
         public void flush() {
             System.out.flush();
             super.flush();
         }
     }
 }
