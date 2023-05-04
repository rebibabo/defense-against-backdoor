import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.math.BigDecimal;
 import java.util.Collections;
 import java.util.HashMap;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;
 import java.util.PriorityQueue;
 import java.util.Queue;
 
 public class Second {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        List<Integer> list = new LinkedList<Integer>();
        Queue<Integer> priorityQueue = new PriorityQueue<Integer>(1000001, Collections.reverseOrder());
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            String line = br.readLine(), line2;
            int max, count = Integer.parseInt(line);
            int pom, c, a, b, f;
            double result, act;
            for(int i = 0; i < count; i++) {
                result = 0;
                line = br.readLine();
                a = parse2(line, 0);
                b = parse2(line, 1);
                c = parse2(line, 2);
                line = br.readLine();
                line2 = br.readLine();
                f = 0;
                while (check(line2, f)) {
                    f++;
                    if (f > line2.length()) {
                        f--;
                        break;
                    }
                }
                f--;
                max = (c - b) / (b - f) + 1;
                for (int ii = 0; ii < a; ii++) {
                    if (map.get(line.substring(ii, ii + 1)) == null) {
                        map.put(line.substring(ii, ii + 1), 1);
                    } else {
                        map.put(line.substring(ii, ii + 1), map.get(line.substring(ii, ii + 1)) + 1);
                    }
                }
                pom = 0;
                for (int ii = 0; ii < b; ii++) {
                    if (!map.containsKey(line2.substring(ii, ii + 1))) {
                        pom = 1;
                    }
                }
                if (pom == 0) {
                    act = 1;
                    for (int ii = 0; ii < b; ii++) {
                        act = act * map.get(line2.substring(ii, ii + 1)) / a;
                    }
                    act = act * max;
                    result = result + max - act;
                }
                map.clear();
                writer.println("Case #" + (i + 1) + ": " + result);
            }
        } finally {
            br.close();
        }
        writer.close();
    }
 
    private static int parse2(String s, int i) {
        if (i == 0)
            return Integer.parseInt(s.substring(0, s.indexOf(' ')));
        else if (i == 1) {
            int ind = 0;
            while (i > 0) {
                ind = s.indexOf(' ', ind + 1);
                i = i - 1;
            }
            return Integer.parseInt(s.substring(ind + 1, s.indexOf(' ', ind + 1)));
        } else {
            int ind = 0;
            while (i > 0) {
                ind = s.indexOf(' ', ind + 1);
                i = i - 1;
            }
            return Integer.parseInt(s.substring(ind + 1));
        }
    }
 
    private static boolean check(String a, int l) {
        return a.substring(0, l).equals(a.substring(a.length() - l));
    }
 }
