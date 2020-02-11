package com.hazelement.onebus.onebusserver.util;

import lombok.Data;

@Data
public class Counter implements Comparable<Counter> {

    private int count = 0;

    public Counter() {
    }

    public Counter(int initalCount) {
        count = initalCount;
    }

    public void increment() {
        count += 1;
    }

    @Override
    public int compareTo(Counter o) {
        return Integer.compare(this.getCount(), o.getCount());
    }
}
