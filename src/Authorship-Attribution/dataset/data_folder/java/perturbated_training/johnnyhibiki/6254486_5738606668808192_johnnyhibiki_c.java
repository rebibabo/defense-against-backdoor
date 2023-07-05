import java.util.ArrayList;
 import java.util.Scanner;
 
 public class C {
    
    public static void main(String[] args){
        new C().run();
    }
    
    int n;
    
    void run(){
        Scanner sc = new Scanner(System.in);
        
        int testNum = sc.nextInt();
        for(int t=1;t<=testNum;t++){
            int n = sc.nextInt();
            int j = sc.nextInt();
            String ans = fnc(n, j);
            System.out.print("Case #" + t + ":\n" + ans);
        }
    }
    
    String fnc(int n, int j){
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        long i = 0;
        while(true){
            String b = Long.toBinaryString(i);
            for(int k=b.length();k<n-2;k++){
                b = "0" + b;
            }
            b = "1" + b + "1";
 
            boolean f = true;
            for(int k=2;k<=10;k++){
                long m = Long.parseLong(b, k);
                if(m%2==0 || m%3==0 || m%5==0 || m%7==0 || m%11==0){
                    
                }else{
                    f = false;
                    break;
                }
            }
            if(f){
                sb.append(b).append(" ");
                for(int k=2;k<=10;k++){
                    long m = Long.parseLong(b, k);
                    if(m%2==0){
                        sb.append(2);
                    }else if(m%3==0){
                        sb.append(3);
                    }else if(m%5==0){
                        sb.append(5);
                    }else if(m%7==0){
                        sb.append(7);
                    }else if(m%11==0){
                        sb.append(11);
                    }
 
                    if(k==10){
                        sb.append("\n");
                    }else{
                        sb.append(" ");
                    }
                }
                cnt++;
                
            }
            if(cnt==j){
                break;
            }
            i++;
        }
        
        return sb.toString();
    }
    
 }
