import java.util.ArrayList;
 import java.util.Scanner;
 
 public class B {
    
    public static void main(String[] args){
        new B().run();
    }
    
    int n;
    
    void run(){
        Scanner sc = new Scanner(System.in);
        
        int testNum = sc.nextInt();
        for(int t=1;t<=testNum;t++){
            String s = sc.next();
            n = s.length();
            String ans = fnc(s);
            System.out.println("Case #" + t + ": " + ans);
        }
    }
    
    String fnc(String s){
        boolean[] in = new boolean[n];
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='+'){
                in[i] = true;
            }
        }
        ArrayList<boolean[]> list = new ArrayList<boolean[]>();
        int st = 0;
        int num = 1;
        list.add(in);
        for(int z=0;;z++){
            int cnt = 0;
            for(int x=st;x<st+num;x++){
                boolean[] f = list.get(x);
                if(check(f)){
                    return Integer.toString(z);
                }
                
                for(int k=0;k<n;k++){
                    boolean[] nf = new boolean[n];
                    for(int i=0;i<=k;i++){
                        nf[i] = f[k-i]^true;
                    }
                    for(int i=k+1;i<n;i++){
                        nf[i] = f[i];
                    }
                    if(!contain(list, nf)){
                        list.add(nf);
                        cnt++;
                    }
                }
                
            }
            st = st+num;
            num = cnt;
        }
    }
    
    boolean check(boolean[] f){
        for(int i=0;i<n;i++){
            if(!f[i]){
                return false;
            }
        }
        return true;
    }
    
    boolean contain(ArrayList<boolean[]> list, boolean[] f){
        for(boolean[] ff : list){
            boolean flag = true;
            for(int i=0;i<n;i++){
                if(f[i]!=ff[i]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                return true;
            }
        }
        return false;
    }
    
 }
