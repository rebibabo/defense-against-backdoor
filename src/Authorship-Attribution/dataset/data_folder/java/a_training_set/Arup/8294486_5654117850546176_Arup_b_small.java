import java.util.*;
 
 public class b_small {
    
    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
        
        for (int loop=1; loop<=numCases; loop++) {
            
            int n = stdin.nextInt();
            int[] data = new int[6];
            boolean ok = true;
            for (int i=0; i<6; i++) {
                data[i] = stdin.nextInt();
                if (2*data[i] > n)
                    ok = false;
            }
            
            if (!ok) {
                System.out.println("Case #"+loop+": IMPOSSIBLE");
            }
            else {
            
                item[] list = new item[3];
                list[0] = new item('R', data[0]);
                list[1] = new item('Y', data[2]);
                list[2] = new item('B', data[4]);
                Arrays.sort(list);
                
                char[] res = new char[n];
                Arrays.fill(res, ' ');
                int cur = 0;
                boolean flag = false;
                for (int z=0; z<3; z++) {
                    
                    for (int i=0; i<list[z].freq; i++) {
                        
                        res[cur] = list[z].c;
                        cur = cur + 2;
                        if (!flag && cur >= n) {
                            cur = 1;
                            flag = true;
                        }
                    }
                }
                    
            
                System.out.println("Case #"+loop+": "+new String(res));
            }
        }
    }
 }
 
 class item implements Comparable<item> {
    
    public char c;
    public int freq;
    
    public item(char myc, int f) {
        c = myc;
        freq = f;
    }
    
    public int compareTo(item other) {
        return other.freq - this.freq;
    }
 }