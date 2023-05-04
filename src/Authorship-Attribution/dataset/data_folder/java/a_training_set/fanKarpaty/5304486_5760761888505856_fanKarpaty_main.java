import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 
 public class Main {
 
     public static boolean hasLetter(char[] a) {
         for (int i = 0; i < a.length;i++)
             if(a[i] != '?')return true;
         return false;
     }
     public static void main(String[] args) throws IOException {
 
         Scanner in = new Scanner(new FileInputStream("input.txt"));
         System.setOut(new PrintStream("output.txt"));
         int T = in.nextInt();
         for (int t = 1; t <= T; t++) {
             int rows = in.nextInt();
             int cols = in.nextInt();
 
             char arr[][] = new char[rows][];
             boolean fl[] = new boolean[rows];
             for (int r = 0; r < rows; r++) {
                 arr[r] = in.next().toCharArray();
                 fl[r] = hasLetter(arr[r]);
             }
 
             Set<Character> checked = new HashSet<>();
             for (int r = 0; r < rows; r++) {
                 for (int c = 0; c <  cols; c++) {
                     char sym = arr[r][c];
                     if (sym != '?' && !checked.contains(sym)) {
                         int rb = r - 1;
                         int re = r + 1;
                         while (rb >= 0 && arr[rb][c] == '?')rb--;
                         while(re < rows && arr[re][c] == '?' && !fl[re])re++;
                         rb++;
 
 
                         int cb = c - 1;
                         int ce = c + 1;
                         while (true) {
                             if (cb < 0)break;
                             boolean ok = true;
                             for (int rr = rb; rr < re; rr++) {
                                 if (arr[rr][cb] != '?')ok = false;
                             }
                             if (!ok)break;
                             cb--;
                         }
 
                         cb++;
                         while (true) {
                             if (ce >= cols)break;
                             boolean ok = true;
                             for (int rr = rb; rr < re; rr++) {
                                 if (arr[rr][ce] != '?')ok = false;
                             }
                             if (!ok)break;
                             ce++;
                         }
 
                         for (int rr = rb; rr < re; rr++) {
                             for (int cc = cb; cc < ce; cc++) {
                                 arr[rr][cc] = sym;
                             }
                         }
 
                         checked.add(sym);
                     }
                 }
             }
 
 
 
             System.out.println("Case #" + t + ":");
             for (int r = 0; r < rows; r++) {
                 System.out.println(String.valueOf(arr[r]));
             }
         }
     }
 }
