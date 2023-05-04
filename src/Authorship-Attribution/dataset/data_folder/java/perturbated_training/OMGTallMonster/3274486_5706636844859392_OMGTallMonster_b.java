import java.util.*;
 import java.util.stream.*;
 import java.io.*;
 import java.math.*;
 import java.awt.geom.*;
 public class B {
   public static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
   public static final PrintWriter outWriter = new PrintWriter(System.out);
   public static class Activity implements Comparable<Activity> {
     public int start;
     public int end;
     public char type;
     public int start() { return start; }
     public int end() { return end; }
     public char type() { return type; }
     Activity(int start_, int end_, char type_) { start=start_; end=end_; type=type_; }
     public Activity clone() { return new Activity(start,end,type); }
     public static Comparator<Activity> cmp = Comparator.comparing(Activity::start).thenComparing(Activity::end).thenComparing(Activity::type);
     public int compareTo(Activity o) { return cmp.compare(this, o); }
     public boolean equals(Object oo) {
       @SuppressWarnings("unchecked")
       Activity o = (Activity)oo;
       return start==o.start&&end==o.end&&type==o.type;
     }
     public int hashCode() { return Objects.hash(start,end,type); }
     public String toString() { return ""+start+","+end+","+type; }
     int time() { return (end-start+720)%720; }
   }
   public static Object solve() {
     int ac = nextInt(), aj = nextInt();
     int n = ac+aj;
     int cTime = 720, jTime = 720;
     ArrayList<Activity> acts = new ArrayList<Activity>(n);
     for (int i = 0; i < ac; i++) {
       acts.add(new Activity(nextInt(), nextInt(), 'c'));
       cTime -= acts.get(i).time();
     }
     for (int i = ac; i < n; i++) {
       acts.add(new Activity(nextInt(), nextInt(), 'j'));
       jTime -= acts.get(i).time();
     }
     if (ac <= 1 && aj <= 1) { return 2; }
     Collections.sort(acts);
     int minStart = acts.get(0).start;
     for (Activity a : acts) {
       a.start -= minStart;
       a.end -= minStart;
       if (a.end < 0) {
         a.end += 1440;
       }
     }
     for (int i = 0; i < n; i++) {
       Activity a = acts.get(i), b = acts.get(i+1 == n ? 0 : i+1);
       if (a.type == b.type) {
         double diff = b.end - a.start;
         if (diff < 0) { diff += 1440; }
         if (a.type == 'c' && diff <= jTime) {
           jTime -= diff;
           ac--;
         } else if (a.type == 'j' && diff <= cTime) {
           cTime -= diff;
           aj--;
         } else {
           continue;
         }
         acts.remove(a);
         acts.remove(b);
         acts.add(i+1 == n ? 0 : i, new Activity(a.start, b.end, a.type));
         n--;
       }
     }
     return Math.max(ac,aj)*2;
   }
   public static void main(String[] args) {
     int T = nextInt();
     for (int i = 0; i < T; i++) {
       outWriter.print("Case #"+(i+1)+": ");
       Object tmp = solve();
       if (tmp != null) { outWriter.println(tmp); }
     }
     outWriter.flush();
   }
   public static StringTokenizer tokenizer = null;
   public static String nextLine() {
     try { return buffer.readLine(); } catch (IOException e) { throw new UncheckedIOException(e); }
   }
   public static String next() {
     while (tokenizer == null || !tokenizer.hasMoreElements()) { tokenizer = new StringTokenizer(nextLine()); }
     return tokenizer.nextToken();
   }
   public static int nextInt() { return Integer.parseInt(next()); }
   public static long nextLong() { return Long.parseLong(next()); }
   public static double nextDouble() { return Double.parseDouble(next()); }
 }
