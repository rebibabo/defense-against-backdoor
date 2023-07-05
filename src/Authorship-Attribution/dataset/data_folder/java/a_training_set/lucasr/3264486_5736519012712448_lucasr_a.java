package qualy;
 
 import java.util.Scanner;
 
 public class A {
    static boolean[] toBoolean(char[] arr) {
        boolean[] ret = new boolean[arr.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arr[i] == '+';
        }
        return ret;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            boolean[] S = toBoolean(sc.next().toCharArray());
            int k = sc.nextInt();
            int tot = 0;
            for (int i = 0; i < S.length; i++) {
                if (!S[i]) {
                    if (i + k <= S.length) {
                        tot++;
                        for (int j = i; j < i + k; j++) S[j] = !S[j];
                    } else tot = -1; 
                }
            }
            
            System.out.println("Case #" + caze + ": " + (tot == -1 ? "IMPOSSIBLE" : tot));
        }
    }
 
 }
