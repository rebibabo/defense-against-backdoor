
 
 import java.io.*;
 import java.util.*;
 
 import static java.lang.Math.*;
 import static java.lang.Integer.*;
 
 public class Asmall {
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
    
    int[] dp = null;
 
    
    public void solve() throws Exception{
        String s = br.readLine();
        
        N = Integer.parseInt(s);
        int n = N;
        dp = new int[n+1];
        for(int i = 0; i <= n; i++){
            dp[i] = -1;
        }
        if(n <= 19){
            println(n);
            return;
        }
        get(10, 10);
        int ans = dp[n];
        println(ans);
    }
    
    void get(int n, int cnt){
        if(n > N) return;
        if(dp[n] != -1 && dp[n] <= cnt){
            return;
        }
        dp[n] = cnt;
        int f = flip(n);
        if(f > n){
            get(f, cnt+1);
        }
        get(n+1, cnt+1);
    }
    
    int flip(int n){
    
        int a = 0;
        while(n > 0){
            int t = n % 10;
            a *= 10;
            a += t;
        
            n /= 10;
        }
        return a;
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("A-small-attempt0.in");
        if(file.exists()){
            System.setIn(new BufferedInputStream(new FileInputStream(file)));
        }
        else{
            throw new Exception("can't find a input file : " + file.getAbsolutePath());
        }
        
        br = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fw = new FileWriter(new File("output.txt"));
        out = new PrintWriter(fw);
        
        Asmall b = new Asmall();
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
