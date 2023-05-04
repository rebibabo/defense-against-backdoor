import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.Locale;
 import java.util.PriorityQueue;
 import java.util.Random;
 import java.util.Scanner;
 
 
 public class B2 {
 
    static boolean verb=true;
    static void log(Object X){if (verb) System.err.println(X);}
    static void log(Object[] X){if (verb) {for (Object U:X) System.err.print(U+" ");System.err.println("");}}
    static void log(int[] X){if (verb) {for (int U:X) System.err.print(U+" ");System.err.println("");}}
    static void logWln(Object X){if (verb) System.err.print(X);}
    static void info(Object o){ System.out.println(o);}
    static void output(Object o){outputWln(""+o+"\n");  }
    static void outputWln(Object o){try {out.write(""+ o);} catch (Exception e) {}}
 
    static String[] options={"R","Y","B","RY","YB","RB"};
    static String[] name={"R","Y","B","O","G","V"};
    static int RED=0,YELLOW=1,BLUE=2,ORANGE=3,GREEN=4,VIOLET=5;
 
 
    static int CX=6;
 
    static HashMap<String,String[]> hm;
 
    static String[] solve(String left,String right,int[] colors){
        String s=left+" "+right;
        for (int i=0;i<CX;i++)
            s+=" "+colors[i];
        String[] Z=hm.get(s);
        if (Z!=null)
            return Z;
 
        
        
        
        
        String nleft;
        String nright;
        boolean zero=true;
        loop:for (int i=0;i<CX;i++)
            if (colors[i]>0){
                zero=false;
                break loop;
            }
        if (zero)
            return new String[]{"",""};
 
        if (left.equals("") && right.equals("")){
            
            for (int i=0;i<CX;i++){
                if (colors[i]>0){
                    int[] tmp=new int[CX];
                    System.arraycopy(colors,0,tmp,0,CX);
                    tmp[i]--;
                    String[] res=solve(options[i],options[i],tmp);
                    if (res!=null){
                        String[] bob=new String[2];
                        bob[0]=res[0]+name[i];
                        bob[1]=res[1];
                        hm.put(s,bob);
                        return bob;
                    }
 
                }
            }
            
            return null;
        }
        
        for (int i=0;i<CX;i++){
            if (colors[i]>0){
                
                int[] tmp=new int[CX];
                System.arraycopy(colors,0,tmp,0,CX);
                tmp[i]--;
                
                
                if (left.equals("")){
                    
                    nleft=options[i];
                    String[] res=solve(nleft,right,tmp);
                    if (res!=null){
                        String[] bob=new String[2];
                        bob[0]=res[0]+name[i];
                        bob[1]=res[1];
                        hm.put(s,bob);
                        return bob;
                    }
 
                } else {
                    
                    nleft=options[i];
                    boolean compat=true;
                    loop:for (int u=0;u<nleft.length();u++){
                        for (int v=0;v<left.length();v++){
                            if (nleft.charAt(u)==left.charAt(v)){
                                compat=false;
                                break loop;
                            }
                        }
                    }
                    if (compat){
                        String[] res=solve(nleft,right,tmp);
                        if (res!=null){
                            String[] bob=new String[2];
                            bob[0]=res[0]+name[i];
                            bob[1]=res[1];
                            hm.put(s,bob);
                            return bob;
                        }
                    } else {
                        
                    }
 
 
                }
 
                
 
 
 
                if (right.equals("")){
                    nright=options[i];
                    String[] res=solve(left,nright,tmp);
                    if (res!=null){
                        String[] bob=new String[2];
                        bob[0]=res[0];
                        bob[1]=name[i]+res[1];
                        hm.put(s,bob);
                        return bob;
                    }
 
                } else {
                    nright=options[i];
                    boolean compat=true;
                    loop:for (int u=0;u<nright.length();u++){
                        for (int v=0;v<right.length();v++){
                            if (nright.charAt(u)==right.charAt(v)){
                                compat=false;
                                break loop;
                            }
                        }
                    }
                    if (compat){
                        String[] res=solve(left,nright,tmp);
                        if (res!=null){
                            String[] bob=new String[2];
                            bob[0]=res[0];
                            bob[1]=name[i]+res[1];
                            hm.put(s,bob);
                            return bob;
                        }
                    }
 
 
                }
 
 
            }
        }
        return null;
 
    }
 
    static class Composite implements Comparable<Composite>{
        int num;
        int col;
 
        public int compareTo(Composite X){
            if (num!=X.num)
                return -num+X.num;
            return col-X.col;
        }
 
        public Composite(int num, int col) {
            this.num = num;
            this.col = col;
        }
 
        public String toString(){
            return "<"+num+" "+name[col]+">";
        }
 
    }
    
    static boolean check(String s){
        for (int i=0;i<N;i++){
            char a=s.charAt(i);
            char b=s.charAt((i+1)%N);
            if (a==b)
                return false;
        }
        return true;
    }
 
    static String solveSmall(int[] colors){
        if (N==1){
            for (int i=0;i<CX;i++)
                if (colors[i]!=0)
                    return name[i];
            
            
        }
        
        int R=colors[RED];
        int Y=colors[YELLOW];
        int B=colors[BLUE];
        int[] ar=new int[]{R,Y,B};
        Arrays.sort(ar);
        
        if (ar[2]>ar[1]+ar[0]){
            
            return "IMPOSSIBLE";
        }
        boolean limit=false;
        if (ar[2]==ar[1]+ar[0])
            limit=true;
        int max=ar[2];
 
 
        int[] ref=new int[]{RED,YELLOW,BLUE};
        
        int last=-1;
        String s="";
        int first=-1;
        for (int i=0;i<N;i++){
            Composite[] arr=new Composite[3];
            for (int u=0;u<3;u++){
                arr[u]=new Composite(colors[u],u);
            }
            Arrays.sort(arr);
            
            int mx=-1;
            ArrayList<Integer> opts=new ArrayList<Integer>();
            loop:for (int id=0;id<3;id++){
 
 
                int u=arr[id].col;
                if (first==-1){
                    if (u!=last){
                        
                        colors[u]--;
                        s+=name[u];
                        last=u;
                        first=u;
                        break loop;
                    }
                } else {
                    if (u!=last){
                        if (mx==-1)  {
                            mx=arr[id].num;
                            opts.add(u);
                        }
                        else {
                        if (mx==arr[id].num)
                        
                            opts.add(u);
                        }
                    }
                    
                }
            }
            if (opts.size()>0){
                if (opts.size()==1){
                    int u=opts.get(0);
                    colors[u]--;
                    s+=name[u];
                    last=u;
                } else {
                    boolean found=false;
                    loop2:for (int u:opts){
                        if (u==first){
                            colors[u]--;
                            s+=name[u];
                            last=u;
                            found=true;
                            break loop2;
                        }
                    }
                    if (!found){
                        int u=opts.get(0);
                        colors[u]--;
                        s+=name[u];
                        last=u;
                    }
                    
                    
                }
            }
            
            
        }
        log(colors);
        log(s);
        if (!check(s)){
            log("Problem");
        }
        if (s.charAt(0)==s.charAt(N-1)){
            log("============Problem");
            log(1/0);
        }
        if (s.length()!=N) {
            log("============Problem");
            log(1/0);
        }
        return s;
    }
 
 
    
    static BufferedWriter out;
 
    static int N;
 
 
 
 
    static void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
        File inputFile=new File("B.in");
        PrintWriter outputFile= new PrintWriter("B.out","UTF-8");
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        
        
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
 
            N=sc.nextInt();
            int[] colors=new int[CX];
            
            int a;
            a=sc.nextInt();
            
            colors[RED]=a;
            a=sc.nextInt();
            colors[ORANGE]=a;
            a=sc.nextInt();
            colors[YELLOW]=a;
            a=sc.nextInt();
            colors[GREEN]=a;
            a=sc.nextInt();
            colors[BLUE]=a;
            a=sc.nextInt();
            colors[VIOLET]=a;
            log("==========");
            log(colors);
 
 
            
            String ss=solveSmall(colors);
            log(ss);
            System.out.println("Case #"+t+": "+ss);
            outputFile.println("Case #"+t+": "+ss);
 
    
        }
    
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
 
 
        process();
 
 
    }
 
 
 
 
 
 
 }
