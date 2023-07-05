import java.util.ArrayDeque;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Deque;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.Set;
 
 public class A {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
 
         List<Map<Integer, Set<Integer>>> parents = new ArrayList<>();
         int pow = 1;
         for (int L = 0; L < 6; L++, pow *= 10) {
             Map<Integer, Set<Integer>> p = new HashMap<>();
             label: for (int j = 1; j < pow; j++) {
                 int[] digits = new int[L + 1];
                 int num = j;
                 while (num > 0) {
                     if (num % 10 > L) {
                         continue label;
                     }
                     digits[num % 10]++;
                     num /= 10;
                 }
                 int decayed = 0;
                 for (int i = 1; i <= L; i++) {
                     decayed = decayed * 10 + digits[i];
                 }
                 p.computeIfAbsent(decayed, k -> new HashSet<>()).add(j);
             }
 
             parents.add(p);
         }
 
         int numCases = sc.nextInt();
         for (int caseNum = 1; caseNum <= numCases; caseNum++) {
             String G = sc.next();
             int L = G.length();
 
             Set<Integer> visited = new HashSet<>();
 
             Deque<Integer> queue = new ArrayDeque<>();
             queue.offer(Integer.parseInt(G));
             while (!queue.isEmpty()) {
                 int n = queue.poll();
                 if (visited.contains(n)) {
                     continue;
                 }
                 visited.add(n);
                 for (int p : parents.get(L).getOrDefault(n, Collections.emptySet())) {
                     if (visited.contains(p)) {
                         continue;
                     }
                     queue.offer(p);
                 }
             }
 
             System.out.print("Case #" + caseNum + ": ");
             System.out.println(visited.size());
         }
     }
 }
