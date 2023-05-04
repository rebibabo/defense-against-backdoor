package com.company;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.util.Arrays;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.StringTokenizer;
 import java.util.stream.Collectors;
 
 public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
 
     public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("test.in"));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("out.out")));
         String fileName = "test.in";
 
         Files.write(Paths.get("out.out"), computeResult(readInt()));
     }
 
    private static List<String> computeResult(int count) {
        List<String> result = new LinkedList<>();
        for(int i = 1; i <= count; i++) {
            result.add(First.computeRow(i));
        }
        return result;
    }
 
    public static int readInt() {
        return Integer.parseInt(nextToken());
    }
 
    public static long readLong() {
        return Long.parseLong(nextToken());
    }
 
    public static double readDouble() {
        return Double.parseDouble(nextToken());
    }
 
    public static String nextToken() {
        while(st == null || !st.hasMoreTokens())    {
            try {
                if(!br.ready()) {
                    pw.close();
                    System.exit(0);
                }
                st = new StringTokenizer(br.readLine());
            }
            catch(IOException e) {
                System.err.println(e);
                System.exit(1);
            }
        }
        return st.nextToken();
    }
 
    private static String readLine()    {
        st = null;
        try {
            return br.readLine();
        }
        catch(IOException e) {
            System.err.println(e);
            System.exit(1);
            return null;
        }
    }
 }
