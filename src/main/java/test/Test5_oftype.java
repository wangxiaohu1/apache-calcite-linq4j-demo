package test;

import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test5_oftype.java, v0.1 2021年12月06日 17:40:21 wangxiaohu Exp $
 */
public class Test5_oftype {

    /**
     * ofType:从集合中过滤指定类型的集合
     */
    @Test
    public void test_oftype(){
        List<Number> numbers = Arrays.asList(2, 2.14, null);
        Enumerable<Integer> integers = Linq4j.asEnumerable(numbers).ofType(Integer.class);

        System.out.println(integers.toList());
        Assert.assertEquals("[2, null]",integers.toList().toString());
    }


    /**
     * ofType:从集合中过滤指定类型的集合
     */
    @Test
    public void test_oftype2(){
        List<Number> numbers = Arrays.asList(2, 2.14, null);
        Enumerable<Integer> integers = Linq4j.ofType(numbers,Integer.class);

        System.out.println(integers.toList());
        Assert.assertEquals("[2, null]",integers.toList().toString());
    }
}
