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
 
    static boolean verb=true;
    static void log(Object X){if (verb) System.err.println(X);}
    static void log(Object[] X){if (verb) {for (Object U:X) System.err.print(U+" ");System.err.println("");}}
    static void log(int[] X){if (verb) {for (int U:X) System.err.print(U+" ");System.err.println("");}}
    static void logWln(Object X){if (verb) System.err.print(X);}
    static void info(Object o){ System.out.println(o);}
    static void output(Object o){outputWln(""+o+"\n");  }
    static void outputWln(Object o){try {out.write(""+ o);} catch (Exception e) {}}
 
 
    
 
    
    static BufferedWriter out;
 
    static int N,D;
    static int[] K,S;
 
 
 
    static void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
        File inputFile=new File("A.in");
        PrintWriter outputFile= new PrintWriter("A.out","UTF-8");
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        
 
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
        
            D=sc.nextInt();
            N=sc.nextInt();
            log("D:"+D+" N:"+N);
            K=new int[N];
            S=new int[N];
            double maxTime=0;
            for (int i=0;i<N;i++){
                K[i]=sc.nextInt();
                S[i]=sc.nextInt();
                if (K[i]<D){
                    double time=(D-K[i]);
                    time/=S[i];
                    log("time:"+time);
                    maxTime=Math.max(time,maxTime);
                }
            }
            double speed=D/maxTime;
            
            String ss=""+speed;
            
 
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
