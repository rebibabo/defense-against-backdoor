
 
 import java.util.*;
 import java.io.*;
 import java.math.*;
 
 
 
 class Codejam {
    
    public static double THR = 0.0000005;
    
     public static void main(String args[]) {
         Scanner in = null;
        try {
            in = new Scanner(new FileInputStream("input.txt"));
            System.setOut(new PrintStream(new File("output.txt")));
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        
        Map<Character, Integer> map = new HashMap<Character, Integer> ();
        map.put('<', 1);
        map.put('^', 2);
        map.put('>', 4);
        map.put('v', 8);
        
        Set<String> en = new HashSet<String> ();
        Set<String> fr = new HashSet<String> ();
        
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = in.nextInt();
            
            in.nextLine();
            
            en.clear();
            fr.clear();
            
            String s = in.nextLine();
            
            String[] ss = s.split(" ");
            for (int i = 0 ; i < ss.length; i++) {
                if (!ss[i].isEmpty()) {
                    en.add(ss[i]);
                }
            }
            
            s = in.nextLine();
            ss = s.split(" ");
        
            int ansInit = 0;
            for (int i = 0 ; i < ss.length; i++) {
                if (!ss[i].isEmpty()) {
                    if (en.contains(ss[i]) && !fr.contains(ss[i])) {
                        ansInit++;
                    }
                    fr.add(ss[i]);
                    
                }
            }
            
            N = N - 2;
            String[][] ar = new String[N][12];
            for (int i = 0; i < N; i++) {
                s = in.nextLine();
                ss = s.split(" ");
                for (int j = 0; j < ss.length; j++) {
                    ar[i][j] = ss[j];
                }
            }
            
            
            
            int mi = 10000000;
            for (int m = (1 << N) - 1; m >= 0; m--) {
                int ans = 0;
                Set<String> adEn = new HashSet<String> ();
                Set<String> adFr = new HashSet<String> ();
                for (int i = 0; i < N; i++) {
                    boolean isEn = ((m & (1 << i)) != 0);
                    for (int j = 0; ar[i][j] != null; j++) {
                        if (isEn) {
                            if (!en.contains(ar[i][j])) {
                                adEn.add(ar[i][j]);
                            }
                        } else {
                            if (!fr.contains(ar[i][j])) {
                                adFr.add(ar[i][j]);
                            }
                        }
                    }
                }
                
                
                for (String str : adEn) {
                    
                    if (fr.contains(str) || adFr.contains(str)) {
                        ans++;
                    }
                }
                
                for (String str : adFr) {
                    if (en.contains(str)) {
                        ans++;
                    }
                }
                
                mi = Math.min(mi, ans + ansInit);
            }
            
            
            System.out.println("Case #" + t + ": " + mi);
        }
     }
  
 }