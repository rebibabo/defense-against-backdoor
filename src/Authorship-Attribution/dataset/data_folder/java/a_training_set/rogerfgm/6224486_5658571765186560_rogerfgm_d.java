
 
 import java.io.*;
 import java.util.*;
 
 import static java.lang.Math.*;
 import static java.lang.Integer.*;
 
 public class D {
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
    
    String G = "GABRIEL";
    String RI = "RICHARD";
    
    public void solve() throws Exception{
        String s = br.readLine();
        
        String[] sp = s.split(" ");
        int X = Integer.parseInt(sp[0]);
        int R = Integer.parseInt(sp[1]);
        int C = Integer.parseInt(sp[2]);
 
        if(R < X && C < X){
            println(RI);
            return;
        }
        int M = R * C;
        int ama = M % X;
        if(ama > 0){
            println(RI);
            return;
        }
        if(X == 1 || X == 2 ){
            println(G);
        }
        else if(X == 3){
            if(R == 1 || C == 1){
                println(RI);
            }
            else{
                println(G);
            }
        }
        else{
            if(R <= 2 || C <= 2){
                println(RI);
            }
            else{
                println(G);
            }
        }
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("D-small-attempt1.in");
        if(file.exists()){
            System.setIn(new BufferedInputStream(new FileInputStream(file)));
        }
        
        br = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fw = new FileWriter(new File("output.txt"));
        out = new PrintWriter(fw);
        
        D b = new D();
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
