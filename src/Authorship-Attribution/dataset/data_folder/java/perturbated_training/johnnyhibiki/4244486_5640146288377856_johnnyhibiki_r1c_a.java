import java.util.*;
 
 public class R1C_A {
    int r, c, w;
    int[][] map;
    
    
    void run(){
        Scanner sc = new Scanner(System.in);
        
        map = new int[11][11];
        for(int i=1;i<11;i++){ 
            for(int j=1;j<=i;j++){ 
                if(j==1 || j==i) map[i][j] = i;
                else if(j>=i/2+i%2) map[i][j] = j+1;
                else map[i][j] = map[i-j][j] + 1;
                
            }
            
        }
        
                
        int t = sc.nextInt();
        for(int test=1;test<=t;test++){
            r = sc.nextInt();
            c = sc.nextInt();
            w = sc.nextInt();
            
            
            System.out.println("case #" + test + ": " + map[c][w]);
        }
        sc.close();
    }
    
    
    public static void main(String[] args) {
        new R1C_A().run();
    }
 
 }
