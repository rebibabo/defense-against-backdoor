import java.util.*;
 
 public class d {
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
 
        
        for (int loop=1; loop<=numCases; loop++) {
 
            int n = stdin.nextInt();
            int add = stdin.nextInt();
            boolean[] need = new boolean[n];
            Arrays.fill(need, true);
            int x = -1, circle = -1;
            for (int i=0; i<add; i++) {
                char c = stdin.next().charAt(0);
                int dummy = stdin.nextInt();
                int col = stdin.nextInt()-1;
                if (c == '+')
                    need[col] = false;
                if (c == 'x')
                    x = col;
                if (c == 'o')
                    circle = col;
            }
 
            ArrayList<item> list = new ArrayList<item>();
            for (int i=0; i<n; i++) {
 
                if (x == i) {
                    list.add(new item(0,i,'o'));
                    circle = i;
                }
                else if (circle == i) {
                    continue;
                }
                else if (i == n-1 && circle == -1 && x == -1) {
                    circle = n-1;
                    list.add(new item(0,i,'o'));
                }
                else if (need[i]) {
                    list.add(new item(0,i,'+'));
                }
            }
            for (int i=1; i<n-1; i++)
                list.add(new item(n-1,i,'+'));
 
            
 
            if (circle == 0) {
                int xRow = n-1;
                for (int i=n-1; i>0; i--) {
                    if (i == circle || i == x) continue;
                    list.add(new item(xRow,i,'x'));
                    xRow--;
                }
            }
            else {
 
                int xRow = n-1;
                for (int i=0; i<n; i++) {
                    if (i == circle || i == x) continue;
                    list.add(new item(xRow,i,'x'));
                    xRow--;
                }
 
            }
 
            int score = n > 1 ? 3*n-2 : 2;
            System.out.println("Case #"+loop+": "+score+" "+list.size());
            for (int i=0; i<list.size(); i++)
                System.out.println(list.get(i));
 
 
 
        }
    }
 }
 
 class item {
 
    public int r;
    public int c;
    public char ch;
 
    public item(int myr, int myc, char mych) {
        r = myr;
        c = myc;
        ch = mych;
    }
 
    public String toString() {
        return ch+" "+(r+1)+" "+(c+1);
    }
 }