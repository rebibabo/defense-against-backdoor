import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 
 
 class GCJ_Battleship_v2 {
 
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
    int R,C,W;
 
    String toString(int x) {
        String res="";
        for (int i=0;i<C;i++){
            if ((x & (1<<i))==(1<<i))
                res+='1';
            else
                res+='0';
        }
        return res;
    }
    
    HashMap<String,Integer> hm;
    
 
    int score(int i,int hits,int missed){
    
        
        String S=i+" "+hits+" "+missed;
        Integer Z=hm.get(S);
        if (Z!=null)
            return Z;
        
        
 
        if (Integer.bitCount(hits)==W)
            return Integer.bitCount(hits | missed);
 
        
        int res=Integer.MAX_VALUE;
        for (int x=0;x<C;x++){
            int msk=1<<x;
            if ((msk & (hits|missed))==0){
                
                int tmp=0;
                for (int j=0;j+(W-1)<C;j++){
                    
                    int target=0;
 
                    for (int u=0;u<W;u++){
                        target|=1<<(u+j);
                    }
 
                    boolean a=((target & missed)==0);
                    boolean b=((target & hits)==hits);
                    if (a && b){ 
                        int nhits=hits;
                        int nmissed=missed;
                        if ((target & msk)==msk){
                            nhits|=msk;
                        }
                        else
                            nmissed|=msk;
                        tmp=Math.max(tmp, score(j,nhits,nmissed));
                
                    }
                }
                res=Math.min(res,tmp);
            }
 
        }
    
        hm.put(S,res);
        return res;
    }
 
    int score(){
        int m=1;
 
        for (int i=0;i+(W-1)<C;i++){
            m=Math.max(m,score(i,0,0));
        }
        return m;
    }
 
    String solve(){
        hm=new HashMap<String,Integer>();
        return ""+score();
    }
 
    
    
 
    void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
    
 
        File inputFile=new File("A-small-attempt0.in");
        
        
 
        
        
        
 
 
 
        PrintWriter outputFile= new PrintWriter("A-small-attempt0.out","UTF-8");
        
        
        
        
        
 
 
 
 
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
 
            R=sc.nextInt();
            C=sc.nextInt();
            W=sc.nextInt();
            String ss=solve();
            log(ss);
 
            System.out.println("Case #"+t+": "+ss);
            outputFile.println("Case #"+t+": "+ss);
 
 
        }
 
 
 
        
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_Battleship_v2 J=new GCJ_Battleship_v2();
 
        J.process();
 
 
    }
 
 
 
 
 }