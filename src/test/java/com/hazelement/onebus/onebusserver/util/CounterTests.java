package com.hazelement.onebus.onebusserver.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class CounterTests {
    @Test
    void counterCountWithout_InitValue_ExpectedBehavior() {
        Counter counter = new Counter();

        int finalCount = 5;
        for (int i = 0; i < finalCount; i += 1) {
            counter.increment();
        }
        assertEquals(finalCount, counter.getCount());
    }

    @Test
    void counterCountWith_InitValue_ExpectedBehavior() {
        int initValue = 9;
        Counter counter = new Counter(initValue);

        int finalCount = 5;
        for (int i = 0; i < finalCount; i += 1) {
            counter.increment();
        }
        assertEquals(finalCount + initValue, counter.getCount());
    }
}
