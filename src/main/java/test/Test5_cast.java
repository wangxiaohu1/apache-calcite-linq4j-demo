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
 * @version Id: Test5_cast.java, v0.1 2021年12月06日 17:23:08 wangxiaohu Exp $
 */
public class Test5_cast {
    /**
     * 集合元素类型转换
     *  使用场景：将格式为Number类型的String字符转换成Integer类型
     * 
     */
    @Test
    public void test_cast(){
        List<Number> numbers = Arrays.asList(2, 2.12, null);
        Enumerable<Integer> cast = Linq4j.asEnumerable(numbers).cast(Integer.class);

        Enumerator<Integer> enumerator = cast.enumerator();
        enumerator.moveNext();
        Integer current = enumerator.current();
        Assert.assertEquals(Integer.valueOf(2), current);

        Exception e = null;
        enumerator.moveNext();
        try{
            Object current2 = enumerator.current();
            Assert.fail("应该异常");
        }catch (ClassCastException ex) {
            e = ex;
        }
        Assert.assertNotNull(e);

        enumerator.moveNext();
        Integer i = enumerator.current();
        Assert.assertNull(i);
    }

    /**
     * Linq4j.cast
     */
    @Test
    public void test_cast2() {
        List<Number> numbers = Arrays.asList(2, 2.12, null);
        Enumerable<Integer> cast = Linq4j.cast(numbers,Integer.class);
    }
    
}
