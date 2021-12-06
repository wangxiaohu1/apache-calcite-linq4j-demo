package test;

import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test6_intersect.java, v0.1 2021年12月06日 17:45:01 wangxiaohu Exp $
 */
public class Test6_intersect {
    /**
     * Intersect：集合交集
     */
    @Test
    public void test_intersect(){
        List<String> strings1 = Arrays.asList("a", "c", "b");
        List<String> strings2 = Arrays.asList("ew", "3", "b");

        Enumerable<String> union = Linq4j.asEnumerable(strings1).intersect(Linq4j.asEnumerable(strings2));
        System.out.println(union.toList().toString());
        Assert.assertEquals("[b]", union.toList().toString());
    }
}
