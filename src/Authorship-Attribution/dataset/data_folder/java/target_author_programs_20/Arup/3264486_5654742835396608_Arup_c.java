import java.util.*;
 
 public class c {
 
 	public static void main(String[] args) {
 
 		Scanner stdin = new Scanner(System.in);
 		int numCases = stdin.nextInt();
 
 		for (int loop=1; loop<=numCases; loop++) {
 
 			int n = stdin.nextInt();
 			int k = stdin.nextInt();
 			PriorityQueue<gap> pq = new PriorityQueue<gap>();
 			pq.offer(new gap(n));
 			int max = -1, min = -1;
 			for (int i=0; i<k; i++) {
 				gap tmp = pq.poll();
 				int val = tmp.n - 1;
 
 				pq.offer(new gap(val/2));
 				pq.offer(new gap(val-val/2));
 				if (i == k-1) {
 					max = val - val/2;
 					min = val/2;
 				}
 			}
 
 			System.out.println("Case #"+loop+": "+max+" "+min);
 		}
 	}
 
 }
 
 class gap implements Comparable<gap> {
 
 	public int n;
 
 	public gap(int x) {
 		n = x;
 	}
 
 	public int compareTo(gap other) {
 		return other.n - this.n;
 	}
 }