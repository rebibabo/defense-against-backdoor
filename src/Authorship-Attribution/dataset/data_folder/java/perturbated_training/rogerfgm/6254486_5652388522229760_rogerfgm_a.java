
 
 import java.io.*;
 import java.util.*;
 
 import static java.lang.Math.*;
 import static java.lang.Integer.*;
 
 public class A {
    static Scanner sc = null;
    static BufferedReader br = null;
    static PrintWriter out = null;
    static PrintStream sysout = System.out;
    static Random rnd = new Random();
    
    int INF = Integer.MAX_VALUE / 10;
    double DF = 0.0000000001;
    
    long b = 1;
    long N = 0;
    int M = 0;
    
 
    
    public void solve() throws Exception{
        String s = br.readLine();
        N = parseInt(s);
        List<Integer> check = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++){
            check.add(i);
        }
        if(N == 0){
            println("INSOMNIA");
        }
        else{
            
            long ans = 1;
            while(ans < 10000000){
                for(int i = check.size()-1; i >= 0; i--){
                    int ck = check.get(i);
                    long M = N * ans;
                    if(check(M, ck)){
                        check.remove(i);
                    }
                }
                if(check.size() == 0){
                    long A = N * ans;
                    println(A);
                    return;
                }
                else{
                    ans++;
                }
            }
            println("INSOMNIA");
        }
    }
    
    boolean check(long m, int ck){
        while(m > 0){
            int c = (int)(m % 10);
            if(c == ck){
                return true;
            }
            m/=10;
        }
        return false;
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
        
        A b = new A();
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
