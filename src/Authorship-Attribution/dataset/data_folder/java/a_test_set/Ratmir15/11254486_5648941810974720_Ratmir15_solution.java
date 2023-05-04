package codejam.y2016.r2.z1;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.util.*;
 
 public class Solution {
     static String [] bank = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
 
     public static void main(String[] args) throws FileNotFoundException {
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
         FileInputStream fileInputStream = new FileInputStream("/Users/ratmir/Downloads/A-small-attempt0 (2).in");
         
         Scanner sc = new Scanner(fileInputStream);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2016/r2/z1/z1.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
 
 
 
         List<Map<Character, Integer>> lst = new ArrayList<Map<Character, Integer>>();
         for (int i=0;i<bank.length;i++) {
             Map<Character, Integer> mm = new HashMap<Character, Integer>();
             for (int j=0;j<bank[i].length();j++) {
                 char key = bank[i].charAt(j);
                 if (!mm.containsKey(key)) {
                     mm.put(key, 0);
                 }
                 mm.put(key, mm.get(key)+1);
             }
             lst.add(mm);
         }
         System.out.println(lst);
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             String s = sc.next();
             String result = "";
             Map<Character, Integer> mm = new HashMap<Character, Integer>();
             List<Integer> list = new ArrayList<Integer>();
             for (int j = 0; j < s.length(); j++) {
                     char key = s.charAt(j);
                     if (!mm.containsKey(key)) {
                         mm.put(key, 0);
                     }
                     mm.put(key, mm.get(key) + 1);
             }
             if (mm.containsKey('Z')) {
                 Integer z = mm.get('Z');
                 for (int i=0;i<z;i++) {
                     list.add(0);
                 }
                 remove(mm, 0, z, lst);
             }
             if (mm.containsKey('W')) {
                 Integer z = mm.get('W');
                 for (int i=0;i<z;i++) {
                     list.add(2);
                 }
                 remove(mm, 2, z, lst);
             }
             if (mm.containsKey('G')) {
                 Integer z = mm.get('G');
                 for (int i=0;i<z;i++) {
                     list.add(8);
                 }
                 remove(mm, 8, z, lst);
             }
             if (mm.containsKey('X')) {
                 Integer z = mm.get('X');
                 for (int i=0;i<z;i++) {
                     list.add(6);
                 }
                 remove(mm, 6, z, lst);
             }
             if (mm.containsKey('U')) {
                 Integer z = mm.get('U');
                 for (int i=0;i<z;i++) {
                     list.add(4);
                 }
                 remove(mm, 4, z, lst);
             }
             if (mm.containsKey('R')) {
                 Integer z = mm.get('R');
                 for (int i=0;i<z;i++) {
                     list.add(3);
                 }
                 remove(mm, 3, z, lst);
             }
             if (mm.containsKey('S')) {
                 Integer z = mm.get('S');
                 for (int i=0;i<z;i++) {
                     list.add(7);
                 }
                 remove(mm, 7, z, lst);
             }
             if (mm.containsKey('O')) {
                 Integer z = mm.get('O');
                 for (int i=0;i<z;i++) {
                     list.add(1);
                 }
                 remove(mm, 1, z, lst);
             }
             if (mm.containsKey('V')) {
                 Integer z = mm.get('V');
                 for (int i=0;i<z;i++) {
                     list.add(5);
                 }
                 remove(mm, 5, z, lst);
             }
             if (mm.containsKey('I')) {
                 Integer z = mm.get('I');
                 for (int i=0;i<z;i++) {
                     list.add(9);
                 }
                 remove(mm, 9, z, lst);
             }
             Collections.sort(list);
             StringBuilder sb = new StringBuilder();
             for (Integer integer : list) {
                 sb.append(integer);
             }
             String res = "Case #" + ti + ": " + sb.toString();
             printWriter.write(res + "\n");
             System.out.println(res);
         }
         printWriter.close();
     }
 
     private static void remove(Map<Character, Integer> mm, int i, Integer z, List<Map<Character, Integer>> lst) {
         Map<Character, Integer> map = lst.get(i);
         for (Map.Entry<Character, Integer> entry : map.entrySet()) {
             Integer integer = mm.get(entry.getKey());
             if (integer==z) {
                 mm.remove(entry.getKey());
             } else {
                 mm.put(entry.getKey(), integer - z);
             }
         }
     }
 }
