package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import java.util.concurrent.Future;
 
 
 @SuppressWarnings({"FieldCanBeLocal", "ConstantConditions", "unused"})
 public class A {
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     
     private static boolean PRINT_TO_CONSOLE = true;
     
 
     private int n;
     private int ans;
     private int[] mods;
     private int p;
 
     void read() {
         n = sc.nextInt();
         p = sc.nextInt();
 
         mods = new int[p];
         for (int i = 0; i < n; ++i) {
             int g = sc.nextInt();
             int mod = g % p;
             ++mods[mod];
         }
     }
 
     void solve() {
         ans = mods[0];
         mods[0] = 0;
 
         if (p == 2) {
             ans += (mods[1] + 1) / 2;
         } else if (p == 3) {
             int add = Math.min(mods[1], mods[2]);
             ans += add;
             mods[1] -= add;
             mods[2] -= add;
             ans += (mods[1] + 2) / 3;
             ans += (mods[2] + 2) / 3;
         } else {
             ans += mods[2] / 2;
             mods[2] = mods[2] % 2;
 
             int add = Math.min(mods[1], mods[3]);
             ans += add;
             mods[1] -= add;
             mods[2] -= add;
         }
     }
 
     void write() {
         out.printf("%d\n", ans);
     }
 
     public static void main(String[] args) throws Exception {
 
         String taskPrefix = "A-";
         String fileName = null;
 
         boolean oneThread = true;
 
 
         String dir = "out/";
 
         if (fileName == null || fileName.isEmpty()) {
             File[] inputs = new File(dir).listFiles(name -> name.getName().startsWith(taskPrefix) && name.getName().endsWith(".in"));
             File recentFile = null;
             for (File file : inputs) {
                 if (recentFile == null || recentFile.lastModified() < file.lastModified()) {
                     recentFile = file;
                 }
             }
             fileName = recentFile.getName().substring(0, recentFile.getName().lastIndexOf('.'));
         }
 
         System.out.println("Read " + fileName + "\n");
 
         String outFileName = dir + fileName + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         Locale.setDefault(Locale.US);
         String inFile = dir + fileName + ".in";
         sc = new Scanner(new File(inFile));
 
         long start = System.currentTimeMillis();
 
         int cases = sc.nextInt();
         if (oneThread) {
             for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
                 out.printf("Case #%s: ", caseNumber);
                 A template = new A();
                 template.caseNumber = caseNumber;
                 template.read();
                 template.solve();
                 template.write();
                 out.flush();
             }
         } else {
             ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), r -> {
                 Thread thread = new Thread(r);
                 thread.setDaemon(true);
                 return thread;
             });
 
             A[] tasks = new A[cases + 1];
             Future<?>[] futures = new Future[cases + 1];
 
             for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
                 A task = new A();
                 task.caseNumber = caseNumber;
                 try {
                     task.read();
                 } catch (Exception e) {
                     throw new RuntimeException("Case #" + task.caseNumber, e);
                 }
                 tasks[caseNumber] = task;
 
                 Future<?> future = executor.submit((Runnable) () -> {
                     try {
                         task.solve();
                     } catch (Exception e) {
                         throw new RuntimeException("Case #" + task.caseNumber, e);
                     }
                 });
                 futures[caseNumber] = future;
             }
 
             for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
                 out.printf("Case #%s: ", caseNumber);
                 futures[caseNumber].get();
 
                 tasks[caseNumber].write();
                 tasks[caseNumber] = null;
                 out.flush();
             }
 
             executor.shutdown();
         }
 
         long elapsed = System.currentTimeMillis() - start;
         System.out.printf("\nTime: %d ms", elapsed);
 
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
