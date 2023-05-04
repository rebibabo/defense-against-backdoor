package codejam.y2016.r1.z2;
 
 import com.javafx.tools.doclets.internal.toolkit.util.DocFinder;
 
 import java.io.*;
 import java.util.*;
 import java.text.*;
 import java.math.*;
 import java.util.regex.*;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         
         Scanner sc = new Scanner(System.in);
         
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2016/r1/z2/z2.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
 
 
 
         int tn = sc.nextInt();
         main:
         for (int ti = 1; ti <= tn; ti++) {
             int n = sc.nextInt();
             final List<List<Integer>> lists = new ArrayList<List<Integer>>(2*n-1);
             for (int i = 0; i < 2*n-1; i++) {
                 List<Integer> cur = new ArrayList<Integer>(n);
                 for (int j=0;j<n;j++) {
                     cur.add(sc.nextInt());
                 }
                 lists.add(cur);
             }
             int cnt = 1<<(2*n-1);
             String res = "";
             inner:
             for (int i=0;i<cnt;i++) {
                 List<Node> b = new ArrayList<Node>(n);
                 int k = i;
                 int cntt = 0;
                 int kx = 0;
                 boolean [] xx = new boolean[2*n-1];
                 while (k > 0) {
                     if (k % 2 == 1) {
                         b.add(new Node(cntt, kx));
                         cntt++;
                         xx[kx] = true;
                     }
                     k = k/2;
                     kx++;
                 }
                 if (cntt==n) {
                     Collections.sort(b, new Comparator<Node>() {
                         @Override
                         public int compare(Node o1, Node o2) {
                             return Integer.compare(lists.get(o1.value).get(0),
                                     lists.get(o2.value).get(0));
                         }
                     });
                     Set<Integer> ins = new HashSet<Integer>();
                     Set<Integer> all = new HashSet<Integer>();
                     for (int idx=0;idx<n;idx++) {
                         all.add(idx);
                         List<Integer> cur = new ArrayList<Integer>(n);
                         for (int j=0;j<n;j++) {
                             int value = b.get(j).value;
                             List<Integer> list = lists.get(value);
                             cur.add(list.get(idx));
                         }
                         for (int j=0;j<2*n-1;j++) {
                             if (!xx[j]) {
                                 if (lists.get(j).equals(cur)) {
                                     xx[j] = true;
                                     ins.add(idx);
                                     break;
                                 }
                             }
                         }
                     }
                     if (ins.size()==n-1) {
                         all.removeAll(ins);
                         int idx = all.iterator().next();
                         List<Integer> cur = new ArrayList<Integer>(n);
                         for (int j=0;j<n;j++) {
                             Integer e = lists.get(b.get(j).value).get(idx);
                             cur.add(e);
                             if (j==0) {
                                 res = res + e;
                             } else {
                                 res = res + " " + e;
                             }
                         }
                         
                         break inner;
                     }
                 }
             }
             res = "Case #" + ti + ": " + res;
             printWriter.write(res + "\n");
             System.out.println(res);
         }
         printWriter.close();
     }
 
     private static class Node {
         private int idx;
         private int value;
 
         public Node(int idx, int value) {
             this.idx = idx;
             this.value = value;
         }
 
         @Override
         public boolean equals(Object o) {
             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;
 
             Node node = (Node) o;
 
             if (idx != node.idx) return false;
             return value == node.value;
 
         }
 
         @Override
         public int hashCode() {
             int result = idx;
             result = 31 * result + value;
             return result;
         }
     }
 }
