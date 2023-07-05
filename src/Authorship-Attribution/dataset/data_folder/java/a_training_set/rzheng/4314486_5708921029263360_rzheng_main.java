import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Scanner;
 
 public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int J = sc.nextInt(), P = sc.nextInt(), S = sc.nextInt(), K = sc.nextInt();
            HashMap<Integer, Integer> map = new HashMap<>();
            HashSet<Integer> set = new HashSet<>();
            for (int s = S; s > 0; s--) {
                for (int p = P; p > 0; p--) {
                    for (int j = J; j > 0; j--) {
                        int a = s * 100 + p * 10;
                        int b = s * 100 + j;
                        int c = p * 10 + j;
                        if (map.containsKey(a) && map.get(a) == K) {
                            continue;
                        }
                        if (map.containsKey(b) && map.get(b) == K) {
                            continue;
                        }
                        if (map.containsKey(c) && map.get(c) == K) {
                            continue;
                        }
                        map.put(a, map.containsKey(a) ? map.get(a) + 1 : 1);
                        map.put(b, map.containsKey(b) ? map.get(b) + 1 : 1);
                        map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
                        set.add(s * 100 + p * 10 + j);
                    }
                }
            }
            System.out.println("Case #" + i + ": " + set.size());
            for (Integer in : set) {
                System.out.println((in % 10) + " " + (in / 10 % 10) + " " + (in / 100));
            }
        }
        sc.close();
    }
 }