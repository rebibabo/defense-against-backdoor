package com.company;
 
 public class First {
 
    public static String computeRow(int index) {
        Integer d = Main.readInt();
        Integer n = Main.readInt();
        Double min = null;
        for (int i = 0; i < n; i++) {
            Integer a = Main.readInt();
            Integer b = Main.readInt();
            double act = (d - a) / (double) b;
            if (min == null || act > min) {
                min = act;
            }
        }
        return "Case #"+ index +": " + (d / min);
    }
 }
