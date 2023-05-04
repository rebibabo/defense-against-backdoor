import java.io.*;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.Scanner;
 
 public class A
 {
    static int N, K;
    static Pancake[] pancakes;
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "A-small3";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            N = in.nextInt();
            K = in.nextInt();
            pancakes = new Pancake[N];
            int maxRadius = 0;
            for (int i = 0; i < N; i++)
            {
                int radius = in.nextInt();
                int height = in.nextInt();
                if (radius > maxRadius)
                    maxRadius = radius;
                pancakes[i] = new Pancake(radius, height);
            }
            Arrays.sort(pancakes);
 
            
            double maxArea = 0;
            for (int bottom = 0; bottom < N; bottom++)
            {
                double area = pancakes[bottom].totalArea;
                int counted = 1;
                for (int i = 0; i < N && counted < K; i++)
                {
                    if (i != bottom &&  pancakes[i].radius <= pancakes[bottom].radius)
                    {
                        area += pancakes[i].sideArea();
                        counted++;
                    }
                }
                if (area > maxArea)
                    maxArea = area;
            }
 
            
            
            
            
            System.out.printf("Case #%d: %s%n", caseNo, maxArea);
            out.printf("Case #%d: %s%n", caseNo, maxArea);
        }
        in.close();
        out.close();
    }
 }
 
 class Pancake implements Comparable<Pancake>
 {
    int radius;
    int height;
    double totalArea;
    double sideArea;
 
    public Pancake(int radius, int height)
    {
        this.radius = radius;
        this.height = height;
        totalArea = sideArea() + topArea();
        sideArea = sideArea();
    }
 
    public double sideArea()
    {
        return height * 2 * Math.PI * radius;
    }
 
    public double topArea()
    {
        return Math.PI * radius * radius;
    }
 
    @Override
    public int compareTo(Pancake other)
    {
        if (this.sideArea > other.sideArea)
            return -10000000;
        else if (this.sideArea < other.sideArea)
            return 10000000;
        return this.radius - other.radius;
    }
 
    public static final Comparator<Pancake> RADIUS_ORDER = new RadiusOrder();
 
    private static class RadiusOrder implements Comparator<Pancake>
    {
        public int compare(Pancake first, Pancake second)
        {
            if (first.radius != second.radius)
                return second.radius - first.radius;
            return second.height - first.height;
        }
    }
 
    public String toString()
    {
        return String.format("%d %d", radius, height);
    }
 
 }
