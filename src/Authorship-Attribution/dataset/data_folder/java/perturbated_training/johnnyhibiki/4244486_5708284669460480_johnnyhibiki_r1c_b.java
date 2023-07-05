import java.util.*;
 
 public class R1C_B {
    int K, L, S, C, max;
    char[] key;
    String str;
    
    int check(int[] p){
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<S;i++) sb.append(key[p[i]]);
        String s = sb.toString();
 
        for(int i=0;i<s.length();i++){
            if(s.indexOf(str, i)!=-1){
                cnt++;
                i = s.indexOf(str, i);
            }else{
                break;
            }
        }
        max = Math.max(max, cnt);
        return cnt;
    }
    
    void fnc(int cnt, int[] p){
        if(cnt==S){
            C += check(p);
        }else{
            for(int i=0;i<K;i++){
                p[cnt] = i;
                fnc(cnt+1, p);
            }
        }
    }
    
    void run(){
        Scanner sc = new Scanner(System.in);
    
        int t = sc.nextInt();
        for(int test=1;test<=t;test++){
            K = sc.nextInt();
            L = sc.nextInt();
            S = sc.nextInt();
            
            key = sc.next().toCharArray();
            str = sc.next();
            
            max = 0;
            C = 0;
            fnc(0, new int[S]);
 
            double ans = 0;
            if(C!=0) ans = C / Math.pow(K, S);
            ans = max - ans;
            System.out.println("case #" + test + ": " + ans);
        }
        sc.close();
    }
    
    
    public static void main(String[] args) {
        new R1C_B().run();
    }
 
 }
