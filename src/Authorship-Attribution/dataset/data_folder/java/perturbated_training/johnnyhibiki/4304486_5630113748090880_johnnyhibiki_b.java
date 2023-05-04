import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Scanner;
 
 public class B {
    
    public static void main(String[] args){
        new B().run();
    }
    
    int n;
    int[][] map;
    ArrayList<String> list;
    
    void run(){
        Scanner sc = new Scanner(System.in);
        
        int testNum = sc.nextInt();
        for(int t=1;t<=testNum;t++){
            n = sc.nextInt();
            map = new int[2*n-1][n];
            for(int i=0;i<2*n-1;i++){
                for(int j=0;j<n;j++){
                    map[i][j] = sc.nextInt();
                }
            }
            String ans = fnc();
            System.out.println("Case #" + t + ": " + ans);
        }
    }
    
    String fnc(){
        String ans = "";
        
        int[] cnt = new int[2501];
        for(int i=0;i<2*n-1;i++){
            for(int j=0;j<n;j++){
                cnt[map[i][j]]++;
            }
        }
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<2501;i++){
            if(cnt[i]%2==1){
                list.add(i);
            }
        }
        Collections.sort(list);
        for(int i=0;i<n;i++){
            ans += list.get(i) + " ";
        }
        return ans.trim();
    }
 
    void loop(String a, String b){
        if(a.length()==0){
            if(!list.contains(b)){
                list.add(b);
            }
        }else{
            String aa = a.substring(1, a.length());
            String bb = b + a.charAt(0);
            String cc = "" + a.charAt(0) + b;
            if(bb.compareTo(cc)>0){
                loop(aa, bb);
            }else{
                loop(aa, cc);
            }
        }
    }
    
 }
