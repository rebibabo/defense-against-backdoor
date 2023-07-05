import java.io.*;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Scanner;
 
 public class C
 {
    public static int J, P, S, K;
 
    public static boolean canAddOutfit(HashMap<Combo, Integer> map, Combo combo)
    {
        Integer freq = map.get(combo);
        if (freq == null)
        {
            return true;
        }
        return (freq.intValue() < K);
    }
    
    public static boolean canRemoveOutfit(HashMap<Combo, Integer> map, Combo combo)
    {
        Integer freq = map.get(combo);
        if (freq == null)
        {
            return false;
        }
        return true;
    }
 
    public static void addOutfit(HashMap<Combo, Integer> map, Combo combo)
    {
        Integer spot = map.get(combo);
        if (spot == null)
        {
            spot = 0;
        }
        spot = spot.intValue() + 1;
        map.put(combo, spot);
    }
    
    public static void removeOutfit(HashMap<Combo, Integer> map, Combo combo)
    {
        Integer spot = map.get(combo);
        if (spot == null)
        {
            System.out.println("PROBLEM");
        }
        spot = spot.intValue() -1;
        if (spot > 0)
            map.put(combo, spot);
        else
            map.remove(combo);
    }
    
    public static ArrayList<Combo> badCombos(HashMap<Combo, Integer> map)
    {
        ArrayList<Combo> badCombos = new ArrayList<Combo> ();
        for (Combo next : map.keySet())
        {
            int freq = map.get(next);
            if (freq > K)
                badCombos.add(next);
        }
        return badCombos;
    }
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "C-small1";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            J = in.nextInt();
            P = in.nextInt();
            S = in.nextInt();
            K = in.nextInt();
 
            ArrayList<Outfit> outfits = new ArrayList<Outfit>();
            HashMap<Combo, Integer> jp = new HashMap<Combo, Integer>();
            HashMap<Combo, Integer> js = new HashMap<Combo, Integer>();
            HashMap<Combo, Integer> ps = new HashMap<Combo, Integer>();
 
            
            for (int s = 1; s <= S; s++)
                for (int p = 1; p <= P; p++)
                    for (int j = 1; j <= J; j++)
                    {
                        Outfit outfit = new Outfit(j, p, s);
                        Combo jpCombo = new Combo(j, p);
                        Combo jsCombo = new Combo(j, s);
                        Combo psCombo = new Combo(p, s);
                        
                        if (canAddOutfit(jp, jpCombo) &&
                                canAddOutfit(js, jsCombo) &&
                                canAddOutfit(ps, psCombo))
                        {
                            outfits.add(outfit);
                            addOutfit(jp, jpCombo);
                            addOutfit(js, jsCombo);
                            addOutfit(ps, psCombo);
                        }
 
                    }
            
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
        
 
            System.out.println(J + " "+ P + " "+ S + "  K:"+ K);
            System.out.println(jp);
            System.out.println(js);
            System.out.println(ps);
            System.out.printf("Case #%d: %s%n", caseNo, outfits.size());
            out.printf("Case #%d: %s%n", caseNo, outfits.size());
            for (Outfit next : outfits)
            {
                System.out.println(next);
                out.println(next);
            }
 
        }
        in.close();
        out.close();
 
    }
 
 }
 
 class Outfit implements Comparable<Outfit>
 {
    int jacket, pants, shirt;
    int badCombos;
 
    public Outfit(int jacket, int pants, int shirt)
    {
        this.jacket = jacket;
        this.pants = pants;
        this.shirt = shirt;
        badCombos = 0;;
    }
 
    public String toString()
    {
        return String.format("%d %d %d", jacket, pants, shirt);
    }
    
 
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + jacket;
        result = prime * result + pants;
        result = prime * result + shirt;
        return result;
    }
 
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Outfit other = (Outfit) obj;
        if (jacket != other.jacket)
            return false;
        if (pants != other.pants)
            return false;
        if (shirt != other.shirt)
            return false;
        return true;
    }
    
    public Combo getJPCombo()
    {
        return new Combo(jacket, pants);
    }
    
    public Combo getJSCombo()
    {
        return new Combo(jacket, shirt);
    }
    
    public Combo getPSCombo()
    {
        return new Combo(pants, shirt);
    }
 
    @Override
    public int compareTo(Outfit other)
    {
        return other.badCombos - this.badCombos;
    }
 
 }
 
 class Combo
 {
    int first, second;
 
    public Combo(int first, int second)
    {
        this.first = first;
        this.second = second;
    }
 
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + first;
        result = prime * result + second;
        return result;
    }
 
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Combo other = (Combo) obj;
        if (first != other.first)
            return false;
        if (second != other.second)
            return false;
        return true;
    }
 
    public String toString()
    {
        return String.format("%d %d", first, second);
    }
 }
