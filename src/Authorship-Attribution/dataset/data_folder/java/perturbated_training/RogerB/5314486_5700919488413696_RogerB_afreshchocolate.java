package round2;
 
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Scanner;
 
 public class AFreshChocolate {
     static class ArrayWrapper {
         int[] arr;
         
         public ArrayWrapper(int[] arr) {
             this.arr = arr;
         }
         
         public boolean equals(Object o) {
             if (o instanceof ArrayWrapper) {
                 return Arrays.equals(arr, ((ArrayWrapper)o).arr);
             } else return false;
         }
         
         public int hashCode() {
             return Arrays.hashCode(arr);
         }
     }
     
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             int groupCount = sc.nextInt();
             int packSize = sc.nextInt();
             int[] modSizeCounts = new int[packSize];
             for (int x = 0; x < groupCount; x++) modSizeCounts[sc.nextInt() % packSize]++;
             int[] countsAndLeftovers = Arrays.copyOf(modSizeCounts, packSize + 1);
             System.out.printf("Case #%d: %d%n", t, new AFreshChocolate().getMaxFresh(new ArrayWrapper(countsAndLeftovers), packSize));
         }
         
         sc.close();
     }
     
     Map<ArrayWrapper, Integer> memTable = new HashMap<>();
     
     public int getMaxFresh(ArrayWrapper countsAndLeftovers, int packSize) {
         int[] arr = countsAndLeftovers.arr;
         int currentLeftovers = arr[arr.length - 1];
         
         if (!memTable.containsKey(countsAndLeftovers)) {
             int maxResult = 0;
             boolean thisFresh = arr[arr.length - 1] == 0;
             for (int groupSize = 0; groupSize < arr.length - 1; groupSize++) {
                 if (arr[groupSize] > 0) {
                     arr[groupSize]--;
                     arr[arr.length - 1] = (currentLeftovers - groupSize + packSize) % packSize;
                     int result = getMaxFresh(new ArrayWrapper(Arrays.copyOf(arr, arr.length)), packSize);
                     arr[arr.length - 1] = currentLeftovers;
                     arr[groupSize]++;
                     
                     if (thisFresh) result++;
                     maxResult = Math.max(maxResult, result);
                 }
             }
             memTable.put(countsAndLeftovers, maxResult);
         }
         return memTable.get(countsAndLeftovers);
     }
     
     public boolean isEmpty(int[] arr) {
         for (int x = 0; x < arr.length - 1; x++) if (arr[x] != 0) return false;
         return true;
     }
 }
