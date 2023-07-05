import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 public class BSame {
     @SuppressWarnings("FieldCanBeLocal")
     private static int caseNumber;
     private static Scanner scan;
     private int[] m;
     private int count;
     private int n;
     private Heap heap;
 
     static long lcm(long a, long b) {
         return (Math.max(a, b) / gcd(a, b)) * Math.min(a, b);
     }
 
     static long gcd(long a, long b) {
         while (b != 0) {
             long t = a % b;
             a = b;
             b = t;
         }
 
         return a;
     }
 
     void solve() {
         count = scan.nextInt();
         n = scan.nextInt() - 1;
 
         m = new int[count];
         heap = new Heap(count);
         long lcm = 1;
         for (int i = 0; i < count; ++i) {
             m[i] = scan.nextInt();
             heap.add(new Barber(i));
             lcm = lcm(lcm, m[i]);
         }
 
         long reduce = 0;
         for (int i = 0; i < count; ++i) {
             reduce += lcm / m[i];
         }
 
         if (reduce < n) {
             n %= reduce;
         }
 
         for (int curr = 0; curr < n; ++curr) {
             nextBarber();
         }
 
         int ans = nextBarber();
 
         System.out.printf("%d\n", ans + 1);
     }
 
     int nextBarber() {
         Barber next = heap.min();
         int index = next.num;
         next.finished += m[index];
         heap.down(0);
 
         return index;
     }
 
     static class Barber implements Comparable<Barber> {
         int num;
         int finished;
 
         Barber(int num) {
             this.num = num;
         }
 
         @Override
         public int compareTo(Barber o) {
             int res = finished - o.finished;
             if (res != 0) {
                 return res;
             }
 
             return num - o.num;
         }
     }
 
     static class Heap {
         Barber[] ar;
         int size;
 
         public Heap(int capacity) {
             ar = new Barber[capacity];
             this.size = 0;
         }
 
         public Barber min() {
             return ar[0];
         }
 
         public void add(Barber val) {
             ar[size++] = val;
             up(size - 1);
         }
 
         private void down(int index) {
             while (index < size) {
                 int child = index * 2 + 1;
                 if (child < size) {
                     int childNext = child + 1;
                     if (childNext < size && ar[childNext].compareTo(ar[child]) < 0) {
                         child = childNext;
                     }
 
                     if (ar[child].compareTo(ar[index]) < 0) {
                         swap(child, index);
                         index = child;
                         continue;
                     }
                 }
                 break;
             }
         }
 
         private void up(int index) {
             while (index != 0) {
                 int parent = (index - 1) / 2;
                 if (ar[index].compareTo(ar[parent]) < 0) {
                     swap(index, parent);
                     index = parent;
                 } else {
                     break;
                 }
             }
         }
 
         private void swap(int x, int y) {
             Barber t = ar[x];
             ar[x] = ar[y];
             ar[y] = t;
         }
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
 
         String file = "B-small-attempt1";
 
 
         redirectToFile(file);
 
         String inFile = file + ".in";
         scan = new Scanner(new File(inFile));
 
         int cases = scan.nextInt();
         for (caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             System.out.printf("Case #%s: ", caseNumber);
             new BSame().solve();
             System.out.flush();
         }
 
         scan.close();
     }
 
     static void redirectToFile(String file) throws Exception {
         System.setOut(new PrintStream(file + ".out"));
     }
 }
