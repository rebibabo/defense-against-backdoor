package com.company;
 
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.LinkedList;
 import java.util.List;
 
 public class First {
 
    public static String computeRow(int index) {
        Integer n = Main.readInt();
        Integer k = Main.readInt();
        List<Pancake> pancakes = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Integer a = Main.readInt();
            Integer b = Main.readInt();
            pancakes.add(new Pancake(a, b));
        }
        Collections.sort(pancakes, (left, right) -> right.valec - left.valec > 0 ? 1 : right.valec - left.valec == 0 ? 0 : -1);
        List<Pancake> pancakes2 = new LinkedList<>();
        for (int i = 0; i < k - 1; i++) {
            pancakes2.add(pancakes.get(i));
        }
        Collections.sort(pancakes2, (left, right) -> right.polomer - left.polomer);
        Double obsah = k == 1 ? 0 : Math.PI * pancakes2.get(0).polomer * pancakes2.get(0).polomer;
        Double max = k == 1 ? 0 : Math.max(0, (Math.PI * pancakes.get(k - 1).polomer * pancakes.get(k - 1).polomer) - obsah) + pancakes.get(k - 1).valec;
        int index2 = k - 1;
        for (int i = k - 1; i < n; i++) {
            Double tmp = Math.max(0, (Math.PI * pancakes.get(i).polomer * pancakes.get(i).polomer) - obsah) + (pancakes.get(i).valec);
            if (tmp > max) {
                index2 = i;
            }
        }
        pancakes2.add(pancakes.get(index2));
        Collections.sort(pancakes2, (left, right) -> right.polomer - left.polomer);
        Double res = Math.PI * pancakes2.get(0).polomer * pancakes2.get(0).polomer;
        for (int i = 0; i < k; i++) {
            res = res + pancakes2.get(i).valec;
        }
        return "Case #"+ index +": " + String.format("%.9f", res);
    }
 
    private static class Pancake {
        int polomer;
 
        int vyska;
 
        Double valec;
 
        public Pancake(int polomer, int vyska) {
            this.polomer = polomer;
            this.vyska = vyska;
            valec = 2 * Math.PI * polomer * vyska;
        }
    }
 }
