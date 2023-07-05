import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
        
        String s = cin.next();
        int length = s.length();
        char buffer[] = new char[length];
        for( int i =0; i<length ; ++ i ) {
            buffer[i] = s.charAt(i);
        }
        
        for( int i = length - 1; i > 0; --i ) {
            if( buffer[i] < buffer[i - 1] ) {
                
                --buffer[i - 1];
                
                for( int j = i; j < length; ++j) {
                    buffer[j] = '9';
                }
            }
        }
        
        String result = "";
        boolean skip = true;
        for(int i =0; i <length; ++i) {
            if( buffer[i] == '0' && skip ) { continue; }
            skip = false;
            result += buffer[i];
        }
 
       System.out.println("Case #" + C + ": " + result);
 
     }
 
   }
 
 }
