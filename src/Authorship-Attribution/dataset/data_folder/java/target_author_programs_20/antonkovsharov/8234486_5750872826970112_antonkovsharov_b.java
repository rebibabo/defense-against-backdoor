import java.util.*;
 import java.io.*;
 
 public class B {
 	FastScanner in;
 	PrintWriter out;
 
 	class Water {
 		double r, c;
 
 		public Water(double r, double c) {
 			super();
 			this.r = r;
 			this.c = c;
 		}
 	}
 	
 	public void solve() throws IOException {
 		Locale.setDefault(Locale.US);
 		int n = in.nextInt();
 		double v = in.nextDouble(), x = in.nextDouble();
 		ArrayList<Water> ws = new ArrayList<>();
 		TreeMap<Double, Double> cws = new TreeMap<>();
 		for (int i = 0; i < n; i++) {
 			double r = in.nextDouble(), c = in.nextDouble();
 			if (cws.containsKey(c))
 				r += cws.get(c);
 			cws.put(c, r);
 		}
 		for (double c : cws.keySet()) {
 			ws.add(new Water(cws.get(c), c));
 		}
 		n = ws.size();
 		Collections.sort(ws, new Comparator<Water>() {
 
 			@Override
 			public int compare(Water arg0, Water arg1) {
 				return Double.compare(arg0.c, arg1.c);
 			}
 		});
 		if (x > ws.get(n - 1).c || x < ws.get(0).c) {
 			out.println("IMPOSSIBLE");
 			return;
 		}
 		if (n == 1) {
 			out.println(v / ws.get(0).r);
 			return;
 		}
 		double alpha = (x - ws.get(0).c) / (ws.get(1).c - ws.get(0).c);
 		double v1 = v * alpha, v0 = v * (1 - alpha);
 		double res = Math.max(v0 / ws.get(0).r, v1 / ws.get(1).r);
 		out.println(res);
 	}
 
 	public void run() {
 		try {
 			in = new FastScanner(new File("a.in"));
 			out = new PrintWriter(new File("a.out"));
 
 			int tests = in.nextInt();
 			for (int i = 0; i < tests; i++) {
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
 		new B().run();
 	}
 }