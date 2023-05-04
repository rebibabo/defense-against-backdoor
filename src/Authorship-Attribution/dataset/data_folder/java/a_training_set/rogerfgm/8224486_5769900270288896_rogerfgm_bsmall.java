
 
 import java.io.*;
 import java.util.*;
 
 import static java.lang.Math.*;
 import static java.lang.Integer.*;
 
 public class Bsmall {
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
        int r = Integer.parseInt(sp[0]);
        int c = Integer.parseInt(sp[1]);
        N = Integer.parseInt(sp[2]);
        int R = Math.max(r, c);
        int C = Math.min(r, c);
        int ans = 0;
        int hf = R * C / 2 + R * C % 2;
        if(N <= hf){
            println(0);
            return;
        }
        if(R == 1 && C == 1){
            println(0);
            return;
        }
        
        
        boolean[][] d1 = new boolean[R][C];
        boolean[][] d2 = new boolean[R][C];
        boolean[][] d3 = new boolean[R][C];
        int cnt1 = get(0, 0, d1);
        int cnt2 = get(1, 0, d2);
        int cnt3 = get(0, 1, d3);
        if(cnt1 >= N || cnt2 >= N || cnt3 >= N){
            println(0);
            return;
        }
        ans = getcnt(N-cnt1, d1);
        ans = Math.min(ans, getcnt(N-cnt2, d2));
        ans = Math.min(ans, getcnt(N-cnt3, d3));
        println(ans);
    }
    
    int getcnt(int cnt, boolean[][] d){
        if(cnt == 0){
            return 0;
        }
        for(int i = 0; i < d.length; i++){
            for(int j = 0; j < d[0].length; j++){
                if(!d[i][j]){
                    int c = 0;
                    c += check(i-1, j, d);
                    c += check(i+1, j, d);
                    c += check(i, j-1, d);
                    c += check(i, j+1, d);
                    if(c == 0){
                    
                        d[i][j] = true;
                        return 1 + getcnt(cnt-1, d);
                    }
                }
            }
        }
        for(int i = 0; i < d.length; i++){
            for(int j = 0; j < d[0].length; j++){
                if(!d[i][j]){
                    int c = 0;
                    c += check(i-1, j, d);
                    c += check(i+1, j, d);
                    c += check(i, j-1, d);
                    c += check(i, j+1, d);
                    if(c == 1){
                    
                        d[i][j] = true;
                        return 1 + getcnt(cnt-1, d);
                    }
                }
            }
        }
        for(int i = 0; i < d.length; i++){
            for(int j = 0; j < d[0].length; j++){
                if(!d[i][j]){
                    int c = 0;
                    c += check(i-1, j, d);
                    c += check(i+1, j, d);
                    c += check(i, j-1, d);
                    c += check(i, j+1, d);
                    if(c == 2){
                        d[i][j] = true;
                        return 2 + getcnt(cnt-1, d);
                    }
                }
            }
        }
        for(int i = 0; i < d.length; i++){
            for(int j = 0; j < d[0].length; j++){
                if(!d[i][j]){
                    int c = 0;
                    c += check(i-1, j, d);
                    c += check(i+1, j, d);
                    c += check(i, j-1, d);
                    c += check(i, j+1, d);
                    if(c == 3){
                        d[i][j] = true;
                        return 3 + getcnt(cnt-1, d);
                    }
                }
            }
        }
        for(int i = 0; i < d.length; i++){
            for(int j = 0; j < d[0].length; j++){
                if(!d[i][j]){
                    int c = 0;
                    c += check(i-1, j, d);
                    c += check(i+1, j, d);
                    c += check(i, j-1, d);
                    c += check(i, j+1, d);
                    if(c == 4){
                        d[i][j] = true;
                        return 4 + getcnt(cnt-1, d);
                    }
                }
            }
        }
        System.err.println("error");
        return 0;
    }
    
    int check(int y, int x, boolean[][] d){
        if(y < 0 || y >= d.length) return 0;
        if(x < 0 | x >= d[0].length) return 0;
        if(d[y][x]) return 1;
        else return 0;
    }
    
    int get(int y, int x, boolean[][] d){
        if(y < 0 || y >= d.length) return 0;
        if(x < 0 || x >= d[0].length) return 0;
        if(d[y][x]) return 0;
        int cnt = 1;
        d[y][x] = true;
        cnt += get(y+1, x + 1, d);
        cnt += get(y-1, x + 1, d);
        cnt += get(y+1, x - 1, d);
        cnt += get(y-1, x - 1, d);
        cnt += get(y+2, x, d);
        cnt += get(y-2, x, d);
        cnt += get(y, x+2, d);
        cnt += get(y, x-2, d);
        return cnt;
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("B-small-attempt1.in");
        if(file.exists()){
            System.setIn(new BufferedInputStream(new FileInputStream(file)));
        }
        else{
            throw new Exception("can't find a input file : " + file.getAbsolutePath());
        }
        
        br = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fw = new FileWriter(new File("output.txt"));
        out = new PrintWriter(fw);
        
        Bsmall b = new Bsmall();
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
