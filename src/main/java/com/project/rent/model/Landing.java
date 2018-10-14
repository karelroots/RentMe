package com.project.rent.model;

public class Landing implements Comparable<Landing> {
    private String name;
    private int count;

    public Landing(String name, int count) {
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
    public int compareTo(Landing o) {
        if (o.count > this.count) {
            return 1;
        }
        return -1;
    }
}
