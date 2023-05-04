import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 
 
 public class Dragon2 {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("dragon.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dragon.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 0; i < t; i++) {
            String[] s = buf.readLine().split(" ");
            int hd = Integer.parseInt(s[0]);
            int ad = Integer.parseInt(s[1]);
            int hk = Integer.parseInt(s[2]);
            int ak = Integer.parseInt(s[3]);
            int b = Integer.parseInt(s[4]);
            int d = Integer.parseInt(s[5]);
            int turns = calcOff(ad, b, hk);
            if(b == 0){
                turns = calcTurns(ad,b,hk,0);
            }
            int[][][][] lookup = new int[101][101][101][101];
            for(int j = 0; j < 101; j++){
                for(int k = 0; k < 101; k++){
                    for(int l = 0; l < 101; l++){
                        Arrays.fill(lookup[j][k][l], -1);
                    }
                }
            }
            System.out.println(turns + " abc" + " " + hd + " " + ak + " " + turns + " " + d);
            int yay = rec(lookup, hd, hd, ak, turns, d);
            if(yay >= 100000){
                out.println("Case #" + (i + 1) + ": IMPOSSIBLE");
            } else {
                out.println("Case #" + (i + 1) + ": " + yay);
            }
        }
        out.close();
    }
    public static int calcTurns(int ad, int b, int hk, int t){
        ad = ad + b * t;
        if(hk % ad != 0) t = t + 1;
        return (hk / ad) + t;
    }
    public static int calcOff(int ad, int b, int hk){
        double t = (Math.sqrt(hk + 0.0) - ad)/ b;
        if(t < 0) t = 0;
        System.out.println(t);
        int fl = ((Double) Math.floor(t)).intValue();
        int ce = ((Double) Math.ceil(t)).intValue();
        return Math.min(calcTurns(ad,b,hk,fl), calcTurns(ad,b,hk,ce));
    }
    public static int calcTank(int hd, int ak){
        int tank = (hd - ak)/ak;
        if(hd % ak != 0) tank++;
        return tank;
    }
    public static int rec(int[][][][] lookup, int health, int hd, int ak, int turns, int d){
        if(turns == 0){
            lookup[health][hd][ak][turns] = 0;
            return 0;
        } else if (turns == 1){
            lookup[health][hd][ak][turns] = 1;
            return 1;
        }
        int best = 100000;
        if (d != 0 && health - ak + d > 0 && ak - d >= 0){
            if(lookup[health-ak+d][hd][ak-d][turns] == -1){
                lookup[health-ak+d][hd][ak-d][turns] = rec(lookup, health-ak+d, hd, ak-d, turns, d);
            }
            if(1 + lookup[health-ak+d][hd][ak-d][turns] < best) best = 1 + lookup[health-ak+d][hd][ak-d][turns];
        }
        if(health - ak > 0){
            if(lookup[health-ak][hd][ak][turns-1] == -1){
                lookup[health-ak][hd][ak][turns-1] = rec(lookup, health-ak, hd, ak, turns-1, d);
            }
            if(1 + lookup[health-ak][hd][ak][turns-1] < best) best = 1 + lookup[health-ak][hd][ak][turns-1];
        }
        if(hd - ak != health && hd - ak > 0){
            if(lookup[hd-ak][hd][ak][turns] == -1){
                lookup[hd-ak][hd][ak][turns] = rec(lookup, hd-ak, hd, ak, turns, d);
            }
            if(1 + lookup[hd-ak][hd][ak][turns] < best) best = 1 + lookup[hd-ak][hd][ak][turns];
        }
        lookup[health][hd][ak][turns] = best;
        return best;
    }
    
    public static int calcCure(int health, int hd, int ak, int t){
        if(ak == 0) return 0;
        int tank = calcTank(hd, ak);
        if(tank <= 1) return Integer.MAX_VALUE;
        int first = health/ak;
        if(health % ak != 0) first++;
        t = t - first + 1;
        if(t == 1) return 0;
        int rounds = t / tank;
        if(t % tank == 0) rounds--;
        System.out.println(hd + " " + ak + " " + tank + " " + first + " " + rounds + " " + t);
        return rounds + 1;
    }
 }
