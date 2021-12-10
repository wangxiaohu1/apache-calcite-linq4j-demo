package test;

import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test7_enumerable.java, v0.1 2021年12月06日 16:49:36 wangxiaohu Exp $
 */
public class Test0_03_enumerator {
    /**
     * emptyEnumerator:空集合迭代器
     */
    @Test
    public void test_emptyEnumerator(){
        Enumerator<String> e = Linq4j.<String>emptyEnumerator();
        Assert.assertEquals(false,e.moveNext());
    }

    /**
     * singletonEnumerator:单元素结合迭代器
     */
    @Test
    public void test_singletonEnumerator(){
        Enumerator<String> e = Linq4j.<String>singletonEnumerator("test");
        Assert.assertEquals("test",e.current());

        Assert.assertEquals(true,e.moveNext());
        Assert.assertEquals("test",e.current());

        Assert.assertEquals(false,e.moveNext());
    }

    /**
     * singletonEnumerator:单元素null值集合迭代器
     */
    @Test
    public void test_singletonNullEnumerator(){
        Enumerator<String> e = Linq4j.<String>singletonNullEnumerator();
        Assert.assertEquals(null,e.current());

        Assert.assertEquals(true,e.moveNext());
        Assert.assertEquals(null,e.current());

        Assert.assertEquals(false,e.moveNext());
    }

}