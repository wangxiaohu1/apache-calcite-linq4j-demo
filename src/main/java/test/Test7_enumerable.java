package test;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test7_enumerable.java, v0.1 2021年12月06日 16:49:36 wangxiaohu Exp $
 */
public class Test7_enumerable {
    /**
     * emptyEnumerable:空集合
     */
    @Test
    public void test_emptyEnumerable(){
        Enumerable<Object> e = Linq4j.emptyEnumerable();
        Assert.assertEquals(false, e.any());
        Assert.assertEquals(0,e.longCount());
        Assert.assertEquals(false,e.enumerator().moveNext());
    }

    /**
     * singletonEnumerable:单元素集合
     */
    @Test
    public void test_singletonEnumerable(){
        Enumerable<Object> e = Linq4j.singletonEnumerable("test");
        Assert.assertEquals(true, e.any());
        Assert.assertEquals(1,e.longCount());
        Assert.assertEquals(true,e.enumerator().moveNext());
    }
}
