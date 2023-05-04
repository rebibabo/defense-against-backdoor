package qual;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 
 public class A {
     public static void main(String[] args) throws FileNotFoundException {
         Kattio io;
 
 
 
         io = new Kattio(new FileInputStream("qual/A-small-attempt0.in"), new FileOutputStream("qual/A-small-0.out"));
 
 
         int cases = io.getInt();
         for (int i = 1; i <= cases; i++) {
             io.print("Case #" + i + ": ");
             new A().solve(io);
         }
         io.close();
     }
 
     private void solve(Kattio io) {
         int n = io.getInt();
         String s = io.getWord();
         int standing = 0, added = 0;
         for (int i = 0; i < s.length(); i++) {
             if (standing < i) {
                 added += (i-standing);
                 standing = i;
             }
             standing += s.charAt(i) - '0';
         }
         io.println(added);
     }
 }
