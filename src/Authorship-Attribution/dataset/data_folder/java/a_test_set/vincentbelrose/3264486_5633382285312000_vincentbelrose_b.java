import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 
 
 public class B {
 
    static boolean verb=true;
    static void log(Object X){if (verb) System.err.println(X);}
    static void log(Object[] X){if (verb) {for (Object U:X) System.err.print(U+" ");System.err.println("");}}
    static void log(int[] X){if (verb) {for (int U:X) System.err.print(U+" ");System.err.println("");}}
    static void logWln(Object X){if (verb) System.err.print(X);}
    static void info(Object o){ System.out.println(o);}
    static void output(Object o){outputWln(""+o+"\n");  }
    static void outputWln(Object o){try {out.write(""+ o);} catch (Exception e) {}}
 
    static boolean ok(String s){
        for (int i=0;i+1<s.length();i++)
            if (s.charAt(i)>s.charAt(i+1))  
                return false;
        return true;
    }
 
    static String solveBourrin(long n){
        for (long x=n;x>0;x--){
            String s=""+x;
            if (ok(s))
                return s;
        }
        return "";
    }
 
    static void test(){
        log("testing");
        for (int i=1;i<1000000;i++){
            String a=solveBourrin(i);
            String b=solve(i);
            if (!a.equals(b)){
                log("Error");
                log("i:"+i);
                log(a+" "+b);
                return;
            }
        }
        log("done");
    }
 
    static String solve(long n){
        String s=""+n;
        int L=s.length();
        if (L==1)
            return s;
 
        if (ok(s))
            return s;
 
        
 
 
 
 
 
        char[] mem=new char[L];
        
        boolean flag=false;
        int i=0;
        while (i<L){
            char c=s.charAt(i);
            if (flag)
                mem[i]='9';
            else {
                if (i+1<L){
                    char d=s.charAt(i+1);
                    if (c<=d)
                        mem[i]=c;
                    else {
                        flag=true;
                        
                        while (i-1>=0 && mem[i-1]==c)
                            i--;
                        mem[i]=(char)(c-1);
                    }
                } 
            }
            i++;
        }
 
        String res="";
        i=0;
        while (mem[i]=='0')
            i++;
        while (i<L) 
            res+=mem[i++];
        return res;
 
    }
 
    
    static BufferedWriter out;
 
    static void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
        File inputFile=new File("B.in");
        PrintWriter outputFile= new PrintWriter("B.out","UTF-8");
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        
 
        
        
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
 
            long n=sc.nextLong();
            String ss=""+solve(n);
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
