package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class C {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
 
 
     public static final int FORWARD = 0;
     public static final int BACKWARD = 1;
     private List<Integer>[] inv;
     private int n;
 
     void solve() {
         n = sc.nextInt();
 
         int[] bff = new int[n];
         inv = new List[n];
         for (int i = 0; i < n; ++i) {
             int f = sc.nextInt() - 1;
             bff[i] = f;
 
             if (inv[f] == null) {
                 inv[f] = new ArrayList<>();
             }
             inv[f].add(i);
         }
 
         int max = 0;
 
         for (int start = 0; start < n; ++start) {
             List<Integer> circle = new ArrayList<>();
             circle.add(start);
 
             int size = 1;
 
             int mode = FORWARD;
 
             boolean[] seen = new boolean[n];
             seen[start] = true;
 
             int curr = start;
 
             boolean good = false;
 
             while (true) {
                 int next = bff[curr];
                 if (seen[next]) {
                     if (next == start) {
                         good = true;
                     } else if (next == circle.get(circle.size() - 2)) { 
                         mode = BACKWARD;
                         good = true;
                     }
                     
                     break;
                 } else {
                     seen[next] = true;
                     circle.add(next);
                     ++size;
                     curr = next;
                 }
             }
 
             if (mode == BACKWARD) {
                 if (inv[curr] == null) {
                     break;
                 }
 
                 size += maxHeight(curr, seen);
             }
 
             if (good) {
                 max = Math.max(max, size);
             }
         }
 
         out.printf("%d\n", max);
     }
 
     int maxHeight(int root, boolean[] seen) {
          return height(root, seen);
     }
 
     int height(int parent, boolean[] seen) {
         seen[parent] = true;
 
         List<Integer> children = inv[parent];
         if (children == null) {
             return 0;
         }
 
         int max = 0;
 
         for (int child : children) {
             if (seen[child]) {
                 continue;
             }
 
             int h = 1 + height(child, seen);
             max = Math.max(max, h);
         }
 
         seen[parent] = false;
 
         return max;
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
 
         String file = "C-small-attempt1";
 
         String outFileName = file + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             out.printf("Case #%s: ", caseNumber);
             C template = new C();
             template.caseNumber = caseNumber;
             template.solve();
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
