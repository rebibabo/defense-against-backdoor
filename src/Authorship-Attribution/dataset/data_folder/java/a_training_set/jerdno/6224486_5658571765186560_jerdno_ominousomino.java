import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Collections;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.PriorityQueue;
 import java.util.Queue;
 
 public class OminousOmino {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        List<Integer> list = new LinkedList<Integer>();
        Queue<Integer> priorityQueue = new PriorityQueue<Integer>(1000001, Collections.reverseOrder());
        try {
            String line = br.readLine(), result;
            int max, act, count = Integer.parseInt(line), a, b, c;
            for(int i = 0; i < count; i++) {
                line = br.readLine();
                a = parse2(line, 0);
                b = parse2(line, 1);
                c = parse2(line, 2);
                result = "";
                if (a == 1)
                    result = "GABRIEL";
                if (a == 2) {
                    if (b * c % 2 == 0)
                        result = "GABRIEL";
                    else
                        result = "RICHARD";
                }
                if (a == 3) {
                    if (b * c % 3 == 0 && b * c != 3)
                        result = "GABRIEL";
                    else
                        result = "RICHARD";
                }
                if (a == 4) {
                    if (b * c % 4 == 0 && b * c > 8)
                        result = "GABRIEL";
                    else
                        result = "RICHARD";
                }
 
 
 
 
                writer.println("Case #" + (i + 1) + ": " + result);
            }
        } finally {
            br.close();
        }
        writer.close();
    }
 
    private static int parse(String s) {
        return Integer.parseInt(s.substring(0, s.indexOf(' ')));
    }
 
    private static int parse2(String s, int i) {
        return Integer.parseInt(s.substring(i * 2, i * 2 + 1));
    }
 }
