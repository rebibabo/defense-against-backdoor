import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 import java.util.TreeSet;
 
 public class B {
 	static TreeSet<Long> TS;
 	public static void main(String[] args) throws Exception {
 		Scanner sc = new Scanner(new File("B.in"));
 		PrintWriter out = new PrintWriter(new File("B.out"));
 		int T = sc.nextInt();
 		TS = new TreeSet<Long>();
 		DP(0,18);
 		for(int t=1;t<=T;t++){
 			long cur = sc.nextLong()+1;
 			String ans = TS.lower(cur).toString();
 			System.out.printf("Case #%d: %s%n", t, ans);
 			out.printf("Case #%d: %s%n", t, ans);
 		}
 		out.close();
 	}
 
 	private static void DP(long i, int s) {
 		if(s==0) return;
 		for(long a=i%10;a<10;a++){
 			long x = (i*10) + a;
 			if(!TS.contains(x)){
 				TS.add(x);
 				DP(x, s-1);	
 			}
 		}
 	}
 }
