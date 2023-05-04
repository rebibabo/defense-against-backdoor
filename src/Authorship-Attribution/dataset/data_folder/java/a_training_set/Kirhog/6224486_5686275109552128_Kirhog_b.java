import java.io.File;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.List;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 public class B {
     @SuppressWarnings("FieldCanBeLocal")
     private static int caseNumber;
     private static Scanner scan;
     private List<Entry> d;
 
     int f() {
         merge();
 
         Entry max = d.get(0);
         int res1 = max.val;
 
         int res2 = Integer.MAX_VALUE;
         if (max.count < res1) {
             for (int div = 2; div <= max.val; ++div) {
                 List<Entry> orig = d;
 
                 d = copy(d);
                 max = d.get(0);
                 int val1 = max.val / div;
                 int val2 = max.val - val1;
                 max.val = val1;
                 d.add(new Entry(val2, max.count));
                 res2 = Math.min(res2, max.count + f());
 
                 d = orig;
             }
         }
 
         return Math.min(res1, res2);
     }
 
     List<Entry> copy(List<Entry> d) {
         List<Entry> copy = new ArrayList<Entry>(d.size());
         for (Entry e : d) {
             copy.add(new Entry(e.val, e.count));
         }
 
         return copy;
     }
 
     void solve() {
         int n = scan.nextInt();
         d = new ArrayList<Entry>(n);
         for (int i = 0; i < n; ++i) {
             d.add(new Entry(scan.nextInt(), 1));
         }
 
         
 
         System.out.printf("%s\n", f());
     }
 
     void merge() {
         sort(d);
 
         List<Entry> result = new ArrayList<Entry>();
         Entry last = d.get(0);
         for (int i = 1; i < d.size(); ++i) {
             Entry current = d.get(i);
             if (current.val != last.val) {
                 result.add(last);
                 last = current;
             } else {
                 last.count += current.count;
             }
         }
         result.add(last);
         sort(result);
 
         d = result;
     }
 
     static void sort(List<Entry> list) {
         Collections.sort(list, new Comparator<Entry>() {
             @Override
             public int compare(Entry o1, Entry o2) {
                 return -(o1.val - o2.val);
             }
         });
     }
 
     static class Entry {
         int val;
         int count;
 
         Entry(int val, int count) {
             this.val = val;
             this.count = count;
         }
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         String file = "B-small-attempt2";
 
         redirectToFile(file);
 
         String inFile = file + ".in";
         scan = new Scanner(new File(inFile));
 
         int cases = scan.nextInt();
         for (caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             System.out.printf("Case #%s: ", caseNumber);
             new B().solve();
             System.out.flush();
         }
 
         scan.close();
     }
 
     static void redirectToFile(String file) throws Exception {
         System.setOut(new PrintStream(file + ".out"));
     }
 }
