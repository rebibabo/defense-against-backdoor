import java.util.*;
 
 public class dsmall {
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
 
        for (int loop=1; loop<=numCases; loop++) {
            int k = stdin.nextInt();
            int c = stdin.nextInt();
            int s = stdin.nextInt();
            System.out.print("Case #"+loop+":");
            for (int i=1; i<=s; i++)
                System.out.print(" "+i);
            System.out.println();
        }
    }
 }