import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 public class B {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("B.txt"));
        PrintWriter out = new PrintWriter(new File("B.out"));
        int cases = sc.nextInt();
        for(int t=1;t<=cases;t++){
            int N = sc.nextInt();
            int place = sc.nextInt();
            long[] array = new long[N];
            for(int a=0;a<N;a++)array[a]=sc.nextLong();
            if(place<=N){
                String answer = String.format("Case #%d: %d", t,place);
                System.out.println(answer);
                out.println(answer);
                continue;
            }
            long low = 0;
            long high = 2000000000000L;
            long ans = 0;
            while(low<=high){
                long mid = (low+high)/2;
                long served = serve(array,mid);
                if(served<place){
                    ans = mid;
                    low = mid+1;
                }
                else{
                    high = mid-1;
                }
            }
            
            long time = ans;
            System.out.println("S"+time);
            long[] cur = new long[N];
            long done = N;
            for(int a=0;a<N;a++){
                done+=time/array[a];
                cur[a] = array[a]-(time%array[a]);
            }
            int answery = 0;
            stuff: while(true){
                time++;
                for(int a=0;a<N;a++){
                    if(cur[a]==0){
                        done++;
                        if(done>=place){
                            answery = a+1;
                            break stuff;
                        }
                        cur[a]=array[a];
                    }
                    cur[a]--;
                }
            }
            System.out.println("T"+time);
            
            String answer = String.format("Case #%d: %d", t,answery);
        
            System.out.println(answer);
            out.println(answer);
        }
        out.close();
    }
 
    private static long serve(long[] array, long mid) {
        if(mid==0)return array.length;
        long ans = array.length;
        for(int a=0;a<array.length;a++){
            ans+=mid/array[a];
        }
        return ans;
    }
 
 }
