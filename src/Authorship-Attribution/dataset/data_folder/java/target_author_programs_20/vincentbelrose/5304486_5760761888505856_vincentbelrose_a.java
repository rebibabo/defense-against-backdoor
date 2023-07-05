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
 	static void info(Object o){	System.out.println(o);}
 	static void output(Object o){outputWln(""+o+"\n");	}
 	static void outputWln(Object o){try {out.write(""+ o);} catch (Exception e) {}}
 
 	
 	static int R,C;
 	static char[][] map;
 	
 	static boolean ok(int i,int l,int r){
 		for (int x=l;x<=r;x++)
 			if (map[i][x]!='?')
 				return false;
 		return true;
 	}
 	
 
 	static String solve(){
 		boolean[][] visited=new boolean[R][C];
 		for (int i=0;i<R;i++){
 			for (int j=0;j<C;j++){
 				char c=map[i][j];
 				if (c!='?' && !visited[i][j]){
 					
 					int u=j;
 					int v=j;
 					while (u-1>=0 && map[i][u-1]=='?')
 						u--;
 					while (v+1<C && map[i][v+1]=='?')
 						v++;
 					int a=i;
 					int b=i;
 					
 					
 					
 					while (a-1>=0 && ok(a-1,u,v))
 						a--;
 					while (b+1<R && ok(b+1,u,v))
 						b++;
 					for (int x=a;x<=b;x++)
 						for (int y=u;y<=v;y++) {
 							visited[x][y]=true;
 							map[x][y]=c;
 						}
 					
 				}
 			}
 		}
 		
 		String res="\n";
 		for (int e=0;e<R;e++)
 			res+=new String(map[e])+"\n";
 		return res;
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
 			log("---------");
 			R=sc.nextInt();
 			C=sc.nextInt();
 			map=new char[R][];
 			for (int i=0;i<R;i++)
 				map[i]=sc.next().toCharArray();
 			String ss=solve();
 			
 			
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
