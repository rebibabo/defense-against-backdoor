
 
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
    double DF = 0.00000001;
    
    long b = 1;
    int N = 0;
    int M = 0;
    
 
    
    public void solve() throws Exception{
        String s = br.readLine();
        
        String[] sp = s.split(" ");
        N = parseInt(sp[0]);
        int k = parseInt(sp[1]);
        s = br.readLine();
        double U = Double.parseDouble(s);
        double[] d = new double[N];
        s = br.readLine();
        sp = s.split(" ");
        for(int i = 0; i < N; i++){
            d[i] = Double.parseDouble(sp[i]);
        }
        
        while(U > DF){
            Arrays.sort(d);
            int cnt = 1;
            for(int i = 1; i < N; i++){
                if(d[i] - d[0] < DF){
                    cnt++;
                }
                else{
                    break;
                }
            }
            if(cnt == N){
                for(int i = 0; i < N; i++){
                    d[i] += U / N;
                }
                break;
            }
            else{
                double u = Math.min(d[cnt] - d[0], U);
                U -= u;
                for(int i = 0; i < cnt; i++){
                    d[i] += u / cnt;
                }
            }
        }
        double ans = 1;
        for(int i = 0; i < N; i++){
            ans *= d[i];
        }
        println(ans + "");
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("C-small-1-attempt1.in");
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
