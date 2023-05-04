import java.io.*;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Scanner;
 
 public class B
 {
    static int B;
    static int place;
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "B-small1";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
    
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            B = in.nextInt();
            place = in.nextInt();
            Barber[] barbers = new Barber[B];
            for (int i = 0; i < B; i++)
            {
                barbers[i] = new Barber(i + 1, in.nextInt(), 0);
            }
 
            Barber nextBarber = barbers[0];
            HashSet<State> states = new HashSet<State> ();
            states.add(new State(barbers, 1));
        
            boolean patternFound = false;
            
            for (int next = 2; next <= place; next++)
            {
                
                int waitTime = nextBarber.wait;
                for (int i = 0; i < B; i++)
                {
                    barbers[i].advance(waitTime);
                }
                nextBarber.cut();
                Arrays.sort(barbers);
                nextBarber = barbers[0];
                
                if (!patternFound)
                {
                State nextState = new State(barbers, next);
                
                
                if (!states.contains(nextState))
                    states.add(nextState);
                else
                {
                
                    place -= (place / (states.size()-1)-2)* (states.size()-1);
                    patternFound = true;
                    }
                }
            
            
            }
 
            System.out.printf("Case #%d: %d%n", caseNo, nextBarber.number);
            out.printf("Case #%d: %d%n", caseNo, nextBarber.number);
        }
        in.close();
        out.close();
 
    }
 
 }
 
 class Barber implements Comparable<Barber>
 {
    int number;
    int service;
    int wait;
 
    public Barber(int number, int service, int wait)
    {
        super();
        this.number = number;
        this.service = service;
        this.wait = wait;
    }
 
    public void cut()
    {
        wait = service;
    }
 
    public void advance(int time)
    {
        wait -= time;
        if (wait < 0)
            System.out.println("Problem");
    }
 
    @Override
    public int compareTo(Barber other)
    {
        if (this.wait != other.wait)
            return this.wait - other.wait;
        return this.number - other.number;
    }
 
    public String toString()
    {
        return String.format("%d %d %d", number, service, wait);
    }
 
 }
 
 class State 
 {
    int [] waitTimes;
    int customer;
    int served;
    
    public State(Barber[] barbers, int customer)
    {
        waitTimes = new int[barbers.length];
        for (int i = 0; i < barbers.length; i++)
            waitTimes[barbers[i].number-1]=barbers[i].wait;
        
        served = barbers[0].number;
        this.customer = customer;
            
    }
 
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(waitTimes) + served;
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
        State other = (State) obj;
        if (this.served != other.served )
            return false;
        if (!Arrays.equals(waitTimes, other.waitTimes))
            return false;
        return true;
    }
    
 }
 
