import java.util.Arrays;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.Scanner;
 
 public class C {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int HD = sc.nextInt(), AD = sc.nextInt(), HK = sc.nextInt(), AK = sc.nextInt(), B = sc.nextInt(),
                    D = sc.nextInt();
            HashSet<State> set = new HashSet<State>();
            LinkedList<State> queue = new LinkedList<State>();
            queue.add(new State(HD, AD, HK, AK, 0));
            int result = -1;
            while (!queue.isEmpty()) {
                State state = queue.remove();
                if (!set.contains(state) && state.HD > 0) {
                    if (state.HK <= state.AD) {
                        result = state.y + 1;
                        break;
                    }
                    set.add(state);
                    queue.add(new State(state.HD - state.AK, state.AD, state.HK - state.AD, state.AK, state.y + 1));
                    queue.add(new State(state.HD - state.AK, state.AD + B, state.HK, state.AK, state.y + 1));
                    queue.add(new State(HD - state.AK, state.AD, state.HK, state.AK, state.y + 1));
                    queue.add(new State(state.HD - (state.AK - D), state.AD, state.HK, Math.max(0, state.AK - D),
                            state.y + 1));
                }
            }
 
            System.out.println("Case #" + i + ": " + (result == -1 ? "IMPOSSIBLE" : result));
        }
        sc.close();
    }
 
    private static class State {
        private int HD, AD, HK, AK, y;
 
        private State(int HD, int AD, int HK, int AK, int y) {
            this.HD = HD;
            this.AD = AD;
            this.HK = HK;
            this.AK = AK;
            this.y = y;
        }
 
        @Override
        public boolean equals(Object o) {
            return HD == ((State) o).HD && AD == ((State) o).AD && HK == ((State) o).HK && AK == ((State) o).AK;
        }
 
        public int hashCode() {
            return Arrays.hashCode(new int[] { HD, AD, HK, AK });
        }
    }
 }