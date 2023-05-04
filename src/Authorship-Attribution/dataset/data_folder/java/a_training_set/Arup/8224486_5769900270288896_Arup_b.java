import java.util.*;
 
 public class b {
 
     public static int R;
     public static int C;
     public static int N;
 
     public static void main(String[] args) {
 
         Scanner stdin = new Scanner(System.in);
         int numCases = stdin.nextInt();
 
         
         for (int loop=1; loop<=numCases; loop++) {
 
             R = stdin.nextInt();
             C = stdin.nextInt();
             N = stdin.nextInt();
 
             int res = 10000;
             for (int i=0; i<(1<<(R*C)); i++) {
 
                 if (Integer.bitCount(i) != N) continue;
 
                 res = Math.min(res, score(i));
 
             }
             System.out.println("Case #"+loop+": "+res);
 
         }
     }
 
     public static int score(int mask) {
 
         boolean[][] rooms = new boolean[R][C];
         for (int i=0; i<R*C; i++)
             if ((mask &(1<<i)) > 0)
                 rooms[i/C][i%C] = true;
 
         int score = 0;
         for (int i=0; i<R; i++)
             for (int j=0; j<C-1; j++)
                 if (rooms[i][j] && rooms[i][j+1])
                     score++;
 
         for (int j=0; j<C; j++)
             for (int i=0; i<R-1; i++)
                 if (rooms[i][j] && rooms[i+1][j])
                     score++;
 
         return score;
     }
 }
