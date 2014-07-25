package ru.test;

import android.test.InstrumentationTestCase;



/**
 * Created by Anna on 17.7.2014.
 */
public class testInsulinCalculation extends InstrumentationTestCase {

    public void test() throws Exception {
        final int expected = 1;
        final int reality = 1;
        assertEquals(expected, reality);
    }
}
