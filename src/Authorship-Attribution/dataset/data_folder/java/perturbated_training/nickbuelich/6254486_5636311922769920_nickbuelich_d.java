import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 public class D {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("D.in"));
        PrintWriter out = new PrintWriter("D.out");
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            int start = sc.nextInt();
            int power = sc.nextInt();
            int friends = sc.nextInt();
            String ans = "IMPOSSIBLE";
            if(start <= friends){
                StringBuilder SB = new StringBuilder();
                for(int a=1;a<=friends;a++){
                    SB.append(a+" ");
                }
                ans = SB.toString();
            }
            
            System.out.printf("Case #%d: %s\n",t,ans);
            out.printf("Case #%d: %s\n",t,ans);
        }
        
        out.close();
    }
 }
