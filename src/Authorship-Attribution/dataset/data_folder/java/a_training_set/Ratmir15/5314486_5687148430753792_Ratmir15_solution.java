package r22017.z2;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         
         
         Scanner sc = new Scanner(System.in);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Downloads/codejam/src/r22017/z2/z2output.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             int n = sc.nextInt();
             int c = sc.nextInt();
             int m = sc.nextInt();
             int[] p = new int[m];
             int[] b = new int[m];
             Map<Integer, TreeMap<Integer, Integer>> seats = new HashMap<Integer, TreeMap<Integer, Integer>>();
             seats.put(1, new TreeMap<Integer, Integer>());
             seats.put(2, new TreeMap<Integer, Integer>());
             int cntt1 = 0;
             int cntt2 = 0;
             for (int i = 0; i < m; i++) {
                 p[i] = sc.nextInt();
                 b[i] = sc.nextInt();
                 Map<Integer, Integer> map = seats.get(b[i]);
                 Integer integer = map.get(p[i]);
                 if (integer == null) {
                     map.put(p[i], 1);
                 } else {
                     map.put(p[i], integer + 1);
                 }
                 if (b[i]==1) {
                     cntt1++;
                 } else {
                     cntt2++;
                 }
             }
             int nn = Math.max(cntt1, cntt2);
             int mn = cntt1 > cntt2 ? 2 : 1;
             int np = 0;
             while (seats.get(mn).size() > 0) {
                 Map.Entry<Integer, Integer> entry = seats.get(mn).firstEntry();
                 Integer x = null;
                 for (Map.Entry<Integer, Integer> s : seats.get(3 - mn).entrySet()) {
                     if (!s.getKey().equals(entry.getKey())) {
                         if (seats.get(mn).containsKey(s.getKey())) {
                             x = s.getKey();
                         }
                     }
                 }
                 if (x == null) {
                     for (Map.Entry<Integer, Integer> s : seats.get(3 - mn).entrySet()) {
                         if (!s.getKey().equals(entry.getKey())) {
                             x = s.getKey();
                         }
                     }
                 }
                 if (x == null) {
                     np++;
                     x = entry.getKey();
                     if (x==1) {
                         nn++;
                         np--;
                     }
                 }
                 
                 
                 Integer integer = seats.get(3 - mn).get(x);
                 if (integer == 1) {
                     seats.get(3 - mn).remove(x);
                 } else {
                     seats.get(3 - mn).put(x, integer - 1);
                 }
                 integer = seats.get(mn).get(entry.getKey());
                 if (integer == 1) {
                     seats.get(mn).remove(entry.getKey());
                 } else {
                     seats.get(mn).put(entry.getKey(), integer - 1);
                 }
             }
 
             String result = String.valueOf(nn)+" "+String.valueOf(np);
             String res = "Case #" + ti + ": " + result;
             printWriter.write(res + "\n");
             System.out.println(res);
         }
         printWriter.close();
     }
 
 
 }
