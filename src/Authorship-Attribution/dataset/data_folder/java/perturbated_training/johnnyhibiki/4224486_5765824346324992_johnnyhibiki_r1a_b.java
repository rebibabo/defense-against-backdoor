import java.util.*;
 
 public class R1A_B {
    int b, n;
    int[] m, mm, p;
 
    
    long LCM(long s, long t){
        if(t>s){
            long l = s;
            s=t;
            t=l;
        }
        long a = s;
        long b = t;         
        while(t!=0){
            long l = t;
            t = s%t;
            s = l;
        }
        return (a/s)*b;
    }
    
    
    void run(){
        Scanner sc = new Scanner(System.in);
    
        int t = sc.nextInt();
        for(int test=1;test<=t;test++){
            b = sc.nextInt();
            n = sc.nextInt();
            
            int ans = -1;
            int tmpmin = Integer.MAX_VALUE;
            int min = Integer.MAX_VALUE;
            int cnt = 1;
            m = new int[b];
            mm = new int[b];
            for(int i=0;i<b;i++){
                m[i] = sc.nextInt();
            }
            
            long lcm = 1;
            for(int i=0;i<b;i++){
                lcm = LCM(lcm, m[i]);
            }
            long difsum = 0;
            for(int i=0;i<b;i++){
                difsum += lcm/m[i];
            }
            
            n = (int)((n-1)%difsum+1);
            
            for(int i=0;i<b;i++){
                mm[i] = m[i];
                min = Math.min(min, m[i]);
                if(cnt==n) ans = i+1;
                cnt++;
            }
            
            while(ans==-1){
                tmpmin = Integer.MAX_VALUE;
                for(int i=0;i<b;i++){
                    mm[i] -= min;
                    if(mm[i]==0){
                        if(cnt==n){
                            ans = i+1;
                            break;
                        }
                        cnt++;
                        mm[i] = m[i];
                    }
                    tmpmin = Math.min(tmpmin, mm[i]);
                }
                min = tmpmin;
            }
            
            System.out.println("case #" + test + ": " + ans);
        }
        sc.close();
    }
    
    
    public static void main(String[] args) {
        new R1A_B().run();
    }
 
 }
