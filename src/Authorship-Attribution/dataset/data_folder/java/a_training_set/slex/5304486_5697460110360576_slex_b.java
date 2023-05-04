import static java.lang.Math.*;
 
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 public class B {
    Scanner scan;
 
    public B(Scanner s) {
        scan = s;
    }
    
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    
    int N,P;
    int[]R;
    int[][]Q;int[][]mQ;int[][]MQ;
    int[]curr;
    boolean test(int k){
        for(int i=0;i<N;i++){
            if(curr[i]>=P)return false;
            if(mQ[i][curr[i]]>k)return false;
            while(curr[i]<P && MQ[i][curr[i]]<k)curr[i]++;
            if(!(curr[i]<P && MQ[i][curr[i]]>=k &&mQ[i][curr[i]]<=k))return false;
        }
        return true;
    }
    boolean finished(){
        for(int i=0;i<N;i++){
            if(curr[i]>=P)return true;
        }
        return false;
    }
    
    
    boolean can(int k, int ing, int pack){
        long need = 1L*k*R[ing];
        int have = Q[ing][pack];
        if(10*have <9L * need)return false;
        if(10*have >11L * need)return false;
        return true;
    }
    
    
    int[][]mem;
    int doit(int m1, int m2, boolean[][]M){
        if(mem[m1][m2]!=-1)return mem[m1][m2];
        int res =0;
        for(int p=0;p<P;p++)for(int q=0;q<P;q++){
            if(!M[p][q])continue;
            if(((1<<p)&m1)!=0)continue;
            if(((1<<q)&m2)!=0)continue;
            int r = 1 + doit(m1|(1<<p), m2|(1<<q), M);
            res = max(res,r);
        }
        
        return mem[m1][m2]=res;
    }
    
    int solvebrut(){
        boolean[][]M = new boolean[P][P];
        if(N==1){
            int res =0;
            for(int p=0;p<P;p++){
                for(int k=1;k<20000000;k++){
                    if(can(k,0,p)){
                        res++;break;
                    }
                }
            }
            return res;
        }else{
            for(int p=0;p<P;p++){
                for(int k=1;k<20000000;k++){
                    if(can(k,0,p)){
                        for(int q = 0;q<P;q++){
                            if(can(k,1,q)){
                                M[p][q]=true;
                            
                            }
                        }
                    }
                }
            }
            mem = new int[1<<9][1<<9];
            for(int[]m:mem)Arrays.fill(m,-1);
            return doit(0,0,M);
        }
    }
    
    public String solve() {
        N = scan.nextInt();P=scan.nextInt();
        R = new int[N];
        for(int i=0;i<N;i++)R[i]=scan.nextInt();
        Q = new int[N][P];mQ = new int[N][P];MQ = new int[N][P];
        for(int i=0;i<N;i++){
            for(int j=0;j<P;j++){
                Q[i][j]=scan.nextInt();
                
            }
            Arrays.sort(Q[i]);
            for(int j=0;j<P;j++){
                mQ[i][j]=(int)ceil(Q[i][j]/(1.1*R[i]));
                MQ[i][j]=(int)floor(Q[i][j]/(0.9*R[i]));
                
            }
        }
        int res = solvebrut();
        return ""+res;
    }
    
    
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = B.class.getName();
        String sampleName = cn+"-sample.in";
        String smallName = cn+"-small-attempt3.in";
        String largeName = cn+"-large.in";
        String name = smallName;
        String outName = name.substring(0, name.indexOf('.'))+".out";
        InputStream in = new BufferedInputStream(new FileInputStream(name));
        PrintStream out = new PrintStream(new File(outName));
        
        Scanner scan = new Scanner(in);
        
        int N = Integer.parseInt(scan.nextLine());
        for(int c=1;c<=N;c++) {
            String res = new B(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out!=System.out)System.err.println(c + " done");
        }
        if(out!=System.out)System.err.println("All done");
 
    }
 }
