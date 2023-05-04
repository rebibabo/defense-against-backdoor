
 
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
        N  = Integer.parseInt(s);
        
        
        int[][] d = new int[N][3];
        boolean f = false;
        for(int i = 0; i < N; i++){
            s = br.readLine();
            String[] sp = s.split(" ");
            for(int j = 0; j < 3; j++){
                d[i][j] = Integer.parseInt(sp[j]);
            }
        }
        if(N == 1 && d[0][1] == 1){
            println(0);
            return;
        }
        
        double first = 0;
        int d1, d2;
        int m1, m2;
        if(N == 1){
            d1 = d2 = d[0][0];
            m1 = d[0][2];
            m2 = m1+1;
        }
        else{
            d1 = d[0][0];
            m1 = d[0][2];
            d2 = d[1][0];
            m2 = d[1][2];
        }
        if(m1 == m2){
            println(0);
            return;
        }
        double first1 = m1 * (360.0-d1) / 360;
        double first2 = m2 * (360.0-d2) / 360;
        if(Math.abs(first1 - first2) <= DF){
            println(0);
            return;
        }
        if(first1 < first2){
            double second1 = m1 * (360.0-d1 + 360) / 360;
            if(second1 <= first2){
                println(1);
                return;
            }
        }
        else{
            double second2 = m2 * (360.0-d2 + 360) / 360;
            if(second2 <= first1){
                println(1);
                return;
            }
        }
        println(0);
        return;
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("C-small-1-attempt0.in");
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
