package qualy;
 
 import java.util.Scanner;
 
 public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            sc.nextInt();
            String vals = sc.next();
            int ans = 0, acum = 0;
            for (int i = 0; i < vals.length(); i++) {
                int tmp = vals.charAt(i) - '0';
                acum += tmp;
                if (acum < i + 1) {
                    acum++;
                    ans++;
                }
            }
            
            System.out.println("Case #" + caze + ": " + ans);
        }
    }
 
 }
