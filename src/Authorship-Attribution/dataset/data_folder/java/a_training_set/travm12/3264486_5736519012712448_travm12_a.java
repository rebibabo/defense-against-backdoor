import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class a {
    public static void main(String[] Args) throws Exception {
        Scanner sc = new Scanner(new File("A-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("a.out"))));
        int cc = 0;
        int t = sc.nextInt();
        while (t-- > 0) {
            
            String s = sc.next();
            int k = sc.nextInt();
            int n = s.length();
 
            
            int[] li = new int[n];
            for (int i = 0; i < n; i++)
                li[i] = (s.charAt(i) == '+') ? 1 : 0;
 
            
            int ans = 0;
            for (int i = 0; i + k - 1 < n; i++)
                if (li[i] == 0) {
                    for (int j = 0; j < k; j++)
                        li[j + i] = 1 - li[j + i];
                    ans++;
                }
 
            
            boolean good = true;
            for (int i = 0; i < n; i++)
                if (li[i] == 0)
                    good = false;
 
            
            if (good)
                out.printf("Case #%d: %d%n", ++cc, ans);
            else
                out.printf("Case #%d: IMPOSSIBLE%n", ++cc);
        }
        out.close();
    }
 }
