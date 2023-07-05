import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.ArrayDeque;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class C {
 
    int n, q;
    int[] e, s;
    int[][] d;
    int[] u, v;
    
    void run() {
        try (Scanner sc = new Scanner(System.in)) {
            StringBuilder ans = new StringBuilder();
            int tcNum = Integer.parseInt(sc.next());
            for (int tc = 1; tc <= tcNum; tc++) {
                n = sc.nextInt();
                q = sc.nextInt();
                e = new int[n];
                s = new int[n];
                for(int i=0;i<n;i++) {
                    e[i] = sc.nextInt();
                    s[i] = sc.nextInt();
                }
                d = new int[n][n];
                for(int i=0;i<n;i++) {
                    for(int j=0;j<n;j++) {
                        d[i][j] = sc.nextInt();
                    }
                }
                u = new int[n];
                v = new int[n];
                for(int i=0;i<q;i++) {
                    u[i] = sc.nextInt();
                    v[i] = sc.nextInt();
                }
                
                ans.append("Case #" + tc + ": " + fnc() + System.lineSeparator());
            }
 
            writeFileStr("src/CSans", ans.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    String fnc() {
        
        double[][] cost = new double[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(cost[i], Double.MAX_VALUE);
        }
            
        for(int i=0;i<n;i++) {
            int st = i;
            int cc = e[i];
            int total = 0;
            while(true){
                boolean f = false;
                for(int j=0;j<n;j++) {
                    if(d[st][j]!=-1 && cc >= d[st][j]) {
                        total += d[st][j];
                        cost[i][j] = (double)total/s[i];
                        cc -= d[st][j];
                        st = j;
                        f = true;
                        break;
                    }
                }
                if(!f){
                    break;
                }
            }
        }
        
        for(int i=0;i<n;i++) cost[i][i] = 0;
 
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    if(cost[j][i]!=Double.MAX_VALUE && cost[i][k]!=Double.MAX_VALUE){
                        cost[j][k] = Math.min(cost[j][k], cost[j][i]+cost[i][k]);
                    }
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<q;i++) {
            sb.append(String.format("%,7f", cost[u[i]-1][v[i]-1])).append(" ");
        }
        
        return sb.toString().trim();
    }
 
 
    void writeFileStr(String fileName, String str) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))))) {
            pw.print(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        new C().run();
    }
 }
