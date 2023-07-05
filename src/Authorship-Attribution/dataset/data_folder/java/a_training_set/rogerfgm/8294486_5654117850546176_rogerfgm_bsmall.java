
 
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
        N = parseInt(sp[0]);
        int R = parseInt(sp[1]);
        int Y = parseInt(sp[3]);
        int B = parseInt(sp[5]);
        
        int prev = 0;
        StringBuilder sb = new StringBuilder();
        if(R >= Y && R >= B){
            sb.append("R");
            R--;
            prev = 0;
        }
        else if(Y >= R && Y >= B){
            sb.append("Y");
            Y--;
            prev = 1;
        }
        else{
            sb.append("B");
            B--;
            prev = 2;
        }
        while(R > 0 || Y > 0 || B  > 0){
            if(R == 1 && Y == 1 && B == 1){
                if(prev == 0){
                    if(sb.charAt(0) == 'Y'){
                        sb.append("YBR");
                    }
                    else if(sb.charAt(0) == 'B'){
                        sb.append("BYR");
                    }
                    else{
                        sb.append("BRY");
                    }
                }
                else if(prev == 1){
                    if(sb.charAt(0) == 'Y'){
                        sb.append("BYR");
                    }
                    else if(sb.charAt(0) == 'B'){
                        sb.append("RBY");
                    }
                    else{
                        sb.append("RBY");
                    }
                }
                else{
                    if(sb.charAt(0) == 'Y'){
                        sb.append("RYB");
                    }
                    else if(sb.charAt(0) == 'B'){
                        sb.append("RBY");
                    }
                    else{
                        sb.append("RYB");
                    }
                }
                
                break;
            }
            if(prev == 0){
                if(Y >= B && Y > 0){
                    sb.append("Y");
                    Y--;
                    prev = 1;
                    continue;
                }
                else if(B > 0){
                    sb.append("B");
                    B--;
                    prev = 2;
                    continue;
                }
            }
            else if(prev == 1){
                if(R >= B && R > 0){
                    sb.append("R");
                    R--;
                    prev = 0;
                    continue;
                }
                else if(B > 0){
                    sb.append("B");
                    B--;
                    prev = 2;
                    continue;
                }
            }
            else if(prev == 2){
                if(R >= Y && R > 0){
                    sb.append("R");
                    R--;
                    prev = 0;
                    continue;
                }
                else if(Y > 0){
                    sb.append("Y");
                    Y--;
                    prev = 1;
                    continue;
                }
            }
            println("IMPOSSIBLE");
            return;
        }
        String ans = sb.toString();
        if(ans.charAt(0) == ans.charAt(ans.length()-1)){
            println("IMPOSSIBLE");
        }
        else{
            println(ans);
        }
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
