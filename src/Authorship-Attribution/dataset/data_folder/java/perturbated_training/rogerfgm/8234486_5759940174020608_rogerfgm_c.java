
 
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
    
    int ans = 0;
    Set<String> ENG = new HashSet<String>();
    Set<String> FRE = new HashSet<String>();
    Set<String> AMB = new HashSet<String>();
    List<String[]> list = null;
    public void solve() throws Exception{
        String s = br.readLine();
        N = Integer.parseInt(s);
        s = br.readLine();
        String[] sp = s.split(" ");
        ENG = new HashSet<String>();
        FRE = new HashSet<String>();
        AMB = new HashSet<String>();
        for(int i =0; i < sp.length; i++){
            ENG.add(sp[i]);
        }
        s = br.readLine();
        sp = s.split(" ");
        for(int i =0; i < sp.length; i++){
            if(ENG.contains(sp[i])){
                ENG.remove(sp[i]);
                AMB.add(sp[i]);
            }
            else if(!AMB.contains(sp[i])){
                FRE.add(sp[i]);
            }
        }
        
    
        
        list = new ArrayList<String[]>();
        for(int i = 2; i < N; i++){
            s = br.readLine();
            sp = s.split(" ");
            list.add(sp);
        }
        N -= 2;
        if(N == 0){
            int ans = AMB.size();
            println(ans);
            return;
        }
        else{
            ans = INF;
            boolean[] bs = new boolean[N];
            check(bs, 0);
        }
        println(ans);
    }
    void check(boolean[] bs, int idx){
        if(idx == N){
            Set<String> eng = new HashSet<String>();
            eng.addAll(ENG);
            Set<String> fre = new HashSet<String>();
            fre.addAll(FRE);
            Set<String> amb = new HashSet<String>();
            amb.addAll(AMB);
            for(int i = 0; i < N; i++){
                String[] sp = list.get(i);
                if(bs[i]){
                    for(int j = 0; j < sp.length; j++){
                        if(fre.contains(sp[j])){
                            fre.remove(sp[j]);
                            amb.add(sp[j]);
                        }
                        else if(!amb.contains(sp[j])){
                            eng.add(sp[j]);
                        }
                    }   
                }
                else{
                    for(int j = 0; j < sp.length; j++){
                        if(eng.contains(sp[j])){
                            eng.remove(sp[j]);
                            amb.add(sp[j]);
                        }
                        else if(!amb.contains(sp[j])){
                            fre.add(sp[j]);
                        }
                    }   
                }
            }
            ans = Math.min(ans, amb.size());
        }
        else{
            check(bs, idx+1);
            bs[idx] = true;
            check(bs, idx+1);
            bs[idx] = false;
        }
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("C-small-attempt0.in");
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
