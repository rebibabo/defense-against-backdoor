import java.util.*;
 
 public class bsmall {
 
    public static double[][] memo;
    public static int typeLen;
    public static int msgLen;
    public static int keyLen;
    public static String keys;
    public static String msg;
    public static int resetK;
    public static int sum;
    public static int max;
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
 
        for (int loop=1; loop<=numCases; loop++) {
 
            keyLen = stdin.nextInt();
            msgLen = stdin.nextInt();
            typeLen = stdin.nextInt();
            keys = stdin.next();
            msg = stdin.next();
            sum = 0;
            max = 0;
 
            char[] str = new char[typeLen];
            recurse(str, 0);
            double res = sum/Math.pow(keyLen, typeLen);
 
            System.out.printf("Case #%d: %.7f\n", loop, max-res);
 
        }
    }
 
    public static void recurse(char[] str, int k) {
 
        if (k == typeLen) {
            int cur = getMatches(str);
            sum += cur;
            max = Math.max(max, cur);
            return;
        }
 
        for (int i=0; i<keyLen; i++) {
            str[k] = keys.charAt(i);
            recurse(str, k+1);
        }
    }
 
    public static int getMatches(char[] str) {
        String tmp = new String(str);
        int cnt = 0;
        for (int i=0; i<=typeLen-msgLen; i++)
            if (tmp.substring(i, i+msgLen).equals(msg))
                cnt++;
        return cnt;
    }
 
 }