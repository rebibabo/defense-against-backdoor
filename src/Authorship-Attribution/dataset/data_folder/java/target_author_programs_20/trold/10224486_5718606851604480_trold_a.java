
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
 
 	boolean nextPerm(char[] list) {
 		if (list.length <= 1) return false;
 		for(int i = list.length - 2 ;; --i) {
 			if (list[i] < list[i+1]) {
 				int j = list.length;
 				while (list[i] >= list[--j]) {}
 				char temp = list[i]; list[i] = list[j]; list[j] = temp;
 				for (int k = i + 1, l = list.length - 1 ; k < l ; k++, l--) {
 					temp = list[k]; list[k] = list[l]; list[l] = temp;
 				}
 				return true;
 			}
 			if (i == 0) {
 				Arrays.sort(list);
 				return false;
 			}
 		}
 	}
 
 	boolean check(char[] plan) {
 		if (plan.length <= 1) return true;
 		char[] next = new char[plan.length / 2];
 		for (int i = 0 ; 2*i < plan.length ; i++) {
 			if (plan[2*i] == plan[2*i+1]) return false;
 			switch(plan[2*i]) {
 				case 'R': next[i] = plan[2*i+1] == 'S' ? 'R' : 'P'; break;
 				case 'P': next[i] = plan[2*i+1] == 'R' ? 'P' : 'S'; break;
 				case 'S': next[i] = plan[2*i+1] == 'P' ? 'S' : 'R'; break;
 			}
 		}
 		return check(next);
 	}
 
 	String compute(int R, int P, int S) {
 		char[] plan = new char[R+P+S];
 		int i = 0;
 		while (P-- > 0) plan[i++] = 'P';
 		while (R-- > 0) plan[i++] = 'R';
 		while (S-- > 0) plan[i++] = 'S';
 		do {
 			if (check(plan)) return new String(plan);
 		} while (nextPerm(plan));
 		return null;
 	}
 
 
 	void run(String[] args) {
 		try {
 			BufferedReader in = new BufferedReader(new FileReader(args[0]));
 		    PrintStream out = new PrintStream(args[0] + ".out");
 			int T = getInt(in);
 			for (int t = 1 ; t <= T ; t++) {
 				int[] input = getInts(in);
 				String sol = compute(input[1], input[2], input[3]);
 				if (sol == null) sol = "IMPOSSIBLE";
 				out.printf("Case #%d: %s\n", t, sol);
 			}
 		} catch (IOException e) {
 			throw new RuntimeException(e);
 		}
 	}
 
 	public static void main (String[] args) {
 		new A().run(args);
 	}
 }
