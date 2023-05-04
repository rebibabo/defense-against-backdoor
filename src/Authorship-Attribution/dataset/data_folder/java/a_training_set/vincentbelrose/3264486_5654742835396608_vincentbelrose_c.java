import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 
 
 public class C {
 
    static boolean verb=true;
    static void log(Object X){if (verb) System.err.println(X);}
    static void log(Object[] X){if (verb) {for (Object U:X) System.err.print(U+" ");System.err.println("");}}
    static void log(int[] X){if (verb) {for (int U:X) System.err.print(U+" ");System.err.println("");}}
    static void log(long[] X){if (verb) {for (long U:X) System.err.print(U+" ");System.err.println("");}}
    static void logWln(Object X){if (verb) System.err.print(X);}
    static void info(Object o){ System.out.println(o);}
    static void output(Object o){outputWln(""+o+"\n");  }
    static void outputWln(Object o){try {out.write(""+ o);} catch (Exception e) {}}
 
 
    static long[] rec(long n,long k){
        long pos=(n+1)/2;
        if (k==1){
 
            return new long[]{n-pos,pos-1};
        }
 
        return rec(n-pos,k/2);
 
    }
 
    static String solve(long n,long k){
        
        long[] tmp=rec(n,k);
        return tmp[0]+" "+tmp[1];
    }
 
    static int getLS(int[] mem,int x){
        int y=x;
        while (y-1>=0 && mem[y-1]==0)
            y--;
        return x-y;
    }
 
    static int getRS(int[] mem,int x){
        int n=mem.length;
        int y=x;
        while (y+1<n && mem[y+1]==0)
            y++;
        return y-x;
    }
 
 
    static String solveBourrin(int n,int k){
        int[] mem=new int[n];
        
        while (k>0){
            int LX=-1;
            int RX=-1;
            int MX=-1;
            int MMX=-1;
            int target=-1;
            for (int i=n-1;i>=0;i--){
                if (mem[i]==0){
                    int LS=getLS(mem,i);
                    int RS=getRS(mem,i);
                    int MS=Math.min(LS,RS);
                    int MMS=Math.max(LS, RS);
                    
                    if (MS>MX){
                        MX=MS;
                        LX=LS;
                        RX=RS;
                        MMX=MMS;
                        target=i;
                    } else 
                        if (MS==MX){
                            if (MMS>=MMX){
                                MX=MS;
                                LX=LS;
                                RX=RS;
                                MMX=MMS;
                                target=i;
 
                            } 
 
                        }
                }
            }
                k--;
                mem[target]=1;
                
                
                
                if (k==0)
                    return MMX+" "+MX;
            
        }
        return "";
    }
 
 
    
    static BufferedWriter out;
 
    static void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
        File inputFile=new File("C.in");
        PrintWriter outputFile= new PrintWriter("C.out","UTF-8");
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        
 
        
 
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
            long n=sc.nextLong();
            long k=sc.nextLong();   
            String ss=solveBourrin((int)n,(int)k);
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
