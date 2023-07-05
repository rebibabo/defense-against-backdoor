package round1a;
 
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 import java.util.Scanner;
 
 public class B {
    
    static class Event implements Comparable<Event> {
        int ingredientId, servings;
        boolean open;
        
        public Event(int ingredientId, int servings, boolean open) {
            this.ingredientId = ingredientId;
            this.servings = servings;
            this.open = open;
        }
 
        @Override
        public int compareTo(Event o) {
            if (servings != o.servings) return servings < o.servings ? -1 : 1;
            if (open != o.open) return open ? -1 : 1;
            return ingredientId - o.ingredientId;
        }
    }
    
    static void putEvents(int id, int needs, int got, List<Event> list) {
        int from = (got * 10 + 11 * needs - 1) / (11 * needs);
        int to = (got * 10) / (9 * needs);
        if (from <= to) {
            list.add(new Event(id, from, true));
            list.add(new Event(id, to, false));
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            int N = sc.nextInt();
            int P = sc.nextInt();
            int[] recipe = new int[N];
            for (int i = 0; i < recipe.length; i++) {
                recipe[i] = sc.nextInt();
            }
            List<Event> all = new ArrayList<Event>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < P; j++) {
                    int tmp = sc.nextInt();
                    putEvents(i, recipe[i], tmp, all);
                }
            }
            Collections.sort(all);
            int[] cur = new int[N], credit = new int[N];
            int ans = 0;
            for (Event event : all) {
                if (event.open) {
                    cur[event.ingredientId]++;
                    boolean ok = true;
                    for (int i = 0; i < cur.length; i++) {
                        if (cur[i] < 1) ok = false;
                    }
                    if (ok) {
                        ans++;
                        for (int i = 0; i < credit.length; i++) {
                            credit[i]++;
                            cur[i]--;
                        }
                    }
                } else {
                    if (credit[event.ingredientId] > 0) credit[event.ingredientId]--;
                    else cur[event.ingredientId]--;
                }
            }
            
            System.out.println("Case #" + caze + ": " + ans);
        }
    }
 
 }
