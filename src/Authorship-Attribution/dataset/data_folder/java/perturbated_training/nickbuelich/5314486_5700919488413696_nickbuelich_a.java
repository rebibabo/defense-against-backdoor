import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class A {
    static int[] group;
    static Integer[][][][] memo;
    static int N,P;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("A.in"));
        PrintWriter out = new PrintWriter(new File("A.out"));
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            P = sc.nextInt();
            group = new int[N];
            memo = new Integer[101][101][101][4];
            int[] stuff = new int[4];
            for(int a=0;a<N;a++){
                group[a]=sc.nextInt();
                stuff[group[a]%P]++;
            }
            
    
            int ans = DP(stuff[1], stuff[2], stuff[3], 0) + stuff[0];
            System.out.printf("Case #%d: %d%n", t, ans);
            out.printf("Case #%d: %d%n", t, ans);
        }
        out.close();
    }
    private static int DP(int one, int two, int three, int remain) {
        if(one+two+three==0)return 0;
        if(memo[one][two][three][remain] != null){
            return memo[one][two][three][remain];
        }
        int ans = 0;
        if(remain==0){
            if(one!=0) ans = Math.max(ans, 1 + DP(one-1 , two, three, P-1));
            if(two!=0) ans = Math.max(ans, 1 + DP(one, two-1, three, P-2));
            if(three!=0) ans = Math.max(ans, 1 + DP(one, two, three-1, P-3));
        }
        
        if(one!=0) ans = Math.max(ans, DP(one-1 , two, three, (remain + P - 1)%P));
        if(two!=0) ans = Math.max(ans, DP(one, two-1, three, (remain + P - 2)%P));
        if(three!=0) ans = Math.max(ans, DP(one, two, three-1, (remain + P - 3)%P));
        
        return memo[one][two][three][remain]=ans;
    }
 }
