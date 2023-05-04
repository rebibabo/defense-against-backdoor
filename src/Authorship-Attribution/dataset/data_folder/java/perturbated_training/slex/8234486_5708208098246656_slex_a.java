import java.io.BufferedInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 
 
 import static java.lang.Math.*; 
 import static java.util.Arrays.*;
 import static java.lang.Character.*;  
 
 
 public class A {
    
    Scanner scan;
    public A(Scanner s) {
        this.scan = s;
    }
 char[][]M;
    boolean check(int i, int j, char d) {
        int dx=0, dy=0;
        switch (d) {
        case '^': dx=0;dy=-1;break;
        case 'v': dx=0;dy=1;break;
        case '<': dx=-1;dy=0;break;
        case '>': dx=1;dy=0;break;
        }
        do {
            i+=dy;j+=dx;
            if(i>=0 &&j>=0 &&i<M.length&&j<M[0].length) {
                if(M[i][j]!='.')return true;
            }else break;
        } while(true);
        
        return false;
    }
    
    public String solve() {
        int n = scan.nextInt();int m = scan.nextInt();
        M = new char[n][];
        for(int i=0;i<n;i++) {
            M[i]=scan.next().toCharArray();
        }
        int res =0;
        for(int i=0;i<n;i++)for(int j=0;j<m;j++){
            if(M[i][j]=='.')continue;
            if(check(i,j,M[i][j]))continue;
            boolean ok = false;
            for(char c:"<>^v".toCharArray())if(check(i,j,c)){res++;ok = true;break;}
            if(!ok)return "IMPOSSIBLE";
        }
        return ""+res;
    }
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = A.class.getName();
        String sampleName = cn+"-sample.in";
        String smallName = cn+"-small-attempt0.in";
        String largeName = cn+"-large.in";
        String name = smallName;
        String outName = name.substring(0, name.indexOf('.'))+".out";
        InputStream in = new BufferedInputStream(new FileInputStream(name));
        PrintStream out = new PrintStream(new File(outName));
        
        Scanner scan = new Scanner(in);
        
        int N = Integer.parseInt(scan.nextLine());
        for(int c=1;c<=N;c++) {
            String res = new A(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out!=System.out)System.err.println(c + " done");
        }
        if(out!=System.out)System.err.println("All done");
        
    }
 
 }
 
 
