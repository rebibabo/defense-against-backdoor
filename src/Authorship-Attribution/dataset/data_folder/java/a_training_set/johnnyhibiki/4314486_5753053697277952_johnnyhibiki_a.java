import java.util.Scanner;
 
 public class A {
    
    public static void main(String[] args){
        new A().run();
    }
 
    int n, sum;
    int[] a;
    
    void run(){
        Scanner sc = new Scanner(System.in);
        
        int testNum = sc.nextInt();
        for(int t=1;t<=testNum;t++){
            n = sc.nextInt();
            a = new int[n];
            sum = 0;
            for(int i=0;i<n;i++){
                a[i] = sc.nextInt();
                sum += a[i];
            }
            String ans = fnc();
            System.out.println("Case #" + t + ": " + ans);
        }
    }
    
    String fnc(){
        StringBuilder sb = new StringBuilder();
        
        while(true){
            if(sum==2){
                for(int i=0;i<n;i++){
                    if(a[i]!=0){
                        sb.append((char)(i+65));
                    }
                }
                break;
            }
            
            int max = -1;
            int id = -1;
            for(int i=0;i<n;i++){
                if(a[i]>max){
                    max = a[i];
                    id = i;
                }
            }
            a[id]--;
            sum--;
            sb.append((char)(id+65));
            
            if(sum==2){
                sb.append(' ');
                for(int i=0;i<n;i++){
                    if(a[i]!=0){
                        sb.append((char)(i+65));
                    }
                }
                break;
            }
            
            for(int i=0;i<n;i++){
                if((double)a[i]/sum >= 0.5){
                    a[i]--;
                    sum--;
                    sb.append((char)(i+65));
                    break;
                }
            }
            
            
            sb.append(' ');
        }
        
        return sb.toString().trim();
    }
 
 
    
 }
