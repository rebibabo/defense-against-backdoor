import java.io.*;
 import java.util.*;
 import java.util.List;
 
 public class Main {
     private static StringTokenizer st;
     private static BufferedReader br;
     public static long MOD = 1000000007;
     private static double EPS = 0.0000001;
 
     public static void print(Object x) {
         System.out.println(x + "");
     }
     public static void printArr(long[] x) {
         StringBuilder s = new StringBuilder();
         for (int i = 0; i < x.length; i++) {
             s.append(x[i] + " ");
         }
         print(s);
     }
     public static void printArr(int[] x) {
         StringBuilder s = new StringBuilder();
         for (int i = 0; i < x.length; i++) {
             s.append(x[i] + " ");
         }
         print(s);
     }
     public static void printArr(char[] x) {
         StringBuilder s = new StringBuilder();
         for (int i = 0; i < x.length; i++) {
             s.append(x[i] + "");
         }
         print(s);
     }
     public static String join(Collection<?> x, String space) {
         if (x.size() == 0) return "";
         StringBuilder sb = new StringBuilder();
         boolean first = true;
         for (Object elt : x) {
             if (first) first = false;
             else sb.append(space);
             sb.append(elt);
         }
         return sb.toString();
     }
 
     public static String nextToken() throws IOException {
         while (st == null || !st.hasMoreTokens()) {
             String line = br.readLine();
             st = new StringTokenizer(line.trim());
         }
         return st.nextToken();
     }
     public static int nextInt() throws IOException {
         return Integer.parseInt(nextToken());
     }
     public static long nextLong() throws IOException {
         return Long.parseLong(nextToken());
     }
     public static double nextDouble() throws IOException {
         return Double.parseDouble(nextToken());
     }
     public static List<Integer> nextInts(int N) throws IOException {
         List<Integer> ret = new ArrayList<Integer>();
         for (int i = 0; i < N; i++) {
             ret.add(nextInt());
         }
         return ret;
     }
 
     public static class Employee {
         List<Employee> children;
         int salary;
 
         public Employee() {
             children = new ArrayList<Employee>();
         }
     }
 
     public static int working(int min, Employee x) {
         if (x.salary < min || x.salary > min + D) return 0;
         int total = 1;
         for (Employee c : x.children) total += working(min, c);
         return total;
     }
 
     public static int D;
 
     public static void main(String[] args) throws IOException {
         br = new BufferedReader(new InputStreamReader(System.in));
         br = new BufferedReader(new FileReader("input.txt"));
 
         int T = nextInt();
         for (int t = 1; t <= T; t++) {
             int N = nextInt();
             D = nextInt();
             int S = nextInt();
             int As = nextInt();
             int Cs = nextInt();
             int Rs = nextInt();
             int M = nextInt();
             int Am = nextInt();
             int Cm = nextInt();
             int Rm = nextInt();
 
             Set<Integer> allSalaries = new HashSet<Integer>();
             Employee[] employees = new Employee[N];
             for (int i = 0; i < N; i++) employees[i] = new Employee();
             employees[0].salary = S;
             allSalaries.add(S);
             for (int i = 1; i < N; i++) {
                 S = (S * As + Cs) % Rs;
                 M = (M * Am + Cm) % Rm;
                 employees[i].salary = S;
                 allSalaries.add(S);
                 employees[M % i].children.add(employees[i]);
             }
 
             int size = 1;
             for (int s : allSalaries) {
                 size = Math.max(size, working(s, employees[0]));
             }
 
             System.out.printf("Case #%d: %d%n", t, size);
         }
     }
 }