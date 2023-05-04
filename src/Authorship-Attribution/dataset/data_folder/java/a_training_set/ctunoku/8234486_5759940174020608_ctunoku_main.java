import java.io.*;
 import java.util.*;
 import java.util.List;
 
 public class Main {
     private static StringTokenizer st;
     private static BufferedReader br;
     public static long MOD = 1000000007;
     private static double EPS = 0.0000001;
 
     public static void print(Object x) {
         System.out.println(x + "");
     }
     public static void printArr(long[] x) {
         StringBuilder s = new StringBuilder();
         for (int i = 0; i < x.length; i++) {
             s.append(x[i] + " ");
         }
         print(s);
     }
     public static void printArr(int[] x) {
         StringBuilder s = new StringBuilder();
         for (int i = 0; i < x.length; i++) {
             s.append(x[i] + " ");
         }
         print(s);
     }
     public static void printArr(char[] x) {
         StringBuilder s = new StringBuilder();
         for (int i = 0; i < x.length; i++) {
             s.append(x[i] + "");
         }
         print(s);
     }
     public static String join(Collection<?> x, String space) {
         if (x.size() == 0) return "";
         StringBuilder sb = new StringBuilder();
         boolean first = true;
         for (Object elt : x) {
             if (first) first = false;
             else sb.append(space);
             sb.append(elt);
         }
         return sb.toString();
     }
 
     public static String nextToken() throws IOException {
         while (st == null || !st.hasMoreTokens()) {
             String line = br.readLine();
             st = new StringTokenizer(line.trim());
         }
         return st.nextToken();
     }
     public static int nextInt() throws IOException {
         return Integer.parseInt(nextToken());
     }
     public static long nextLong() throws IOException {
         return Long.parseLong(nextToken());
     }
     public static double nextDouble() throws IOException {
         return Double.parseDouble(nextToken());
     }
     public static List<Integer> nextInts(int N) throws IOException {
         List<Integer> ret = new ArrayList<Integer>();
         for (int i = 0; i < N; i++) {
             ret.add(nextInt());
         }
         return ret;
     }
 
 
     public static int N;
     public static Set<String> eStart;
     public static Set<String> fStart;
     public static Set<String> sharedStart;
     public static List<List<String>> sentences;
 
     public static void add(List<String> sentence, Set<String> lang) {
         for (String word : sentence) lang.add(word);
     }
 
     public static int shared(long mask) {
         Set<String> english = new HashSet<String>();
         Set<String> french = new HashSet<String>();
         for (int i = 2; i < N; i++) {
             if (((mask >> i) & 1) == 1) add(sentences.get(i), english);
             else  add(sentences.get(i), french);
         }
 
         Set<String> newShared = new HashSet<String>();
         for (String w: english) {
             if (sharedStart.contains(w)) continue;
             if (fStart.contains(w) || french.contains(w))
                 newShared.add(w);
         }
         for (String w: french) {
             if (sharedStart.contains(w)) continue;
             if (eStart.contains(w) || english.contains(w))
                 newShared.add(w);
         }
         return sharedStart.size() + newShared.size();
     }
 
     public static String solve() {
         eStart = new HashSet<String>();
         fStart = new HashSet<String>();
         add(sentences.get(0), eStart);
         add(sentences.get(1), fStart);
         sharedStart = new HashSet<String>(eStart);
         sharedStart.retainAll(fStart);
         long min = 1000;
         for (long i = 1; i < 1 << N; i += 4) {
             min = Math.min(min, shared(i));
         }
         return min + "";
     }
 
     public static void main(String[] args) throws IOException {
         br = new BufferedReader(new InputStreamReader(System.in));
         br = new BufferedReader(new FileReader("input.txt"));
 
         int T = nextInt();
         for (int t = 1; t <= T; t++) {
             N = nextInt();
             sentences = new ArrayList<List<String>>();
             for (int i = 0; i < N; i++) {
                 String line = br.readLine();
                 String[] words = line.trim().split("\\s+");
                 List<String> sentence = new ArrayList<String>();
                 for (String w : words) sentence.add(w);
                 sentences.add(sentence);
             }
 
             System.out.printf("Case #%d: %s%n", t, solve());
         }
     }
 }