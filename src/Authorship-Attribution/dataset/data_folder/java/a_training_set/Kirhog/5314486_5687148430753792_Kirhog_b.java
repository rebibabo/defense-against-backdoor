package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.LinkedList;
 import java.util.Locale;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import java.util.concurrent.Future;
 
 
 @SuppressWarnings({"FieldCanBeLocal", "ConstantConditions", "unused"})
 public class B {
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     
     private static boolean PRINT_TO_CONSOLE = true;
     
 
     private int n;
     private int ans;
     private int c;
     private int m;
 
     private Map<Integer, int[]> map = new HashMap<>();
     int n1 = 0;
     int n2 = 0;
     private int rides;
     private int promotions;
 
     LinkedList<Ticket> tickets = new LinkedList<>();
     Map<Integer, LinkedList<Ticket>> ticketByPerson = new HashMap<>();
     Map<Integer, LinkedList<Ticket>> ticketByPlace = new HashMap<>();
 
     void read() {
         n = sc.nextInt();
         c = sc.nextInt();
         m = sc.nextInt();
 
         
 
         for (int i = 0; i < m; ++i) {
             int pos = sc.nextInt() - 1;
             int buyer = sc.nextInt() - 1;
             Ticket t = new Ticket(pos, buyer);
             tickets.add(t);
 
             LinkedList<Ticket> tt = ticketByPerson.get(buyer);
             if (tt == null) {
                 tt = new LinkedList<>();
                 ticketByPerson.put(buyer, tt);
             }
             tt.add(t);
 
             tt = ticketByPlace.get(pos);
             if (tt == null) {
                 tt = new LinkedList<>();
                 ticketByPlace.put(pos, tt);
             }
             tt.add(t);
         }
     }
 
     void solve() {
         rides = 0;
         promotions = 0;
 
         boolean[] used = new boolean[c];
         Ticket[] places = new Ticket[n];
         while (true) {
             Arrays.fill(used, false);
             Arrays.fill(places, null);
 
             boolean found = false;
 
             
             Set<Integer> keysToRemove = new HashSet<>();
             for (Map.Entry<Integer, LinkedList<Ticket>> entry : ticketByPerson.entrySet()) {
                 if (entry.getValue().isEmpty()) {
                     keysToRemove.add(entry.getKey());
                     continue;
                 }
                 found = true;
 
                 LinkedList<Ticket> tt = entry.getValue();
                 for (Iterator<Ticket> iterator = tt.iterator(); iterator.hasNext(); ) {
                     Ticket t = iterator.next();
                     if (places[t.pos] == null) {
                         places[t.pos] = t;
                         iterator.remove();
                         used[t.buyer] = true;
 
                         break;
                     }
                 }
 
                 if (entry.getValue().isEmpty()) {
                     keysToRemove.add(entry.getKey());
                 }
             }
 
             if (!found) {
                 break;
             }
 
             for (Integer key : keysToRemove) {
                 ticketByPerson.remove(key);
             }
 
             
             for (int i = 0; i < n; ++i) {
                 if (places[i] == null) {
                     for (Map.Entry<Integer, LinkedList<Ticket>> entry : ticketByPerson.entrySet()) {
                         if (entry.getValue().isEmpty()) {
                             keysToRemove.add(entry.getKey());
                             continue;
                         }
 
                         if (used[entry.getKey()]) {
                             continue;
                         }
 
                         LinkedList<Ticket> tt = entry.getValue();
                         for (Iterator<Ticket> iterator = tt.iterator(); iterator.hasNext(); ) {
                             Ticket t = iterator.next();
                             if (t.pos > i) {
                                 places[t.pos] = t;
                                 iterator.remove();
                                 used[t.buyer] = true;
                                 ++promotions;
                                 break;
                             }
                         }
                     }
                 }
             }
 
             ++rides;
         }
     }
 
     static class Ticket {
         int pos;
         int buyer;
 
         public Ticket(int pos, int buyer) {
             this.pos = pos;
             this.buyer = buyer;
         }
     }
 
     void write() {
         out.printf("%d %d\n", rides, promotions);
     }
 
     public static void main(String[] args) throws Exception {
 
         String taskPrefix = "B-";
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
                 B template = new B();
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
 
             B[] tasks = new B[cases + 1];
             Future<?>[] futures = new Future[cases + 1];
 
             for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
                 B task = new B();
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
         System.out.printf("\nTime for %s: %d ms", fileName, elapsed);
 
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
