import java.util.Scanner;
 
 public class A {
 
    void run() {
        Scanner sc = new Scanner(System.in);
 
        int testNum = sc.nextInt();
        for (int t = 1; t <= testNum; t++) {
            String s = sc.next();
            int k = Integer.parseInt(sc.next());
            System.out.println("Case #" + t + ": " + fnc(s, k));
        }
    }
 
    String fnc(String s, int k) {
        String ans = "";
 
        boolean[] b = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                b[i] = true;
            }
        }
 
        int cnt = 0;
        for (int i = 0; i <= s.length() - k; i++) {
            if (!b[i]) {
                cnt++;
                for (int j = 0; j < k; j++) {
                    b[i+j] ^= true;
                }
            }
        }
 
        ans = "" + cnt;
        for (int i = s.length() - k; i < s.length(); i++) {
            if (!b[i]) {
                ans = "IMPOSSIBLE";
                break;
            }
        }
 
        return ans;
    }
 
    public static void main(String[] args) {
        new A().run();
    }
 }
