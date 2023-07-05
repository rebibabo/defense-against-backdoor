import java.util.*;
 
 public class a {
 
    public static emp[] list;
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
 
        for (int loop=1; loop<=numCases; loop++) {
 
            int n = stdin.nextInt();
            int maxd = stdin.nextInt();
            list = new emp[n];
            long[] sVals = new long[4];
            for (int i=0; i<4; i++)
                sVals[i] = stdin.nextLong();
            int origS = (int)sVals[0];
 
            long[] mVals = new long[4];
            for (int i=0; i<4; i++)
                mVals[i] = stdin.nextLong();
 
            list[0] = new emp((int)sVals[0], -1);
            for (int i=1; i<n; i++) {
                sVals[0] = (sVals[0]*sVals[1] + sVals[2])%sVals[3];
                mVals[0] = (mVals[0]*mVals[1] + mVals[2])%mVals[3];
                list[((int)mVals[0])%i].kids.add(i);
                list[i] = new emp((int)sVals[0], (int)mVals[0]);
            }
            list[0].fillInData();
 
            int best = 1;
            for (int i=origS-maxd; i<=origS; i++) {
                int cur = list[0].max(i, i+maxd);
                best = Math.max(best, cur);
            }
 
            System.out.println("Case #"+loop+": "+best);
        }
    }
 }
 
 class emp {
 
    public int size;
    public int parent;
    public int salary;
    public ArrayList<Integer> kids;
    public int maxSal;
    public int minSal;
 
    public emp(int sal, int myPar) {
        size = 1;
        parent = myPar;
        kids = new ArrayList<Integer>();
        salary = sal;
        maxSal = sal;
        minSal = sal;
    }
 
    public void fillInData() {
        for (int i=0; i<kids.size(); i++)
            a.list[kids.get(i)].fillInData();
        for (int i=0; i<kids.size(); i++) {
            size += a.list[kids.get(i)].size;
            maxSal = Math.max(maxSal, a.list[kids.get(i)].maxSal);
            minSal = Math.min(minSal, a.list[kids.get(i)].minSal);
        }
    }
 
    public int max(int low, int high) {
        if (salary < low || salary > high) return 0;
        int cnt = 1;
        for (int i=0; i<kids.size(); i++)
            cnt += a.list[kids.get(i)].max(low, high);
        return cnt;
    }
 }
 
 class node implements Comparable<node> {
 
    public int index;
    public int numKids;
 
    public node(int i, int k) {
        index = i;
        numKids = k;
    }
 
    public int compareTo(node other) {
        return this.numKids - other.numKids;
    }
 }