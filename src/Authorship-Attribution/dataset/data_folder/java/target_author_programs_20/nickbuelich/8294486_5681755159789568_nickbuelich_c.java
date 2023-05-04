import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class C {
 	static long oo = 987654321;
 	public static void main(String[] args) throws Exception {
 		Scanner sc = new Scanner(new File("C.in"));
 		PrintWriter out = new PrintWriter(new File("C.out"));
 		int T = sc.nextInt();
 		for(int t=1;t<=T;t++){
 			int N = sc.nextInt();
 			int Q = sc.nextInt();
 			long[] E = new long[N];
 			double[] S = new double[N];
 			for(int a=0;a<N;a++){
 				E[a]=sc.nextLong();
 				S[a]=sc.nextDouble();
 			}
 			
 			long[] D = new long[N];
 			for(int a=0;a<N;a++){
 				for(int b=0;b<N;b++){
 					if(a+1==b){
 						D[b]=sc.nextLong();
 					}
 					else{
 						sc.nextInt();
 					}
 				}
 			}
 			sc.nextInt();
 			sc.nextInt();
 			
 			double[][] G = new double[N][N];
 			
 			long[] DD = new long[N];
 			for(int a=1;a<N;a++){
 				DD[a] = DD[a-1]+D[a];
 			}
 			
 			for(int a=0;a<N;a++){
 				for(int b=a+1;b<N;b++){
 					G[a][b]= 987654321987654312.0;
 					if(DD[b]-DD[a] <= E[a]){
 						G[a][b]=(DD[b]-DD[a])/S[a];
 					}
 				}
 			}
 			
 			floydwarshals(G);
 			
 			double ans = G[0][N-1];
 			
 			System.out.printf("Case #%d: %f%n", t, ans);
 			out.printf("Case #%d: %f%n", t, ans);
 		}
 		out.close();
 	}
 	
 	public static double[][] floydwarshals(double[][] G){
 		int n = G.length;
 		for(int i = 0; i < n; i++){
 			for(int j = 0; j < n; j++){
 				for(int k = 0; k < n; k++){
 					if(G[j][k] > G[j][i]+G[i][k]){
 						G[j][k] = G[j][i]+G[i][k];
 					}
 				}
 			}
 		}
 		return G;
 	}	
 }
