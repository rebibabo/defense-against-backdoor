import java.io.File;
 import java.io.PrintWriter;
 import java.util.LinkedList;
 import java.util.Scanner;
 
 
 public class C {
    static double ME;
    static double cap = 1e8;
    static int it = 100000;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C.in"));
        PrintWriter out = new PrintWriter(new File("C.out"));
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            
            
        double ans = Double.MAX_VALUE;
        LinkedList<Chicken> LEFT = new LinkedList<Chicken>();
        LinkedList<Chicken> RIGHT = new LinkedList<Chicken>();
        ME = sc.nextDouble();
        int N = sc.nextInt();
        
        int[] pos = new int[N];
        int[] speed = new int[N];
        
        for(int a=0;a<N;a++){
            pos[a]=sc.nextInt();
        }
        for(int a=0;a<N;a++){
            speed[a]=sc.nextInt();
        }
        for(int a=0;a<N;a++){
            if(pos[a]<0)LEFT.add(new Chicken(-pos[a],speed[a]));
            else RIGHT.add(new Chicken(pos[a],speed[a]));
        }
        
        ans = Math.min(magic(LEFT,RIGHT),magic(RIGHT,LEFT));
        
        
            System.out.printf("Case #%d: %.10f%n",t,ans);
            out.printf("Case #%d: %.10f%n",t,ans);
        }
        
        out.close();
    }
    private static double magic(LinkedList<Chicken> LEFT, LinkedList<Chicken> RIGHT) {
        double low = 0;
        double high = cap;
        int itr = it;
        while(itr-->0){
            double mid = (low+high)/2;
            boolean good = true;
            double TRAV = ME * mid;
            for(Chicken c : LEFT){
                double pos = c.atTime(mid,0);
                if(pos>TRAV){
                    good = false;
                }
            }
            
            if(good){
                high = mid;
            }
            else{
                low = mid;
            }
            
        }
        
        double leftTime = (low + high)/2;
        double curpos = leftTime * ME;
        low = 0;
        high = cap;
        itr = it;
        while(itr-->0){
            double mid = (low+high)/2;
            boolean good = true;
            double TRAV = ME * (mid);
            for(Chicken c : RIGHT){
                double pos = c.atTime((mid+leftTime),curpos);
                if(pos>TRAV){
                    good = false;
                }
            }
            
            if(good){
                high = mid;
            }
            else{
                low = mid;
            }
            
        }
        
        
        return ((high+low)/2.0) + leftTime;
    }
    static class Chicken{
        double pos;
        double speed;
        Chicken(int a, int s){
            pos = a;
            speed= s;
        }
        public double atTime(double mid, double offset) {
            return pos+(speed*mid)+offset;
        }
    }
 }
