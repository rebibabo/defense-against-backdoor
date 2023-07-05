
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Locale;
 import java.util.PriorityQueue;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 public class GCJ_templateA {
 
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
 
     class Composite implements Comparable<Composite>{
        int v;
        int idx;
        
        public int compareTo(Composite X){
            if (v!=X.v)
                return v-X.v;
            return idx-X.idx;
        }
 
        public Composite(int v, int idx) {
            super();
            this.v = v;
            this.idx = idx;
        }
        
        
        
     }
    
    String solve(int N,int[] P){
        PriorityQueue<Composite> pq=new PriorityQueue<Composite>();
        Composite[] Q=new Composite[N];
        int sum=0;
        for (int i=0;i<N;i++){
            Q[i]=new Composite(-P[i],i);
            sum+=P[i];
            pq.add(Q[i]);
        }
        ArrayList<String> res=new ArrayList<String>();
        while (pq.size()>0){
            Composite A=pq.poll();
            String nameA=""+(char)('A'+A.idx);
            String nameB="";
            if (pq.size()>0){
                Composite B=pq.poll();
                nameB+=(char)('A'+B.idx);
                B.v++;
                if (B.v!=0)
                pq.add(B);
            }
            A.v++;
            if (A.v!=0)
                pq.add(A);
            res.add(nameA+nameB);
        }
        int L=res.size();
        String rs="";
        for (int i=L-1;i>=0;i--){
            rs+=res.get(i)+" ";
        }
        return rs;
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
            int[] P=new int[N];
            for (int i=0;i<N;i++){
                P[i]=sc.nextInt();
            }
            String ss=""+solve(N,P);
            System.out.println("Case #"+t+": "+ss);
            outputFile.println("Case #"+t+": "+ss);
 
 
        }
 
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_templateA J=new GCJ_templateA();
 
        J.process();
 
 
    }
 
 
 
 
 
 
 }