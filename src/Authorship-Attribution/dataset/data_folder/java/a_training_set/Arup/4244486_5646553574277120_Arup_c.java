import java.util.*;
 
 public class c {
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
 
        for (int loop=1; loop<=numCases; loop++) {
 
            int max = stdin.nextInt();
            int n = stdin.nextInt();
            long value = stdin.nextLong();
            int[] tmp = new int[n];
            for (int i=0; i<n; i++)
                tmp[i] = stdin.nextInt();
 
            
            int[] denom = new int[n*max];
            for (int i=0; i<n; i++)
                for (int j=0; j<max; j++)
                    denom[max*i+j] = tmp[i];
            Arrays.sort(denom);
 
            int cnt = 0;
            long sum = 0;
            int index = 0;
 
            while (index < denom.length && sum < value) {
 
                
                if (sum+1 < denom[index]) {
                    cnt++;
                    long added = (sum+1)*max;
                    sum += added;
                }
 
                else {
                    sum += denom[index];
                    index++;
                }
            }
 
            while (sum < value) {
                cnt++;
                long added = (sum+1)*max;
                sum += added;
            }
 
            
            System.out.println("Case #"+loop+": "+cnt);
        }
    }
 }