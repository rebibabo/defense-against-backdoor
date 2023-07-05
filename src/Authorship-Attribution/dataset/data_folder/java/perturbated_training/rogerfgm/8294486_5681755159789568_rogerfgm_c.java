
 
 import java.io.*;
 import java.util.*;
 
 import static java.lang.Math.*;
 import static java.lang.Integer.*;
 
 public class C {
    static Scanner sc = null;
    static BufferedReader br = null;
    static PrintWriter out = null;
    static PrintStream sysout = System.out;
    static Random rnd = new Random();
    
    int INF = Integer.MAX_VALUE / 10;
    double DF = 0.0000000001;
    
    long b = 1;
    int N = 0;
    int M = 0;
    
 
    
    public void solve() throws Exception{
        String s = br.readLine();
        
        String[] sp = s.split(" ");
        N = parseInt(sp[0]);
        int Q = parseInt(sp[1]); 
        int[][] d = new int[N][2];
        for(int i = 0; i < N; i++){
            s = br.readLine();
            sp = s.split(" ");
            d[i][0] = parseInt(sp[0]); 
            d[i][1] = parseInt(sp[1]); 
        }
        int[] dist = new int[N-1];
        for(int i = 0; i < N; i++){
            s = br.readLine();
            sp = s.split(" ");
            if(i < N-1){
                dist[i] = parseInt(sp[i+1]);
            }
        }
        s = br.readLine();
        
        double[] ans = new double[N];
        for(int i = 1; i < N; i++){
            ans[i] = Long.MAX_VALUE;
        }
        for(int i = 0; i < N-1; i++){
            int remdist = d[i][0];
            double speed = d[i][1];
            double curtime = ans[i];
            for(int j = i; j < N-1; j++){
                int targetdist = dist[j];
                if( remdist >= targetdist){
                    double targettime = targetdist / speed;
                    curtime += targettime;
                    ans[j+1] = Math.min(ans[j+1], curtime);
                    remdist-=targetdist;
                }
                else{
                    break;
                }
            }
        }
        println(ans[N-1] + "");
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("C-small-attempt1.in");
        if(file.exists()){
            System.setIn(new BufferedInputStream(new FileInputStream(file)));
        }
        else{
            throw new Exception("can't find a input file : " + file.getAbsolutePath());
        }
        
        br = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fw = new FileWriter(new File("output.txt"));
        out = new PrintWriter(fw);
        
        C b = new C();
        int T = 0;
        if(sc != null){
            T = sc.nextInt();
        }
        else{
            T = parseInt(br.readLine());
        }
        int t = 1;
        while(t <= T){
            out.print("Case #" + t + ": ");
            System.out.print("Case #" + t + ": ");
            b.solve();
            t++;
        }
        out.close();
        fw.close();
    }
    
    void print(int i){
        out.print(i + "");
        System.out.print(i);
    }
    void println(int i){
        out.println(i + "");
        System.out.println(i);
    }
    void print(String s){
        out.print(s);
        System.out.print(s);
    }
    void println(String s){
        out.println(s);
        System.out.println(s);
    }
    void print(long i){
        out.print(i + "");
        System.out.print(i);
    }
    void println(long i){
        out.println(i + "");
        System.out.println(i);
    }
 }
