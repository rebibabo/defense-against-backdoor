
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
 
 public class GCJ_Fractiles {
 
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
    
    String solve(int K,int C,int S){
        String res="";
        if (K==S){
            for (int i=0;i<S;i++)
                res+=(i+1)+" ";
        }
        return res;
    }
 
 
    
    StringTokenizer st;
    BufferedReader in;
    BufferedWriter out;
 
 
 
    void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
 
 
        
        File inputFile=new File("D.in");
 
 
 
        
        PrintWriter outputFile= new PrintWriter("D.out","UTF-8");
 
 
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
            int K=sc.nextInt();
            int C=sc.nextInt();
            int S=sc.nextInt();
            String s=solve(K,C,S);
            System.out.println("Case #"+t+": "+s);
            outputFile.println("Case #"+t+": "+s);
 
        }
 
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_Fractiles J=new GCJ_Fractiles();
 
        J.process();
 
 
    }
 
 
 
 
 
 
 }
