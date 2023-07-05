
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.List;
 import java.util.Scanner;
 
 public class A {
     
     class PancakeComparator implements Comparator<Pancake> {
 
         @Override
         public int compare(Pancake o1, Pancake o2) {
             if (o1.radius != o2.radius) {
                 return o1.radius - o2.radius;
             }
             return o1.height - o2.height;
         }
     }
 
     class SurfaceAreaComparator implements Comparator<Pancake> {
 
         @Override
         public int compare(Pancake o1, Pancake o2) {
             return Double.compare(o1.surfaceArea, o2.surfaceArea);
         }
         
     }
     
     class SurfaceAreaLastComparator implements Comparator<Pancake> {
 
         @Override
         public int compare(Pancake o1, Pancake o2) {
             return Double.compare(o1.surfaceAreaLast, o2.surfaceAreaLast);
         }
         
     }
     
     class Pancake {
         Pancake(int id, int radius, int height) {
             this.id = id;
             this.height = height;
             this.radius = radius;
             this.surfaceArea = 2 * Math.PI * radius * height;
             this.surfaceAreaLast = this.surfaceArea + Math.PI * radius * radius;
         }
         int id;
         int height;
         int radius;
         double surfaceArea;
         double surfaceAreaLast;
     }
     
     public void solve(Scanner scan, PrintWriter out) {
         int n = scan.nextInt();
         int k = scan.nextInt();
         
         List<Pancake> pancakes = new ArrayList<>();
         
         for (int i = 0; i < n; i++) {
             pancakes.add(new Pancake(i, scan.nextInt(), scan.nextInt()));
         }
         
         Collections.sort(pancakes, new PancakeComparator());
         
         double best = 0;
         
         for (int i = k-1; i < n; i++) {
             List<Pancake> top = pancakes.subList(0, i);
             Pancake bottom = pancakes.get(i);
 
             Collections.sort(top, new SurfaceAreaComparator());
             
             double surfaceArea = bottom.surfaceAreaLast;
             
             for (int j = 0; j < k-1; j++) {
                 surfaceArea += top.get(top.size() - j - 1).surfaceArea;
             }
             
             best = Math.max(best, surfaceArea);
         }
         
         System.out.println(best);
         out.println(best);
     }
 
     public static void main(String[] args) throws Exception {
         String filename = "A-small-attempt0";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new A().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
     
 }