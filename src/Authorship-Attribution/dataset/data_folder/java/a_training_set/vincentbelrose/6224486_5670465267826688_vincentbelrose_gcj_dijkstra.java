import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Locale;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 
 
 class GCJ_Dijkstra {
 
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
 
    int I=2;
    int J=3;
    int K=4;
    String[] name={"ZERO","1","i","j","k"};
 
    void solve(){
        
    }
    
    int map(int x,int y){
        return map[x-1][y-1];
    }
    
    int[][] map={{1,I,J,K},{I,-1,K,-J},{J,-K,-1,I},{K,J,-I,-1}};
    
    String YES="YES";
    String NO="NO";
    
    
    String solveBourrin(String s,long X,int L){
    
        int x=s.charAt(0)-'i'+I;
        int mult=1;
        int step=0;
        int sign=1;
        for (int i=0;i<L*X;i++){
            int y=(s.charAt(i%L)-'i')+I;
            mult=map(mult,y);
            if (mult<0){
                mult*=-1;
                sign*=-1;
            }
            if (step==0 && mult==I){
                step++;
                mult=1;
            } else {
                if (step==1 && mult==J){
                    step++;
                    mult=1;
                }
            }
        }
        if (step==2 && mult==K && sign==1)
            return YES;
        else
            return NO;
        
    }
    
    void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
 
 
        
        File inputFile=new File("C-small.in");
        
        
 
        
        PrintWriter outputFile= new PrintWriter("C-small.out","UTF-8");
 
 
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
            int L=sc.nextInt();
            Long X=sc.nextLong();
            String s=sc.next();
            String ss=solveBourrin(s,X,L);
            
            System.out.println("Case #"+t+": "+ss);
            outputFile.println("Case #"+t+": "+ss);
 
 
        }
 
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_Dijkstra J=new GCJ_Dijkstra();
 
        J.process();
 
 
    }
 
 
 
 
 }