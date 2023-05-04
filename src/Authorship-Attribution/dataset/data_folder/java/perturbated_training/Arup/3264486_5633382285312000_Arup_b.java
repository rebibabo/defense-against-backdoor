import java.util.*;
 
 public class b {
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
 
        for (int loop=1; loop<=numCases; loop++) {
            char[] num = stdin.next().toCharArray();
            System.out.println("Case #"+loop+": "+solve(num));
        }
 
    }
 
    public static long solve(char[] num) {
 
        int down = -1;
        for (int i=0; i<num.length-1; i++) {
            if (num[i+1] < num[i]) {
                down = i;
                break;
            }
        }
 
        if (down == -1) return Long.parseLong(new String(num));
 
        while (down > 0 && num[down-1] == num[down]) down--;
        num[down]--;
        for (int i=down+1; i<num.length; i++) num[i] = '9';
        String s = new String(num);
        while (s.charAt(0) == '0') s = s.substring(1);
        return Long.parseLong(s);
    }
 }