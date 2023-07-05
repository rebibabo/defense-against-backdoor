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
    static class PerInt {
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
    public A(Scanner s) {
        scan = s;
    }
    
    int L;
    
    ArrayList<int[]>rev(int[]s){
        int sum = 0;
        for(int i=0;i<L;i++){
            sum +=s[i];
        }
        if(sum!=L)return new ArrayList<>();
        ArrayList<int[]>R = new ArrayList<>();
        int[]x = new int[L];
        int p=0;
        for(int i=1;i<=L;i++){
            int c =s[i-1];
            for(int j=0;j<c;j++)x[p++]=i;
        }
        PerInt P = new PerInt(L);
        R.add(x);
        while(P.hasNext()){
            int[] a =P.next();
            if(a==null)break;
            int[]y = new int[L];
            for(int i=0;i<L;i++){
                y[i]=x[a[i]];
            }
            R.add(y);
        }
        return R;
    }
    
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    
    String hss(int[]x){
        StringBuffer sb = new StringBuffer();
        for(int y:x)sb.append((char)('0'+y));
        return sb.toString();
    }
    
    String doit(String s){
        String r="";
        for(int i=1;i<=L;i++){
            int cnt =0;
            for(char c:s.toCharArray())if(c-'0'==i)cnt++;
            r+=cnt;
        }
        return r;
    }
    
    boolean validate(String from, String to){
        String s = from;
        HashSet<String>U = new HashSet<>();
        if(from.equals(to))return true;
        while(true){
            String ns = doit(s);
            if(ns.equals(to))return true;
            if(U.contains(ns))return false;
            s =ns;
            U.add(s);
        }
    }
    
    public String solve(){
        String s= scan.next();
        L = s.length();
        int res=0;
        for(int i=1;i<1000000;i++){
            String f = i+"";
            if(f.length()>L)continue;
            while(f.length()<L)f="0"+f;
            boolean ok = true;
            for(char c:f.toCharArray()){
                if(c-'0'>L)ok=false;
            }
            if(!ok)continue;
            if(validate(f, s)){
                res++;
            }
        }
        return res+"";
    }
    
    public String solve2() {
        String s= scan.next();
        L = s.length();
        int[]x = new int[L];
        for(int i=0;i<L;i++)x[i]=s.charAt(i)-'0';
        LinkedList<int[]>Q = new LinkedList<>();
        int res =0;
        Q.add(x);
        HashSet<String>U = new HashSet<>();
        while(!Q.isEmpty()){
            int[] p =Q.removeFirst();
            String hs = hss(p);
            if(!validate(hs, s)){
                System.err.println("error "+hs +" "+s);
            }
            if(U.contains(hs))continue;
            U.add(hs);
            res++;
            ArrayList<int[]>A = rev(p);
            Q.addAll(A);
        }
        return ""+res;
    }
    
    
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = "src/"+A.class.getName();
        String sampleName = cn+"-sample.in";
        String smallName = cn+"-small-attempt4.in";
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
