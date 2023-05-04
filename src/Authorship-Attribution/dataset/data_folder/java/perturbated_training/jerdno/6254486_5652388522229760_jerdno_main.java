package com.company;
 
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;
 import java.util.stream.Collectors;
 
 public class Main {
 
     public static void main(String[] args) throws IOException {
         String fileName = "test.in";
         List<String> list = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
         Files.write(Paths.get("out.out"), computeResult(Integer.parseInt(list.get(0)), list));
     }
 
    private static List<String> computeResult(int count, List<String> input) {
        List<String> result = new LinkedList<>();
        for(int i = 1; i <= count; i++) {
            result.add(computeRow(Arrays.asList(input.get(i).split(" ")), i));
        }
        return result;
    }
 
    private static String computeRow(List<String> parsedRow, int index) {
        String result = "INSOMNIA";
        Long start = Long.parseLong(parsedRow.get(0)), act;
        boolean end = start == 0;
        int i = 1;
        Map<Integer, Boolean> map = initMap();
        while (!end) {
            act = start * i;
            updateMap(map, Arrays.asList(act.toString().split("")).stream().map(Integer::parseInt).collect(Collectors.toList()));
            if (testMap(map)) {
                result = act.toString();
                end = true;
            }
            i++;
        }
        return "Case #"+ index +": " + result;
    }
 
    private static Map<Integer, Boolean> initMap() {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, false);
        }
        return map;
    }
 
    private static Map<Integer, Boolean> updateMap(Map<Integer, Boolean> in, List<Integer> list) {
        for (Integer l : list) {
            in.put(l, true);
        }
        return in;
    }
 
    private static boolean testMap(Map<Integer, Boolean> in) {
        boolean res = true;
        for (int i = 0; i < 10; i++) {
            res = res && in.get(i);
        }
        return res;
    }
 }
