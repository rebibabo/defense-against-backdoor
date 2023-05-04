import java.io.File;
 import java.io.PrintWriter;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 
 public class A {
    static String[] nums = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
    public static void main(String[] args) throws Exception {
        
        
        Scanner sc = new Scanner(new File("A.in"));
        PrintWriter out = new PrintWriter(new File("A.out"));
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            int N = sc.nextInt();
            StringBuilder ans = new StringBuilder();
            int[] party = new int[N];
            int total = 0;
            
            for(int a=0;a<N;a++){
                party[a] = sc.nextInt();
                total+=party[a];
            }
            
            while(total>0){
                PriorityQueue<Party> P = new PriorityQueue<Party>();
                for(int a=0;a<N;a++){
                    P.add(new Party(a,party[a]));
                }
                
                Party removed = P.poll();
                total--;
                removed.size--;
                party[removed.index]--;
                ans.append((char)(removed.index+'A'));
                P.add(removed);
                
                if(P.peek().size>total/2){
                    Party removed2 = P.poll();
                    total--;
                    removed2.size--;
                    party[removed2.index]--;
                    ans.append((char)(removed2.index+'A'));
                    P.add(removed2);
                }
                
                ans.append(" ");
            }
            
            System.out.printf("Case #%d: %s%n",t,ans);
            out.printf("Case #%d: %s%n",t,ans);
        }
        
        out.close();
    }
    static class Party implements Comparable<Party>{
        int index;
        int size;
        Party(int i, int s){
            this.index = i;
            this.size = s;
        }
        @Override
        public int compareTo(Party arg0) {
            return Integer.compare(arg0.size, this.size);
        }
        
    }
 }
