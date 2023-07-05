import java.io.BufferedInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 
 
 import static java.lang.Math.*; 
 import static java.util.Arrays.*;
 import static java.lang.Integer.*;
 import static java.lang.Character.*;  
 
 
 public class C {
    
    Scanner scan;
    int[][]M = {
            {},
            {0,1,2,3,4},
            {0,2,-1,4,-3},
            {0,3,-4,-1,2},
            {0,4,3,-2,-1},
            
    };
    public C(Scanner s) {
        this.scan = s;
    }
    int next(int out, char c){
        int d = c - 'i'+2;
        int sig = out>0?1:-1;
        
        int ret =M[abs(out)][d]*sig;
        
        return ret;
    }
    
    int prev(int out, char c){
        int d = c - 'i'+2;
        int sig = out>0?1:-1;
        return M[d][abs(out)]*sig;
    }
    
    int redAll(String s) {
        int curr = 1;
        for(int i=0;i<s.length();i++)curr = next(curr, s.charAt(i));
        return curr;
    }
    public String solve() {
        int L = scan.nextInt();
        long X = scan.nextLong();
        String s = scan.next();
        L = s.length();
        int out = redAll(s);
        if(out == 1) return "NO";
        if(out == -1 && X%2==0)return "NO";
        if(abs(out)!=1 && (X%2==1 || X%4==0))return "NO";
        int s1 = -1;long s2 = -1;
        int curr = 1;
        for(int i=0;i<X*L && i<10*L;i++){
            char c = s.charAt(i%L);
            curr = next(curr,c);
            if(curr == 2){
                s1 = i+1;break;
            }
        }
        curr=1;
        for(int i=0;i<X*L && i<10*L;i++){
            long p = X*L-i-1;
            char c = s.charAt((int)(p%L));
            curr = prev(curr,c);
            if(curr == 4){
                s2 = p;break;
            }
        }
        
        if(s1==-1||s2==-1)return "NO";
        if(s2<=s1)return "NO";
        
        String ss = "";
        for(int i=0;i<X;i++)ss+=s;
        String sub1 = ss.substring(0,s1);
        String sub2 = ss.substring(s1,(int)s2);
        String sub3 = ss.substring((int)s2);
        int r1 = redAll(sub1);int r2 = redAll(sub2);int r3 =redAll(sub3);
        if(r1!=2 || r2!=3 || r3!=4){
            System.err.println(s);
            System.err.println(X);
            System.err.println(redAll(ss));
        }
 
 
 
 
        return "YES";
    }
    int intLine(){
        return parseInt(scan.nextLine());
    }
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = C.class.getName();
        String sampleName = cn+"-sample.in";
        String smallName = cn+"-small-attempt1.in";
        String largeName = cn+"-large.in";
        String name = smallName;
        String outName = name.substring(0, name.indexOf('.'))+".out";
        InputStream in = new BufferedInputStream(new FileInputStream(name));
        PrintStream out = new PrintStream(new File(outName));
        
        Scanner scan = new Scanner(in);
        
        int N = parseInt(scan.nextLine());
        for(int c=1;c<=N;c++) {
            String res = new C(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            System.err.println(c + " done");
        }
        
        System.err.println("All done");
        
    }
 
 }
 
 
