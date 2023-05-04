import java.util.ArrayDeque;
 import java.util.Scanner;
 
 public class B {
    
    public static void main(String[] args){
        new B().run();
    }
 
    int B, M;
    
    void run(){
        Scanner sc = new Scanner(System.in);
        
        int testNum = sc.nextInt();
        for(int t=1;t<=testNum;t++){
            B = sc.nextInt();
            M = sc.nextInt();
 
            String ans = fnc();
            System.out.println("Case #" + t + ": " + ans);
        }
    }
    
    String fnc(){
        StringBuilder sb = new StringBuilder();
        
        boolean[][] map = new boolean[B][B];
        int cnt = 0;
        ArrayDeque<String> q = new ArrayDeque<String>();
        q.offer("0");
        while(!q.isEmpty()){
            String s = q.poll();
            if(s.endsWith(""+(B-1))){
                cnt++;
                for(int i=0;i<s.length()-1;i++){
                    int u = s.charAt(i)-48;
                    int v = s.charAt(i+1)-48;
                    map[u][v] = true;
                }
                
                if(cnt==M){
                    sb.append("POSSIBLE");
                    sb.append("\n");
                    for(int i=0;i<B;i++){
                        for(int j=0;j<B;j++){
                            if(map[i][j]) sb.append("1");
                            else sb.append("0");
                        }
                        if(i!=B-1) sb.append("\n");
                    }
                    return sb.toString();
                }
            }
            for(int i=B-1;i>=0;i--){
                String t = Integer.toString(i);
                if(!s.contains(t)){
                    q.offer(s+t);
                }
            }
        }
        
        return "IMPOSSIBLE";
    }   
 }
