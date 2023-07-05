import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Collections;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.PriorityQueue;
 import java.util.Queue;
 
 public class Second {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        List<Integer> list = new LinkedList<Integer>();
        Queue<Integer> priorityQueue = new PriorityQueue<Integer>(1000001, Collections.reverseOrder());
        try {
            String line = br.readLine();
            int max, act, count = Integer.parseInt(line);
            int result, pom, c, a, b, f;
            for(int i = 0; i < count; i++) {
                line = br.readLine();
                a = parse2(line, 0);
                b = parse2(line, 1);
                c = parse2(line, 2);
                result = b / c;
                f = b % c;
                if (f == 0)
                    result  =  result + c - 1;
                else
                    result = result + c;
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
 }
