import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Scanner;
 
 public class A {
    
    public static void main(String[] args){
        new A().run();
    }
    
    String org;
    ArrayList<String> list;
    
    void run(){
        Scanner sc = new Scanner(System.in);
        
        int testNum = sc.nextInt();
        for(int t=1;t<=testNum;t++){
            org = sc.next();
            String ans = fnc();
            System.out.println("Case #" + t + ": " + ans);
        }
    }
    
    String fnc(){
        String ans = "";
        
        list = new ArrayList<String>();
        
        String a = org.substring(1, org.length());
        String b = "" + org.charAt(0);
        loop(a, b);
        
        Collections.sort(list);
        ans = list.get(list.size()-1);
        return ans;
    }
 
    void loop(String a, String b){
        if(a.length()==0){
            if(!list.contains(b)){
                list.add(b);
            }
        }else{
            String aa = a.substring(1, a.length());
            String bb = b + a.charAt(0);
            loop(aa, bb);
            bb = "" + a.charAt(0) + b;
            loop(aa, bb);
        }
    }
    
 }
