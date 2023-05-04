import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.HashMap;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 
 
 class GCJ_StandingOvation {
 
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
 
 
    
    StringTokenizer st;
    BufferedReader in;
    BufferedWriter out;
 
    int smax;
    String S;
 
    int solve(){
        int prev=0;
        int cur=0;
        int needed=0;
        for (int i=0;i<=smax;i++){
            int c=S.charAt(i)-'0';
            if (c>0) {
                if (prev<i){
                    needed+=i-prev;
                    prev=i;
                }
                prev+=c;
            }
        }
        return needed;
    }
 
 
 
    void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
 
 
        File inputFile=new File("A-small.in");
 
 
 
        PrintWriter outputFile= new PrintWriter("A-small.out","UTF-8");
 
 
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
            smax=sc.nextInt();
            S=sc.next();
            int ss=solve();
            System.out.println("Case #"+t+": "+ss);
            outputFile.println("Case #"+t+": "+ss);
 
 
        }
 
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_StandingOvation J=new GCJ_StandingOvation();
 
        J.process();
 
 
    }
 
 
 
 
 }