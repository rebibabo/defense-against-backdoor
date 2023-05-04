package com.company;
 
 public class First {
 
    public static String computeRow(int index) {
        String impossible = "IMPOSSIBLE";
        String c = Main.nextToken(), d = "";
        Integer k = Main.readInt();
        Integer act = 0;
        Integer result = 0, pom = 0;
 
        while (act <= c.length() - k) {
            if (c.charAt(act) == '-') {
                result++;
                int roz = 0;
                for (int i = act; i < act + k; i++) {
                    if (c.charAt(i) == '-') {
                        d = c.substring(0, i) + "+" + c.substring(i + 1);
                        roz++;
                    } else {
                        d = c.substring(0, i) + "-" + c.substring(i + 1);
                        roz--;
                    }
                    c = d;
                }
 
 
 
 
            }
            act++;
        }
        for (int i  = 0; i < c.length(); i++) {
            if (c.charAt(i) == '-') {
                return "Case #"+ index +": " + impossible;
            }
        }
        return "Case #"+ index +": " + result;
    }
 }
