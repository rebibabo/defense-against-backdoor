package r22017.z3;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class Solution {
 
     public enum Type {
         ANY, H, V;
     }
 
     public static void main(String[] args) throws FileNotFoundException {
         
         
         Scanner sc = new Scanner(System.in);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Downloads/codejam/src/r22017/z3/z4output.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             int r = sc.nextInt();
             int c = sc.nextInt();
             sc.nextLine();
             char[][] field = new char[r][c];
             for (int i = 0; i < r; i++) {
                 field[i] = sc.nextLine().toCharArray();
             }
             boolean impossible = false;
             Set<Cell> cells = new HashSet<Cell>();
             Map<Cell, Type> map = new HashMap<Cell, Type>();
             main:
             for (int i = 0; i < r; i++) {
                 for (int j = 0; j < c; j++) {
                     if (field[i][j] == '.') {
                         cells.add(new Cell(i, j));
                     }
                     if (field[i][j] == '-' || field[i][j] == '|') {
                         boolean noV = false;
                         int cr = i - 1;
                         while (cr >= 0) {
                             if (field[cr][j] == '#') {
                                 break;
                             }
                             if (field[cr][j] == '-' || field[cr][j] == '|') {
                                 noV = true;
                                 break;
                             }
                             cr--;
                         }
                         cr = i + 1;
                         while (cr < r) {
                             if (field[cr][j] == '#') {
                                 break;
                             }
                             if (field[cr][j] == '-' || field[cr][j] == '|') {
                                 noV = true;
                                 break;
                             }
                             cr++;
                         }
                         boolean noH = false;
                         cr = j - 1;
                         while (cr >= 0) {
                             if (field[i][cr] == '#') {
                                 break;
                             }
                             if (field[i][cr] == '-' || field[i][cr] == '|') {
                                 noH = true;
                                 break;
                             }
                             cr--;
                         }
                         cr = j + 1;
                         while (cr < c) {
                             if (field[i][cr] == '#') {
                                 break;
                             }
                             if (field[i][cr] == '-' || field[i][cr] == '|') {
                                 noH = true;
                                 break;
                             }
                             cr++;
                         }
                         
                         if (noH && noV) {
                             impossible = true;
                             break main;
                         } else {
                             if (noH) {
                                 map.put(new Cell(i,j), Type.V);
                                 field[i][j] = '|';
                             }
                             if (noV) {
                                 map.put(new Cell(i,j), Type.H);
                                 field[i][j] = '-';
                             }
                             if (!noH && !noV) {
                                 map.put(new Cell(i,j), Type.ANY);
                             }
                         }
                     }
                 }
             }
             
             boolean tryMore = true;
             while (tryMore && !impossible) {
                 tryMore = false;
                 main2:
                 for (int i = 0; i < r; i++) {
                     for (int j = 0; j < c; j++) {
                         if (field[i][j] == '.') {
                             boolean noV = false;
                             Cell cv = null;
                             Cell ch = null;
                             int cr = i - 1;
                             while (cr >= 0) {
                                 if (field[cr][j] == '#') {
                                     break;
                                 }
                                 if (field[cr][j] == '-' || field[cr][j] == '|') {
                                     cv = new Cell(cr, j);
                                     Type type = map.get(cv);
                                     if (!type.equals(Type.H)) {
                                         noV = true;
 
                                     }
                                     break;
                                 }
                                 cr--;
                             }
                             cr = i + 1;
                             while (cr < r) {
                                 if (field[cr][j] == '#') {
                                     break;
                                 }
                                 if (field[cr][j] == '-' || field[cr][j] == '|') {
                                     cv = new Cell(cr, j);
                                     Type type = map.get(cv);
                                     if (type==null) {
                                         System.out.println(map);
                                     }
                                     if (!type.equals(Type.H)) {
                                         noV = true;
                                     }
                                     break;
                                 }
                                 cr++;
                             }
                             boolean noH = false;
                             cr = j - 1;
                             while (cr >= 0) {
                                 if (field[i][cr] == '#') {
                                     break;
                                 }
                                 if (field[i][cr] == '-' || field[i][cr] == '|') {
                                     ch = new Cell(i, cr);
                                     Type type = map.get(ch);
                                     if (!type.equals(Type.V)) {
                                         noH = true;
                                     }
                                     break;
                                 }
                                 cr--;
                             }
                             cr = j + 1;
                             while (cr < c) {
                                 if (field[i][cr] == '#') {
                                     break;
                                 }
                                 if (field[i][cr] == '-' || field[i][cr] == '|') {
                                     ch = new Cell(i, cr);
                                     Type type = map.get(ch);
                                     if (!type.equals(Type.V)) {
                                         noH = true;
                                     }
                                     break;
                                 }
                                 cr++;
                             }
                             
                             if (!noH && !noV) {
                                 impossible = true;
                                 break main2;
                             } else {
                                 if (noH && !noV && !map.get(ch).equals(Type.H)) {
                                     map.put(ch, Type.H);
                                     field[ch.r][ch.c] = '-';
                                     tryMore = true;
                                 }
                                 if (noV && !noH && !map.get(cv).equals(Type.V))  {
                                     map.put(cv, Type.V);
                                     field[cv.r][cv.c] = '|';
                                     tryMore = true;
                                 }
                             }
                         }
                     }
                 }
                 if (!tryMore) {
                     for (Map.Entry<Cell, Type> cellTypeEntry : map.entrySet()) {
                         if (cellTypeEntry.getValue().equals(Type.ANY)) {
                             map.put(cellTypeEntry.getKey(), Type.V);
                             field[cellTypeEntry.getKey().r][cellTypeEntry.getKey().c] = '|';
                         }
                     }
                     System.out.println("SET");
                 }
             }
             
 
             String result = "IMPOSSIBLE";
             if (!impossible) {
                 
                 List<Cell> amb = new ArrayList<Cell>();
                 for (Map.Entry<Cell, Type> cellTypeEntry : map.entrySet()) {
                     if (cellTypeEntry.getValue().equals(Type.ANY)) {
                         amb.add(cellTypeEntry.getKey());
                     }
                 }
                 System.err.println(cells.size()+" "+amb.size());
                 Random rand = new Random();
                 while (check(r,c,field,cells)) {
                     for (int i=0;i<amb.size();i++) {
                         Cell cell = amb.get(i);
                         field[cell.r][cell.c] = rand.nextBoolean()?'|':'-';
                     }
                     
                 }
                 boolean imp = check(r, c, field, cells);
                 if (imp) {
                     result = "POSSIBLE\n";
                     for (int i = 0; i < r; i++) {
                         for (int j = 0; j < c; j++) {
                             result = result + field[i][j];
                         }
                         result = result + "\n";
                     }
                     System.out.println(result);
                     throw new RuntimeException();
                 }
 
 
 
 
 
 
 
 
 
 
 
 
                 result = "POSSIBLE\n";
                 for (int i = 0; i < r; i++) {
                     for (int j = 0; j < c; j++) {
                         result = result + field[i][j];
                     }
                     result = result + "\n";
                 }
                 result = result.trim();
             }
             String res = "Case #" + ti + ": " + result;
             printWriter.write(res + "\n");
             System.out.println(res);
         }
         printWriter.close();
     }
 
     private static boolean check(int r, int c, char[][] field, Set<Cell> cells) {
         Set<Cell> cntr = new HashSet<Cell>();
         boolean imp = false;
         for (int i = 0; i < r; i++) {
             for (int j = 0; j < c; j++) {
                 if (field[i][j] == '-') {
                     int cr = j - 1;
                     while (cr >= 0) {
                         if (field[i][cr] == '.') {
                             cntr.add(new Cell(i,cr));
                         }
                         if (field[i][cr] == '#') {
                             break;
                         }
                         if (field[i][cr] == '-' || field[i][cr] == '|') {
                             imp = true;
                             break;
                         }
                         cr--;
                     }
                     cr = j + 1;
                     while (cr < c) {
                         if (field[i][cr] == '.') {
                             cntr.add(new Cell(i,cr));
                         }
                         if (field[i][cr] == '#') {
                             break;
                         }
                         if (field[i][cr] == '-' || field[i][cr] == '|') {
                             imp = true;
                             break;
                         }
                         cr++;
                     }
                 }
                 if (field[i][j] == '|') {
                     int cr = i - 1;
                     while (cr >= 0) {
                         if (field[cr][j] == '.') {
                             cntr.add(new Cell(cr,j));
                         }
                         if (field[cr][j] == '#') {
                             break;
                         }
                         if (field[cr][j] == '-' || field[cr][j] == '|') {
                             imp = true;
                             break;
                         }
                         cr--;
                     }
                     cr = i + 1;
                     while (cr < r) {
                         if (field[cr][j] == '.') {
                             cntr.add(new Cell(cr,j));
                         }
                         if (field[cr][j] == '#') {
                             break;
                         }
                         if (field[cr][j] == '-' || field[cr][j] == '|') {
                             imp = true;
                             break;
                         }
                         cr++;
                     }
                 }
             }
         }
         if (cntr.size()!=cells.size()) {
             imp = true;
         }
         return imp;
     }
 
     private static class Cell {
         private final int r;
         private final int c;
 
         public Cell(int r, int c) {
             this.r = r;
             this.c = c;
         }
 
         @Override
         public boolean equals(Object o) {
             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;
 
             Cell cell = (Cell) o;
 
             if (r != cell.r) return false;
             return c == cell.c;
 
         }
 
         @Override
         public int hashCode() {
             int result = r;
             result = 31 * result + c;
             return result;
         }
 
         @Override
         public String toString() {
             return "{" +
                     "r=" + r +
                     ", c=" + c +
                     '}';
         }
     }
 
 }
 
 
 