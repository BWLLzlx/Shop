package com.lhc.test;

import com.lhc.utils.WebUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class WebUtilsTest {

    @Test
    public void copyParamToBean() {
    }

    @Test
    public void parseInt() {
    }

    @Test
    public void getUUID() {
    }

    @Test
    public void getLoss() {
        Integer[] a = new Integer[3];
        a[0] = 5;a[1] = 4;a[2] = 4;
        Integer[] b = new Integer[3];
        b[0] = 4;b[1] = 4;b[2] = 4;
        double result = WebUtils.getLoss(a,b);

    }
}