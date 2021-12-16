package test.linq4jtest;

import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test6_union.java, v0.1 2021年12月06日 17:45:01 wangxiaohu Exp $
 */
public class Test7_union_union {
    /**
     * union：集合并集，重复的元素只会保留一个
     */
    @Test
    public void test_union(){
        List<String> listA = Arrays.asList("a", "c", "b");
        List<String> listB = Arrays.asList("ew", "3", "b");

        List<String> union = Linq4j.asEnumerable(listA).union(Linq4j.asEnumerable(listB)).toList();
        Assert.assertEquals("[a, b, ew, c, 3]", union.toString());
        Assert.assertEquals(3, listA.size());
        Assert.assertEquals(3, listB.size());
    }
}
