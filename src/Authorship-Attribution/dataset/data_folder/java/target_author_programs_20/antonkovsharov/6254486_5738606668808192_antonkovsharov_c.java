import java.util.*;
 import java.io.*;
 
 public class C {
 	FastScanner in;
 	PrintWriter out;
 
 	public void solve() throws IOException {
 		out.println();
 		int n = in.nextInt(), J = in.nextInt();
 		for (int i = 1; i <= J; i++) {
 			String s = Integer.toBinaryString(i) + "1";
 			while (s.length() < n / 2) {
 				s = "0" + s;
 			}
 			s = new StringBuffer(s).reverse().toString() + s;
 			out.print(s + " ");
 			for (int j = 2; j <= 10; j++) {
 				out.print((j + 1) + " ");
 			}
 			out.println();
 		}
 	}
 
 	public void run() {
 		try {
 			in = new FastScanner(new File("a.in"));
 			out = new PrintWriter(new File("a.out"));
 
 			int tn = in.nextInt();
 			for (int i = 0; i < tn; i++) {
 				System.err.println(i);
 				out.print("Case #" + (i + 1) + ": ");
 				solve();
 			}
 
 			out.close();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 	}
 
 	class FastScanner {
 		BufferedReader br;
 		StringTokenizer st;
 
 		FastScanner(File f) {
 			try {
 				br = new BufferedReader(new FileReader(f));
 			} catch (FileNotFoundException e) {
 				e.printStackTrace();
 			}
 		}
 
 		String next() {
 			while (st == null || !st.hasMoreTokens()) {
 				try {
 					st = new StringTokenizer(br.readLine());
 				} catch (IOException e) {
 					e.printStackTrace();
 				}
 			}
 			return st.nextToken();
 		}
 
 		int nextInt() {
 			return Integer.parseInt(next());
 		}
 
 		long nextLong() {
 			return Long.parseLong(next());
 		}
 
 		double nextDouble() {
 			return Double.parseDouble(next());
 		}
 	}
 
 	public static void main(String[] arg) {
 		new C().run();
 	}
 }