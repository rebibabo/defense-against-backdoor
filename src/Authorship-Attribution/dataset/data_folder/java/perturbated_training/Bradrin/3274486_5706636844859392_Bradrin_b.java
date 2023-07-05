
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.List;
 import java.util.Scanner;
 
 public class B {
     
     class ActivityComparator implements Comparator<Activity> {
 
         @Override
         public int compare(Activity o1, Activity o2) {
             return o1.start - o2.start;
         }
         
     }
     
     class Activity {
         int owner;
         int start;
         int end;
         int duration;
 
         Activity(int owner, int start, int end) {
             this.owner = owner;
             this.start = start;
             this.end = end;
             this.duration = end - start;
             if (duration < 0) {
                 duration += 1440;
             }
         }
     }
     
     int n;
    
    public void solve(Scanner scan, PrintWriter out) {
        int ac = scan.nextInt();
        int aj = scan.nextInt();
        
        n = ac + aj;
 
         List<Activity> a = new ArrayList<>();
         List<Activity> b = new ArrayList<>();
         List<Activity> all = new ArrayList<>();
 
         for (int i = 0; i < ac; i++) {
             a.add(new Activity(0, scan.nextInt(), scan.nextInt()));
         }
         for (int i = 0; i < aj; i++) {
             b.add(new Activity(1, scan.nextInt(), scan.nextInt()));
         }
 
         Collections.sort(a, new ActivityComparator());
         Collections.sort(b, new ActivityComparator());
         
         all.addAll(a);
         all.addAll(b);
 
         Collections.sort(all, new ActivityComparator());
 
         List<Activity> changeTimes = new ArrayList<>();
         List<Activity> aExtraChangeTimes = new ArrayList<>();
         List<Activity> bExtraChangeTimes = new ArrayList<>();
         
         Activity[] activities = new Activity[ac + aj];
         all.toArray(activities);
         
         for (int i = 0; i < activities.length; i++) {
             int next = (i+1)%n;
             if (activities[i].owner != activities[next].owner) {
                 changeTimes.add(new Activity(0, activities[i].end, activities[next].start));
             } else if (activities[i].owner == 0) {
                 aExtraChangeTimes.add(new Activity(0, activities[i].end, activities[next].start));
             } else if (activities[i].owner == 1) {
                 bExtraChangeTimes.add(new Activity(1, activities[i].end, activities[next].start));
             } else {
                 System.out.println(i + " " + next + " " + activities[i].owner + " " + activities[next].owner);
                 throw new RuntimeException();
             }
         }
 
         Collections.sort(aExtraChangeTimes, new ActivityComparator());
         Collections.sort(bExtraChangeTimes, new ActivityComparator());
 
         int aTotalDuration = totalDuration(a);
         int bTotalDuration = totalDuration(b);
         
 
         
         if (aj == 0) {
             int result = bar(changeTimes, aTotalDuration, aExtraChangeTimes, bExtraChangeTimes);
             System.out.println(result);
             out.println(result);
             return;
         } else if (ac == 0) {
             int result = bar(changeTimes, bTotalDuration, bExtraChangeTimes, aExtraChangeTimes);
             System.out.println(result);
             out.println(result);
             return;
         }
         
         int result = Math.max(foo(changeTimes, aTotalDuration, aExtraChangeTimes, bExtraChangeTimes), foo(changeTimes, bTotalDuration, bExtraChangeTimes, aExtraChangeTimes));
         
         System.out.println(result);
         out.println(result);
    }
    
    private int bar(List<Activity> changeTimes, int totalDuration, List<Activity> extraChangeTimes, List<Activity> ownExtraChangeTimes) {
        int current = 1440;
        current -= totalDuration;
         int changes = extraChangeTimes.size() * 2;
 
         List<Activity> copy = new ArrayList<>();
         copy.addAll(extraChangeTimes);
         while (current > 720) {
             Activity a = copy.remove(copy.size() - 1);
             changes -= 2;
 
             current -= a.duration;
         }
         return Math.max(1, changes);
     }
 
     private int foo(List<Activity> changeTimes, int totalDuration, List<Activity> extraChangeTimes, List<Activity> ownExtraChangeTimes) {
 
         totalDuration += totalDuration(changeTimes);
 
         totalDuration += totalDuration(ownExtraChangeTimes);
 
        int changes = changeTimes.size()-1;
        List<Activity> copy = new ArrayList<>();
        copy.addAll(extraChangeTimes);
        while (totalDuration < 720) {
            Activity a = copy.remove(copy.size() - 1);
            changes += 2;
 
            totalDuration += a.duration;
        }
         return Math.min(n, changes);
     }
 
     public int totalDuration(List<Activity> activities) {
        int totalDuration = 0;
        for (Activity a : activities) {
            totalDuration += a.duration;
        }
        return totalDuration;
    }
    
     public static void main(String[] args) throws Exception {
        String filename = "B-small-attempt3";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new B().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }