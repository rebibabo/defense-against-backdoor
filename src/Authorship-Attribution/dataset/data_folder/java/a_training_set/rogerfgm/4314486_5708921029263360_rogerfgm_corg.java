
 
 import java.io.*;
 import java.util.*;
 
 import static java.lang.Math.*;
 import static java.lang.Integer.*;
 
 public class Corg {
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
        int J = parseInt(sp[0]);
        int P = parseInt(sp[1]);
        int S = parseInt(sp[2]);
        int K = parseInt(sp[3]);
        int[] ijs = new int[100];
        int[] jks = new int[100];
        int[] iks = new int[100];
        
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        
        for(int i = 1; i <= J; i++){
            int j = i;
            int k = i;
                    
            int ij = i * 10 + j;
            int jk = j * 10 + k;
            int ik = i * 10 + k;
            if(ijs[ij] < K && jks[jk] < K && iks[ik] < K){
                ans++;
                ijs[ij]++;
                jks[jk]++;
                iks[ik]++;
                sb.append(i + " " + j + " " + k + "\n");
            }
        }
        if( P >= 2 && S >= 3){
            int i = 1; 
            int j = 2;
            int k = 3;
            int ij = i * 10 + j;
            int jk = j * 10 + k;
            int ik = i * 10 + k;
            if(ijs[ij] < K && jks[jk] < K && iks[ik] < K){
                ans++;
                ijs[ij]++;
                jks[jk]++;
                iks[ik]++;
                sb.append(i + " " + j + " " + k + "\n");
            }
        }
        if(J == 3){
            int i = 3; 
            int j = 2;
            int k = 1;
            int ij = i * 10 + j;
            int jk = j * 10 + k;
            int ik = i * 10 + k;
            if(ijs[ij] < K && jks[jk] < K && iks[ik] < K){
                ans++;
                ijs[ij]++;
                jks[jk]++;
                iks[ik]++;
                sb.append(i + " " + j + " " + k + "\n");
            }
        }
        for(int k = 1; k <= S; k++){
            for(int j = 1; j <= P; j++){
               for(int i = 1; i <= J; i++){
                
                
            
                
                    if(i == j && j == k){
                        continue;
                    }
                    if(i == 1 && j == 2 && k == 3){
                        continue;
                    }
                    if(i == 3 && j == 2 && k == 1){
                        continue;
                    }
                    int ij = i * 10 + j;
                    int jk = j * 10 + k;
                    int ik = i * 10 + k;
                    if(ijs[ij] < K && jks[jk] < K && iks[ik] < K){
                        ans++;
                        ijs[ij]++;
                        jks[jk]++;
                        iks[ik]++;
                        sb.append(i + " " + j + " " + k + "\n");
                    }
                }
            }
        }
        println(ans);
        print(sb.toString());
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("C-small-attempt4.in");
        if(file.exists()){
            System.setIn(new BufferedInputStream(new FileInputStream(file)));
        }
        else{
            throw new Exception("can't find a input file : " + file.getAbsolutePath());
        }
        
        br = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fw = new FileWriter(new File("output.txt"));
        out = new PrintWriter(fw);
        
        Corg b = new Corg();
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
