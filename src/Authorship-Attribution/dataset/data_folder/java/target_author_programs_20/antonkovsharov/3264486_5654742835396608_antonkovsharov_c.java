import java.util.*;
 import java.io.*;
 
 public class C {
 	FastScanner in;
 	PrintWriter out;
 	
 	TreeMap<Long, Long> cnt;
 	
 	void add(long key, long val) {
 		if (!cnt.containsKey(key)) {
 			cnt.put(key, 0L);
 		}
 		cnt.put(key, cnt.get(key) + val);
 	}
 	
 	public void solve() throws IOException {
 		long n = in.nextLong();
 		long k = in.nextLong();
 		cnt = new TreeMap<>();
 		add(n, 1);
 		while (true) {
 			long x = cnt.lastKey();
 			long y = cnt.get(x);
 			if (y >= k) {
 				out.println((x / 2) + " " + ((x - 1) / 2));
 				return;
 			} else {
 				add(x / 2, y);
 				add((x - 1) / 2, y);
 				k -= y;
 				cnt.remove(x);
 			}
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