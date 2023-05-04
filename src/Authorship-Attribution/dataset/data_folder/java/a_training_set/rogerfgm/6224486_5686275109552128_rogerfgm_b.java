
 
 import java.io.*;
 import java.util.*;
 
 import static java.lang.Math.*;
 import static java.lang.Integer.*;
 
 public class B {
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
        s = br.readLine();
        String[] sp = s.split(" ");
        int[] d = new int[N];
        for(int i = 0; i < N; i++){
            d[i] = Integer.parseInt(sp[i]);
        }
        int ans = check(d);
        
        
        println(ans);
    }
    
    int check(int[] d){
        Arrays.sort(d);
        int len = d.length;
        if(d[len-1] <= 3){
            return d[len-1];
        }
        int ans = d[len-1];
        int[] nd = new int[len+1];
        for(int i = 0; i < d.length-1; i++){
            nd[i] = d[i];
        }
        nd[len-1] = d[len-1] / 2;
        nd[len] = d[len-1] / 2 + d[len-1] % 2;
        ans = Math.min(ans, 1 + check(nd));
        for(int i = 0; i < d.length; i++){
            if(d[i] > 0){
                d[i] = d[i] -1;
            }
        }
        ans = Math.min(ans, 1 + check(d));
        return ans;
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("B-small-attempt5.in");
        if(file.exists()){
            System.setIn(new BufferedInputStream(new FileInputStream(file)));
        }
        
        br = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fw = new FileWriter(new File("output.txt"));
        out = new PrintWriter(fw);
        
        B b = new B();
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
