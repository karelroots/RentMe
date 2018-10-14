package com.project.rent.model;

public class Browser implements Comparable<Browser> {
    private String name;
    private int count;

    public Browser(String name, int count) {
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
    public int compareTo(Browser o) {
        if(o.count > this.count) {
            return 1;
        }
        return -1;
    }
}
