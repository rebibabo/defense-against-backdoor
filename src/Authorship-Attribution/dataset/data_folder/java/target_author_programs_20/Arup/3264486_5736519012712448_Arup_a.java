import java.util.*;
 
 public class a {
 
 	public static void main(String[] args) {
 
 		Scanner stdin = new Scanner(System.in);
 		int numCases = stdin.nextInt();
 
 		for (int loop=1; loop<=numCases; loop++) {
 
 			String s = stdin.next();
 			int k = stdin.nextInt();
 			boolean[] arr = get(s);
 
 			int res = 0;
 			for (int i=0; i<=s.length()-k; i++) {
 				if (!arr[i]) {
 					for (int j=i; j<i+k; j++)
 						arr[j] = !arr[j];
 					res++;
 				}
 			}
 
 			boolean ans = true;
 			for (int i=0; i<arr.length; i++) {
 				if (!arr[i]) {
 					ans = false;
 					break;
 				}
 			}
 
 			if (!ans)
 				System.out.println("Case #"+loop+": IMPOSSIBLE");
 			else
 				System.out.println("Case #"+loop+": "+res);
 		}
 	}
 
 	public static boolean[] get(String s) {
 		boolean[] res = new boolean[s.length()];
 		for (int i=0; i<s.length(); i++)
 			res[i] = (s.charAt(i) == '+');
 		return res;
 	}
 }