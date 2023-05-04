
 
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
    
    String getstr(String s){
        if(s.equals("11")){
            return "1";
        }
        else if(s.equals("1i")){
            return "i";
        }
        else if(s.equals("1j")){
            return "j";
        }
        else if(s.equals("1k")){
            return "k";
        }
        else if(s.equals("i1")){
            return "i";
        }
        else if(s.equals("j1")){
            return "j";
        }
        else if(s.equals("k1")){
            return "k";
        }
        else if(s.equals("ii")){
            return "1";
        }
        else if(s.equals("ij")){
            return "k";
        }
        else if(s.equals("ik")){
            return "j";
        }
        else if(s.equals("ji")){
            return "k";
        }
        else if(s.equals("jj")){
            return "1";
        }
        else if(s.equals("jk")){
            return "i";
        }
        else if(s.equals("ki")){
            return "j";
        }
        else if(s.equals("kj")){
            return "i";
        }
        else if(s.equals("kk")){
            return "1";
        }
        return "";
    }
    
    int getint(String s){
        if(s.equals("ii")){
            return 1;
        }
        else if(s.equals("ij")){
            return 0;
        }
        else if(s.equals("ik")){
            return 1;
        }
        else if(s.equals("ji")){
            return 1;
        }
        else if(s.equals("jj")){
            return 1;
        }
        else if(s.equals("jk")){
            return 0;
        }
        else if(s.equals("ki")){
            return 0;
        }
        else if(s.equals("kj")){
            return 1;
        }
        else if(s.equals("kk")){
            return 1;
        }
        return 0;
    }
    
 
    
    Map<String, String> map = null;
    Map<String, Boolean> mapm = null;
    
    public void solve() throws Exception{
        String s = br.readLine();
        String[] sp = s.split(" ");
        int L = Integer.parseInt(sp[0]);
        int X = Integer.parseInt(sp[1]);
        map = new HashMap<String, String>();
        mapm = new HashMap<String, Boolean>();
        s = br.readLine();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < X; i++){
            sb.append(s);
        }
        if(check(sb.toString())){
            println("YES");
        }
        else{
            println("NO");
        }
    }
    
    boolean check(String s){
        int cnt = 0;
        boolean fi = false;
        boolean fj = false;
        while(s.length() > 1){
            if(!fi && s.startsWith("i")){
                s = s.substring(1);
                fi = true;
                continue;
            }
            if(fi && !fj && s.startsWith("j")){
                s = s.substring(1);
                fj = true;
                continue;
            }
            
            String fst = s.substring(0, 2);
            String scd = s.substring(2);
            s = getstr(fst) + scd;
            cnt += getint(fst);
        }
        if(fi && fj && s.equals("k") && cnt % 2 == 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("C-small-attempt0.in");
        if(file.exists()){
            System.setIn(new BufferedInputStream(new FileInputStream(file)));
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
