package com.company;
 
 import java.util.Collections;
 import java.util.LinkedList;
 import java.util.List;
 
 public class Second {
    public static String computeRow(int index) {
        String impossible = "IMPOSSIBLE";
        Integer n = Main.readInt();
        Integer r = Main.readInt();
        Integer o = Main.readInt();
        Integer y = Main.readInt();
        Integer g = Main.readInt();
        Integer b = Main.readInt();
        Integer v = Main.readInt();
        List<Integer> numbers = new LinkedList<>();
        numbers.add(r);
        numbers.add(y);
        numbers.add(b);
        Collections.sort(numbers);
        if (numbers.get(2) > numbers.get(1) + numbers.get(0)) {
            return "Case #"+ index +": " + impossible;
        }
        int max = numbers.get(2);
        String result = "";
        String first = "";
        String second = "";
        String third = "";
        if (numbers.get(2).equals(r)) {
            first = "R";
        } else if (numbers.get(2).equals(y)) {
            first = "Y";
        } else {
            first = "B";
        }
        if (numbers.get(1).equals(r) && !first.equals("R")) {
            second = "R";
        } else if (numbers.get(1).equals(y) && !first.equals("Y")) {
            second = "Y";
        } else {
            second = "B";
        }
        if (numbers.get(0).equals(r) && !first.equals("R") && !second.equals("R")) {
            third = "R";
        } else if (numbers.get(0).equals(y) && !first.equals("Y") && !second.equals("Y")) {
            third = "Y";
        } else {
            third = "B";
        }
        for (int i = 0; i < max; i++) {
            result = result + first;
            numbers.set(2, numbers.get(2) - 1);
            boolean done = false;
            if (numbers.get(1) > 0) {
                result = result + second;
                numbers.set(1, numbers.get(1) - 1);
                done = true;
            }
            if (numbers.get(2) < numbers.get(1) + numbers.get(0) || !done) {
                result = result + third;
                numbers.set(0, numbers.get(0) - 1);
            }
        }
        return "Case #"+ index +": " + result;
    }
 }
