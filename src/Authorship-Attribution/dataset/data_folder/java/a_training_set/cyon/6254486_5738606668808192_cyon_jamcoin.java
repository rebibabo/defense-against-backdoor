package qualif;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;
 import java.util.StringTokenizer;
 import java.io.PrintWriter;
 import java.util.stream.Collectors;
 
 public class Jamcoin {
 
     public static void main(String[]args) throws IOException {
         
 
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter pw = new PrintWriter(System.out);
         int T = Integer.parseInt(br.readLine());
         for(int i=1;i<=T;i++) {
             pw.println("Case #" + i + ": " + solve(br));
         }
         pw.flush();
     }
 
     private static String solve(BufferedReader br) throws IOException {
 
         StringBuilder bob = new StringBuilder("\n");
 
         int N = 16;
         int J = 50;
         int MAX = 1 << 14;
 
         for (int i=0;i<MAX;i++) {
             boolean ok = true;
             List<Long> divs = new ArrayList<>();
             String num = String.format("1%14s1", Integer.toBinaryString(i)).replace(' ', '0');
             
             for (int rad=2;rad<=10;rad++) {
                 long val = Long.parseLong(num, rad);
                 long div = findDiv(val);
                 if (div == 1) {
                     ok=false;
                     break;
                 }
                 else {
                     divs.add(div);
                 }
             }
             if (ok) {
                 bob.append(num).append(" ").append(divs.stream().map(l -> l.toString()).collect(Collectors.joining(" ")));
                 J--;
                 if (J != 0) bob.append("\n");
             }
             if (J == 0) break;
         }
 
         return bob.toString();
     }
 
     private static long findDiv(long val) {
         long root = (long)Math.ceil(Math.sqrt(val));
         for (long i=2;i<root;i++) {
             if (val%i==0) return i;
         }
         return 1;
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
