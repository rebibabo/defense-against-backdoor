import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 
 public class D {
    
    public void solve(Scanner scan, PrintWriter out) {
        System.out.println();
         int n = scan.nextInt();
         int[] array = new int[n];
         for (int i = 0; i < n; i++) {
             array[i] = scan.nextInt()-1;
         }
         List<List<Integer>> lists = new ArrayList<>();
 
         for (int i = 0; i < n; i++) {
             List<Integer> list = new ArrayList<>();
             list.add(i);
             int current = array[i];
             System.out.print(i + " ");
             while (!list.contains(current)) {
                 System.out.print(current + " ");
                 list.add(current);
                 current = array[current];
             }
             lists.add(list);
             System.out.println();
         }
         
         int maxLength = 0;
         
         for (int i = 0; i < lists.size(); i++) {
             List<Integer> list = lists.get(i);
             int last = list.get(list.size()-1);
             if (lists.get(last).get(1) == i) {
                 maxLength = Math.max(maxLength, list.size());
             }
         }
         for (int i = 0; i < lists.size(); i++) {
             List<Integer> list = lists.get(i);
             int last = list.get(list.size()-1);
             int lastButOne = list.get(list.size()-2);
             for (int j = 0; j < lists.size(); j++) {
                 if (i != j) {
                     List<Integer> l = reverse(lists.get(j));
                     System.out.println("Merging " + print(list) + " and " + print(l));
                     int pos = -1;
                     System.out.println("Looking for " + lastButOne + " and " + last);
                     for (int k = 1; k < l.size(); k++) {
                         if (l.get(k) == last && l.get(k-1) == lastButOne) {
                             pos = k;
                             break;
                         }
                     }
                     if (pos == -1) {
                         continue;
                     }
                     int reverseLength = 0;
                     System.out.println("Starting at pos " + pos);
                     for (int k = pos+1; k < l.size(); k++) {
                         int current = l.get(k);
                         if (list.contains(current)) {
                             break;
                         }
                         reverseLength++;
                     }
                     System.out.println("Comparing maxLength to " + (list.size() + reverseLength));
                     maxLength = Math.max(maxLength, list.size() + reverseLength);
                 }
             }
         }
         
        System.out.println(maxLength);
        out.println(maxLength);
    }
    
    
    private String print(List<Integer> list) {
        String s = "[";
        for (int i : list) {
            s += i +  ",";
        }
        s += "]";
         return s;
     }
 
 
     private List<Integer> reverse(List<Integer> list) {
        List<Integer> l = new ArrayList<>();
        for (int i = list.size()-1; i >= 0; i--) {
            l.add(list.get(i));
        }
         return l;
     }
 
     public static void main(String[] args) throws Exception {
        String filename = "C-small-attempt0";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new D().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }