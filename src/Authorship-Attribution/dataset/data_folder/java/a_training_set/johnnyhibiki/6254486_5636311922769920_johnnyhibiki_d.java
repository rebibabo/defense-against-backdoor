import java.util.Scanner;
 
 public class D {
    
    public static void main(String[] args){
        new D().run();
    }
    
    void run(){
        Scanner sc = new Scanner(System.in);
        
        int testNum = sc.nextInt();
        for(int t=1;t<=testNum;t++){
            int k = sc.nextInt();
            int c = sc.nextInt();
            int s = sc.nextInt();
            String ans = fnc(k, c, s);
            System.out.println("Case #" + t + ": " + ans);
        }
    }
    
    String fnc(int k, int c, int s){
        String ans = "";
        if(c==1){
            if(s<k){
                ans = "IMPOSSIBLE";
            }else{
                for(int i=1;i<=k;i++){
                    ans += i + " ";
                }
            }
        }else{
            int nums = k/2 + k%2;
            if(s<nums){
                ans = "IMPOSSIBLE";
            }else{
                for(int i=0;i<k/2;i++){
                    ans += (i*2*k+(i+1)*2) + " ";
                }
                if(k%2==1){
                    ans += k*k;
                }
            }
        }
        
        return ans.trim();
    }
    
 }
