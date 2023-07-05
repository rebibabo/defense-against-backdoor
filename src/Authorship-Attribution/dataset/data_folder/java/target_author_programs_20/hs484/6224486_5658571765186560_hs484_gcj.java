package gcj.Qual_2015.D;
 
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
 
 
 		if (true) { filename = "D-small-attempt0.in"; }
 
 
 		Scanner sc = null;
 		try {
 			sc = new Scanner(new File(dir + "/" + filename));
 		} catch (FileNotFoundException e) {
 			tr(e.getMessage());
 			return;
 		}
 		PrintWriter fout = new PrintWriter(new File(dir + "/" + filename + ".res"));
 
 		GCJ obj = new GCJ();
 		int TNO = sc.nextInt();
 		for (int tno = 0; tno < TNO; tno++) {
 			fout.write(String.format("Case #%d: ", (tno + 1)));
 			obj.solve(sc, fout);
 			fout.flush();
 		}
 		fout.flush();
 		fout.close();
 		long end = System.currentTimeMillis();
 		tr((end - start) + "ms");
 	}
 
 	void solve(Scanner sc, PrintWriter fout) {
 		int X = sc.nextInt();
 		int R = sc.nextInt();
 		int C = sc.nextInt();
 
 		if (winR(X, R, C)) {
 			fout.println("RICHARD");
 		} else {
 			fout.println("GABRIEL");
 		}
 	}
 
 	boolean winR(int X, int R, int C) {
 		if (R > C) return winR(X, C, R);
 		
 		if (R * C % X != 0) return true;
 		if (C < X) return true;
 		if (R + C - 1 < X) return true;
 		for (int r = 1; r <= X; r++) {
 			int c = r == 1 ? X - r : X - r + 1;
 			if (r <= c) {
 				if (r <= R && c <= C)
 					;
 				else
 					return true;
 			}
 		}
 		if (X >= 7) return true;
 		return false;
 	}
 }
