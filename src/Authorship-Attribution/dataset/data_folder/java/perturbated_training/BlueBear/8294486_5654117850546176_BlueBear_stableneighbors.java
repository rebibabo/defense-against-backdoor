import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Scanner;
 import java.util.stream.Stream;
 
 public class StableNeighbors {
 
     static class Unicorn {
         int cnt;
         char color;
 
         public Unicorn(int cnt, char color) {
             this.cnt = cnt;
             this.color = color;
         }
     }
 
     static Unicorn[] unicorn;
     static int N;
 
     static boolean isPure(char color) {
         return color == 'R' || color == 'Y' || color == 'B';
     }
 
     static String doit(Unicorn[] unicorn) {
         Arrays.sort(unicorn, (u, v) -> Integer.compare(u.cnt, v.cnt));
         if (unicorn[0].cnt + unicorn[1].cnt < unicorn[2].cnt) return "IMPOSSIBLE";
 
         String[] circle = new String[unicorn[2].cnt];
         Arrays.fill(circle, "");
         for (int i = 0; i < unicorn[0].cnt; i++)
             circle[i] += unicorn[0].color;
         for (int i = 0; i < unicorn[1].cnt; i++)
             circle[circle.length - 1 - i] += unicorn[1].color;
 
         StringBuilder builder = new StringBuilder();
         for (String s : circle)
             builder.append(unicorn[2].color + s);
         return builder.toString();
     }
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("B-small-attempt0.in"));
         PrintStream cout = new PrintStream("B-small-attempt0.out");
 
 
 
 
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
 
 
             unicorn = new Unicorn[6];
             N = cin.nextInt();
             for (int i = 0; i < 6; i++) {
                 unicorn[i] = new Unicorn(cin.nextInt(), "ROYGBV".charAt(i));
             }
 
             Unicorn[] lala = Stream.of(unicorn).filter(u -> isPure(u.color)).toArray(Unicorn[]::new);
             cout.printf("Case #%d: %s%n", _case, doit(lala));
         }
 
         cin.close();
         cout.close();
     }
 }
