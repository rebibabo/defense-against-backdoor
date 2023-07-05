import java.io.File;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 
 public class B {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("B.in"));
        PrintWriter out = new PrintWriter(new File("B.out"));
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            long ans = 0;
            int N = sc.nextInt();
            int K = sc.nextInt();
            int NK1 = N-K+1;
        
            long[] S = new long[NK1];
            for(int a=0;a<NK1;a++){
                S[a]=sc.nextLong();
            }
            
            long smallest = 0;
            long largest = 0;
            long[] nums = new long[N];
            
            int pos = 0;
            for(int a=0;a<N;a++){
                int b = a+K;
                if(pos+1==NK1)break;
                long diff = S[pos+1]-S[pos];
                nums[b] = nums[a]+diff;
                
                
                pos++;
            }
            ans = 0;
    
                
    
        
        
            for(int a=0;a<K;a++){
                int cur = a;
                long sm = 0;
                long ll = 0;
                while(cur<N){
                    sm = Math.min(sm,nums[cur]);
                    ll = Math.max(ll,nums[cur]);
                    cur+=K;                 
                }
                ans = Math.max(ans,ll-sm);
            }
            
            long hack = S[0]%N;
            ans+=hack;
            System.out.printf("Case #%d: %d%n",t,ans);
            out.printf("Case #%d: %d%n",t,ans);
        }
        
        out.close();
    }
 }
