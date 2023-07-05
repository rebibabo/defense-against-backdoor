
 import java.util.Scanner;
 
 public class C {
 
    void run() {
        Scanner sc = new Scanner(System.in);
 
        int testNum = sc.nextInt();
        for (int t = 1; t <= testNum; t++) {
            long n = Long.parseLong(sc.next());
            long k = Long.parseLong(sc.next());
            System.out.println("Case #" + t + ": " + fnc(n, k));
        }
    }
 
    String fnc(long n, long k) {
 
        long[] num = new long[4];
        long[] cnt = new long[4];
        long[] val = new long[2];
        num[0] = n;
        cnt[0] = 1;
 
        long m = 0;
        while(true){
            long max = -1;
            int id = -1;
            for(int i=0;i<4;i++){
                if(max < num[i]){
                    max = num[i];
                    id = i;
                }
            }
 
            max--;
            val[0] = max/2 + max%2;
            val[1] = max/2;
 
            for(int j=0;j<2;j++){
                boolean f = false;
                for(int i=0;i<4;i++){
                    if(num[i]==val[j]){
                        cnt[i] += cnt[id];
                        f = true;
                    }
                }
                if(!f){
                    for(int i=0;i<4;i++){
                        if(num[i]==0){
                            num[i] = val[j];
                            cnt[i] += cnt[id];
                            break;
                        }
                    }
                }
            }
 
            if(m < k && k <= m+cnt[id]){
                return val[0] + " " + val[1];
            }
            
            m += cnt[id];   
            num[id] = 0;
            cnt[id] = 0;
        }
    }
 
    public static void main(String[] args) {
        new C().run();
    }
 }
