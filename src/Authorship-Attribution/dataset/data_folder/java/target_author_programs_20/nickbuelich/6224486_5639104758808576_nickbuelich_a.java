import java.io.File;
 import java.io.PrintWriter;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 
 public class A {
 
 	public static void main(String[] args) throws Exception {
 		Scanner sc = new Scanner(new File("standing.in"));
 		PrintWriter out = new PrintWriter(new File("standing.out"));
 		int T = sc.nextInt();
 		for(int t=1;t<=T;t++){
 			int N = sc.nextInt();
 			char[] S = sc.next().toCharArray();
 			int standing = 0;
 			int newbs = 0;
 			for(int a=0;a<=N;a++){
 				if(standing>=a)
 					standing+=S[a]-'0';
 				else{
 					int added = a-standing;
 					newbs+= added;
 					standing += added + S[a]-'0';
 				}
 			}
 			out.printf("Case #%d: %d%n",t,newbs);
 		}
 		
 		
 		out.close();
 	}
 
 
 }
