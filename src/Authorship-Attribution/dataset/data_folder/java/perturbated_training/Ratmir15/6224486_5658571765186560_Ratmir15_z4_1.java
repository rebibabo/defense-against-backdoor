package c2015_c.c2015_r1;
 
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.*;
 import java.util.function.BooleanSupplier;
 
 public class Z4_1 {
 
     private static int[][] figures_3 = {{1, 1, 1, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 1, 0, 0, 1, 0, 0}, {1, 1, 0, 1, 0, 0, 0, 0, 0}};
 
     public static void main(String[] args) throws Exception {
         Map<String, Boolean> on_paper = new HashMap<String, Boolean>();
         Map<String, Integer> cnt = new TreeMap<String, Integer>();
         on_paper.put("111", false);
         on_paper.put("112", false);
         on_paper.put("113", false);
         on_paper.put("114", false);
         on_paper.put("122", false);
         on_paper.put("123", false);
         on_paper.put("124", false);
         on_paper.put("133", false);
         on_paper.put("134", false);
         on_paper.put("144", false);
         on_paper.put("211", true);
         on_paper.put("212", false);
         on_paper.put("213", true);
         on_paper.put("214", false);
         on_paper.put("222", false);
         on_paper.put("223", false);
         on_paper.put("224", false);
         on_paper.put("233", true);
         on_paper.put("234", false);
         on_paper.put("244", false);
         on_paper.put("311", true);
         on_paper.put("312", true);
         on_paper.put("313", true);
         on_paper.put("314", true);
         on_paper.put("322", true);
         on_paper.put("323", false);
         on_paper.put("324", true);
         on_paper.put("333", false);
         on_paper.put("334", false);
         on_paper.put("344", true);
         on_paper.put("411", true);
         on_paper.put("412", true);
         on_paper.put("413", true);
         on_paper.put("414", true);
         on_paper.put("422", true);
         on_paper.put("423", true);
         on_paper.put("424", true);
         on_paper.put("433", true);
         on_paper.put("434", false);
         on_paper.put("444", false);
 
         FileWriter fw = new FileWriter("C:\\output.txt");
         BufferedWriter out = new BufferedWriter(fw);
         
         String pathname = "C:\\Users\\YC14rp1\\Downloads\\D-small-attempt2.in";
         
         Scanner scanner = new Scanner(new File(pathname));
         int tn = scanner.nextInt();
         scanner.nextLine();
         for (int ti = 1; ti <= tn; ti++) {
             int x = scanner.nextInt();
             int r = scanner.nextInt();
             int c = scanner.nextInt();
             if (r < c) {
                 int rc = r;
                 r = c;
                 c = rc;
             }
             
             boolean flag = false;
             if (r*c%x!=0) {
                 flag = true;
             } else {
                 if (x <= r) {
                     switch (x) {
                         case 1: {
                             flag = false;
                             break;
                         }
                         case 2: {
                             flag = r * c % 2 != 0;
                             break;
                         }
                         case 3: {
                             if (c == 1) {
                                 flag = true;
                             } else {
                                 if ((c % 2 == 0) && (r == 4)) {
                                     flag = true;
                                 } else {
                                     
                                     flag = false;
                                 }
                             }
                             break;
                         }
                         case 4: {
                             switch (c) {
                                 case 1: {
                                     flag = true;
                                     break;
                                 }
                                 case 2: {
                                     flag = true;
                                     break;
                                 }
                                 case 3: {
                                     flag = false;
                                     break;
                                 }
                                 case 4: {
                                     flag = false;
                                     break;
                                 }
                             }
                         }
                     }
                 } else {
                     flag = true;
                 }
             }
             if (flag != on_paper.get("" + x + c + r)) {
                 throw new RuntimeException(flag + " " + x + c + r + on_paper.get("" + x + c + r));
             }
             String s;
             if (flag) {
                 s = "RICHARD";
             } else {
                 s = "GABRIEL";
             }
             s = "Case #" + ti + ": " + s;
             System.out.println(s);
             out.write(s);
             out.write("\n");
             Integer integer = cnt.get("" + x + c + r);
             if (integer==null) {
                 cnt.put("" + x + c + r,1);
             } else {
                 cnt.put("" + x + c + r, integer+1);
             }
         }
         for (Map.Entry<String, Integer> stringIntegerEntry : cnt.entrySet()) {
             System.out.println(stringIntegerEntry.getKey()+" "+stringIntegerEntry.getValue());
         }
         out.close();
     }
 
 }
