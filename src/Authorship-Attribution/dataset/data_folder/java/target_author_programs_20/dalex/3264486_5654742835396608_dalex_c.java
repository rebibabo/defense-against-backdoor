import java.io.*;
 import java.util.Arrays;
 import java.util.Locale;
 import java.util.StringTokenizer;
 import java.util.TreeSet;
 
 public class C {
     private static class Segment implements Comparable<Segment> {
         private int L, R;
 
         private Segment(int l, int r) {
             L = l;
             R = r;
         }
 
         @Override
         public int compareTo(Segment s) {
             int len1 = R - L + 1;
             int len2 = s.R - s.L + 1;
             if (len1 != len2) {
                 return Integer.compare(len1, len2);
             }
             if (this != s && L == s.L) {
                 throw new AssertionError();
             }
             return Integer.compare(L, s.L);
         }
 
         private int getMid() {
             return (L + R) / 2;
         }
     }
 
     private void solveTest() throws IOException {
         int n = readInt();
         int k = readInt();
         TreeSet<Segment> set = new TreeSet<>();
         set.add(new Segment(1, n));
         for (int man = 0; man < k; man++) {
             Segment s = set.pollLast();
             int mid = s.getMid();
             if (mid < s.L || s.R < mid) {
                 throw new AssertionError();
             }
             if (s.L <= mid - 1) {
                 set.add(new Segment(s.L, mid - 1));
             }
             if (mid + 1 <= s.R) {
                 set.add(new Segment(mid + 1, s.R));
             }
             if (man == k - 1) {
                 int[] arr = {mid - s.L, s.R - mid};
                 Arrays.sort(arr);
                 writer.println(arr[1] + " " + arr[0]);
             }
         }
     }
 
     private void solve() throws IOException {
         int T = readInt();
         for (int t = 1; t <= T; t++) {
             writer.printf("Case #%d: ", t);
             solveTest();
         }
     }
 
     
     private static final File INPUT_FILE = new File("C-small-1-attempt0.in");
     private static final File OUTPUT_FILE = new File("output.txt");
 
     public static void main(String[] args) throws IOException {
         new C().run();
     }
 
     private void run() throws IOException {
         Locale.setDefault(Locale.US);
         reader = new BufferedReader(new FileReader(INPUT_FILE));
         writer = new PrintWriter(OUTPUT_FILE);
         try {
             solve();
         } finally {
             reader.close();
             writer.close();
         }
     }
 
     private BufferedReader reader;
     private StringTokenizer tokenizer;
     private PrintWriter writer;
 
     private int readInt() throws IOException {
         return Integer.parseInt(readString());
     }
 
     private long readLong() throws IOException {
         return Long.parseLong(readString());
     }
 
     private String readString() throws IOException {
         while (tokenizer == null || !tokenizer.hasMoreTokens()) {
             tokenizer = new StringTokenizer(reader.readLine());
         }
         return tokenizer.nextToken();
     }
 }
