package gcj2016.r1a.a;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class GCJ {
 
 	public static void tr(Object... o) {
 		System.err.println(Arrays.deepToString(o));
 	}
 
 	public static void main(String[] args) throws Throwable {
 		long start = System.currentTimeMillis();
 
 		String pkg = new Object(){}.getClass().getEnclosingClass().getPackage().getName().replace('.', '/');
 		String dir = "src/" + pkg;
 
 		String filename = "";
 
 
 		if (true) { filename = "A-small-attempt0.in"; }
 
 
 		Scanner sc = null;
 		try {
 			sc = new Scanner(new File(dir + "/" + filename));
 		} catch (FileNotFoundException e) {
 			tr(e.getMessage());
 			return;
 		}
 		PrintWriter fout = new PrintWriter(new File(dir + "/" + filename + ".res"));
 
 		GCJ obj = new GCJ();
 		int T = sc.nextInt();
 		for (int t = 0; t < T; t++) {
 			fout.write(String.format("Case #%d: ", (t + 1)));
 			obj.solve(sc, fout);
 			fout.flush();
 		}
 		fout.flush();
 		fout.close();
 		long end = System.currentTimeMillis();
 		tr((end - start) + "ms");
 	}
 	
 	void solve(Scanner sc, PrintWriter out) {
 		char[] s = sc.next().toCharArray();
 		String ans = solve(s, 0, s.length);
 		out.println(ans);
 	}
 	
 	String solve(char[] s, int a, int b) { 
 		if (b - a == 0) return "";
 		if (b - a == 1) return "" + s[a];
 		int ma = 0;
 		for (int i = a; i < b; i++) ma = Math.max(ma, s[i]);
 		String res = "";
 		for (int i = a; i < b; i++) {
 			if (s[i] == ma) {
 				res = solve(s, a, i);
 				for (int j = i; j < b; j++) {
 					if (s[j] == ma) res = s[j] + res;
 					else res = res + s[j];
 				}
 				break;
 			}
 		}
 		return res;
 	}
 }
