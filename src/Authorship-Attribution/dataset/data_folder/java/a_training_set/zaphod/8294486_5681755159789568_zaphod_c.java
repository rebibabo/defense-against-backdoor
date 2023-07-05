import java.io.*;
 import java.util.Scanner;
 
 public class C
 {
    static int N, Q;
    static int[] distances;
    static int[] speeds;
    static int[][] connections;
    static final int INF = 1000000001;
    static double minTime;
    
    static void findRoute(int city, int currentSpeed, int distanceLeft, double timeTaken)
    {
        int distanceTravelled = connections[city-1][city];
        if (distanceTravelled > distanceLeft)
            return;
        timeTaken += 1.0* distanceTravelled/currentSpeed;
        if (city == N)
        {
            if (timeTaken< minTime)
                minTime = timeTaken;
            
            return;
        }
        
        
        findRoute(city +1, speeds[city],distances[city], timeTaken );
        
        
        distanceLeft = distanceLeft -distanceTravelled;
        findRoute(city +1, currentSpeed,distanceLeft, timeTaken );
    }
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "C-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            N = in.nextInt();
            Q = in.nextInt();
            distances = new int[N + 1];
            speeds = new int[N + 1];
            for (int i = 1; i <= N; i++)
            {
                distances[i] = in.nextInt();
                speeds[i] = in.nextInt();
            }
            connections = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++)
            {
                for (int j = 1; j <= N; j++)
                {
                    int distance = in.nextInt();
                    if (distance == -1)
                        distance = INF;
                    connections[i][j] = distance;
                }
 
            }
            System.out.printf("Case #%d:", caseNo);
            out.printf("Case #%d:", caseNo);
            for (int q = 0; q < Q; q++)
            {   
                int start = in.nextInt();
                int dest = in.nextInt();
                minTime = Double.POSITIVE_INFINITY;
                
                
                int city = 1;
                int currentSpeed = speeds[city];
                int distanceLeft = distances[city]; 
                findRoute(1, currentSpeed, distanceLeft, 0);
                
                System.out.printf(" %f", minTime);
                out.printf(" %f", minTime);
            }
 
            System.out.println();
            out.println();
 
 
        }
        in.close();
        out.close();
    }
 }