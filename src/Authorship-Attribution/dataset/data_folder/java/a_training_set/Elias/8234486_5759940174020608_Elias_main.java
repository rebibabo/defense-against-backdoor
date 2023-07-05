import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.HashSet;
 import java.util.Scanner;
 
 
 public class Main {
    public static void main(String[] args) throws IOException {
        new Main();
    }
    
    HashSet<String> e; 
    HashSet<String> f;
    HashSet<String> sols;
    
    public Main() throws IOException {
        
        Scanner sc = new Scanner(new File("C-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new File("C-small-attempt0.out"));
        
        int amountOfTasks = sc.nextInt();
        for (int task = 1; task <= amountOfTasks; task++) {
            int n = sc.nextInt()-2;
            sc.nextLine();
            String eng = sc.nextLine();
            String fr = sc.nextLine();
            String[] english = eng.split(" ");
            String[] french = fr.split(" ");
            e = new HashSet<String>();
            for (int i = 0; i < english.length; i++) {
                e.add(english[i]);
            }
            f = new HashSet<String>();
            for (int i = 0; i < french.length; i++) {
                f.add(french[i]);
            }
            
            sols = new HashSet<String>();
            for (String s : e) {
                if (f.contains(s)) {
                    sols.add(s);
                }
            }
            for (String s : sols) {
                e.remove(s);
                f.remove(s);
            }   
            
            Sentence[] sentences = new Sentence[n];
            for (int i = 0; i < n; i++) {
                String s = sc.nextLine();
                String[] array = s.split(" ");
                HashSet<String> set = new HashSet<String>();
                for (int j = 0; j < array.length; j++) {
                    String str = array[j];
                    if (!sols.contains(str)) {
                        set.add(str);
                    }
                }
                sentences[i] = new Sentence(set);
            }
                        
            int sol = sols.size();
            if (n > 0) {
                sol = solve(0, sentences);
            }
            
            String solution = "Case #" + task + ": " + sol;
            System.out.println(solution);
            out.println(solution);
        }
        
        out.close();
        sc.close();
    }
    
    public int solve(int i, Sentence[] sentences) {
        if (i == sentences.length) return sols.size();
        HashSet<String> set = sentences[i].set;
        
        HashSet<String> toAddFr = new HashSet<String>();
        HashSet<String> toRemoveEng = new HashSet<String>();
        for (String s : set) {
            if (sols.contains(s)) continue;
            if (f.contains(s)) {
                toAddFr.add(s);
                f.remove(s);
                sols.add(s);
            } else if (!e.contains(s)){
                toRemoveEng.add(s);
                e.add(s);
            }
        }
        int x = solve(i+1, sentences);
        for (String s : toAddFr) {
            f.add(s);
            sols.remove(s);
        }
        for (String s : toRemoveEng) {
            e.remove(s);
        }
        
        HashSet<String> toAddEng = new HashSet<String>();
        HashSet<String> toRemoveFr = new HashSet<String>();
        for (String s : set) {
            if (sols.contains(s)) continue;
            if (e.contains(s)) {
                toAddEng.add(s);
                e.remove(s);
                sols.add(s);
            } else if (!f.contains(s)){
                toRemoveFr.add(s);
                f.add(s);
            }
        }
        int y = solve(i+1, sentences);
        for (String s : toAddEng) {
            e.add(s);
            sols.remove(s);
        }
        for (String s : toRemoveFr) {
            f.remove(s);
        }
        return Math.min(x, y);
    }
    
    class Sentence{
        HashSet<String> set;
        public Sentence(HashSet<String> set) {
            this.set = set;
        }
    }
 }