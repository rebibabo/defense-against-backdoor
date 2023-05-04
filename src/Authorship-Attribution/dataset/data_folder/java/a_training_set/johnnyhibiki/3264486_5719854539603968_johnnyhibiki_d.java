
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class D {
 
    public static String BR = System.getProperty("line.separator");
    
    void run() {
        Scanner sc = new Scanner(System.in);
 
        StringBuilder sb = new StringBuilder();
        
        int testNum = sc.nextInt();
        for (int t = 1; t <= testNum; t++) {
            int n = Integer.parseInt(sc.next());
            int m = Integer.parseInt(sc.next());
            int[][] map = new int[n][n];
            for(int i=0;i<m;i++){
                String type = sc.next();
                int y = Integer.parseInt(sc.next())-1;
                int x = Integer.parseInt(sc.next())-1;
                if(type.equals("o")){
                    map[y][x] = 3;
                }else if(type.equals("x")){
                    map[y][x] = 2;
                }else if(type.equals("+")){
                    map[y][x] = 1;
                }
            }
            sb.append("Case #" + t + ": " + fncSmall(n, map));
            
            
        }
        
        writeFileStr("C:\\Users\\johnnyhibiki\\Documents\\Eclipse2\\GCJ2017Q\\src\\DSans", sb.toString());
    }
 
    public static void writeFileStr(String fileName, String str){       
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))))) {
            pw.println(str);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    String fncSmall(int n, int[][] map) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        int xoClm = -1;
        for(int j=0;j<n;j++){
            if(map[0][j]==2){
                cnt++;
                map[0][j] = 3;
                sb.append("o 1 " + (j+1)).append(BR);
            }
            if(map[0][j]>=3){
                xoClm = j;
            }
        }
        if(xoClm==-1){
            xoClm = 0;
            cnt++;
            map[0][0] = 3;
            sb.append("o 1 1").append(BR);
        }
        
        for(int j=0;j<n;j++){
            if(map[0][j]==0){
                cnt++;
                map[0][j] = 1;
                sb.append("+ 1 " + (j+1)).append(BR);
            }
        }
        
        if(xoClm==n-1){
            for(int i=1;i<n;i++){
                cnt++;
                map[i][n-i-1] = 2;
                sb.append("x " + (i+1) + " " + (n-i+1)).append(BR);
            }
        }else{
            int k = -1;
            for(int i=1;i<n;i++){
                if(i+k==xoClm){
                    k = 0;
                }
                cnt++;
                map[i][i+k] = 2;
                sb.append("x " + (i+1) + " " + (i+k+1)).append(BR);
            }
        }
        
        for(int j=1;j<n-1;j++){
            cnt++;
            map[n-1][j] = 1;
            sb.append("+ " + n + " " + (j+1)).append(BR);
        }
        
        int score = 0;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]==1 || map[i][j]==2){
                    score += 1;
                }else if(map[i][j]==3){
                    score += 2;
                }
                
            }
            
        }
        
        
        String str = score + " " + cnt + BR + sb.toString();
        
        return str;
    }
 
    public static void main(String[] args) {
        new D().run();
    }
 }
