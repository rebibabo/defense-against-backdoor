import static java.lang.Math.*;
 
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 public class A {
    Scanner scan;
 
    public A(Scanner s) {
        scan = s;
    }
    
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    
    class P implements Comparable<P>{
        char name;
        int cnt;
        P(int n, int c){
            name = (char)('A'+n);
            cnt=c;
        }
        @Override
        public int compareTo(P arg0) {
            return arg0.cnt-cnt;
        }
    }
    public String solve() {
        int n = scan.nextInt();
        P[]A = new P[n];
        int tot=0;
        for(int i=0;i<n;i++){
            A[i]=new P(i,scan.nextInt());
            tot+=A[i].cnt;
        }
        StringBuffer sb = new StringBuffer();
        while(true) {
            Arrays.sort(A);
            if(A[0].cnt==0)break;
            if(tot%2==1) {
                A[0].cnt--;
                sb.append(A[0].name+" ");
                tot--;
            } else if(A[0].cnt==A[1].cnt){
                A[0].cnt--;
                A[1].cnt--;
                sb.append(A[0].name+""+A[1].name+" ");
            } else {
                A[0].cnt--;
                A[0].cnt--;
                sb.append(A[0].name+""+A[0].name+" ");  
            }
        }
        return sb.toString();
    }
    
    
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = "src/"+A.class.getName();
        String sampleName = cn+"-sample.in";
        String smallName = cn+"-small-attempt0.in";
        String largeName = cn+"-large.in";
        
        String name = smallName;
        
        String outName = name.substring(0, name.indexOf('.'))+".out";
        InputStream in = new BufferedInputStream(new FileInputStream(name));
        PrintStream out =null;
        out = new PrintStream(new File(outName));
        if(out==null) out = System.out;
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
