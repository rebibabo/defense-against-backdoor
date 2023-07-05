
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 public class GCJ_Pancakes {
 
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
 
 
    String solve(String s){
        int L=s.length();
        char last='+';
        int cnt=0;
        for (int i=0;i<L;i++){
            char  c=s.charAt(L-1-i);
            if (c!=last){
                last=c;
                cnt++;
            }
        }
        return ""+cnt;
    }
 
    
    StringTokenizer st;
    BufferedReader in;
    BufferedWriter out;
 
 
 
    void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
 
 
        
        File inputFile=new File("B.in");
 
 
 
        
        PrintWriter outputFile= new PrintWriter("B.out","UTF-8");
 
 
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
        
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
        
            String s=sc.next();
            String ss=solve(s);
            System.out.println("Case #"+t+": "+ss);
            outputFile.println("Case #"+t+": "+ss);
 
 
        }
 
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_Pancakes J=new GCJ_Pancakes();
 
        J.process();
 
 
    }
 
 
 
 
 
 
 }
