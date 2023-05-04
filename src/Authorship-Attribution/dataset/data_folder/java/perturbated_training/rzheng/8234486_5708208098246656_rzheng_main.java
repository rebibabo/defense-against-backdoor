import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class Main {
    private static char[][] graph;
 
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("A-small-attempt0.in"));
        System.setOut(new PrintStream("A-small-attempt0.out"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            graph = new char[sc.nextInt()][sc.nextInt()];
            for (int j = 0; j < graph.length; j++) {
                graph[j] = sc.next().toCharArray();
            }
            int count = count();
            System.out.println("Case #" + i + ": " + (count == -1 ? "IMPOSSIBLE" : count));
        }
        sc.close();
    }
 
    private static int count() {
        int count = 0;
        for (int j = 0; j < graph.length; j++) {
            for (int k = 0; k < graph[j].length; k++) {
                if (graph[j][k] != '.') {
                    if (!has(j, k, graph[j][k])) {
                        if (has(j, k, '^') || has(j, k, '>') || has(j, k, 'v') || has(j, k, '<')) {
                            count++;
                        } else {
                            return -1;
                        }
                    }
                }
            }
        }
        return count;
    }
 
    private static boolean has(int i, int j, char character) {
        if (character == '^') {
            for (int k = i - 1; k >= 0; k--) {
                if (graph[k][j] != '.') {
                    return true;
                }
            }
        } else if (character == '>') {
            for (int k = j + 1; k < graph[i].length; k++) {
                if (graph[i][k] != '.') {
                    return true;
                }
            }
        } else if (character == 'v') {
            for (int k = i + 1; k < graph.length; k++) {
                if (graph[k][j] != '.') {
                    return true;
                }
            }
        } else {
            for (int k = j - 1; k >= 0; k--) {
                if (graph[i][k] != '.') {
                    return true;
                }
            }
        }
        return false;
    }
 }