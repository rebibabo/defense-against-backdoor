package roundq;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.Scanner;
 import java.util.SortedSet;
 import java.util.TreeSet;
 
 import javax.naming.ldap.SortControl;
 
 public class Zad2 {
    
    private int t;
    
    private int d[];
    
    private int p[][];
    
    private class Comp implements Comparator<Integer> {
 
        @Override
        public int compare(Integer o1, Integer o2) {
            
            return - o1.compareTo(o2);
        }
        
    }
    
    private void sortDesc(int[] a) {
        Arrays.sort(a);
        for (int i = 0; i < a.length / 2; i++) {
            int tmp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = tmp;
        }
    }
    
    public void readInput() throws FileNotFoundException {
        Scanner s = new Scanner(new File("zad2.in"));
        
        t = s.nextInt();
        d = new int[t];
        p = new int[t][];
        
        for (int i = 0; i < t; i++) {
            d[i] = s.nextInt();
            p[i] = new int[d[i]];
            
            for (int j = 0; j < d[i]; j++) {
                p[i][j] = s.nextInt();
            }
        }
        
        s.close();
    }
    
    private boolean contains(int[] a, int n) {
        for (int i : a) {
            if (i == n) {
                return true;
            }
        }
        
        return false;
    }
    
    private int minTime2(int[] a) {
        sortDesc(a);
        
        int min = a[0];
        
        if (min < 4) {
            return min;
        }
        
        if (min == 4 && contains(a, 3)) {
            return 4;
        }
        
        if (min == 5 && contains(a, 4)) {
            return 5;
        }
        
        int[] newA = new int[a.length + 1];
        for (int i = 1; i <= a[0]/2; i++) {
            System.arraycopy(a, 0, newA, 0, a.length);
            
            newA[a.length] = a[0] - i;
            newA[0] = i;
            
            min = Math.min(min, 1 + minTime2(newA));
        }
        
        return min;
    }
    
    private int minTime(int[] a) {
        System.out.println(Arrays.toString(a));
        int min = a[0];
        
        if (min < 4) {
            return min;
        }
        
        for (int i = 1; i < a.length; i++) {
            
            if (a[i] < min + i) {
                int[] newA = new int[a.length + i];
                System.arraycopy(a, 0, newA, 0, a.length);
                for (int j = 0; j < i; j++) {
                    newA[j] = a[j] / 2 + (a[j] % 2);
                    newA[j + a.length] = a[j] / 2;
                }
                sortDesc(newA);
                return Math.min(min, i + minTime(newA));
                
            }
            
        }
        
        if (a.length < min) {
            int[] newA = new int[2 * a.length];
            System.arraycopy(a, 0, newA, 0, a.length);
            for (int j = 0; j < a.length; j++) {
                newA[j] = a[j] / 2 + (a[j] % 2);
                newA[j + a.length] = a[j] / 2;
            }
            sortDesc(newA);
            return Math.min(min, a.length + minTime(newA));
        }
        
        return min;
    }
    
    private int solveCase(int caseNum) {
        sortDesc(p[caseNum]);
        return minTime2(p[caseNum]);
    }
    
    public void solveAndPrintOutput() {
        
        for (int i = 0; i < t; i++) {
            System.out.println("Case #" + (i+1) + ": " + solveCase(i));
        }
 
    }
    
    public void printData() {
        System.out.println(t);
        for (int i = 0; i < t; i++) {
            System.out.println(d[i]);
            
            for(int j = 0; j < d[i]; j++) {
                System.out.print(p[i][j] + " ");
            }
            System.out.println();
        }
    }
 
    public static void main(String[] args) {
 
        Zad2 z = new Zad2();
        try {
            z.readInput();
            z.solveAndPrintOutput();
            
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        
        
 
 
    }
 
 }
