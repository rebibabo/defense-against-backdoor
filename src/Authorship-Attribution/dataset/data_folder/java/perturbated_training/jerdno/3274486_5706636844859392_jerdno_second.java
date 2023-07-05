package com.company;
 
 import java.util.Collections;
 import java.util.LinkedList;
 import java.util.List;
 
 public class Second {
    public static String computeRow(int index) {
        Integer c = Main.readInt();
        Integer j = Main.readInt();
        List<Time> ctime = new LinkedList<>();
        List<Time> jtime = new LinkedList<>();
        List<Time> times = new LinkedList<>();
        List<Medzera> medzeras = new LinkedList<>();
        for (int i = 0 ; i < c; i++) {
            Integer a = Main.readInt();
            Integer b = Main.readInt();
            ctime.add(new Time(a, b, false));
            times.add(new Time(a, b, false));
        }
        for (int i = 0 ; i < j; i++) {
            Integer a = Main.readInt();
            Integer b = Main.readInt();
            jtime.add(new Time(a, b, true));
            times.add(new Time(a, b, true));
        }
        Collections.sort(ctime, (left, right) -> left.from - right.from);
        Collections.sort(jtime, (left, right) -> left.from - right.from);
        Collections.sort(times, (left, right) -> left.from - right.from);
        times.add(new Time(1440 + times.get(0).from, 1440 + times.get(0).from, times.get(0).isJAct));
        int ct = 720;
        int jt = 720;
        int total = c + j;
        for (int i = 0 ; i < c; i++) {
            jt = jt - (ctime.get(i).to - ctime.get(i).from);
        }
        for (int i = 0 ; i < j; i++) {
            ct = ct - (jtime.get(i).to - jtime.get(i).from);
        }
        int from = 0, res = 0;
        boolean lastJ = false;
        for (int i = 0 ; i < total + 1; i++) {
            boolean same = i == 0 ? false : lastJ && times.get(i).isJAct ? true : !lastJ && !times.get(i).isJAct ? true : false;
            if (same) {
                medzeras.add(new Medzera(from, times.get(i).from, !lastJ));
            } else if (i > 0) {
                res++;
            }
            from = times.get(i).to;
            lastJ = times.get(i).isJAct;
        }
        Collections.sort(medzeras, (left, right) -> left.total - right.total);
        for (int i = 0; i < medzeras.size(); i++) {
            if (medzeras.get(i).isJResp) {
                if (medzeras.get(i).total <= jt) {
                    jt = jt - medzeras.get(i).total;
                } else {
                    res = res + 2;
                }
            } else {
                if (medzeras.get(i).total <= ct) {
                    ct = ct - medzeras.get(i).total;
                } else {
                    res = res + 2;
                }
            }
        }
 
 
 
        return "Case #"+ index +": " + res;
    }
 
    private static class Time {
        int from;
 
        int to;
 
        boolean isJAct;
 
        public Time(int from, int to, boolean isJ) {
            this.from = from;
            this.to = to;
            this.isJAct = isJ;
        }
    }
 
    private static class Medzera {
        int from;
 
        int to;
 
        int total;
 
        boolean isJResp;
 
        public Medzera(int from, int to, boolean isJResp) {
            this.from = from;
            this.to = to;
            this.total = to - from;
            this.isJResp = isJResp;
        }
    }
 }
