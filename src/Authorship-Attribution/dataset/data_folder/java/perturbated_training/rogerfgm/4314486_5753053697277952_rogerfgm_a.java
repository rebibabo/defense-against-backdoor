
 
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
    
 
    
    public void solve() throws Exception{
        String s = br.readLine();
        
        
        N = parseInt(s);
        s = br.readLine();
        
        String[] sp = s.split(" ");
        D[] ds = new D[N];
        for(int i = 0; i < N; i++){
            ds[i] = new D();
            ds[i].c = (char)('A' + i);
            ds[i].d = parseInt(sp[i]);
        }
        Comparator<D> comp = new Comparator<D>() {
            
            @Override
            public int compare(D o1, D o2) {
                
                return o2.d - o1.d;
            }
        };
        
        Arrays.sort(ds,comp);
        StringBuilder sb = new StringBuilder();
        while(true){
            Arrays.sort(ds, comp);
            if(ds[0].d == 0){
                break;
            }
            if(N == 2){
                if(ds[0].d == 1 && ds[1].d == 1){
                    if(sb.length() != 0){
                        sb.append(" ");
                    }
                    String add = ds[0].c + "" + ds[1].c;
                    sb.append(add);
                    ds[0].d = ds[1].d = 0;
                    continue;
                }
            }
            else{
                if(ds[0].d == 1 && ds[1].d == 1 && ds[2].d == 0){
                    if(sb.length() != 0){
                        sb.append(" ");
                    }
                    String add = ds[0].c + "" + ds[1].c;
                    sb.append(add);
                    ds[0].d = ds[1].d = 0;
                    continue;
                }
                else if(ds[0].d == 1 && ds[1].d == 1 && ds[2].d == 1){
                    if(sb.length() != 0){
                        sb.append(" ");
                    }
                    
                    sb.append(ds[0].c);
                    ds[0].d--;
                    continue;
                }
            }
            if(sb.length() != 0){
                sb.append(" ");
            }
            if(ds[0].d == ds[1].d){
                String add = ds[0].c + "" + ds[1].c;
                sb.append(add);
                ds[0].d--;
                ds[1].d--;
            }
            else{
                ds[0].d--;
                sb.append(ds[0].c);
            }
            
            
            
        }
        println(sb.toString());
        
        
    }
    
    class D{
        int d = 0;
        char c;
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("A-small-attempt3.in");
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
