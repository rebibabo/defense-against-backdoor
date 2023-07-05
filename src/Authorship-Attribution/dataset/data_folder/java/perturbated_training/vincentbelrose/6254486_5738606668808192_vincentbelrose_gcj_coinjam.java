
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 public class GCJ_CoinJam {
 
    void log(long[] X){
        int L=X.length;
        for (int i=0;i<L;i++){
            logWln(X[i]+" ");
        }
        log("");
    }
 
    void log(double[] X){
        int L=X.length;
        for (int i=0;i<L;i++){
            logWln(X[i]+" ");
        }
        log("");
    }
 
    void log(int[] X){
        int L=X.length;
        for (int i=0;i<L;i++){
            logWln(X[i]+" ");
        }
        log("");
    }
    void log(Object[] X){
        int L=X.length;
        for (int i=0;i<L;i++){
            logWln(X[i]+" ");
        }
        log("");
    }
 
 
    void log(Object o){
        logWln(o+"\n");
    }
 
    void logWln(Object o){
        System.out.print(o);
        
 
    }
    void info(Object o){
        System.out.println(o);
        
    }
 
    boolean isPrime(long x){
        return new BigInteger(""+x).isProbablePrime(15);
    }
 
    long findSomeDivisor(long x){
        long y=2;
        while (x%y!=0)
            y++;
        return y;
    }
 
    String[] solve(int N,int J){
        long min=(1L<<(N-1))+1;
        long max=1L<<(N);
        long x=min;
        int cnt=0;
        String[] znx=new String[J];
        while (x<max && cnt<J){
            boolean ok=false;
 
 
            ok=true;
            String s=Long.toBinaryString(x);
            String res=s;
            loop:for (int u=2;u<=10;u++){
                long w=Long.parseLong(s,u);
                if (isPrime(w)){
                    ok=false;
                    break loop;
                } else {
                    res+=" "+findSomeDivisor(w);
                }
            }
            if (ok){
                log(res);
                znx[cnt]=res;
                cnt++;
            }
            x+=2;
        }
        return znx;
    }
 
    
    StringTokenizer st;
    BufferedReader in;
    BufferedWriter out;
 
 
 
    void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
 
 
        
        File inputFile=new File("C.in");
 
 
 
        
        PrintWriter outputFile= new PrintWriter("C.out","UTF-8");
 
 
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
            int N=sc.nextInt();
            int J=sc.nextInt();
            String[] ss=solve(N,J);
            System.out.println("Case #"+t+":");
            outputFile.println("Case #"+t+":");
            for (String x:ss) {
                System.out.println(x);
                outputFile.println(x);
            }
 
        }
 
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_CoinJam J=new GCJ_CoinJam();
 
        J.process();
 
 
    }
 
 
 
 
 
 
 }
