import java.io.File;
 import java.io.PrintWriter;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.Scanner;
 
 
 public class C {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C.in"));
        PrintWriter out = new PrintWriter("C.out");
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            int J = sc.nextInt();
            int P = sc.nextInt();
            int S = sc.nextInt();
            int K = sc.nextInt();
 
            int[] JP = new int[100];
            int[] PS = new int[100];
            int[] JS = new int[100];
            
            int total = 0;
            StringBuilder SB = new StringBuilder();
            for(int j=0;j<J;j++){
                for(int p=0;p<P;p++){
                    for(int s=0;s<S;s++){
                        int jp = j*10 + p;
                        int ps = p*10 + s;
                        int js = j*10 + s;
                        
                        if(JP[jp]>=K)continue;
                        if(PS[ps]>=K)continue;
                        if(JS[js]>=K)continue;
                        
                        JP[jp]++; 
                        PS[ps]++;
                        JS[js]++;
                        
                        total++;
                        SB.append((j+1)+" "+(p+1)+" "+(s+1)+"\n");
                    }
                }
            }
            
            System.out.printf("Case #%d: %d%n%s",t,total,SB);
            out.printf("Case #%d: %d%n%s",t,total,SB);
        }
        
        out.close();
    }
 }
