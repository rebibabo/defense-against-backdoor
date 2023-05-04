package com.company;
 
 import java.util.LinkedList;
 import java.util.List;
 import java.util.stream.Collectors;
 
 public class Third {
    public static String computeRow(List<String> parsedRow, int index) {
        String result = "";
        String str;
        boolean tmp;
        int count = 0, start = (int)Math.pow(2, Integer.parseInt(parsedRow.get(0)) - 1) + 1, end = (int)Math.pow(2, Integer.parseInt(parsedRow.get(0)));
        for (int i = start; i < end; i = i + 2) {
            str = computeString(i);
            tmp = true;
            List<Long> dividers = new LinkedList<>();
            for (int ii = 2; ii <= 10; ii++) {
                Long divider;
                divider = divider(computeNumber(str, ii));
                if (divider == null) {
                    tmp = false;
                } else {
                    dividers.add(divider);
                }
            }
            if (tmp) {
                result = result + new StringBuilder(str).reverse().toString() + dividers.stream().map(it -> " " + it.toString()).collect(Collectors.joining()) + "\n";
                count++;
            }
            if (count == Integer.parseInt(parsedRow.get(1))) {
                break;
            }
        }
        return "Case #"+ index +": \n" + result;
    }
 
    private static Long divider(long n) {
        if(n % 2 == 0) {
            return 2L;
        }
        if(n % 3 == 0) {
            return 3L;
        }
        long sqrtN = (long)Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0) {
                return i - 1;
            }
            if (n % (i + 1) == 0) {
                return i + 1;
            }
        }
        return null;
    }
 
    private static String computeString(int i) {
        StringBuilder res = new StringBuilder("");
        while (i > 0) {
            res.append(i % 2);
            i = i / 2;
        }
        return res.toString();
    }
 
    private static long computeNumber(String str, int base) {
        long res = 0;
        int i = 0;
        for(String s : str.split("")) {
            if (s.equals("1")) {
                res = res + pow(base, i);
            }
            i++;
        }
        return res;
    }
 
    private static long pow(long base, long pow) {
        long result = 1;
        for (int i = 1; i <= pow; i++) {
            result *= base;
        }
        return result;
    }
 }
