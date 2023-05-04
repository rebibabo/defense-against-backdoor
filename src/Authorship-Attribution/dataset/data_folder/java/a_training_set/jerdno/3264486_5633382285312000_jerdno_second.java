package com.company;
 
 public class Second {
    public static String computeRow(int index) {
        String c = Main.nextToken(), result = "";
        int last = Integer.parseInt(c.substring(0, 1)), size = c.length(), changed = 0;
        for (int i = 1; i < size; i++) {
            int act = Integer.parseInt(c.substring(i, i + 1));
            if (last > act) {
                changed = 1;
                if (last > 1) {
                    while (true) {
                        i--;
                        int pom = Integer.parseInt(c.substring(i, i + 1));
                        if (pom == last && i > 0) {
                            continue;
                        }
                        if (pom == last) {
                            i--;
                        }
                        break;
                    }
                    i++;
                    i++;
                    if (i > 0) {
                        result = result + c.substring(0, i - 1);
                    }
                    result = result + (last - 1);
                    for (int ii = i; ii < size; ii++) {
                        result = result + "9";
                    }
                    break;
                } else {
                    for (int ii = 0; ii < size - 1; ii++) {
                        result = result + "9";
                    }
                    break;
                }
            }
            last = act;
        }
        return "Case #"+ index +": " + (changed == 1 ? result : c);
    }
 }
