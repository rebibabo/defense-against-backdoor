package com.company;
 
 import java.util.List;
 
 public class Forth {
    public static String computeRow(List<String> parsedRow, int index) {
        String result = "";
        for (int i = 1; i <= Integer.parseInt(parsedRow.get(0)); i++) {
            result = result + " " + i;
        }
        return "Case #"+ index +":" + result;
    }
 }
