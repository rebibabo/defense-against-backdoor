
 import java.io.*;
 import java.util.*;
 
 public class A {
 	int getInt(BufferedReader in) throws IOException {
 		return Integer.parseInt(in.readLine());
 	}   
 	int[] getInts(BufferedReader in) throws IOException {
 		String[] words = in.readLine().split(" ");
 		int[] ret = new int[words.length];
 		for (int i = 0 ; i < words.length ; i++) ret[i] = Integer.parseInt(words[i]);
 		return ret;
 	}
 
 	String compute(char[] line, int k) {
 		int flips = 0;
 		for (int i = k ; i <= line.length ; i++) {
 			if (line[i - k] == '-') {
 				flips++;
 				for (int j = i - k ; j < i ; j++) {
 					line[j] = line[j] == '-' ? '+' : '-';
 				}
 			}
 		}
 		for (int i = line.length - k ; i < line.length ; i++) if (line[i] == '-') return "IMPOSSIBLE";
 		return ""+flips;
 
 	}
 
 	void run(String[] args) {
 		try {
 			BufferedReader in = new BufferedReader(new FileReader(args[0]));
 		    PrintStream out = new PrintStream(args[0] + ".out");
 			int T = getInt(in);
 			for (int t = 1 ; t <= T ; t++) {
 				String line = in.readLine();
 				String[] words = line.split(" ");
 				int k = Integer.parseInt(words[1]);
 				out.printf("Case #%d: %s\n", t, compute(words[0].toCharArray(), k));
 			}
 		} catch (IOException e) {
 			throw new RuntimeException(e);
 		}
 	}
 
 	public static void main(String[] args) {
 		new A().run(args);
 	}
 }
