package gcj2017.qual.a;
 
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
 		int K = sc.nextInt();
 		int ans = 0;
 		for (int i = 0; i + K <= s.length; i++) {
 			if (s[i] == '-') {
 				++ans;
 				for (int j = 0; j < K; j++) {
 					s[i+j] = flip(s[i+j]);
 				}
 			}
 		}
 		for (int i = 0; i < s.length; i++) {
 			if (s[i] == '-') ans = -1;
 		}
 		if (ans == -1)
 			out.println("IMPOSSIBLE");
 		else
 			out.println(ans);
 	}
 	
 	char flip(char c) {
 		if (c == '+') return '-';
 		else return '+';
 	}
 	
 }
