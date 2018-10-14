package com.project.rent.model;

public class OpSys implements Comparable<OpSys> {
    private String name;
    private int count;

    public OpSys(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(OpSys o) {
        if (o.count > this.count) {
            return 1;
        }
        return -1;
    }
}
