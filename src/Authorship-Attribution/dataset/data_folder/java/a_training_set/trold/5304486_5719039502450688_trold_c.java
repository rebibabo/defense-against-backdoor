
 import java.io.*;
 import java.util.*;
 
 class State {
    int Hd, Ad, Hk, Ak;
 
    State(int Hd, int Ad, int Hk, int Ak) {
        this.Hd = Hd;
        this.Ad = Ad;
        this.Hk = Hk;
        this.Ak = Ak;
    }
 
    public int hashCode() {
        return Hd << 24 | Ad << 16 | Hk << 8 | Ak;
    }
 
    public boolean equals(Object o) {
        if (!(o instanceof State)) return false;
        State that = (State) o;
        return 
            this.Hd == that.Hd &&
            this.Ad == that.Ad &&
            this.Hk == that.Hk &&
            this.Ak == that.Ak;
    }
 
    State attack() {
        State next = new State(Hd, Ad, Hk - Ad, Ak);
        if (Hk > 0) next.Hd -= next.Ak;
        return next;
    }
 
    State buff(int B) {
        State next = new State(Hd, Ad + B, Hk, Ak);
        if (Hk > 0) next.Hd -= next.Ak;
        return next;
    }
 
    State cure(int H) {
        State next = new State(H, Ad, Hk, Ak);
        if (Hk > 0) next.Hd -= next.Ak;
        return next;
    }
 
    State debuff(int D) {
        State next = new State(Hd, Ad, Hk, Math.max(0, Ak - D));
        if (Hk > 0) next.Hd -= next.Ak;
        return next;
    }
 
    public String toString() {
        return String.format("(%d, %d) vs (%d, %d)", Hd, Ad, Hk, Ak);
    }
 }
 
 public class C {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    int[] getInts(BufferedReader in) throws IOException {
        return Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
 
    String compute(int Hd, int Ad, int Hk, int Ak, int B, int D) {
        Set<State> seen = new HashSet<State>();
        List<State> list = new ArrayList<>();
        list.add(new State(Hd, Ad, Hk, Ak));
        seen.add(list.get(0));
        for (int t = 1 ; !list.isEmpty() ; t++) {
            List<State> next = new ArrayList<>();
            for (State s : list) {
                for (State n : new State[]{s.attack(), s.buff(B), s.cure(Hd), s.debuff(D)}) {
                    if (n.Hk <= 0) return "" + t;
                    if (n.Hd > 0 && !seen.contains(n)) {
                        seen.add(n);
                        next.add(n);
                    }
                }
            }
            list = next;
        }
        return "IMPOSSIBLE";
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                int[] v = getInts(in);
                out.printf("Case #%d: %s\n", t, compute(v[0], v[1], v[2], v[3], v[4], v[5]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main(String[] args) {
        new C().run(args);
    }
 }
