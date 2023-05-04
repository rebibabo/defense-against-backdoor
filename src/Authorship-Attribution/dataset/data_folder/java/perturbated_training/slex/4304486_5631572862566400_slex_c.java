import static java.lang.Math.*;
 
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 public class C {
    Scanner scan;
 
    public C(Scanner s) {
        scan = s;
    }
    
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    
    boolean validate(ArrayList<Integer>A){
        int n = A.size();
        for(int i=0;i<A.size();i++){
            int x = A.get(i);
            int f1=A.get((i+1)%n);
            int f2 =A.get((i+n-1)%n);
            if(f1!=F[x]&&f2!=F[x])return false;
        }
        return true;
    }
    
    int[]F;
    
    ArrayList<Integer>rev(ArrayList<Integer>A){
        ArrayList<Integer>B = new ArrayList<>();
        for(int i=A.size()-1;i>=0;i--)B.add(A.get(i));
        return B;
    }
    ArrayList<Integer>doit(ArrayList<Integer>onst,int st, boolean[]OU) {
        boolean ok = true;
        ArrayList< Integer>A = new ArrayList<>();
        if(onst!=null)
            A.addAll(onst);
        A.add(st);
        boolean[] U = OU.clone();
        U[st]=true;
        while(true){
            int f = F[A.get(A.size()-1)];
            if(U[f]==false){
                A.add(f); U[f]=true;
            } else {
                if(A.get(0)==f) {
                    
                }else if(A.size()>1 && A.get(A.size()-2)==f){
                    ArrayList<Integer>best = A;
                    for(int i=0;i<F.length;i++) {
                        if(U[i]==false) {
                            ArrayList<Integer>B=doit(A,i, U);
                            if(B.size()>best.size()){
                                best =B;
                            }
                            B=doit(rev(A),i, U);
                            if(B.size()>best.size()){
                                best =B;
                            }
                        }
                    }
                    A = best;
                } else {
                    ok = false;
                }
                break;
            }
        }
        if(ok) return A;
        else return new ArrayList<>();
    }
    
    
    int solveBrut(){
        int n = scan.nextInt();
        F = new int[n];
        int res =0;
        for(int i=0;i<n;i++)F[i]=scan.nextInt()-1;
        for(int m=0;m<(1<<n);m++){
            ArrayList<Integer>A = new ArrayList<>();
            for(int i=0;i<n;i++){
                if((m&(1<<i))!=0)A.add(i);
            }
            PerInt pi = new PerInt(A.size());
            do{
                ArrayList<Integer>OR = new ArrayList<>();
                for(int i=0;i<A.size();i++)OR.add(A.get(pi.St[i]));
                if(validate(OR))res = max(res,A.size());
                pi.next();
            } while(pi.St!=null);
        }
        return res;
    }
    
    class PerInt {
        int[] St;
        public PerInt( int n){
            St = new int[n];
            for( int i = 0; i < St.length; i++ ) St[i] = i;
        }
        public int[] next(){        
            int j = St.length - 1;
            while( j > 0 && St[j] < St[j-1] )j--;
            if( j > 0 ){
                int k = j - 1;
                int x = St[k];
                j = St.length - 1;
                while( St[j] < x) j--;
                St[k] = St[j];
                St[j] = x;
                for( j = 0; j < (St.length-k)/2;j++ ){
                    x = St[k+1+j];
                    St[k+1+j] = St[St.length-1 -j];
                    St[St.length-j-1] = x;
                }
            }
            else St = null;
            return St;  
        }
        public boolean hasNext(){
            return St != null;
        }
    }
    public String solve() {
        int n = scan.nextInt();
        F = new int[n];
        for(int i=0;i<n;i++)F[i]=scan.nextInt()-1;
        ArrayList< Integer>A = new ArrayList<>();
        int res =0;
        for(int st=0;st<n;st++) {
            A = doit(null,st, new boolean[n]);
            if(!validate(A))throw new RuntimeException("ERRORRR");
            res = max(res,A.size());
        }
        return ""+res;
    }
    
    
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn ="src/"+ C.class.getName();
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
            String res = new C(scan).solveBrut()+"";
            out.printf("Case #%d: %s\n",c, res);
            if(out!=System.out)System.err.println(c + " done");
        }
        if(out!=System.out)System.err.println("All done");
        
    }
 }
