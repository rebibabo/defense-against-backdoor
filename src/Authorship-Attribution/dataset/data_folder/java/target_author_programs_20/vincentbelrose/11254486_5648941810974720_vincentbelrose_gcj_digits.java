
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 public class GCJ_Digits {
 
 	void log(long[] X){
 		int L=X.length;
 		for (int i=0;i<L;i++){
 			logWln(X[i]+" ");
 		}
 		log("");
 	}
 
 	void log(double[] X){
 		int L=X.length;
 		for (int i=0;i<L;i++){
 			logWln(X[i]+" ");
 		}
 		log("");
 	}
 
 	void log(int[] X){
 		int L=X.length;
 		for (int i=0;i<L;i++){
 			logWln(X[i]+" ");
 		}
 		log("");
 	}
 	void log(Object[] X){
 		int L=X.length;
 		for (int i=0;i<L;i++){
 			logWln(X[i]+" ");
 		}
 		log("");
 	}
 
 
 	void log(Object o){
 		logWln(o+"\n");
 	}
 
 	void logWln(Object o){
 		System.out.print(o);
 		
 
 	}
 	void info(Object o){
 		System.out.println(o);
 		
 	}
 
 
 
 	String solve(String s){
 		String[] names={"ZERO","ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE"};
 
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 
 
 		int[] num=new int[26];
 		int L=s.length();
 		for (int u=0;u<L;u++){
 			int c=s.charAt(u);
 			num[c-'A']++;
 		}
 		int[] res=new int[10];
 		int val;
 		String t;
 		
 		res[2]=num['W'-'A'];
 		t=names[2];
 		val=res[2];
 		for (int u=0;u<t.length();u++){
 			char c=t.charAt(u);
 			num[c-'A']-=val;
 		}
 		
 		res[0]=num['Z'-'A'];
 		t=names[0];
 		val=res[0];
 		for (int u=0;u<t.length();u++){
 			char c=t.charAt(u);
 			num[c-'A']-=val;
 		}
 		
 		res[4]=num['U'-'A'];
 		t=names[4];
 		val=res[4];
 		for (int u=0;u<t.length();u++){
 			char c=t.charAt(u);
 			num[c-'A']-=val;
 		}
 		
 		res[6]=num['X'-'A'];
 		t=names[6];
 		val=res[6];
 		for (int u=0;u<t.length();u++){
 			char c=t.charAt(u);
 			num[c-'A']-=val;
 		}
 		
 		res[7]=num['S'-'A'];
 		t=names[7];
 		val=res[7];
 		for (int u=0;u<t.length();u++){
 			char c=t.charAt(u);
 			num[c-'A']-=val;
 		}
 		
 		res[8]=num['G'-'A'];
 		t=names[8];
 		val=res[8];
 		for (int u=0;u<t.length();u++){
 			char c=t.charAt(u);
 			num[c-'A']-=val;
 		}
 		
 		res[5]=num['V'-'A'];
 		t=names[5];
 		val=res[5];
 		for (int u=0;u<t.length();u++){
 			char c=t.charAt(u);
 			num[c-'A']-=val;
 		}
 		
 		res[3]=num['T'-'A'];
 		t=names[3];
 		val=res[3];
 		for (int u=0;u<t.length();u++){
 			char c=t.charAt(u);
 			num[c-'A']-=val;
 		}
 
 		
 		res[1]=num['O'-'A'];
 		t=names[1];
 		val=res[1];
 		for (int u=0;u<t.length();u++){
 			char c=t.charAt(u);
 			num[c-'A']-=val;
 		}
 		
 				res[9]=num['I'-'A'];
 				t=names[9];
 				val=res[9];
 				for (int u=0;u<t.length();u++){
 					char c=t.charAt(u);
 					num[c-'A']-=val;
 				}
 
 		String rs="";
 		ArrayList<Integer> znx=new ArrayList<Integer>();
 		int p=0;
 		for (int u=0;u<10;u++){
 			for (int v=0;v<res[u];v++) {
 				znx.add(u);
 				rs+=(char)('0'+u);
 			}
 		}
 		
 		
 		
 		return rs;
 	}
 
 
 
 
 
 	
 	StringTokenizer st;
 	BufferedReader in;
 	BufferedWriter out;
 
 
 
 
 	void process() throws Exception {
 		Locale.setDefault(Locale.US);
 
 
 
 
 		
 		File inputFile=new File("A.in");
 
 
 
 		
 		PrintWriter outputFile= new PrintWriter("A.out","UTF-8");
 
 
 
 		Scanner sc=new Scanner(inputFile);
 		sc.useLocale(Locale.US);
 
 		int T=sc.nextInt();
 		for (int t=1;t<=T;t++){
 			String s=sc.next();
 
 
 
 			String ss=""+solve(s);
 			System.out.println("Case #"+t+": "+ss);
 			outputFile.println("Case #"+t+": "+ss);
 
 
 		}
 
 
 
 		sc.close();
 		outputFile.close();
 
 
 	}
 
 
 
 	public static void main(String[] args) throws Exception {
 		GCJ_Digits J=new GCJ_Digits();
 
 		J.process();
 
 
 	}
 
 
 
 
 
 
 }
