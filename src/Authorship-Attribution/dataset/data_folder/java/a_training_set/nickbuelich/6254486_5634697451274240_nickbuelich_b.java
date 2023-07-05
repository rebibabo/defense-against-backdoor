import java.io.File;
 import java.io.PrintWriter;
 import java.util.Collections;
 import java.util.LinkedList;
 import java.util.Scanner;
 
 
 public class B {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("B.in"));
        PrintWriter out = new PrintWriter(new File("B.out"));
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            long ans = 999999;
            String in = sc.next();
            int N = in.length();
            int[] array = new int[N];
            for(int a=0;a<N;a++){
                if(in.charAt(a)=='-')array[a]=1;
            }
            for(int mask=0;mask<1<<N;mask++){
                ans = Math.min(ans, solve(mask,array.clone()));
            }
            
            System.out.printf("Case #%d: %d%n",t,ans);
            out.printf("Case #%d: %d%n",t,ans);
        }
        
        out.close();
    }
 
    private static long solve(int mask, int[] clone) {
        int N = clone.length;
        int flips = 0;
        for(int a=N-1;a>=0;a--){
            if(((mask>>a)&1)==1){
                flips++;
            }
            if(((clone[a]+flips)%2)==1)return 99999;
        }
        
        
        return Integer.bitCount(mask);
    }
 }
