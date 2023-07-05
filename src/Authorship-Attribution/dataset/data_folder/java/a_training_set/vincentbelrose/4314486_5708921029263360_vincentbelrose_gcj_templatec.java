
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
 
 public class GCJ_templateC {
 
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
 
 
 
 
    ArrayList<String> solve(int J,int P,int S,int K){
        int[][] JP=new int[J][P];
        int[][] JS=new int[J][S];
        int[][] SP=new int[S][P];
        int[][][] JSP=new int[J][S][P];
        ArrayList<String> res=new ArrayList<String>();
        for (int s=0;s<S;s++)
            for (int p=0;p<P;p++)
                
            for (int j=0;j<J;j++)
                {
                    if (JSP[j][s][p]==0){
                        if (JP[j][p]<K && JS[j][s]<K && SP[s][p]<K){
                            res.add(new String((j+1)+" "+(p+1)+" "+(s+1)));
                            JP[j][p]++;
                            SP[s][p]++;
                            JS[j][s]++;
                            JSP[j][s][p]++;
                            
                        }
                    }
                }
        return res;
        
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
 
            int J=sc.nextInt();
            int P=sc.nextInt();
            int S=sc.nextInt();
            int K=sc.nextInt();
            ArrayList<String> rs=solve(J,P,S,K);
            
            System.out.println("Case #"+t+": "+rs.size());
            for (String x:rs)
                System.out.println(x);
            outputFile.println("Case #"+t+": "+rs.size());
            for (String x:rs)
                outputFile.println(x);
            
 
 
        }
 
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_templateC J=new GCJ_templateC();
 
        J.process();
 
 
    }
 
 
 
 
 
 
 }