
 
 import java.io.*;
 import java.util.*;
 
 import static java.lang.Math.*;
 import static java.lang.Integer.*;
 
 public class Bsmall2 {
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
        int A = parseInt(sp[0]);
        int C = parseInt(sp[1]);
        N = A + C;
        if(N == 2){
            int[] d1 = new int[2];
            int[] d2 = new int[2];
            
            s = br.readLine();
            sp = s.split(" ");
            d1[0] = parseInt(sp[0]);
            d1[1] = parseInt(sp[1]);
            s = br.readLine();
            sp = s.split(" ");
            d2[0] = parseInt(sp[0]);
            d2[1] = parseInt(sp[1]);
            if(d1[0] > d2[0]){
                int tmp0 = d1[0];
                int tmp1 = d1[1];
                d1[0] = d2[0];
                d1[1] = d2[1];
                d2[0] = tmp0;
                d2[1] = tmp1;
            }
            if(A == 0 || C == 0){
 
                int rem = 720 - (d1[1] - d1[0]) - (d2[1] - d2[0]);
                if(d2[0] - d1[1] <= rem){
                    
                    if(d2[0] <= 720 || d1[1] >= 720){
                        println("2");
                    }
                    else{
                        println("3");
                    }
                    return;
                }
                
                if(d1[0] <= (1440 - d2[1])){
                    int mai = Math.min(d1[0], rem);
                    d1[0] -= mai;
                    rem -= mai;
                    d2[1] += rem;
                    if(d2[1] > 1440){
                        d2[1] = 1440;
                    }
                }
                else{
                    int mai = Math.min((1440 - d2[1]), rem);
                    d2[1] += mai;
                    rem -= mai;
                    d1[0] -= rem;
                    if(d1[0] < 0){
                        d1[0] = 0;
                    }
                }
                
                int ans = 3;
                if(d1[0] != 0){
                    ans++;
                }
                if(d2[1] != 1440){
                    ans++;
                }
                println(ans);
            }
            else{
                if(d1[1] <= 720 && d2[0] >= 720 || 
                        d2[1] <= 720 && d1[0] >= 720){
                    println("2");
                }
                else{
                    println("3");
                }
            }
            
        }
        else{
            int[] d1 = new int[2];
            s = br.readLine();
            sp = s.split(" ");
            d1[0] = parseInt(sp[0]);
            d1[1] = parseInt(sp[1]);
            if(d1[1] <= 720 || d1[0] >= 720){
                println("2");
            }
            else{
                println("3");
            }
        }
        
        
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("B-small-attempt4.in");
        if(file.exists()){
            System.setIn(new BufferedInputStream(new FileInputStream(file)));
        }
        else{
            throw new Exception("can't find a input file : " + file.getAbsolutePath());
        }
        
        br = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fw = new FileWriter(new File("output.txt"));
        out = new PrintWriter(fw);
        
        Bsmall2 b = new Bsmall2();
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
            if(t == 17){
                System.out.print("");
            }
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
