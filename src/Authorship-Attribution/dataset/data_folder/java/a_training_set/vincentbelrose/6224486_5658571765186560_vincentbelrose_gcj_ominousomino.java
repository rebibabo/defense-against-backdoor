import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.Locale;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 
 
 class GCJ_OminousOmino {
 
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
    
    String RIC="RICHARD";
    String GAB="GABRIEL";
    
    String solve(int X,int R,int C){
        if (X==1)
            return GAB;
        if ((R*C)%X!=0)
            return RIC;
        if (X==2){
                return GAB;
        }
        if (X==3){
            if (R*C==3)
                return RIC;
            else
                return GAB;
        }
        if (X==4){
            if (R*C==4)
                return RIC;
            if (R*C==8)
                return RIC;
            return GAB;
        }
        
        return "ERROR";
    }
    
    void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
 
 
        
        File inputFile=new File("D-small.in");
        
        
 
        
        PrintWriter outputFile= new PrintWriter("D-small.out","UTF-8");
 
 
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        long startTime,endTime;
        
        
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
            
            int X=sc.nextInt();
            int R=sc.nextInt();
            int C=sc.nextInt();
            String ss=solve(X,R,C);
            System.out.println("Case #"+t+": "+ss);
            outputFile.println("Case #"+t+": "+ss);
 
 
        }
 
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_OminousOmino J=new GCJ_OminousOmino();
 
        J.process();
 
 
    }
 
 
 
 
 }