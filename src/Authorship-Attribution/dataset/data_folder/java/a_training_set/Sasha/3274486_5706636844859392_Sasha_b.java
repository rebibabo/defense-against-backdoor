import java.io.File;
 import java.io.PrintStream;
 import java.lang.reflect.Array;
 import java.util.*;
 
 public class B {
 
     private static final int HALF = 12 * 60;
     private static final int FULL = 24 * 60;
 
     private static class Interval implements Comparable<Interval> {
         int s;
         int f;
         boolean first;
 
         Interval(int s, int f, boolean first) {
             this.s = s;
             this.f = f;
             this.first = first;
         }
 
         @Override
         public int compareTo(Interval o) {
             return Integer.compare(this.s, o.s);
         }
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         Scanner in = new Scanner(new File("problem.in"));
         PrintStream out = new PrintStream(new File("problem.out"));
 
         int T = in.nextInt();
 
         for (int test = 1; test <= T; test++) {
             int ac = in.nextInt();
             int aj = in.nextInt();
 
             Interval[] intervals = new Interval[ac + aj];
 
 
             for (int i = 0; i < ac; i++) {
                 intervals[i] = new Interval(in.nextInt(), in.nextInt(), true);
             }
 
             for (int i = 0; i < aj; i++) {
                 intervals[ac + i] = new Interval(in.nextInt(), in.nextInt(), false);
             }
 
             Arrays.sort(intervals);
 
 
             int change = 0, c1 = 0, c2 = 0;
             List<Integer> replace1 = new ArrayList<>();
             List<Integer> replace2 = new ArrayList<>();
 
             for (int i = 0; i < intervals.length; i++) {
                 if (intervals[i].first) {
                     c1 += intervals[i].f - intervals[i].s;
                     if (i > 0) {
                         if (!intervals[i - 1].first) {
                             change++;
                         } else {
                             c1 += intervals[i].s - intervals[i - 1].f;
                             replace1.add(intervals[i].s - intervals[i - 1].f);
                         }
                     }
                 } else {
                     c2 += intervals[i].f - intervals[i].s;
                     if (i > 0) {
                         if (intervals[i - 1].first) {
                             change++;
                         } else {
                             c2 += intervals[i].s - intervals[i - 1].f;
                             replace2.add(intervals[i].s - intervals[i - 1].f);
                         }
                     }
                 }
             }
 
             if (intervals.length > 0) {
                 if (intervals[0].first == intervals[intervals.length - 1].first) {
                     if (intervals[0].first) {
                         c1 += intervals[0].s + FULL - intervals[intervals.length - 1].f;
                         replace1.add(intervals[0].s + FULL - intervals[intervals.length - 1].f);
                     } else {
                         c2 += intervals[0].s + FULL - intervals[intervals.length - 1].f;
                         replace2.add(intervals[0].s + FULL - intervals[intervals.length - 1].f);
                     }
                 } else {
                     change++;
                 }
             }
             if (c1 > HALF) {
                 Collections.sort(replace1);
                 int i = replace1.size() - 1;
                 while (c1 > HALF) {
                     c1 -= replace1.get(i);
                     i--;
                     change += 2;
                 }
 
             } else if (c2 > HALF) {
                 Collections.sort(replace2);
                 int i = replace2.size() - 1;
                 while (c2 > HALF) {
                     c2 -= replace2.get(i);
                     i--;
                     change += 2;
                 }
             }
 
 
             out.printf("Case #%d: %d\n", test, change);
 
         }
     }
 }
