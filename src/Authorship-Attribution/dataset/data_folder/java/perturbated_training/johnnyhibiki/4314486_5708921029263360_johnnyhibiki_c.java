import java.util.Scanner;
 
 public class C {
    
    public static void main(String[] args){
        new C().run();
    }
 
    int J, P, S, K;
    
    void run(){
        Scanner sc = new Scanner(System.in);
        
        int testNum = sc.nextInt();
        for(int t=1;t<=testNum;t++){
            J = sc.nextInt();
            P = sc.nextInt();
            S = sc.nextInt();
            K = sc.nextInt();
 
            String ans = fnc();
            System.out.println("Case #" + t + ": " + ans);
        }
    }
    
    String fnc(){
        StringBuilder sb = new StringBuilder();
        
        int[][] jp = new int[S][S];
        int[][] ps = new int[S][S];
        int[][] sj = new int[S][S];
        int cnt = 0;
        for(int i=0;i<J;i++){
            for(int j=0;j<P;j++){
                for(int k=0;k<S;k++){
                    if(jp[i][j]<K && ps[j][k]<K && sj[k][i]<K){
                        jp[i][j]++;
                        ps[j][k]++;
                        sj[k][i]++;
                        sb.append((i+1) + " " + (j+1) + " " + (k+1) + "\n");
                        cnt++;
                    }
                }
            }
        }
        
        return "" + cnt + "\n" + sb.toString().trim();
    }   
 }
