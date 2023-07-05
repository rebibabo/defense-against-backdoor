import java.io.BufferedInputStream;
 import java.util.Scanner;
 import java.util.TreeMap;
 
 
 public class TaskB {
 
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int d = sc.nextInt();
            TreeMap<Integer, Integer> tree = new TreeMap<Integer, Integer>();
            for (int j = 0; j < d; j++) {
                Integer val = Integer.valueOf(sc.nextInt());
                add(tree, val, 1);
            }
            int answer = calculate(tree);
            print(i+1, answer);
        }
        sc.close();
        System.err.println(System.currentTimeMillis() - time);
    }
    
    private static int calculate(TreeMap<Integer, Integer> tree) {
        Integer max = tree.lastKey();
        if (max <= 3) {
            return max;
        }
        int maxCount = tree.get(max);
        tree.remove(max);
        TreeMap<Integer, Integer> tree2 = (TreeMap<Integer, Integer>) tree.clone();
        if (max % 2 == 0) {
            add(tree, max / 2, 2 * maxCount);
        }
        else {
 
            add(tree, max / 2 + 1, 2 * maxCount);
        }
        if (max % 3 == 0) {
            add(tree2, max / 3, 3 * maxCount);
        }
        else {
            add(tree2, max / 3 + 1, 3 * maxCount);
        }
        return Math.min(Math.min(max, maxCount + calculate(tree)), 2 * maxCount + calculate(tree2));
    }
    
    private static void print(int caseNum, int answer) {
        System.out.println("Case #" + caseNum + ": " + answer);
    }
    
    public static void add(TreeMap<Integer, Integer> tree, Integer key, Integer value) {
        if (tree.containsKey(key)) {
            tree.put(key, Integer.valueOf(tree.get(key) + value));
        }
        else {
            tree.put(key, value);
        }
    }
 
 }
