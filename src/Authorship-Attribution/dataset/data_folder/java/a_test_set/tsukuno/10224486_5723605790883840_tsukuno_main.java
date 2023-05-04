import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
 
       int r = cin.nextInt();
       int c = cin.nextInt();
       int pair[][] = new int[r + c][2];
       for(int i=0; i<r+c; ++i) {
         pair[i][0] = cin.nextInt() - 1;
         pair[i][1] = cin.nextInt() - 1;
       }
 
       int max = 1 << (r * c);
       String ans[] = null;
 
       for(int i=0; i<max; ++i) {
         if( solve(r, c, i, pair) ) {
           ans = format(i, r, c);
           break;
         }
       }
 
       System.out.println("Case #" + C + ":");
 
       if( ans == null ) { System.out.println("IMPOSSIBLE"); }
       else {
         for(int i=0; i<r; ++i) {
           System.out.println(ans[i]);
         }
       }
 
     }
 
   }
 
   final int UP = 0;
   final int DN = 1;
   final int LE = 2;
   final int RI = 3;
 
   boolean solve(int r, int c, int state, int pair[][]) {
 
     int all = (r + c) * 2;
     int res[] = new int[all];
     for(int i=0; i<all; ++i) { res[i] = -1; }
 
     for(int i=0; i<all; ++i) {
       
       if( res[i] >= 0 ) { continue; }
       
       if( 0 <= i && i < c ) {
         res[i] = enter(0, i, UP, r, c, state);
       }
       
       if( c <= i && i < c + r ) {
         res[i] = enter(i - c, c - 1, RI, r, c, state);
       }
       
       if( c + r <= i && i < c + r + c ) {
         res[i] = enter(r - 1, c + r + c - 1 - i, DN, r, c, state);
       }
       
       if( c + r + c <= i ) {
         res[i] = enter(c + r + c + r - 1 - i, 0, LE, r, c, state);
       }
       res[ res[i] ] = i;
     }
 
     for( int p[] : pair ) {
       if( res[ p[0] ] != p[1] ) { return false; }
     }
 
     return true;
 
   }
 
   
   
   
   
   
   
   
   
 
   int enter(int Y, int X, int dir, int r, int c, int state) {
     
     if( Y == -1 ) {
       return X;
     }
     if( X == c ) {
       return Y + c;
     }
     if( Y == r ) {
       return c + r + c - 1 - X;
     }
     if( X == -1 ) {
       return c + r + c + r - 1 - Y;
     }
     
     if( (state & (1 << (Y * c + X))) == 0 ) {
       switch(dir) {
       case UP:
         return enter(Y, X - 1, RI, r, c, state);
       case DN:
         return enter(Y, X + 1, LE, r, c, state);
       case LE:
         return enter(Y - 1, X, DN, r, c, state);
       case RI:
         return enter(Y + 1, X, UP, r, c, state);
       }
     }
     
     else {
       switch(dir) {
       case UP:
         return enter(Y, X + 1, LE, r, c, state);
       case DN:
         return enter(Y, X - 1, RI, r, c, state);
       case LE:
         return enter(Y + 1, X, UP, r, c, state);
       case RI:
         return enter(Y - 1, X, DN, r, c, state);
       }
     }
     throw new RuntimeException("implementation error");
   }
 
   String[] format(int state, int r, int c) {
     String ret[] = new String[r];
     String tmp = "";
     for(int i=0; i<r*c; ++i) {
       tmp += (state & (1 << i)) == 0 ? "/" : "\\";
       if( i % c == c - 1 ) {
         ret[i / c] = tmp;
         tmp = "";
       }
     }
     return ret;
   }
 
 }
