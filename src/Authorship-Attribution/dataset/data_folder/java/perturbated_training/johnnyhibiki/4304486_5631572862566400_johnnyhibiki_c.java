import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Scanner;
 
 public class C {
    
    public static void main(String[] args){
        new C().run();
    }
    
    int n;
    int max;
    int[] arr;
    
    void run(){
        Scanner sc = new Scanner(System.in);
        
        int testNum = sc.nextInt();
        for(int t=1;t<=testNum;t++){
            n = sc.nextInt();
            arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i] = sc.nextInt()-1;
            }
            String ans = fnc();
            System.out.println("Case #" + t + ": " + ans);
        }
    }
    
    String fnc(){
        String ans = "";
        
        max = -1;
        makeperm(0, new int[n], new boolean[n]);
        ans = Integer.toString(max);
        
        return ans.trim();
    }
 
    
    void makeperm(int k, int[] perm, boolean[] flag){
        if(k==n){
            check(perm);
        }else{
            for(int i=0;i<n;i++){
                if(flag[i]==true) continue;
                perm[k] = i;
                flag[i] = true;
                makeperm(k+1, perm, flag);
                flag[i] = false;
            }
        }
    }
    
    void check(int[] perm){
        int sum = 0;
        int len = 0;
        for(int i=0;i<n;i++){
            boolean f = false;
            if(i==n-1){
                if(arr[n-1]==perm[n-2] || arr[n-1]==perm[0]){
                    f = true;
                }
            }else if(i==0){
                if(arr[0]==perm[1] || arr[0]==perm[n-1]){
                    f = true;
                }
            }else{
                if(arr[i]==perm[i+1] || arr[i]==perm[i-1]){
                    f = true;
                }
            }
            
            if(f){
                sum++;
            }else{
                len = Math.max(len, sum);
                sum = 0;
            }
        }
        len = Math.max(len, sum);
        
        max = Math.max(max, len);
    }
    
 }
