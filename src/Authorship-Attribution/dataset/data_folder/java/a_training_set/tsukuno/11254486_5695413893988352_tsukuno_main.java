import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   long diff = 0;
   long ans = 0;
   long ans2 = 0;
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
 
       String c = cin.next();
       String j = cin.next();
 
       diff = Long.MAX_VALUE;
 
       int N = c.length();
       solve(c, j, 0, N);
 
       System.out.println("Case #" + C + ": " + pad(ans, N) + " " + pad(ans2, N));
 
     }
 
   }
 
   String pad(long l, int N) {
     String s = "" + l;
     while( s.length() < N ) { s = "0" + s; }
     return s;
   }
 
   String rep(String s, int pos, int val) {
     return s.substring(0, pos) + val + s.substring(pos + 1);
   }
 
   String min(String s, int pos) {
     String ret = s.substring(0, pos);
     for(int i=pos; i<s.length(); ++i) {
       if( s.charAt(i) == '?' ) { ret += 0; }
       else { ret += s.charAt(i); }
     }
     return ret;
   }
 
   String max(String s, int pos) {
     String ret = s.substring(0, pos);
     for(int i=pos; i<s.length(); ++i) {
       if( s.charAt(i) == '?' ) { ret += 9; }
       else { ret += s.charAt(i); }
     }
     return ret;
   }
 
   void ends(String s, String t) {
     long a = Long.parseLong(s);
     long b = Long.parseLong(t);
     long d = Math.abs(a - b);
     if( diff > d ) {
       diff = d;
       ans = a;
       ans2 = b;
     }
     else if( diff == d ) {
       if( a < ans ) {
         diff = d;
         ans = a;
         ans2 = b;
       }
       else if( a == ans ) {
         if( b < ans2 ) {
           diff = d;
           ans = a;
           ans2 = b;
         }
       }
     }
   }
 
   void solve(String c, String j, int pos, int N) {
 
     if( pos == N ) {
       ends(c, j);
       return;
     }
 
     if( c.charAt(pos) == '?' ) {
       if( j.charAt(pos) == '?' ) {
         
         ends(min(rep(c, pos, 1), pos + 1), max(rep(j, pos, 0), pos + 1));
         ends(max(rep(c, pos, 0), pos + 1), min(rep(j, pos, 1), pos + 1));
         
         
         solve(rep(c, pos, 0), rep(j, pos, 0), pos + 1, N);
       }
       else {
         
         int tmp = j.charAt(pos) - '0';
         if( tmp != 0 ) {
           
           ends(max(rep(c, pos, tmp - 1), pos + 1), min(j, pos + 1));
         }
         if( tmp != 9 ) {
           
           ends(min(rep(c, pos, tmp + 1), pos + 1), max(j, pos + 1));
         }
         
         solve(rep(c, pos, tmp), j, pos + 1, N);
       }
     }
     else {
       
       int tmp = c.charAt(pos) - '0';
       if( j.charAt(pos) == '?' ) {
         if( tmp != 0 ) {
           
           ends(min(c, pos + 1), max(rep(j, pos, tmp - 1), pos + 1));
         }
         if( tmp != 9 ) {
           
           ends(max(c, pos + 1), min(rep(j, pos, tmp + 1), pos + 1));
         }
         
         solve(c, rep(j, pos, tmp), pos + 1, N);
       }
       else {
         
         int tmp2 = j.charAt(pos) - '0';
         if( tmp > tmp2 ) {
           
           ends(min(c, pos + 1), max(j, pos + 1));
         }
         else if( tmp < tmp2 ) {
           
           ends(max(c, pos + 1), min(j, pos + 1));
         }
         else {
           
           solve(c, j, pos + 1, N);
         }
       }
     }
 
   }
 
 }
