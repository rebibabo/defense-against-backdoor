import java.util.*;
 
 public class QA {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int t = sc.nextInt();
        for(int test=1;test<=t;test++){
            int n = sc.nextInt();
            String s = sc.next();
            
            long ans = 0;
            long sum = 0;
            for(int i=0;i<=n;i++){
                long p = s.codePointAt(i)-48;               
                if(p!=0 && sum<i){
                    ans += (i-sum);
                    sum += (i-sum);
                }
                sum += p;
            }
            
            System.out.println("case #" + test + ": " + ans);
        }
        sc.close();
    }
 
 }
