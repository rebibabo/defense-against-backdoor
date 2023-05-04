
 
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
    int N = 0;
    int M = 0;
    
    char U = '^';
    char D = 'v';
    char L = '<';
    char RR = '>';
    char E = '.';
    String IMP = "IMPOSSIBLE";
    char[][] cs = null;
    int R = 0;
    int C = 0;
    public void solve() throws Exception{
        String s = br.readLine();
        
        String[] sp = s.split(" ");
        R = Integer.parseInt(sp[0]);
        C = Integer.parseInt(sp[1]);
        cs = new char[R][C];
        for(int i = 0; i < R; i++){
            s = br.readLine();
            for(int j = 0; j < C; j++){
                cs[i][j] = s.charAt(j);
            }
        }
        int ans = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(cs[i][j] == E) continue;
                boolean f = false;
                if(cs[i][j] == U){
                    for(int k = i-1; k >= 0; k--){
                        if(cs[k][j] != E){
                            f = true;
                            break;
                        }
                    }
                    if(f){
                        continue;
                    }
                    else{
                        f = find(i, j);
                    }
                    if(f){
                        ans++;
                    }
                    else{
                        println(IMP);
                        return;
                    }
                }
                else if(cs[i][j] == D){
                    for(int k = i+1; k < R; k++){
                        if(cs[k][j] != E){
                            f = true;
                            break;
                        }
                    }
                    if(f){
                        continue;
                    }
                    else{
                        f = find(i, j);
                    }
                    if(f){
                        ans++;
                    }
                    else{
                        println(IMP);
                        return;
                    }
                }
                else if(cs[i][j] == L){
                    for(int k = j-1; k >= 0; k--){
                        if(cs[i][k] != E){
                            f = true;
                            break;
                        }
                    }
                    if(f){
                        continue;
                    }
                    else{
                        f = find(i, j);
                    }
                    if(f){
                        ans++;
                    }
                    else{
                        println(IMP);
                        return;
                    }
                }
                else{
                    for(int k = j+1; k <C; k++){
                        if(cs[i][k] != E){
                            f = true;
                            break;
                        }
                    }
                    if(f){
                        continue;
                    }
                    else{
                        f = find(i, j);
                    }
                    if(f){
                        ans++;
                    }
                    else{
                        println(IMP);
                        return;
                    }
                }
            }
        }
        println(ans);
    }
    
    boolean find(int y, int x){
        for(int i = 0; i < C; i++){
            if(i == x) continue;
            if(cs[y][i] != E){
                return true;
            }
        }
        for(int i = 0; i < R; i++){
            if(i == y) continue;
            if(cs[i][x] != E){
                return true;
            }
        }
        
        return false;
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("A-small-attempt1.in");
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
