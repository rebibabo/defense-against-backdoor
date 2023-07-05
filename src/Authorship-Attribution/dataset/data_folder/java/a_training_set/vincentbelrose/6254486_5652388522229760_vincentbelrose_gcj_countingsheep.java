
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 public class GCJ_CountingSheep {
 
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
 
 
    String solve(long N){
        
        if (N==0)
            return "INSOMNIA";
        int sum=0;
        int[] cnt=new int[10];
        long w=N;
        while (sum<10){
            String s=""+w;
            int L=s.length();
            for (int i=0;i<L;i++){
                int x=s.charAt(i)-'0';
                if (cnt[x]==0){
                    sum++;
                    cnt[x]=1;
                }
            }
            
            w+=N;
        }
        
        return ""+(w-N);
    }
 
    
    StringTokenizer st;
    BufferedReader in;
    BufferedWriter out;
 
 
 
    void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
 
 
        
        File inputFile=new File("A.in");
 
 
 
        
        PrintWriter outputFile= new PrintWriter("A.out","UTF-8");
 
 
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
        
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
        
            int N=sc.nextInt();
            String s=solve(N);
            System.out.println("Case #"+t+": "+s);
            outputFile.println("Case #"+t+": "+s);
 
 
        }
 
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_CountingSheep J=new GCJ_CountingSheep();
 
        J.process();
 
 
    }
 
 
 
 
 
 
 }
