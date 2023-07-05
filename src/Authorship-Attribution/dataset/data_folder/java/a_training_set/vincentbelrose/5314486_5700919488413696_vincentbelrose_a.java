import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Arrays;
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
    static class Composite implements Comparable<Composite>{
        int r;
        int h;
 
        public int compareTo(Composite X){
            if (r!=X.r)
                return X.r-r;
            return X.h-h;
        }
 
        public Composite(int r, int h) {
            this.r = r;
            this.h = h;
        }
 
 
        public String toString(){
            return "("+r+" "+h+")";
        }
        
    }
 
 
 
 
    static void process() throws Exception {
        Locale.setDefault(Locale.US);
 
        File inputFile=new File("A.in");
        PrintWriter outputFile= new PrintWriter("A.out","UTF-8");
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
 
        
        double pi=Math.PI;
 
        int T=sc.nextInt();
 
 
 
        for (int t=1;t<=T;t++){
 
            int n=sc.nextInt();
            int p=sc.nextInt();
            int[] g=new int[n];
            
            int[] num=new int[4];
            int cnt=0;
            
            for (int i=0;i<n;i++) {
                g[i]=sc.nextInt()%p;
                num[g[i]]++;
            }
            int res=0;
            res+=num[0];
            if (p==2){
                int left=0;
                while (num[1]>0){
                    if (num[1]>0) {
                        if (left==0)
                            res++;
                        left+=1;
                        left%=p;
                        num[1]--;
                    }
                }
            } else if (p==3){
                boolean goon=true;
                int left=0;
                while (num[1]>0 || num[2]>0){
                    if (num[1]>0 && num[2]>0){
                        res++;
                        num[1]--;
                        num[2]--;
                    } else if (num[1]>0) {
                        if (left==0)
                            res++;
                        left+=1;
                        left%=p;
                        num[1]--;
                        
                    } else if (num[2]>0) {
                        if (left==0)
                            res++;
                        left+=2;
                        left%=p;
                        num[2]--;
                        
                    }
                }
                
            }
            
            
            String ss=""+res;
 
 
 
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
