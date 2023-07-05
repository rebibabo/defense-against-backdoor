package com.company;
 
 import java.util.*;
 import java.util.stream.Collectors;
 
 public class Third {
    public static String computeRow(int index) {
        Integer n = Main.readInt();
        Integer k = Main.readInt();
        PriorityQueue<Integer> fifo = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        fifo.add(n);
        for (int i  = 1; i < k; i++) {
            Integer a = fifo.poll();
            fifo.add(a / 2);
            fifo.add((a - 1) / 2);
        }
        Integer a = fifo.remove();
        return "Case #"+ index +": " + a / 2 + " " + (a - 1) / 2;
    }
 
 }
