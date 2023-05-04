
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
 
 
 
 public class GCJ_templateB {
 
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
 
 
    ArrayList<Integer> getDiv(int M){
        ArrayList<Integer> p=new ArrayList<Integer>();
        int X=M;
        while (X>1){
            for (int x=2;x<=X;x++){
                while (X%x==0){
                    p.add(x);
                    X/=x;
                }
            }
        }
        return p;
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
      
      
      
      int getPath(int[][] mat,int B){
          int[] num=new int[B];
          num[0]=1;
          for (int i=1;i<B;i++)
              for (int j=0;j<i;j++){
                  if (mat[j][i]==1){
                      num[i]+=num[j];
                  }
              }
          return num[B-1];
      }
      
     String solveBourrin(int B,int M){
         int max=(B*B-B)/2;
         int LX=1<<max;
         log("max:"+max);
         for (int u=0;u<LX;u++){
             int i=0;
             int j=1;
             int c=0;
             int[][] map=new int[B][B];
             while (c<max){
                 int msk=1<<c;
                 if ((u&msk)!=0){
                     map[i][j]=1;
                 }
                 j++;
                 if (j==B){
                     i++;
                     j=i+1;
                 }
                 c++;
             }
             int x=getPath(map,B);
             if (x==M){
                 String rs="POSSIBLE\n";
                 for (int e=0;e<B;e++) {
                     for (int f=0;f<B;f++){
                         rs+=map[e][f];
                     }
                     rs+="\n";
                 }
                 return rs;
             }
         }
         return "IMPOSSIBLE\n";
     }
 
    String solveOther(int B,int M){
        log("B:"+B+" M:"+M);
        int[][] mat=new int[B][B];
        int[] num=new int[B];
        int[] mx=new int[B];
        num[0]=1;
        mx[0]=1;
        for (int i=1;i<B;i++){
            
            PriorityQueue<Composite> pq=new PriorityQueue<Composite>();
            for (int j=0;j<i;j++){
                pq.add(new Composite(-num[j],j));
            }
            for (Composite X:pq){
                int n=-X.v;
                int j=X.idx;
                mx[i]+=n;
                if (num[i]+n<=M){
                    num[i]+=n;
                    mat[j][i]=1;
                    
                }
                
            }
            
        }
        log("FInal: "+num[B-1]+" max:"+mx[B-1]);
        if (num[B-1]!=M){
            log("==== Think impossible+" +B+" "+M);
            return "IMPOSSIBLE\n";
        } else 
            log("!!! Think possible "+B+" "+M);
        
        String rs="POSSIBLE\n";
        for (int i=0;i<B;i++){
            for (int j=0;j<B;j++){
                rs+=mat[i][j];
            }
            rs+="\n";
        }
        return rs;
    }
    
    String solveKO(int B,int M){
        ArrayList<Integer> p=new ArrayList<Integer>();
        int X=M;
        while (X>1){
            for (int x=2;x<=X;x++){
                while (X%x==0){
                    p.add(x);
                    X/=x;
                }
            }
        }
        if (p.size()==0)
            p.add(1);
        long u=1;
        for (int x:p){
            u*=x;
        }
        u++;
        if (u>B)
            return "IMPOSSIBLE\n";
        int[][] mat=new int[B][B];
        int cur=0;
        
        for (int x:p){
            for (int y=cur;y<cur+x;y++)
                mat[y][y+1]=1;
            for (int y=cur+1;y<=cur+x;y++){
                mat[cur][y]=1;
            }
            cur+=x;
        }
        String rs="POSSIBLE\n";
        for (int i=0;i<B;i++){
            for (int j=0;j<B;j++){
                rs+=mat[i][j];
            }
            rs+="\n";
        }
        return rs;
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
 
            int B=sc.nextInt();
            int M=sc.nextInt();
            
            
            
            String ss=""+solveBourrin(B,M);
            
            System.out.print("Case #"+t+": "+ss);
            outputFile.print("Case #"+t+": "+ss);
            
 
 
        }
 
    
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_templateB J=new GCJ_templateB();
 
        J.process();
 
 
    }
 
 
 
 
 
 
 }