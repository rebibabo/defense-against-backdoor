import java.util.*;
 
 public class c {
 
    final public static int[][] TABLE = {{1,2,3,4},{2,-1,4,-3},
                                         {3,-4,-1,2},{4,3,-2,-1}};
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
 
        for (int loop=1; loop<=numCases; loop++) {
            int len = stdin.nextInt();
            int rep = stdin.nextInt();
            char[] pat = stdin.next().toCharArray();
 
            
            int n = len*rep;
            char[] str = new char[len*rep];
            for (int i=0; i<rep; i++)
                for (int j=0; j<len; j++)
                    str[i*len+j] = pat[j];
 
            int[] vals = new int[n];
            vals[0] = (int)(str[0] - 'i' + 2);
 
            
            for (int i=1; i<n; i++) {
                int neg = 0, myVal = vals[i-1];
                if (vals[i-1] < 0) {
                    neg = 1;
                    myVal = -vals[i-1];
                }
 
                int thisval = (int)(str[i] - 'i' + 2);
                if (neg == 0)
                    vals[i] = TABLE[myVal-1][thisval-1];
                else
                    vals[i] = -TABLE[myVal-1][thisval-1];
            }
 
            if (vals[n-1] != -1) {
                System.out.println("Case #"+loop+": NO");
            }
            else {
 
                boolean res = false;
                for (int end=0; end<n-2; end++) {
 
                    
                    if (vals[end] == 2) {
 
                        for (int split=end+1; split<n-1; split++) {
 
                            if (vals[split] == 4) {
                                res = true;
                                break;
                            }
 
                        }
                        if (res) break;
                    }
                }
 
                System.out.print("Case #"+loop+": ");
                if (res) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }
 }