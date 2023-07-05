
 import java.io.*;
 import java.util.*;
 
 public class B {
 	int getInt(BufferedReader in) throws IOException {
 		return Integer.parseInt(in.readLine());
 	}   
 	int[] getInts(BufferedReader in) throws IOException {
 		String[] words = in.readLine().split(" ");
 		int[] ret = new int[words.length];
 		for (int i = 0 ; i < words.length ; i++) ret[i] = Integer.parseInt(words[i]);
 		return ret;
 	}
 
 	void inc(int[] a) {
 		for (int i = 0 ; i + 1 < a.length ; i++) {
 			if (a[i] + 1 < a[i+1]) {
 				a[i]++;
 				return;
 			}
 			a[i] = i == 0 ? 0 : a[i-1] + 1;
 		}
 		a[a.length-1]++;
 	}
 
 	int bruteforce(int R, int C, int N) {
 		if (N < 2) return 0;
 		int M = R * C;
 		int[] T = new int[N];
 		for (int i = 0 ; i < N ; i++) {
 			T[i] = i;
 		}
 		int best = Integer.MAX_VALUE;
 		while (T[N-1] < R*C) {
 			int val = 0;
 			for (int i = 0 ; i < N ; i++) {
 				int xi = T[i] % R;
 				int yi = T[i] / R;
 				for (int j = i + 1 ; j < N ; j++) {
 					int xj = T[j] % R;
 					int yj = T[j] / R;
 					if (Math.abs(xi - xj) + Math.abs(yi - yj) == 1) {
 						val++;
 					}
 				}
 			}
 			best = Math.min(best, val);
 			inc(T);
 		}
 		return best;
 	}
 
 	void run(String[] args) {
 		try {
 			BufferedReader in = new BufferedReader(new FileReader(args[0]));
 		    PrintStream out = new PrintStream(args[0] + ".out");
 			int CASES = getInt(in);
 			for (int CASE = 1 ; CASE <= CASES ; CASE++) {
 				int[] param = getInts(in);
 				int R = param[0];
 				int C = param[1];
 				int N = param[2];
 				out.printf("Case #%d: %d\n", CASE, bruteforce(R, C, N));
 			}
 		} catch (IOException e) {
 			throw new RuntimeException(e);
 		}
 	}
 
 	public static void main (String[] args) {
 		new B().run(args);
 	}
 }
