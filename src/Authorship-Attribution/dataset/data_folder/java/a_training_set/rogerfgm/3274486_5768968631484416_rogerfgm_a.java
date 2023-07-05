
 
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
    int K = 0;
    
 
    
    public void solve() throws Exception{
        String s = br.readLine();
        
        String[] sp = s.split(" ");
        N = parseInt(sp[0]);
        K = parseInt(sp[1]);
        
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            s = br.readLine();
            sp = s.split(" ");
            List<Integer> l = new ArrayList<>();
            l.add(parseInt(sp[0]));
            l.add(parseInt(sp[1]));
            list.add(l);
        }
        Collections.sort(list, new Comparator<List<Integer>>() {
 
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if(o1.get(0).intValue() == o2.get(1).intValue()){
                    return o1.get(1).intValue() - o2.get(1).intValue();
                }
                return o1.get(0).intValue() - o2.get(0).intValue();
            }
        });
        double rad = list.get(list.size()-1).get(0);
        
        double[] vals = new double[N];
        for(int i = 0; i < N; i++){
            
            rad = list.get(i).get(0);
            
            double h = list.get(i).get(1);
            vals[i] = 2 * Math.PI * rad * h;
        }
        double ans = 0;
        for(int i = N-1; i >= K-1; i--){
            rad = list.get(i).get(0);
            double a =  Math.PI * rad * rad;
            double h = list.get(i).get(1);
            a +=  2 * Math.PI * rad * h;
            if(K > 1){
                double[] v2 = new double[i];
                int k = 0;
                for(int j = i-1; j >= 0; j--){
                    v2[k++] = vals[j];
                }
                Arrays.sort(v2);
            
                for(int l = 0; l < K-1; l++){
                    a += v2[v2.length-1 - l];
                }
            }
            ans = Math.max(ans, a);
        }
        
        println(ans + "");
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
