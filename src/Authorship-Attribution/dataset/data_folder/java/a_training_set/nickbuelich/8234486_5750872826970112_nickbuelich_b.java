import java.io.File;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 
 
 public class B{
    static double[] DT,DV;
    static int N;
    static double T,X;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(new File("B.in"));
        PrintWriter out = new PrintWriter(new File("B.out"));
        int TT = sc.nextInt();
        for(int t=1;t<=TT;t++){
            N = sc.nextInt();
            X = sc.nextDouble();        
            T = sc.nextDouble();
            
            DT = new double[N];
            DV = new double[N];
            
            double slowest = 0;
            Temp[] ARR = new Temp[N];
            for(int a=0;a<N;a++)
            {
                DV[a]=sc.nextDouble();
                DT[a]=sc.nextDouble();
                ARR[a] = new Temp(DT[a],DV[a]);
            }
            Arrays.sort(ARR);
            for(int a=0;a<N;a++){
                DT[a]=ARR[a].DT;
                DV[a]=ARR[a].DV;
            }
            double fastest = 0;
            for(int a=0;a<N;a++){
                slowest = Math.max(slowest,X/DV[a]);
                fastest+=DV[a];
            }
            fastest=X/fastest;
            double time = -1;
            double low = fastest;
            double high = slowest;
        
            int cool = 200;
            while(cool-->0){
                double mid = (high+low)/2;
                boolean cando = cando(mid);
                if(cando){
                    time = mid;
                    high = mid;
                }
                else{
                    low = mid;
                }
                if(N==1)break;
                
            }
            cando(.5);
        
            if(time<0)out.printf("Case #%d: IMPOSSIBLE%n",t,time);
            else out.printf("Case #%d: %.10f%n",t,time);
            
        }
        out.close();
    }
    static class Temp implements Comparable<Temp>{
        double DV;
        double DT;
        Temp(double a, double b){
            DT=a;
            DV=b;
        }
        
        
        @Override
        public int compareTo(Temp o) {
            
            return Double.compare(this.DT,o.DT);
        }
        
    }
 
    private static boolean cando(double mid) {
        double coldest =0;
        double coldestV = 0;
        double hottest =0;
        double hottestV = 0;
        for(int a=0;a<N;a++){
            double add = Math.min(X-coldestV,mid*DV[a]);
            if(add<0)continue;
            coldest = ((add*DT[a])+(coldestV*coldest) )/ (add+coldestV);
            coldestV+=add;
        }
        
        for(int a=N-1;a>=0;a--){
            double add = Math.min(X-hottestV,mid*DV[a]);
            if(add<0)continue;
            hottest = ((add*DT[a])+(hottestV*hottest) )/ (add+hottestV);
            hottestV+=add;
        }
        boolean flag = true;
        flag &= X<=coldestV+(1e-10);
        flag &= X<=hottestV+(1e-10);
        flag &= hottest>=T-(1e-10);
        flag &= coldest<=T+(1e-10);
 
 
        return flag;
    }
 
 }
