import java.io.File;
 import java.io.PrintWriter;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 
 public class B {
 
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("pancake.in"));
        PrintWriter out = new PrintWriter(new File("pancake.out"));
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            int N = sc.nextInt();
            int[] array = new int[N];
            for(int a=0;a<N;a++)
                array[a]=sc.nextInt();
            int low = 1;
            int high = 1001;
            int ans = 1001;
            while(low<=high){
                int mid = (low+high)/2;
                if(solve(mid,array)){
                    ans = mid;
                    high = mid-1;
                }
                else {
                    low = mid+1;
                }
            }
            out.printf("Case #%d: %d%n",t,ans);
        }
        
        
        out.close();
    }
 
    private static boolean solve(int time, int[] array) {
        for(int max = 1; max <= time; max++){
            int extra = 0;
            for(int a=0;a<array.length;a++){
                int stacks = (int)Math.ceil((array[a]+0.0)/max);
                extra+=stacks-1;
            }
            if(extra+max<=time)return true;
        }
        return false;
    }
 
 }
