import java.util.*;
 
 public class R1A_A {
    int n;
    int[] m;
    
    
    void run(){
        Scanner sc = new Scanner(System.in);
    
        int t = sc.nextInt();
        for(int test=1;test<=t;test++){
            n = sc.nextInt();
            
            m = new int[n];
            for(int i=0;i<n;i++) m[i] = sc.nextInt();
            
            int ans1 = 0;
            int difmax = 0;
            for(int i=0;i<n-1;i++){
                int dif = m[i]-m[i+1];
                if(dif>0){
                    ans1 += dif;
                    difmax = Math.max(dif, difmax);
                }
            }
            
            int ans2 = 0;
            for(int i=0;i<n-1;i++){
                if(m[i]-difmax>0) ans2 += difmax;
                else ans2 += m[i];
            }
            
            System.out.println("case #" + test + ": " + ans1 + " " + ans2);
        }
        sc.close();
    }
    
    
    public static void main(String[] args) {
        new R1A_A().run();
    }
 
 }
