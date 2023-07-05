
 
 import java.io.*;
 import java.util.*;
 
 import static java.lang.Math.*;
 import static java.lang.Integer.*;
 
 public class A4 {
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
    char[][] d = null;
    char[][] a = null;
    char qa = '?';
    int R = 0;
    int C = 0;
    List<Character> list = null;
    public void solve() throws Exception{
        String s = br.readLine();
        
        String[] sp = s.split(" ");
        R = Integer.parseInt(sp[0]);
        C = Integer.parseInt(sp[1]);
        d = new char[R][C];
        a = new char[R][C];
        list = new ArrayList<>();
        for(int i = 0; i < R; i++){
            s = br.readLine();
            for(int j = 0; j < C; j++){
                d[i][j] = s.charAt(j);
                a[i][j] = d[i][j];
                if(d[i][j] != qa && !list.contains(d[i][j])){
                    list.add(d[i][j]);
                }
            }
        }
        search(0, 0);
        
 
    }
    
    boolean search(int r, int c){
        int nr = 0;
        int nc = 0;
        if(r == R && c == C){
            if(check()){
                println("");
                for(int i = 0; i < R; i++){
                    String s = "";
                    for(int j = 0; j < C; j++){
                        s += a[i][j];
                    }
                    println(s);
                }
                return true;
            }
            else{
                return false;
            }
        }
        if(r == R-1 && c == C-1){
            nr = R;
            nc = C;
        }
        else if(c == C-1){
            nr = r+1;
            nc = 0;
        }
        else{
            nr = r;
            nc = c+1;
        }
        if(d[r][c] == qa){
            for(int i = 0; i < list.size(); i++){
                a[r][c] = list.get(i);
                if(search(nr, nc)){
                    return true;
                }
            }
            
        }
        else{
            return search(nr, nc);
        }
        
        return false;
    }
    
    boolean check(){
        boolean[][] used = new boolean[R][C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(used[i][j]){
                    continue;
                }
                if(!check2(used, i, j)){
                    return false;
                }
                
            }
        }
        return true;
    }
    boolean check2(boolean[][] used, int r, int c){
    
        
        char target = a[r][c];
        used[r][c] = true;
        find(used, r, c, target);
        for(int i = 0; i < R; i++){
            for(int j =0; j < C; j++){
                if(!used[i][j] && a[i][j] == target){
                    return false;
                }
            }
        }
        for(int i = 0; i < R; i++){
            for(int j =0; j < C; j++){
                if( a[i][j] == target){
                    if(i > 0 && j > 0){
                        if(a[i-1][j] == target && a[i][j-1] == target &&
                                a[i-1][j-1] != target){
                            return false;
                        }
                    }
                    if(i > 0 && j < C-1){
                        if(a[i-1][j] == target && a[i][j+1] == target &&
                                a[i-1][j+1] != target){
                            return false;
                        }
                    }
                    if(i <  R-1 && j > 0){
                        if(a[i+1][j] == target && a[i][j-1] == target &&
                                a[i+1][j-1] != target){
                            return false;
                        }
                    }
                    if(i <  R-1 && j < C-1){
                        if(a[i+1][j] == target && a[i][j+1] == target &&
                                a[i+1][j+1] != target){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
        
    }
    
    void find(boolean[][] used, int r, int c, char target){
        int nr = r;
        int nc = c;
        if(r < R-1){
            nr = r+1;
            nc = c;
            if(!used[nr][nc] && a[nr][nc] == target){
                used[nr][nc] = true;
                find(used, nr, nc, target);
            }
        }
        if(r > 0){
            nr = r-1;
            nc = c;
            if(!used[nr][nc] && a[nr][nc] == target){
                used[nr][nc] = true;
                find(used, nr, nc, target);
            }
        }
        if(c < C-1){
            nr = r;
            nc = c+1;
            if(!used[nr][nc] && a[nr][nc] == target){
                used[nr][nc] = true;
                find(used, nr, nc, target);
            }
        }
        if(c > 0){
            nr = r;
            nc = c-1;
            if(!used[nr][nc] && a[nr][nc] == target){
                used[nr][nc] = true;
                find(used, nr, nc, target);
            }
        }
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("A-small-attempt2.in");
        if(file.exists()){
            System.setIn(new BufferedInputStream(new FileInputStream(file)));
        }
        else{
            throw new Exception("can't find a input file : " + file.getAbsolutePath());
        }
        
        br = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fw = new FileWriter(new File("output.txt"));
        out = new PrintWriter(fw);
        
        A4 b = new A4();
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
