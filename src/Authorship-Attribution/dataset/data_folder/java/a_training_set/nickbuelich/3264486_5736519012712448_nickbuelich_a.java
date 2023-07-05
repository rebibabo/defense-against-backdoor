import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class A {
 
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("A.in"));
        PrintWriter out = new PrintWriter(new File("A.out"));
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            String ans = "IMPOSSIBLE";
            int used = 0;
            char[] stuff = sc.next().toCharArray();
            int K = sc.nextInt();
            for(int a=0;a<=stuff.length-K;a++){
                if(stuff[a]=='-'){
                    used++;
                    for(int b=0;b<K;b++){
                        stuff[a+b] = stuff[a+b] == '-' ? '+' : '-';
                    }
                }
            }
            ans = used+"";
            for(int a=0;a<stuff.length;a++){
                if(stuff[a] == '-'){
                    ans = "IMPOSSIBLE";
                }
            }
 
            
            System.out.printf("Case #%d: %s%n", t, ans);
            out.printf("Case #%d: %s%n", t, ans);
        }
        out.close();
    }
 }
