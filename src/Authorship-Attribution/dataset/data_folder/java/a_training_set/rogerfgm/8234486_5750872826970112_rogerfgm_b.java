
 
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
    
    String IMP = "IMPOSSIBLE";
    
    public void solve() throws Exception{
        String s = br.readLine();
        
        String[] sp = s.split(" ");
        N = Integer.parseInt(sp[0]);
        double V = Double.parseDouble(sp[1]);
        double X = Double.parseDouble(sp[2]);
        if(N == 1){
            s = br.readLine();
            sp = s.split(" ");
            double r = Double.parseDouble(sp[0]);
            double c = Double.parseDouble(sp[1]);
            if(Math.abs(c - X) > DF){
                println(IMP);
            }
            else{
                double ans = V / r;
                println(ans);
            }
        }
        else{
            s = br.readLine();
            sp = s.split(" ");
            double r1 = Double.parseDouble(sp[0]);
            double c1 = Double.parseDouble(sp[1]);
            s = br.readLine();
            sp = s.split(" ");
            double r2 = Double.parseDouble(sp[0]);
            double c2 = Double.parseDouble(sp[1]);
            if(same(c1, c2)){
                if(same(c1, X)){
                    double r = r1 + r2;
                    double ans = V / r;
                    println(ans);
                }
                else{
                    println(IMP);
                }
            }
            else{
                if(same(c1, X)){
                    double ans = V / r1;
                    println(ans);
                }
                else if(same(c2, X)){
                    double ans = V / r2;
                    println(ans);
                }
                else{
                    if(c1 < X && c2 < X){
                        println(IMP);
                    }
                    else if(c1 > X && c2 > X){
                        println(IMP);
                    }
                    else{
                        double d1 = Math.abs(c1 - X);
                        double d2 = Math.abs(c2 - X);
                        double totaldiff = d1 + d2;
                        double ans = 0;
                        double v1 = V * d2 / totaldiff;
                        double a1 = v1 / r1;
                        double v2 = V * d1 / totaldiff;
                        double a2 = v2 / r2;
                        ans = Math.max(a1, a2);
                        println(ans);
                    }
                }
            }
        }
    }
    
    boolean same(double d1, double d2){
        if(Math.abs(d1 - d2) < DF){
            return true;
        }
        return false;
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("B-small-attempt0.in");
        if(file.exists()){
            System.setIn(new BufferedInputStream(new FileInputStream(file)));
        }
        else{
            throw new Exception("can't find a input file : " + file.getAbsolutePath());
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
    void print(double i){
        out.print(i + "");
        System.out.print(i);
    }
    void println(double i){
        out.println(i + "");
        System.out.println(i);
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
