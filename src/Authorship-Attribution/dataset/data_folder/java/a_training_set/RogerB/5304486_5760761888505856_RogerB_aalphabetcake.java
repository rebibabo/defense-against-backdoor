package roud1A;
 
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.Scanner;
 
 public class AAlphabetCake {
     public static void main (String[] args) {
         try(Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
             int tests = sc.nextInt();
             for (int t = 1; t <= tests; t++) {
                 int height = sc.nextInt();
                 int width = sc.nextInt();
                 char[][] cake = new char[height][];
                 for (int y = 0; y < height; y++) cake[y] = sc.next().toCharArray();
                 for (int y = 0; y < height; y++) {
                     for (int x = 1; x < width; x++) {
                         if (cake[y][x] == '?') cake[y][x] = cake[y][x - 1];
                     }
                 }
                 for (int y = 0; y < height; y++) {
                     for (int x = width - 2; x >= 0; x--) {
                         if (cake[y][x] == '?') cake[y][x] = cake[y][x + 1];
                     }
                 }
                 for (int y = height - 2; y >= 0; y--) {
                     for (int x = 0; x < width; x++) {
                         if (cake[y][x] == '?') cake[y][x] = cake[y + 1][x];
                     }
                 }
                 for (int y = 1; y < height; y++) {
                     for (int x = 0; x < width; x++) {
                         if (cake[y][x] == '?') cake[y][x] = cake[y - 1][x];
                     }
                 }
                 System.out.printf("Case #%d:%n", t);
                 for (int y = 0; y < height; y++) System.out.println(new String(cake[y]));
             }
         }
     }
 }
