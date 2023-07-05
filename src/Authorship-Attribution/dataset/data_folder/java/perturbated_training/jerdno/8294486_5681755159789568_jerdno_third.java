package com.company;
 
 import java.util.*;
 
 public class Third {
    public static String computeRow(int index) {
        Integer n = Main.readInt();
        Integer q = Main.readInt();
        List<City> cities = new LinkedList<>();
        List<Route> routes = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int a = Main.readInt();
            int b = Main.readInt();
            cities.add(new City(a, b));
        }
        for (int i = 0; i < n; i++) {
            for (int ii = 0; ii < n; ii++) {
                int a = Main.readInt();
                cities.get(i).distances.add(a);
            }
        }
        for (int i = 0; i < q; i++) {
            int a = Main.readInt();
            int b = Main.readInt();
            routes.add(new Route(a, b));
        }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
        Map<Integer, LinkedList<Double>> ff = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            long dist = 0;
            double min = min(ff.get(i));
            for (int ii = i + 1; ii < n; ii++) {
                dist += cities.get(ii - 1).distances.get(ii);
                if (dist <= cities.get(i).dist) {
                    if (ff.get(ii) == null) {
                        ff.put(ii, new LinkedList<>());
                    }
                    ff.get(ii).add(min + dist / (double) cities.get(i).speed);
                }
            }
        }
        return "Case #"+ index +": " + min(ff.get(n - 1));
    }
 
    private static double min(List<Double> f) {
        double res = 0;
        int i = 0;
        if (f == null)
            return 0;
        Collections.sort(f);
        return f.get(0);
    }
 
    private static class City {
        int dist;
        int speed;
        List<Integer> distances = new LinkedList<>();
 
        public City(int dist, int speed) {
            this.dist = dist;
            this.speed = speed;
        }
    }
 
    private static class Route {
        int from;
        int to;
 
        public Route(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
 }
