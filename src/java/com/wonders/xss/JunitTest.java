package com.wonders.xss;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/11/24
 * Time: 20:05
 * To change this template use File | Settings | File Templates.
 */
public class JunitTest {

    @Test
    public void test(){
        Assert.assertEquals(false, true);
    }

    @Test(expected = ArithmeticException.class)
    public void divideByZero() {
        int a =0/0;
    }

    @Test
    public void add() {
        System.out.println("add");


        Assert.assertEquals(3+3, 6);
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after");
    }


}
